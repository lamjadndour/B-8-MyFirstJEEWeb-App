package ma.youcode.firstwebapp.dao;


import java.util.List;

import ma.youcode.firstwebapp.models.Article;

public interface ArticleDao {


    int add(String articleTitle, String articleBody);

    List<Article> getAllArticles();

    List<Article> getArticleById(int ArticleID);

    List<Article> searchArticleByTitle(String articleTitle);

    int deleteArticle(int ArticleID);

    int editArticle(int articleID, String ArticleTitle, String ArticleBody);
}
