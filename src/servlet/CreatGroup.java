package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Group;



@WebServlet("/creatgroup")
public class CreatGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CreatGroup() {
        super();
    }
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		Group gp = new Group();
		gp.setGroupName(req.getParameter("group_name"));
		gp.setGroupAdm(true);
		gp.setGroupUserId((String) session.getAttribute("userid"));
		// groupオブジェクトをセッションスコープに一旦保存（DBに入れるのはConfirmの後）
		session.setAttribute("groupToAdd", gp);

			// 確認画面にリダイレクト
			RequestDispatcher dispatcher = req.getRequestDispatcher("/ActionLogger/?view=creatgroupconfirm");
			dispatcher.forward(req, resp);
	}

}
