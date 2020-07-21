package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import model.Group;

//DB上のactionテーブルに対応するDAO
public class GroupDAO {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/actionlogger";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";
	//全ての所属グループ取得
		public List<Group> allGet(String userId) {
			List<Group> list = new ArrayList<>();
			HashMap<String, List<String>> hm = new HashMap<String, List<String>>();
			// データベース接続
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

				// SELECT文の準備
				String sql = "SELECT * FROM MGT_GROUP INNER JOIN BELONG ON BELONG_GROUPID = GROUP_ID AND BELONG_USERID  = ?;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, userId);
				// SELECTを実行
				ResultSet rs = pStmt.executeQuery();
				// SELECT文の結果をactionに格納
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
	//指定のユーザーが管理しているグループを取得
		public List<Group> admGroupGet(String userId) {
			List<Group> list = new ArrayList<>();
			HashMap<String, List<String>> hm = new HashMap<String, List<String>>();
			// データベース接続
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

				// SELECT文の準備
				String sql = "SELECT * FROM MGT_GROUP INNER JOIN BELONG ON BELONG_GROUPID = GROUP_ID AND BELONG_USERID = ? AND ADM = TRUE;";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, userId);
				// SELECTを実行
				ResultSet rs = pStmt.executeQuery();
				// SELECT文の結果をactionに格納
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
	public boolean creat(Group gp) {
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			Statement st = conn.createStatement();
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
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
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
