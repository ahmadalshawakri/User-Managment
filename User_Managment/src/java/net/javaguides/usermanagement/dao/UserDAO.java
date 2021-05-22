package net.javaguides.usermanagement.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.User;

/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Ramesh Fadatare
 *
 */
public class UserDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/com?useUnicode=true&characterEncoding=utf8&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "1234";

	private static final String INSERT_USERS_SQL = "INSERT INTO admin" + "  (name, email, country,initial_deposit) VALUES "
			+ " (?,?,?,?);";

	private static final String SELECT_USER_BY_ID = "select id,name,email,country,initial_deposit from admin where id =?";
	private static final String SELECT_ALL_USERS = "select * from admin";
	private static final String DELETE_USERS_SQL = "delete from admin where id = ?;";
	private static final String UPDATE_USERS_SQL = "update admin set name = ?,email= ?, country =?,initial_deposit=?,where id = ?;";
        private static final String ADD_INITAIL_SQL="select initial_deposit from admin where email=?;";
	public UserDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
                System.out.println("ssssssssssssss"+ e);

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
                    System.err.println("rrrrrrrrrrrrrrrrrr" + e);
			e.printStackTrace();
		}
		return connection;
	}

	public void insertUser(User user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
		PreparedStatement pr = connection.prepareStatement(INSERT_USERS_SQL)) {
			pr.setString(1, user.getName());
			pr.setString(2, user.getEmail());
			pr.setString(3, user.getCountry());
                        pr.setString(4, user.getInitial_deposit());
			System.out.println(pr);
			pr.executeUpdate();
                        System.out.println("EXECUTED");
		} catch (SQLException e) {
                    System.err.println("fffffffffffff" + e);
			e.printStackTrace();
		}
	}

	public User selectUser(int id) {
		User user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement pr = connection.prepareStatement(SELECT_USER_BY_ID);) {
			pr.setInt(1, id);
			System.out.println(pr);
			// Step 3: Execute the query or update query
			ResultSet rs = pr.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
                                String initial_deposit=rs.getString("initial_deposit");
				user = new User(id, name, email, country,initial_deposit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<User> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<User> users = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
                                String initial_deposit=rs.getString("initial_deposit");
				users.add(new User(id, name, email, country,initial_deposit));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(User user) throws SQLException {
		            boolean rowUpdated = false;

                try{

                Connection connection = getConnection();
		    PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getCountry());
                        statement.setString(4, user.getInitial_deposit());
			statement.setInt(5, user.getId());

			rowUpdated = statement.executeUpdate() > 0;

                }catch(Exception e){
                    System.out.println("vvvvvvvvvvvvvvv" + e);
                }
		return rowUpdated;
	}
        }

	