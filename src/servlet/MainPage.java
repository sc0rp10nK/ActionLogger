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
import dao.GroupDAO;
import model.Action;
import model.Group;

/**
 * Servlet implementation class MainPage
 */
@WebServlet("/")
public class MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MainPage() {
		// TODO Auto-generated constructor stub
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// HttpSessionインタフェースのオブジェクトを取得
		HttpSession session = request.getSession();
		// useridデータをsessionスコープで保存
		String userid = (String) session.getAttribute("userid");

		if (userid == null) {
			// MainViewを表示
			response.sendRedirect("/ActionLogger/login");

		} else {
			// DBから活動記録を取得
			ActionDAO actionDAO = new ActionDAO();
			List<Action> actlist = actionDAO.allGet((String)(session.getAttribute("userid")));
			request.setAttribute("actList",actlist);
			List<Action> actltyList = actionDAO.ltyGet((String)(session.getAttribute("userid")));
			request.setAttribute("actLtyList",actltyList);
			//DBから所属中 & 管理中グループを取得
			GroupDAO groupDAO = new GroupDAO();
			List<Group> gplist = groupDAO.allGet((String)(session.getAttribute("userid")));
			request.setAttribute("gpList",gplist);
			List<Group> admGpList = groupDAO.admGroupGet((String)(session.getAttribute("userid")));
			request.setAttribute("admGpList",admGpList);
			// MainViewを表示
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainView.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
