package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CommentsDao;
import domain.Comment;

/**
 * Servlet implementation class MainController
 */
@WebServlet("")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("name") != null) {
			response.sendRedirect("/Giannism/login");
		} else {

			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		// PrintWriter out = new PrintWriter(response.getWriter(), true);

		// reading parameters from request
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String comments = request.getParameter("comments");

		// define RequestDispatcher object to forward any errors

		// define RequestDispatcher object to forward if data are correct and
		// successfully stored in database
		RequestDispatcher succdis = request.getRequestDispatcher("/index.jsp");

		try {

			if (name == null || name.length() == 0) {

				return;

			}

			if (email == null || email.length() == 0) {

				return;

			}

			if (comments == null || comments.length() == 0) {

				return;

			}

			Comment comment = new Comment(name, email, comments);

			CommentsDao cDao = new CommentsDao();

			cDao.post(comment); // open connection

			// adding Student object to request
			session.setAttribute("subm", "true");
			succdis.forward(request, response);

		} catch (Exception e) {

			request.setAttribute("errormessage", e.getMessage());

			return;
		}

	}

}
