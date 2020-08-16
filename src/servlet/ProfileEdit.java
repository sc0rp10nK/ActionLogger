package servlet;

import static model.InputChecker.checkLongInput;
import static model.InputChecker.checkMailAddress;
import static model.InputChecker.checkPhoneNumber;

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

@WebServlet("/profileedit")
public class ProfileEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProfileEdit() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
		// DBからユーザーを取得
		UserDAO userDAO = new UserDAO();
		User user = userDAO.get(req.getParameter("userid"));
		// DBからの取得が成功 AND パスワードハッシュが合致
		if (user != null && user.getPwdHash().equals(passwordHash)) {
			try {
				String name = (String) req.getParameter("name");
				String address = (String) req.getParameter("address");
				String tel = (String) req.getParameter("tel");
				String email = (String) req.getParameter("email");
				if (!name.isEmpty()) {
					user.setName(checkLongInput(name));
				} 
				if (!address.isEmpty()) {
					user.setAddress(checkLongInput(address));
				} 
				if (!tel.isEmpty()) {
					user.setTel(checkPhoneNumber(tel));

				}if (!email.isEmpty()) {
					user.setEmail(checkMailAddress(email));
				}
				if (userDAO.edit(user)) {
					user = userDAO.get(req.getParameter("userid"));
					session.setAttribute("userid", user.getUserId());
					session.setAttribute("username", user.getName());
					session.setAttribute("user", user);
					// 確認画面にリダイレクト
					resp.sendRedirect("/ActionLogger/?view=profile");
				}else {
					// 表示データを用意する
					ErrorViewData errorData = new ErrorViewData("問題が発生しました。", "トップに戻る", "/ActionLogger/");
					req.setAttribute("errorData", errorData);
					// エラー表示にフォワード
					RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorView.jsp");
					dispatcher.forward(req, resp);
					return;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (InputCheckException e1) {
				// 表示データを用意する
				ErrorViewData errorData = new ErrorViewData("フォームに入力された内容に問題がありました。", "入力画面に戻る",
						"/ActionLogger/?view=profile");
				req.setAttribute("errorData", errorData);
				// エラー表示にフォワード
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorView.jsp");
				dispatcher.forward(req, resp);
			}
		} else {
			// 表示データを用意する
			ErrorViewData errorData = new ErrorViewData("パスワードが間違っています。", "入力画面に戻る", "/ActionLogger/?view=profile");
			req.setAttribute("errorData", errorData);
			// エラー表示にフォワード
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorView.jsp");
			dispatcher.forward(req, resp);
		}
	}

}
