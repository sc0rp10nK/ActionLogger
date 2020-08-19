package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ActionDAO;
import dao.GroupDAO;
import dao.UserDAO;
import model.Action;
import model.Group;
import model.Member;
import model.User;
import model.ValidationKey;

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
		String search = (String) request.getParameter("search");
		String view = (String) request.getParameter("view");
		UserDAO userDAO = new UserDAO();
		ActionDAO actionDAO = new ActionDAO();
		GroupDAO groupDAO = new GroupDAO();
		if (userid == null) {
			// MainViewを表示
			response.sendRedirect("/ActionLogger/login");

		} else {
			List<Action> actlist = new ArrayList<>();
			actlist = actionDAO.allGet((String) (session.getAttribute("userid")));
			if (view != null) {
				if (view.equals("getaction")) {
					// urlにuseridとgroupidが格納されているのか確認
					if (request.getParameter("id") != null && request.getParameter("gpid") != null) {
						String gpId = (String) request.getParameter("gpid");
						String getUserId = (String) request.getParameter("id");
						if (search != null) {
							String date = (String) request.getParameter("date");
							String place = (String) request.getParameter("place");
							String order = (String) request.getParameter("order");
							// DBから活動記録を取得
							actlist = actionDAO.searchAllGet(getUserId, date, place, order, gpId, userid);
							if (actlist == null) {
								// エラーが出たのでメインページにリダイレクト
								response.sendRedirect("/ActionLogger");
							} else {
								session.setAttribute("getActlist", actlist);
							}
						} else {
							// DBから活動記録を取得
							actlist = actionDAO.allGet(getUserId, gpId, userid);
							User user = userDAO.get(getUserId);

							if (actlist == null) {
								// エラーが出たのでメインページにリダイレクト
								response.sendRedirect("/ActionLogger");
							} else {
								session.setAttribute("getActlist", actlist);
								session.setAttribute("getUserId", user.getUserId());
								session.setAttribute("getGroupId", gpId);
								session.setAttribute("getUserName", user.getName());
							}
						}
					} else {
						// urlにuseridとgroupidが格納されていないのでメインページにリダイレクト
						response.sendRedirect("/ActionLogger");
					}
				} else if (view.equals("activities")) {
					if (search != null) {
						String date = (String) request.getParameter("date");
						String place = (String) request.getParameter("place");
						String order = (String) request.getParameter("order");
						actlist = actionDAO.searchAllGet(userid, date, place, order);
					}else {
						String actId = (String) request.getParameter("deletebtn");
						actionDAO.delete(actId);
						actlist = actionDAO.allGet((String) (session.getAttribute("userid")));
					}
					
				} else if (view.equals("getmember")) {
					String gpId = (String) request.getParameter("id");
					// DBから管理しているグループのメンバーリストを取得
					List<Member> mbList = groupDAO.admGpGet((String) (session.getAttribute("userid")), gpId);
					if (mbList == null) {
						// エラーが出たのでメインページにリダイレクト
						response.sendRedirect("/ActionLogger");
					} else {
						session.setAttribute("mbList", mbList);
					}
				}else if(view.equals("profile")) {
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
					session.setAttribute("validationKey", validationKey);
				}else if(view.equals("password")) {
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
					session.setAttribute("validationKey", validationKey);
				}
			}
			// DBから活動記録を取得
			List<Action> actltyList = actionDAO.ltyGet((String) (session.getAttribute("userid")));
			// DBから所属中 & 管理中グループを取得
			List<Group> gplist = groupDAO.allGet((String) (session.getAttribute("userid")));
			List<Group> admGpList = groupDAO.admGroupGet((String) (session.getAttribute("userid")));

			session.setAttribute("actList", actlist);
			session.setAttribute("actLtyList", actltyList);
			session.setAttribute("gpList", gplist);
			session.setAttribute("admGpList", admGpList);

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
