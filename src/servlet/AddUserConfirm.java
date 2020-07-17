package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.ErrorViewData;
import model.User;
import model.ValidationKey;

//ユーザーが登録するユーザー情報を確認した後、OKをクリックしたときの処理
@WebServlet("/adduserconfirm")
public class AddUserConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddUserConfirm() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		// フォームから送られた確認キーが保存したものと一致するか確認
		ValidationKey validationKey = (ValidationKey) session.getAttribute("validationKey");
		if (!req.getParameter("vKey").equals(validationKey.getValue())) {
			// 一致しなかったので、セッションスコープに保存したキーを破棄し、エラーページに
			session.removeAttribute("validationKey");
			
			//表示データを用意する
			ErrorViewData errorData = new ErrorViewData("問題が発生しました。",
													"トップに戻る","/ActionLogger/");
			req.setAttribute("errorData", errorData);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorView.jsp");
			dispatcher.forward(req, resp);
			return;
		}

		// statusがconfirmedの場合
		// 本来は正当な登録確認であることをチェックするべきであるが、とりあえずOmit
		if (req.getParameter("status").equals("confirmed")) {
			// セッションスコープに保存していた、DB登録前のユーザー情報を取得
			User user = (User) session.getAttribute("userToAdd");
			UserDAO userDAO = new UserDAO();
			userDAO.save(user); // DBに保存
			// TODO 主キーの重複で保存できなかった場合の処理を追加

		}
		// DBへの保存が成功したものとして、ログインページに遷移
		resp.sendRedirect("/ActionLogger/login");
	}
}
