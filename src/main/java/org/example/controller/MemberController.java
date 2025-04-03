package org.example.controller;

import org.example.Util;
import org.example.dto.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberController extends Controller {
    Scanner sc;
    List<Member> members;
    String cmd;

    int memberId = 3;

    public MemberController(Scanner sc) {
        this.sc = sc;
        members = new ArrayList<>();
    }

    public void doAction(String cmd, String actionCommand) {
        this.cmd = cmd;

        switch (actionCommand) {
            case "join":
                doJoin();
                break;
            case "login":
                doLogin();
                break;
            case "logout":
                doLogout();
                break;
            default:
                System.out.println("Unknown action");
                break;
        }
    }

    private void doJoin() {
        System.out.println("== 회원가입 ==");

        int id = memberId + 1;

        String regDate = Util.getNow();
        String loginId = null;

        while (true) {
            System.out.print("로그인 아이디 : ");
            loginId = sc.nextLine().trim();
            if (!isJoinableLoginId(loginId)) {
                System.out.println("다른 ID를 입력하세요.");
                continue;
            }
            break;
        }
        String password = null;
        while (true) {
            System.out.print("비밀번호 : ");
            password = sc.nextLine().trim();
            System.out.print("비밀번호 확인: ");
            String passwordConfirm = sc.nextLine().trim();

            if (!password.equals(passwordConfirm)) {
                System.out.println("비밀번호가 틀립니다.");
                continue;
            }
            break;
        }

        System.out.print("이름 : ");
        String name = sc.nextLine().trim();

        Member member = new Member(id, regDate, loginId, password, name);
        members.add(member);

        System.out.println("회원이 가입되었습니다");
        memberId++;
    }

    private void doLogin() {
        System.out.println("== 로그인 ==");
        System.out.print("로그인 아이디 : ");
        String loginId = sc.nextLine().trim();
        System.out.print("비밀번호 : ");
        String loginPw = sc.nextLine().trim();

        Member member = getMemberByLoginId(loginId);

        if (member == null) {
            System.out.println("아이디가 존재하지 않습니다.");
            return;
        }

        if (member.getLoginPw().equals(loginPw) == false) {
            System.out.println("비밀번호가 틀렸습니다.");
            return;
        }

        loginedMember = member;
        System.out.printf("%s님 로그인 성공!\n", loginedMember.getName());
    }

    private void doLogout() {
        loginedMember = null;
        System.out.println("로그아웃 되었습니다.");
    }

    private Member getMemberByLoginId(String loginId) {
        for (Member member : members) {
            if (member.getLoginId().equals(loginId)) {
                return member;
            }
        }
        return null;
    }

    public void makeTestMember() {
        System.out.println("== 회원 테스트 데이터 생성 ==");

        members.add(new Member(1, Util.getNow(), "test1", "test1", "test1"));
        members.add(new Member(2, Util.getNow(), "test2", "test2", "test2"));
        members.add(new Member(3, Util.getNow(), "test12", "test12", "test12"));
    }

    private boolean isJoinableLoginId(String loginId) {
        for (Member member : members) {
            if (member.getLoginId().equals(loginId)) {
                return false;
            }
        }
        return true;
    }
}
