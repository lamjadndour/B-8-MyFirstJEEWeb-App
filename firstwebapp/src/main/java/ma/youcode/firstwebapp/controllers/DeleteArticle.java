package ma.youcode.firstwebapp.controllers;

import java.io.IOException;
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
@WebServlet("/deleteArticle/*")
public class DeleteArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleDaoImpl articleDaoImpl;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Instantiate ArticleDaoImpl
		articleDaoImpl = new ArticleDaoImpl();

		// Get article id
		int articleID = Integer.parseInt(request.getParameter("id"));
		System.out.println(articleID);
		
		// edited that article
		int isDeleted = articleDaoImpl.deleteArticle(articleID);
		
		
        // flash message
        final String successMessage = "<div class='alert alert-success' role='alert'>Article deleted successfully...!</div>";
        final String errorMessage = "<div class='alert alert-danger' role='alert'>Article did not deleted</div>";
        
		// message which holds success or failure editing operation
		String editMessage = isDeleted == 1 ? successMessage : errorMessage;
		
        
        // set reacted message
        HttpSession session = request.getSession();
        session.setAttribute("message", editMessage);
		
		// redirect to home page
		response.sendRedirect(request.getContextPath());
		
	}
}
