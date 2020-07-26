package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GroupDAO;

@WebServlet("/joingroup")
public class JoinGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JoinGroup() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		GroupDAO groupDAO = new GroupDAO();
		groupDAO.add((String) session.getAttribute("userid"),req.getParameter("group_id")); // DBに保存
		// DBへの保存が成功したものとして、メインページに遷移
		resp.sendRedirect("/ActionLogger/");
	}

}
