package ma.youcode.firstwebapp.models;

public class Article {
    private int articleID;
    private String articleTitle;
    private String articleBody;

    /**
     * Class constructor
     */
    public Article(){
        super();
    }

    /**
     * Class constructor for creating new article
     *
     * @param articleID id of article
     * @param articleTitle title of article
     * @param articleBody body of article
     */
    public Article(int articleID, String articleTitle, String articleBody) {
        this.articleID = articleID;
        this.articleTitle = articleTitle;
        this.articleBody = articleBody;
    }

    // Setters
    /**
     * @param articleID set the id of article
     */
    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    /**
     * @param articleTitle set the title of article
     */
    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    /**
     * @param articleBody set the body of article
     */
    public void setArticleBody(String articleBody) {
        this.articleBody = articleBody;
    }

    // Getters
    /**
     * @return the id of article
     */
    public int getArticleID() {
        return articleID;
    }

    /**
     * @return the title of article
     */
    public String getArticleTitle() {
        return articleTitle;
    }

    /**
     * @return the body of article
     */
    public String getArticleBody() {
        return articleBody;
    }
}	
