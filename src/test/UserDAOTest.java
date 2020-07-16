package test;

import dao.UserDAO;
import model.User;

public class UserDAOTest {
	public static void saveUser() {
		UserDAO userDAO = new UserDAO();
		User user = new User();
		user.setUserId("user001");
		user.setPwdHash("ju78iklo");
		user.setName("松山太郎");
		user.setAddress("愛媛県松山市柳井町3-3-31");
		user.setTel("089-931-8555");
		user.setEmail("taro@example.com");

		if( userDAO.save(user) ) {
			System.out.println("success");
		}
		else
		{
			System.out.println("failed");
		}
	}
	public static void getUser() {
		UserDAO userDAO = new UserDAO();
		User user = userDAO.get("y-omasa");
	}
	
	public static void main(String[] args) {
		saveUser();
		getUser();

	}

}
