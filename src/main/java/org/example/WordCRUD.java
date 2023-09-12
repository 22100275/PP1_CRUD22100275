package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{

    ArrayList<Word> list = new ArrayList<>();
    Scanner s = new Scanner(System.in);
    final String fname = "Dictionary.txt";

    WordCRUD() {}
    @Override
    public Word add() {
        System.out.print("=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.nextLine();
        String trimmed = word.trim();
        System.out.print("뜻 입력 : ");
        String meaning = s.nextLine();

        return new Word(level, trimmed, meaning);
    }

    public void addItem() {
        Word one = add();
        list.add(one);
        System.out.println("새 단어가 단어장에 추가되었습니다. ");
    }



    public void listAll() {
        System.out.println("--------------------------------");
        for(int i = 0; i < list.size(); i++) {
            System.out.print((i+1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("--------------------------------");

    }



    public ArrayList<Integer> listAll(String keyword) {
        int j = 0;
        ArrayList<Integer> idlist = new ArrayList<>();
        System.out.println("--------------------------------");
        for(int i = 0; i < list.size(); i++) {
            String word = list.get(i).getWord();
            if(!word.contains(keyword)) continue;
            System.out.print((j+1) + " ");
            System.out.println(list.get(i).toString());
            idlist.add(i); //0번째 단어가 저장되면 0이 저장됨 ex)0, 3, 6, 8
            j++; //그냥 순서대로 인덱스를 출력하기 위한 변수
        }
        System.out.println("--------------------------------");
        return idlist; //0, 3, 6, 8이 담긴 어레이리스트를 리턴
    }

    public void listAll(int level) {
        int j = 0;
        System.out.println("--------------------------------");
        for(int i = 0; i < list.size(); i++) {
            int ilevel = list.get(i).getLevel();
            if(ilevel != level) continue;
            System.out.print((j+1) + " ");
            System.out.println(list.get(i).toString());
            j++;
        }
        System.out.println("--------------------------------");
    }

    public void updateItem() {
        System.out.print("=> 수정할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print("=> 수정할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();
        System.out.print("=> 뜻 입력 : ");
        String meaning = s.nextLine();
        Word word = list.get(idlist.get(id-1));
        word.setMeaning(meaning);
        System.out.println("단어가 수정되었습니다. ");

    }

    public void deleteItem() {
        System.out.print("=> 삭제할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword); //메소드를 실행하면서 찾아진 0, 4, 6, 8 이 담긴 어레이리스트를 얻어옴
        System.out.print("=> 삭제할 번호 선택 : ");
        int id = s.nextInt(); //3번을 누르면 6번 값을 선택한거나 마찬가지
        s.nextLine();

        System.out.print("=> 정말로 삭제하실래요?(Y/n) ");
        String ans = s.next();
        if(ans.equalsIgnoreCase("y")) {
            list.remove((int)idlist.get(id-1)); //어레이의 2번인 6번을 선택, 6번이 삭제된다!
            System.out.println("단어가 삭제되었습니다. ");
        } else {
            System.out.println("취소되었습니다. ");
        }
    }

    public void loadFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(fname))) {
            String line;
            int count = 0;

            while(true) {
                line = br.readLine();
                if(line == null) break;

                String data[] = line.split("\\|");
                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(level, word, meaning));
                count++;
            }
            br.close();
            System.out.println("==> " + count + "개 로딩 완료!!!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFile() {
        try {
            PrintWriter pr = new PrintWriter(new FileWriter(fname));
            for(Word one : list) {
                pr.write(one.toFileString() + "\n");
            }
            pr.close();
            System.out.println("==> 데이터 저장 완료 !!!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchLevel() {
        System.out.print("=> 원하는 레벨은? (1~3) ");
        int level = s.nextInt();
        listAll(level);
    }

    public void searchWord() {
        System.out.print("=> 원하는 단어는? ");
        String keyword = s.next();
        listAll(keyword);
    }
}
