package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GroupDAO;
import model.Group;

/**
 * Servlet implementation class CreatGroupConfirm
 */
@WebServlet("/creatgroupconfirm")
public class CreatGroupConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreatGroupConfirm() {
        super();
    }
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

			// セッションスコープに保存していた、DB登録前のグループ情報を取得
			Group gp = (Group) session.getAttribute("groupToAdd");
			GroupDAO groupDAO = new GroupDAO();
			groupDAO.creat(gp); // DBに保存
			// TODO 主キーの重複で保存できなかった場合の処理を追加


		// DBへの保存が成功したものとして、メインページに遷移
		resp.sendRedirect("/ActionLogger/");
	}

}
