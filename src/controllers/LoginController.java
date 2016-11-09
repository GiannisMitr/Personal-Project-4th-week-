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
import dao.UsersDao;

import domain.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session.getAttribute("name") != null) {
			request.getRequestDispatcher("/results.jsp").forward(request, response);
		} else {
			response.sendRedirect("/Giannism");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher succdis = request.getRequestDispatcher("/results.jsp");
		HttpSession session = request.getSession(false);

		if (request.getParameter("logout") != null) {
			session.invalidate();
			response.sendRedirect("/Giannism");
		} else if (request.getParameter("delete") != null) {

			int id = Integer.parseInt(request.getParameter("delete"));
			CommentsDao cDao = new CommentsDao();
			try {
				cDao.delete(id);
				succdis.forward(request, response);
			} catch (Exception e) {
				request.setAttribute("errormessage", e.getMessage());

				return;
			}

		}

		else {
			String password = request.getParameter("password");
			String email = request.getParameter("email");

			try {
				if (email == null || email.length() == 0) {
					session.setAttribute("pass", "wrong");
					response.sendRedirect("/Giannism");
				}

				if (password == null || password.length() == 0) {
					session.setAttribute("pass", "wrong");
					response.sendRedirect("/Giannism");
				}

				UsersDao uDao = new UsersDao();
				User user = uDao.find(email);

				if (user != null && user.getPassword().equals(password)) {

					session.setAttribute("name", user.getName());
					succdis.forward(request, response);
				}

				else {

					session.setAttribute("wrong", "true");
					response.sendRedirect("/Giannism");
				}

			} catch (Exception e) {

				request.setAttribute("errormessage", e.getMessage());

				return;
			}

		}
	}
}
