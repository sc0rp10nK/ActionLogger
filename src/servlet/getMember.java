package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GroupDAO;
import model.Member;

@WebServlet("/getmember")
public class getMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public getMember() {
		super();

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// HttpSessionインタフェースのオブジェクトを取得
		HttpSession session = req.getSession();
		GroupDAO groupDAO = new GroupDAO();
		List<Member> mbList = groupDAO.admGpGet((String) (session.getAttribute("userid")), req.getParameter("id"));
		if (mbList == null) {
			//エラーが出たのでメインページにリダイレクト
			resp.sendRedirect("/ActionLogger");
		} else {
			req.setAttribute("mbList", mbList);
			//グループ表示にリダイレクト
			RequestDispatcher dispatcher = req.getRequestDispatcher("/ActionLogger/?view=groupView");
			dispatcher.forward(req, resp);
		}
	}
}
