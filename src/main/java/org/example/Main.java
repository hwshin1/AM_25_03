package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("== 프로그램 실행 ==");

        int lastId = 0;
        List<Article> articles = new ArrayList<>();

        while (true) {
            System.out.print("명령어 ) ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                System.out.println("== 프로그램 종료 ==");
                break;
            } else if (cmd.isEmpty()) {
                System.out.println("명령어가 입력되지 않았습니다.");
            }

            if (cmd.equals("article write")) {
                int id = lastId + 1;

                System.out.print("제목 : ");
                String title = sc.nextLine().trim();
                System.out.print("내용 : ");
                String content = sc.nextLine().trim();

                Article article = new Article(id, title, content);
                articles.add(article);

                System.out.println(id + "번 글이 생성되었습니다.");
                lastId++;
            } else if (cmd.equals("article list")) {
                if (articles.size() == 0) {
                    System.out.println("등록된 게시글이 없습니다.");
                    continue;
                }

                System.out.println("=".repeat(30));
                System.out.println("   번호   /   제목   /   내용   ");

                for (int i = articles.size() - 1; i >= 0; i--) {
                    Article article = articles.get(i);
                    System.out.printf("   %d   /   %s   /   %s   \n",
                            article.getId(),
                            article.getTitle(),
                            article.getContent());
                }
                System.out.println("=".repeat(30));
            } else if (cmd.startsWith("article delete")) {
                int id = Integer.parseInt(cmd.split(" ")[2]);

                Article foundArticle = null;

                for (int i = articles.size() - 1; i >= 0; i--) {
                    Article article = articles.get(i);
                    if (article.getId() == id) {
                        foundArticle = article;
                        break;
                    }
                }

                if (foundArticle == null) {
                    System.out.println("해당 게시글이 없습니다.");
                    continue;
                }

                articles.remove(foundArticle);
                System.out.println(id + "번이 삭제되었습니다.");
            } else if (cmd.startsWith("article detail")) {
                int id = Integer.parseInt(cmd.split(" ")[2]);
                Article foundArticle = null;

                for (Article article : articles) {
                    if (article.getId() == id) {
                        foundArticle = article;
                        break;
                    }
                }

                if (foundArticle == null) {
                    System.out.println("해당 게시글이 없습니다.");
                    continue;
                }

                System.out.println("== 게시글 상세보기 ==");
                System.out.println("번호 : " + foundArticle.getId());
                System.out.println("제목 : " + foundArticle.getTitle());
                System.out.println("내용 : " + foundArticle.getContent());
            } else if (cmd.startsWith("article modify")) {
                int id = Integer.parseInt(cmd.split(" ")[2]);
                Article foundArticle = null;

                for (Article article : articles) {
                    if (article.getId() == id) {
                        foundArticle = article;
                        break;
                    }
                }

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

                foundArticle.setTile(newTitle);
                foundArticle.setContent(newContent);

            } else {
                System.out.println("사용할 수 없는 명령어");
            }
        }

        sc.close();
    }
}

class Article {
    int id;
    String title;
    String content;

    public Article(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTile(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}