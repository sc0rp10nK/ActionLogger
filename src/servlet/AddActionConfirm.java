package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ActionDAO;
import model.Action;

//ユーザーが登録するユーザー情報を確認した後、OKをクリックしたときの処理
@WebServlet("/addactionconfirm")
public class AddActionConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddActionConfirm() {
		super();
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		// statusがconfirmedの場合
		// 本来は正当な登録確認であることをチェックするべきであるが、とりあえずOmit
		if (req.getParameter("status").equals("confirmed")) {
		// セッションスコープに保存していた、DB登録前の活動情報を取得
		Action act = (Action) session.getAttribute("actionToAdd");
		ActionDAO actionDAO = new ActionDAO();
		actionDAO.set(act); // DBに保存
		session.removeAttribute("actionToAdd");
		}
		// DBへの保存が成功したものとして、メインページに遷移
		resp.sendRedirect("/ActionLogger/");
	}
}
