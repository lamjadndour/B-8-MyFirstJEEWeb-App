package ma.youcode.firstwebapp.impldao;

import ma.youcode.firstwebapp.dao.ArticleDao;
import ma.youcode.firstwebapp.database.Database;
import ma.youcode.firstwebapp.models.Article;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ArticleDaoImpl implements ArticleDao {
    private List<Article> article;


    public ArticleDaoImpl() {
        super();
    }


    @Override
    public int add(String articleTitle, String articleBody) {
        // Create connection to database
        Database database = new Database();
        Connection connection = database.makeConnection();

        try {
            // Prepare query
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Articles (ArticleTitle, ArticleBody) VALUES (?, ?)");
            ps.setString(1,articleTitle);
            ps.setString(2, articleBody);
            // execute query
            int isAdded = ps.executeUpdate();
            System.out.println("IsAdd : "+isAdded);
            // return 1
            if (isAdded > 0) {
            	return 1;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        
        return 0;
    }


    @Override
    public List<Article> getAllArticles() {
        // Create articles
        article = new ArrayList<Article>();

        // Create connection to database
        Database database = new Database();
        Connection connection = database.makeConnection();

        // Query
        String query = "SELECT * FROM articles";

        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(query);

            // Iterate through db rows
            while(queryResult.next()) {
                // Get data from db
                int articleID = queryResult.getInt(1);
                String articleTitle = queryResult.getString("ArticleTitle");
                String articleBody = queryResult.getString("ArticleBody");

                // Store articles from db in articles list
                article.add(new Article(articleID, articleTitle, articleBody));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return article;
    }

	@Override
	public List<Article> getArticleById(int articleID) {
        // Create articles
        article = new ArrayList<Article>();

        // Create connection to database
        Database database = new Database();
        Connection connection = database.makeConnection();

        // Query
        String query = "SELECT * FROM articles WHERE ArticleID = '" + articleID + "';";

        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(query);

            // Iterate through db rows
            while(queryResult.next()) {
                // Get data from db
                int id = queryResult.getInt(1);
                String articleTitle = queryResult.getString("ArticleTitle");
                String articleBody = queryResult.getString("ArticleBody");

                // Store articles from db in articles list
                article.add(new Article(id, articleTitle, articleBody));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        
		return article;
	}

	@Override
	public List<Article> searchArticleByTitle(String articleTitle) {
        // Create articles
        article = new ArrayList<Article>();

        // Create connection to database
        Database database = new Database();
        Connection connection = database.makeConnection();

        // Search Query
        String query = "SELECT * FROM articles WHERE ArticleTitle LIKE '%" + articleTitle + "%';";

        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(query);

            // Iterate through db rows
            while(queryResult.next()) {
                // Get data from db
                int articleID = queryResult.getInt(1);
                String title = queryResult.getString("ArticleTitle");
                String articleBody = queryResult.getString("ArticleBody");

                // Store articles from db in articles list
                article.add(new Article(articleID, title, articleBody));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return article;
	}

    public int deleteArticle(int articleID) {

        // Query
        String query = "DELETE FROM articles WHERE ArticleID='" +  articleID + "';";

        // Create connection to database
        Database database = new Database();
        Connection connection = database.makeConnection();

        try {
            // prepare statment
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int isDeleted = preparedStatement.executeUpdate();

            // if data deleted
            if (isDeleted > 0) {
                // close connection
                connection.close();

                return 1;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int editArticle(int articleID, String ArticleTitle, String ArticleBody) {
        // Query


        // Create connection to database
        Database database = new Database();
        Connection connection = database.makeConnection();
        int isEdited= 0;

        try {
            // prepare statment
            //String query = "UPDATE Articles SET ArticleTitle = '" + ArticleTitle + ", ArticleBody= '" + ArticleBody + "' WHERE ArticleID = " + articleID;
            PreparedStatement ps = connection.prepareStatement("UPDATE Articles SET ArticleTitle = ?, ArticleBody= ? WHERE ArticleID = ?");
            ps.setString(1,ArticleTitle);
            ps.setString(2, ArticleBody);
            ps.setInt(3, articleID);

            isEdited = ps.executeUpdate();


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return isEdited;
    }



}
