package ma.youcode.firstwebapp.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ma.youcode.firstwebapp.database.Database;
import ma.youcode.firstwebapp.impldao.ArticleDaoImpl;

/**
 * Servlet implementation class EditArticle
 */
@WebServlet("/editArticle/*")
public class EditArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleDaoImpl articleDaoImpl;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public EditArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String articleID = request.getParameter("id");
		response.sendRedirect(request.getContextPath() + "/editArticle.jsp?id=" + articleID);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get article data from user
		int articleID = Integer.parseInt(request.getParameter("id"));
        String articleTitle = request.getParameter("articleTitle");
        String articleBody = request.getParameter("articleBody");

		// Instantiate ArticleDaoImpl
		articleDaoImpl = new ArticleDaoImpl();
        // add article to database
        int isEdited = articleDaoImpl.editArticle(articleID, articleTitle, articleBody);
  
        // flash message
        final String successMessage = "<div class='alert alert-success' role='alert'>Article edited successfully...!</div>";
        final String errorMessage = "<div class='alert alert-danger' role='alert'>Article did not edited</div>";
        
        // check if data added to database
        String message = isEdited == 1 ? successMessage : errorMessage;
        
        // set reacted message
        HttpSession session = request.getSession();
        session.setAttribute("message", message);
        
        // redirect to home page
        response.sendRedirect("/firstwebapp_war_exploded/");
	}
	
	/**
	 * Edit article by id
	 * @param articleID - id of article
	 * @param ArticleTitle - title of article
	 * @param ArticleBody - body of article
	 * @return1 if article edited otherwise return 0
	 */
}
