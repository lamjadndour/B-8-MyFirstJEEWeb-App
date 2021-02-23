package ma.youcode.firstwebapp.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ma.youcode.firstwebapp.impldao.ArticleDaoImpl;

/**
 * Servlet implementation class Main
 */
@WebServlet("/addArticle")
public class AddArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ArticleDaoImpl articleDaoImpl;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Instantiate ArticleDaoImpl
        articleDaoImpl = new ArticleDaoImpl();

        PrintWriter out = response.getWriter();

        // Get article data from user
        String articleTitle = request.getParameter("articleTitle");
        String articleBody = request.getParameter("articleBody");


        // add article to database
        int isAdded = articleDaoImpl.add(articleTitle, articleBody);
  
        // flash message
        final String successMessage = "<div class='alert alert-success' role='alert'>Article added successfully...!</div>";
        final String errorMessage = "<div class='alert alert-danger' role='alert'>Article did not added</div>";
        
        // check if data added to database
        String message = isAdded == 1 ? successMessage : errorMessage;
        
        // set reacted message
        HttpSession session = request.getSession();
        session.setAttribute("message", message);
        
        // redirect to home page
        response.sendRedirect("/firstwebapp_war_exploded/");
	}

}
