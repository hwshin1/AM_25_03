package org.example.controller;

import org.example.Util;
import org.example.dto.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArticleController extends Controller {
    Scanner sc;
    List<Article> articles;
    String cmd;

    int lastId = 3;

    public ArticleController(Scanner sc) {
        this.sc = sc;
        articles = new ArrayList<>();
    }

    public void doAction(String cmd, String actionCommand) {
        this.cmd = cmd;

        switch (actionCommand) {
            case "write" :
                doWrite();
                break;
            case "list" :
                showList();
                break;
            case "delete" :
                doDelete();
                break;
            case "detail" :
                showDetails();
                break;
            case "modify" :
                doModify();
                break;
            default :
                System.out.println("Unknown action: " + actionCommand);
                break;
        }
    }

    public void doWrite() {
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
    }

    public void showList() {
        System.out.println("==게시글 목록==");
        if (articles.isEmpty()) {
            System.out.println("등록된 게시글이 없습니다.");
            return;
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
            if (forPrintArticles.isEmpty()) {
                System.out.println("검색 결과 없음");
                return;
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
    }

    public void doDelete() {
        int id = Integer.parseInt(cmd.split(" ")[2]);

        Article foundArticle = getArticle(id);

        if (foundArticle == null) {
            System.out.println("해당 게시글이 없습니다.");
            return;
        }

        articles.remove(foundArticle);
        System.out.println(id + "번이 삭제되었습니다.");
    }

    public void showDetails() {
        int id = Integer.parseInt(cmd.split(" ")[2]);

        Article foundArticle = getArticle(id);

        if (foundArticle == null) {
            System.out.println("해당 게시글이 없습니다.");
            return;
        }

        System.out.println("== 게시글 상세보기 ==");
        System.out.println("번호 : " + foundArticle.getId());
        System.out.println("작성날짜 : " + foundArticle.getRegDate());
        System.out.println("수정날짜 : " + foundArticle.getUpdateTime());
        System.out.println("제목 : " + foundArticle.getTitle());
        System.out.println("내용 : " + foundArticle.getContent());
    }

    public void doModify() {
        int id = Integer.parseInt(cmd.split(" ")[2]);

        Article foundArticle = getArticle(id);

        if (foundArticle == null) {
            System.out.println("해당 게시글이 없습니다.");
            return;
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
    }

    public void makeTestDate() {
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
