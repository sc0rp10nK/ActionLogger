package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Action;


@WebServlet("/addaction")
public class AddAction extends HttpServlet {
    static int accessCountInDatabase = 0;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		Action act = new Action();

			act.setActionDate(req.getParameter("date"));
			act.setActionSTm(req.getParameter("start_time"));
			act.setActionETm(req.getParameter("end_time"));
			act.setActionPlace(req.getParameter("place"));
			act.setActionReason(req.getParameter("reason"));
			act.setActionRemarks(req.getParameter("remarks"));
			act.setActionUserId((String) session.getAttribute("userid"));
			// actionオブジェクトをセッションスコープに一旦保存（DBに入れるのはConfirmの後）
			session.setAttribute("actionToAdd", act);

			// 確認画面にリダイレクト
			RequestDispatcher dispatcher = req.getRequestDispatcher("/ActionLogger/?view=addactionconfirm");
			dispatcher.forward(req, resp);

	}

}
