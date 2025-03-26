package org.example;

import java.util.Scanner;

public class App {
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("== 프로그램 실행 ==");

        MemberController memberController = new MemberController(sc);
        ArticleController articleController = new ArticleController(sc);

        articleController.makeTestDate();
        memberController.makeTestMember();

        while (true) {
            System.out.print("명령어 ) ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                System.out.println("== 프로그램 종료 ==");
                break;
            } else if (cmd.isEmpty()) {
                System.out.println("명령어가 입력되지 않았습니다.");
            }

            if (cmd.equals("member join")) {
                memberController.doJoin();
            } else if (cmd.equals("article write")) {
                articleController.doWrite();
            } else if (cmd.startsWith("article list")) {
                articleController.showList(cmd);
            } else if (cmd.startsWith("article delete")) {
                articleController.doDelete(cmd);
            } else if (cmd.startsWith("article detail")) {
                articleController.showDetails(cmd);
            } else if (cmd.startsWith("article modify")) {
                articleController.doModify(cmd);
            } else {
                System.out.println("사용할 수 없는 명령어");
            }
        }

        sc.close();
    }
}
