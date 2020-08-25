package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Group;
import model.Member;

//DB上のactionテーブルに対応するDAO
public class GroupDAO {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/actionlogger";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	//全ての所属グループ取得
	public List<Group> allGet(String userId) {
		List<Group> list = new ArrayList<>();
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql = "SELECT * FROM MGT_GROUP INNER JOIN BELONG ON BELONG_GROUPID = GROUP_ID AND BELONG_USERID  = ? AND LEAVE_DATETIME IS NULL;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userId);
			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();
			// SELECT文の結果をgroupに格納
			while (rs.next()) {
				Group gp = new Group();
				gp.setGroupId(rs.getString("GROUP_ID"));
				gp.setGroupName(rs.getString("GROUP_NAME"));
				gp.setGroupUserId(rs.getString("BELONG_USERID"));
				gp.setGroupAdm(rs.getBoolean("ADM"));
				//配列に格納
				list.add(gp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	//指定ユーザーのグループ取得
	public List<Group> groupGet(String userId, boolean ADM) {
		List<Group> list = new ArrayList<>();
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql;
			//管理グループか参加グループの判定
			if (ADM) {
				sql = "SELECT * FROM MGT_GROUP INNER JOIN BELONG ON BELONG_GROUPID = GROUP_ID AND BELONG_USERID = ? AND ADM = TRUE AND LEAVE_DATETIME IS NULL;";
			} else {
				sql = "SELECT * FROM MGT_GROUP INNER JOIN BELONG ON BELONG_GROUPID = GROUP_ID AND BELONG_USERID = ? AND ADM = FAlSE AND LEAVE_DATETIME IS NULL;";
			}
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userId);
			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();
			// SELECT文の結果をgroupに格納
			while (rs.next()) {
				Group gp = new Group();
				gp.setGroupId(rs.getString("GROUP_ID"));
				gp.setGroupName(rs.getString("GROUP_NAME"));
				gp.setGroupUserId(rs.getString("BELONG_USERID"));
				gp.setGroupAdm(rs.getBoolean("ADM"));
				//配列に格納
				list.add(gp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	//管理者用 グループ内の情報取得
	public List<Member> admGpGet(String userId, String groupId) {
		List<Member> list = new ArrayList<>();
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			//実行しているユーザーが管理者判定
			// SELECT文の準備
			String sql = "SELECT count(*) cnt FROM MGT_GROUP INNER JOIN BELONG ON BELONG_GROUPID = GROUP_ID AND BELONG_USERID = ? AND ADM = TRUE AND GROUP_ID = ? AND LEAVE_DATETIME IS NULL;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userId);
			pStmt.setString(2, groupId);
			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();
			rs.next();
			if (rs.getInt("cnt") < 1) {
				return null;
			}
			//所属しているユーザー情報の取得
			// SELECT文の準備
			String user_sql = "SELECT * FROM MGT_GROUP INNER JOIN BELONG ON BELONG_GROUPID = GROUP_ID INNER JOIN USER ON BELONG_USERID = USERID AND GROUP_ID = ?;";
			PreparedStatement user_pStmt = conn.prepareStatement(user_sql);
			user_pStmt.setString(1, groupId);
			// SELECTを実行
			ResultSet user_rs = user_pStmt.executeQuery();
			// SELECT文の結果をメンバーに格納
			while (user_rs.next()) {
				Member mb = new Member();
				mb.setGpName(user_rs.getString("GROUP_NAME"));
				mb.setGpId(user_rs.getString("GROUP_ID"));
				mb.setUserId(user_rs.getString("USERID"));
				mb.setName(user_rs.getString("NAME"));
				mb.setAddress(user_rs.getString("ADDRESS"));
				mb.setTel(user_rs.getString("TEL"));
				mb.setEmail(user_rs.getString("EMAIL"));
				if (user_rs.getBoolean("ADM")) {
					mb.setAdm("管理者");
				} else {
					mb.setAdm("メンバー");
				}
				//配列に格納
				list.add(mb);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
	//グループ退出
	public boolean leave(String userId,String groupId) {
		// データベース接続
				try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
					// UPDATE文の準備
					String sql = "UPDATE BELONG SET LEAVE_DATETIME = ? WHERE BELONG_USERID = ? AND BELONG_GROUPID = ?";
					PreparedStatement pStmt = conn.prepareStatement(sql);
					// 現在時刻を取得
					LocalDateTime date = LocalDateTime.now();
					DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					String fdate = dtformat.format(date);
					// UPDATE文中の「?」に使用する値を設定しSQLを完成
					pStmt.setString(1, fdate);
					pStmt.setString(2, userId);
					pStmt.setString(3, groupId);
					// UPDATE文を実行
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
	//グループ参加
	public boolean join(String userId, String groupId) {
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			//グループが存在するかのチェック
			String sql = ("SELECT count(*) cnt FROM MGT_GROUP where group_id = ?");
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, groupId);
			ResultSet rs = pStmt.executeQuery();
			rs.next();
			if (rs.getInt("cnt") < 1) {
				return false;
			}
			//所属テーブルにそのユーザーが存在しないのかチェック
			String B_sql = ("SELECT count(*) cnt FROM BELONG where BELONG_USERID = ? AND BELONG_GROUPID = ?");
			PreparedStatement B_pStmt = conn.prepareStatement(B_sql);
			B_pStmt.setString(1, userId);
			B_pStmt.setString(2, groupId);
			ResultSet B_rs = B_pStmt.executeQuery();
			B_rs.next();
			if (B_rs.getInt("cnt") > 0) {
				return false;
			}
			//所属テーブルにグループを作成したユーザーの情報を作成
			// INSERT文の準備(idは自動連番なので指定しなくてよい）
			sql = "INSERT INTO BELONG "
					+ "( BELONG_GROUPID, BELONG_USERID, ADM) "
					+ "VALUES ( ?, ?, ?)";
			pStmt = conn.prepareStatement(sql);
			// INSERT文中の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, groupId); // グループid
			pStmt.setString(2, userId);// ユーザーid
			pStmt.setBoolean(3, false);// 一般として登録
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
	//グループ作成
	public boolean creat(Group gp) {
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			while (true) {
				//GroupID作成 & 重複チェック
				String id_tmp = creatId();
				String sql = ("select count(*) cnt from MGT_GROUP where group_id = ?");
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, id_tmp);
				ResultSet rs = pStmt.executeQuery();
				rs.next();
				if (rs.getInt("cnt") < 1) {
					gp.setGroupId(id_tmp);
					break;
				}
			}
			//MGT_GROUPテーブルにグループを作成
			// INSERT文の準備(idは自動連番なので指定しなくてよい）
			String sql = "INSERT INTO MGT_GROUP "
					+ "( group_id, group_name) "
					+ "VALUES ( ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT文中の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, gp.getGroupId()); // id
			pStmt.setString(2, gp.getGroupName());// グループ名前
			// INSERT文を実行
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
			//所属テーブルにグループを作成したユーザーの情報を作成
			// INSERT文の準備(idは自動連番なので指定しなくてよい）
			sql = "INSERT INTO BELONG "
					+ "( BELONG_GROUPID, BELONG_USERID, ADM) "
					+ "VALUES ( ?, ?, ?)";
			pStmt = conn.prepareStatement(sql);
			// INSERT文中の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, gp.getGroupId()); // グループid
			pStmt.setString(2, gp.getGroupUserId());// ユーザーid
			pStmt.setBoolean(3, gp.isGroupAdm());// 管理者判定
			// INSERT文を実行
			result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private String creatId() {
		String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 18) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}
}
