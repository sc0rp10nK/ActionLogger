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

import dao.ActionDAO;
import dao.UserDAO;
import model.Action;
import model.User;

@WebServlet("/getaction")
public class getAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public getAction() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// HttpSessionインタフェースのオブジェクトを取得
		HttpSession session = req.getSession();
		//urlにuseridとgroupidが格納されているのか確認
		if (req.getParameter("id") != null && req.getParameter("gpid") != null) {
			// DBから活動記録を取得
			ActionDAO actionDAO = new ActionDAO();
			List<Action> actlist = actionDAO.allGet(req.getParameter("id"),req.getParameter("gpid"),(String) session.getAttribute("userid"));
			// DBからユーザーを取得
			UserDAO userDAO = new UserDAO();
			User user = userDAO.get(req.getParameter("id"));
			if (actlist == null) {
				//エラーが出たのでメインページにリダイレクト
				resp.sendRedirect("/ActionLogger");
			} else {
				req.setAttribute("getActlist", actlist);
				session.setAttribute("getUserName", user.getName());
				//アクション表示にリダイレクト
				RequestDispatcher dispatcher = req.getRequestDispatcher("/ActionLogger/?view=getaction");
				dispatcher.forward(req, resp);
			}
		}else {
			//urlにuseridとgroupidが格納されていないのでメインページにリダイレクト
			resp.sendRedirect("/ActionLogger");
		}
	}
}
