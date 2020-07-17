package dao;

//sql
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//action
import model.Action;

//DB上のactionテーブルに対応するDAO
public class ActionDAO {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/actionlogger";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	public boolean set(Action act) {
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select count(*) cnt from action");
			rs.next();
			int count = rs.getInt("cnt");
			count++;
			String id = String.valueOf(count);
			act.setActionId(id);
			// 現在時刻を取得
			LocalDateTime date = LocalDateTime.now();
			DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String fdate = dtformat.format(date);
			act.setActionADT(fdate);
			// INSERT文の準備(idは自動連番なので指定しなくてよい）
			String sql = "INSERT INTO action "
					+ "( action_id, action_add_datetime , action_date, action_start_time, action_end_time, action_place, action_reason, action_remarks, action_userid) "
					+ "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT文中の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, act.getActionId()); // id
			pStmt.setString(2, act.getActionADT());// 追加時の時間
			pStmt.setString(3, act.getActionDate());// 日付
			pStmt.setString(4, act.getActionSTm());// 開始時間
			pStmt.setString(5, act.getActionETm());// 終了時間
			pStmt.setString(6, act.getActionPlace());// 場所
			pStmt.setString(7, act.getActionReason());// 理由
			pStmt.setString(8, act.getActionRemarks());// 備考
			pStmt.setString(9, act.getActionUserId());// ユーザーID
			// INSERT文を実行
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
