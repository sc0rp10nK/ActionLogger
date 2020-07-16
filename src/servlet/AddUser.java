package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ErrorViewData;
import model.InputCheckException;
import model.User;
import model.ValidationKey;

//static import
import static model.InputChecker.checkLongInput;
import static model.InputChecker.checkPhoneNumber;
import static model.InputChecker.checkMailAddress;


//ユーザー追加機能
//GETでアクセスされた場合　登録フォームを表示
//POSTでアクセスされた場合　登録フォームから送られたデータを処理
//登録フォームから送られたデータは、DB保存候補としてsession変数に保存
@WebServlet("/adduser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddUser() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 正当なフォームから送られたデータであることを確認するためのキーの生成
		ValidationKey validationKey = new ValidationKey();
		try {
			Random random = new Random();
			String randomStr = String.valueOf(random.nextLong());
			MessageDigest validation = MessageDigest.getInstance("MD5");
			validation.reset();
			validation.update(randomStr.getBytes("utf8"));
			String vkey = String.format("%032x", new BigInteger(1, validation.digest()));
			validationKey.setValue(vkey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// フォーム確認キーをセッションスコープに設定
		HttpSession session = req.getSession();
		session.setAttribute("validationKey", validationKey);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/addUserForm.jsp");
		dispatcher.forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		// フォームから送られた確認キーが保存したものと一致するか確認
		ValidationKey validationKey = (ValidationKey) session.getAttribute("validationKey");
		if (!req.getParameter("vKey").equals(validationKey.getValue())) {
			// 一致しなかったので、セッションスコープに保存したキーを破棄し、エラーページに
			session.removeAttribute("validationKey");
			//表示データを用意する
			ErrorViewData errorData = new ErrorViewData("問題が発生しました。",
													"トップに戻る","/ActionLoggerSample/");
			req.setAttribute("errorData", errorData);
			//エラー表示にフォワード
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorView.jsp");
			dispatcher.forward(req, resp);
			return;
		}

		User user = new User();
		try {
			user.setUserId( checkLongInput(req.getParameter("userid")) );
			user.setName(checkLongInput(req.getParameter("name")) );
			user.setAddress( checkLongInput(req.getParameter("address")) );
			user.setTel( checkPhoneNumber(req.getParameter("tel")) );
			user.setEmail( checkMailAddress(req.getParameter("email")) );

			// パスワードのハッシュ化
			String rawPassword = req.getParameter("password");
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(rawPassword.getBytes("utf8"));
			String passwordHash = String.format("%064x", new BigInteger(1, digest.digest()));

			user.setPwdHash(passwordHash); // ハッシュ値をオブジェクトに設定

			// userオブジェクトをセッションスコープに一旦保存（DBに入れるのはConfirmの後）
			session.setAttribute("userToAdd", user);

			// 確認画面にリダイレクト
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/addUserConfirm.jsp");
			dispatcher.forward(req, resp);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InputCheckException e1) {
			//表示データを用意する
			ErrorViewData errorData = new ErrorViewData("フォームに入力された内容に問題がありました。",
													"入力画面に戻る","/ActionLoggerSample/adduser");
			req.setAttribute("errorData", errorData);
			//エラー表示にフォワード
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorView.jsp");
			dispatcher.forward(req, resp);
		}
	}

}
