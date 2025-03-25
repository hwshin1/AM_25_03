package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Article> articles = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("== 프로그램 실행 ==");

        int lastId = 3;

        makeTestDate();

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
            } else if (cmd.equals("article list")) {
                if (articles.size() == 0) {
                    System.out.println("등록된 게시글이 없습니다.");
                    continue;
                }

                System.out.println("=".repeat(30));
                System.out.println("   번호   /   날짜   /   제목   /   내용   ");

                for (int i = articles.size() - 1; i >= 0; i--) {
                    Article article = articles.get(i);

                    // 날짜가 오늘이면 시간이 나오고 날짜가 지난 날이면 날짜가 나온다.
                    if (Util.getNow().split(" ")[0].equals(article.getRegDate().split(" ")[0])) {
                        System.out.printf("   %d   /   %s   /   %s   /   %s   \n",
                                article.getId(),
                                article.getRegDate().split(" ")[1],
                                article.getTitle(),
                                article.getContent());
                    } else {
                        System.out.printf("   %d   /   %s   /   %s   /   %s   \n",
                                article.getId(),
                                article.getRegDate().split(" ")[0],
                                article.getTitle(),
                                article.getContent());
                    }
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
                System.out.println("작성날짜 : " + foundArticle.getRegDate());
                System.out.println("수정날짜 : " + foundArticle.getUpdateTime());
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

    private static void makeTestDate() {
        System.out.println("== 테스트 데이터 생성 ==");

        articles.add(new Article(1, "2025-03-24 12:00:12", "2025-03-24 12:00:12", "asdf", "asdf"));
        articles.add(new Article(2, Util.getNow(), Util.getNow(), "qewr", "qwer"));
        articles.add(new Article(3, Util.getNow(), Util.getNow(), "zxcv", "zxcv"));
    }
}

class Article {
    int id;
    String regDate;
    String updateTime;
    String title;
    String content;

    public Article(int id, String regDate, String updateTime, String title, String content) {
        this.id = id;
        this.regDate = regDate;
        this.updateTime = updateTime;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getRegDate() {
        return regDate;
    }

    public String getUpdateTime() {
        return updateTime;
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

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
