package org.example.ArticleManager;

import org.example.Dao.ArticleDao;
import org.example.Dao.MemberDao;

public class Container {
    public static ArticleDao articleDao;
    public static MemberDao memberDao;

    static {
        articleDao = new ArticleDao();
        memberDao = new MemberDao();
    }
}
