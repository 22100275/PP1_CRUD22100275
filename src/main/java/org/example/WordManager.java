package org.example;

import java.util.Scanner;

public class WordManager {
    Scanner s = new Scanner(System.in);
    WordCRUD wordCRUD;

    WordManager() {
        wordCRUD = new WordCRUD(s);
    }
    public int selectMenu() {
        System.out.println("***영단어 마스터***");
        System.out.println("\n********************");
        System.out.println("1. 모든 단어 보기");
        System.out.println("2. 수준별 단어 보기");
        System.out.println("3. 단어 검색");
        System.out.println("4. 단어 추가");
        System.out.println("5. 단어 수정");
        System.out.println("6. 단어 삭제");
        System.out.println("7. 파일 저장");
        System.out.println("0. 나가기");
        System.out.println("********************");
        System.out.print("=> 원하는 메뉴는?");

        return s.nextInt();
    }

    public void start() {
        while(true) {
            int menu = selectMenu();
            if(menu == 0) break;
            if(menu == 4) {
                //create
                wordCRUD.addItem();
            }

            else if(menu == 1){
                //list
                wordCRUD.listAll();
            }

            else if(menu == 5) { //update
                wordCRUD.updateItem();
            }

            else if(menu == 6) { //delete
                wordCRUD.deleteItem();
            }
        }
    }
}
