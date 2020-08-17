package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.ErrorViewData;
import model.InputCheckException;
import model.User;
import model.ValidationKey;

@WebServlet("/passwordchange")
public class PasswordChange extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PasswordChange() {
		super();

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		// フォームから送られた確認キーが保存したものと一致するか確認
		ValidationKey validationKey = (ValidationKey) session.getAttribute("validationKey");
		if (!req.getParameter("vKey").equals(validationKey.getValue())) {
			// 一致しなかったので、セッションスコープに保存したキーを破棄し、エラーページに
			session.removeAttribute("validationKey");
			// 表示データを用意する
			ErrorViewData errorData = new ErrorViewData("問題が発生しました。", "トップに戻る", "/ActionLogger/");
			req.setAttribute("errorData", errorData);
			// エラー表示にフォワード
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorView.jsp");
			dispatcher.forward(req, resp);
			return;
		}
		String passwordHash = "";
		String newPasswordHash = "";
		try {
			// パスワードのハッシュ化
			String rawPassword = req.getParameter("password");
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(rawPassword.getBytes("utf8"));
			passwordHash = String.format("%064x", new BigInteger(1, digest.digest()));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {
			// 新しいパスワードのハッシュ化
			String rawPassword = req.getParameter("newPassword");
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(rawPassword.getBytes("utf8"));
			newPasswordHash = String.format("%064x", new BigInteger(1, digest.digest()));

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// DBからユーザーを取得
		UserDAO userDAO = new UserDAO();
		User user = userDAO.get(req.getParameter("userid"));
		// DBからの取得が成功 AND パスワードハッシュが合致
		if (user != null && user.getPwdHash().equals(passwordHash)) {
			user.setPwdHash(newPasswordHash);
			if(userDAO.changePass(user)) {
				user = userDAO.get(req.getParameter("userid"));
				session.setAttribute("user", user);
				// 確認画面にリダイレクト
				resp.sendRedirect("/ActionLogger");
			}else {
				// 表示データを用意する
				ErrorViewData errorData = new ErrorViewData("問題が発生しました。", "トップに戻る", "/ActionLogger/");
				req.setAttribute("errorData", errorData);
				// エラー表示にフォワード
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorView.jsp");
				dispatcher.forward(req, resp);
				return;
			}
		} else {
			// 表示データを用意する
			ErrorViewData errorData = new ErrorViewData("パスワードが間違っています。", "入力画面に戻る", "/ActionLogger/?view=password");
			req.setAttribute("errorData", errorData);
			// エラー表示にフォワード
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorView.jsp");
			dispatcher.forward(req, resp);

		}
	}

}
