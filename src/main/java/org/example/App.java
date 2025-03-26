package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    List<Article> articles;

    public App() {
        articles = new ArrayList<>();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("== 프로그램 실행 ==");

        MemberController memberController = new MemberController(sc);
        int lastId = 3;

        makeTestDate();
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
                System.out.println("== 게시글 생성 ==");
                int id = lastId + 1;
                String getTime = Util.getNow();
                String updateTime = Util.getNow();
                System.out.print("제목 : ");
                String title = sc.nextLine().trim();
                System.out.print("내용 : ");
                String content = sc.nextLine().trim();

                Article article = new Article(id, getTime, updateTime, title, content);
                articles.add(article);

                System.out.println(id + "번 글이 생성되었습니다.");
                lastId++;
            } else if (cmd.startsWith("article list")) {
                System.out.println("==게시글 목록==");
                if (articles.size() == 0) {
                    System.out.println("아무것도 없어");
                    continue;
                }

                String searchKeyword = cmd.substring("article list".length()).trim();

                List<Article> forPrintArticles = articles;

                if (searchKeyword.length() > 0) {
                    System.out.println("검색어 : " + searchKeyword);
                    forPrintArticles = new ArrayList<>();

                    for (Article article : articles) {
                        if (article.getTitle().contains(searchKeyword)) {
                            forPrintArticles.add(article);
                        }
                    }
                    if (forPrintArticles.size() == 0) {
                        System.out.println("검색 결과 없음");
                        continue;
                    }
                }

                System.out.println("   번호    /     날짜       /   제목     /    내용   ");
                for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
                    Article article = forPrintArticles.get(i);
                    if (Util.getNow().split(" ")[0].equals(article.getRegDate().split(" ")[0])) {
                        System.out.printf("  %d   /    %s        /    %s     /    %s   \n", article.getId(), article.getRegDate().split(" ")[1], article.getTitle(), article.getContent());
                    } else {
                        System.out.printf("  %d   /    %s        /    %s     /    %s   \n", article.getId(), article.getRegDate().split(" ")[0], article.getTitle(), article.getContent());
                    }
                }
                System.out.println("=".repeat(30));
            } else if (cmd.startsWith("article delete")) {
                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = getArticle(id);

                if (foundArticle == null) {
                    System.out.println("해당 게시글이 없습니다.");
                    continue;
                }

                articles.remove(foundArticle);
                System.out.println(id + "번이 삭제되었습니다.");
            } else if (cmd.startsWith("article detail")) {
                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = getArticle(id);

                if (foundArticle == null) {
                    System.out.println("해당 게시글이 없습니다.");
                    continue;
                }

                System.out.println("== 게시글 상세보기 ==");
                System.out.println("번호 : " + foundArticle.getId());
                System.out.println("작성날짜 : " + foundArticle.getRegDate());
                System.out.println("수정날짜 : " + foundArticle.getUpdateTime());
                System.out.println("제목 : " + foundArticle.getTitle());
                System.out.println("내용 : " + foundArticle.getContent());
            } else if (cmd.startsWith("article modify")) {
                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = getArticle(id);

                if (foundArticle == null) {
                    System.out.println("해당 게시글이 없습니다.");
                    continue;
                }

                System.out.println("기존 제목 : " + foundArticle.getTitle());
                System.out.println("기존 내용 : " + foundArticle.getContent());

                System.out.print("새 제목 : ");
                String newTitle = sc.nextLine().trim();
                System.out.print("새 내용 : ");
                String newContent = sc.nextLine().trim();

                foundArticle.setTitle(newTitle);
                foundArticle.setContent(newContent);
                foundArticle.setUpdateTime(Util.getNow());

                System.out.println(id + "번 글이 수정되었습니다.");
            } else {
                System.out.println("사용할 수 없는 명령어");
            }
        }

        sc.close();
    }

    private void makeTestDate() {
        System.out.println("== 게시글 테스트 데이터 생성 ==");

        articles.add(new Article(1, "2025-03-24 12:00:12", "2025-03-24 12:00:12", "asdf", "asdf"));
        articles.add(new Article(2, Util.getNow(), Util.getNow(), "qewr", "qwer"));
        articles.add(new Article(3, Util.getNow(), Util.getNow(), "zxcv", "zxcv"));
    }

    private Article getArticle(int id) {
        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);

            if (article.getId() == id) {
                return article;
            }
        }

        return null;
    }
}
