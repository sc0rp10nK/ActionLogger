package dao;

//sql
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//その他
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//action
import model.Action;

//DB上のactionテーブルに対応するDAO
public class ActionDAO {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/actionlogger";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	//全ての行動記録取得
	public List<Action> allGet(String userId) {
		List<Action> list = new ArrayList<>();
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql = "SELECT * FROM action WHERE ACTION_USERID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userId);

			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();
			// SELECT文の結果をactionに格納
			while (rs.next()) {
				Action act = new Action();
				act.setActionId(rs.getString("ACTION_ID"));
				act.setActionADT(rs.getString("ACTION_ADD_DATETIME"));
				act.setActionDate(rs.getString("ACTION_DATE"));
				act.setActionSTm(rs.getString("ACTION_START_TIME"));
				act.setActionETm(rs.getString("ACTION_END_TIME"));
				act.setActionPlace(rs.getString("ACTION_PLACE"));
				act.setActionReason(rs.getString("ACTION_REASON"));
				act.setActionRemarks(rs.getString("ACTION_REMARKS"));
				act.setActionUserId(rs.getString("ACTION_USERID"));
				//配列に格納
				list.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	//自分が管理してるユーザーの全ての行動記録取得
		public List<Action> allGet(String userId,String groupId,String myUserId) {
			List<Action> list = new ArrayList<>();
			// データベース接続
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
				//実行しているユーザーが管理者判定
				// SELECT文の準備
				String sql = "SELECT count(*) cnt FROM MGT_GROUP INNER JOIN BELONG ON BELONG_GROUPID = GROUP_ID AND BELONG_USERID = ? AND ADM = TRUE AND GROUP_ID = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, myUserId);
				pStmt.setString(2, groupId);
				// SELECTを実行
				ResultSet rs = pStmt.executeQuery();
				rs.next();
				if (rs.getInt("cnt") < 1) {
					return null;
				}
				// SELECT文の準備
				sql = "SELECT * FROM action WHERE ACTION_USERID = ?";
				pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, userId);

				// SELECTを実行
				rs = pStmt.executeQuery();
				// SELECT文の結果をactionに格納
				while (rs.next()) {
					Action act = new Action();
					act.setActionId(rs.getString("ACTION_ID"));
					act.setActionADT(rs.getString("ACTION_ADD_DATETIME"));
					act.setActionDate(rs.getString("ACTION_DATE"));
					act.setActionSTm(rs.getString("ACTION_START_TIME"));
					act.setActionETm(rs.getString("ACTION_END_TIME"));
					act.setActionPlace(rs.getString("ACTION_PLACE"));
					act.setActionReason(rs.getString("ACTION_REASON"));
					act.setActionRemarks(rs.getString("ACTION_REMARKS"));
					act.setActionUserId(rs.getString("ACTION_USERID"));
					//配列に格納
					list.add(act);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			return list;
		}
	//最近の行動記録取得
	public List<Action> ltyGet(String userId) {
		List<Action> list = new ArrayList<>();
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql = "SELECT * FROM action  WHERE ACTION_USERID = ? ORDER BY ACTION_ADD_DATETIME DESC LIMIT 5;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userId);

			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();
			// SELECT文の結果をactionに格納
			while (rs.next()) {
				Action act = new Action();
				act.setActionId(rs.getString("ACTION_ID"));
				act.setActionADT(rs.getString("ACTION_ADD_DATETIME"));
				act.setActionDate(rs.getString("ACTION_DATE"));
				act.setActionSTm(rs.getString("ACTION_START_TIME"));
				act.setActionETm(rs.getString("ACTION_END_TIME"));
				act.setActionPlace(rs.getString("ACTION_PLACE"));
				act.setActionReason(rs.getString("ACTION_REASON"));
				act.setActionRemarks(rs.getString("ACTION_REMARKS"));
				act.setActionUserId(rs.getString("ACTION_USERID"));
				//配列に格納
				list.add(act);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	public boolean set(Action act) {
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			Statement st = conn.createStatement();
			//Action_ID作成
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
