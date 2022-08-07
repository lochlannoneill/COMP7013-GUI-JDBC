package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import model.Admin;

// TODO - need to fix the primary key for record deletion. try to do ID properly in these functions
public class ControllerAdmin {
	private static final String DATABASE = "staff_database";
    private static final String TABLE = "table_admins";
    private static Connection connection = DatabaseConnection.getConnection(DATABASE);

    public static void addAdmin(String first, String middle, String last, LocalDate startDate, double salary, int phone) {
        try {
			Random rand = new Random();
			String id = String.valueOf(
				first.substring(0, 1).toLowerCase()
				+ middle.substring(0, 1).toLowerCase()
				+ last.toLowerCase()
				+ String.valueOf(rand.nextInt(99))
			);
			
            String query;
            Statement statement = connection.createStatement();
            query = "INSERT INTO `" + DATABASE + "`.`" + TABLE
					+ "` (`id`, `first`, `middle`, `last`, `startDate`, `salary`, `phone`) VALUES ('" 
					+ id + "','"
					+ (first.substring(0, 1).toUpperCase() + first.substring(1)) + "','"
					+ (middle.substring(0, 1).toUpperCase() + middle.substring(1)) + "','"
					+ (last.substring(0, 1).toUpperCase() + last.substring(1)) + "','"
					+ startDate + "','"
					+ salary + "','"
					+ phone + "')";
			statement.executeUpdate(query);
            
			statement.close();
			System.out.println(first + " added to " + TABLE + " table");

        } catch
            (SQLException SQLException) {
                SQLException.printStackTrace();
                // close();
            }
    }

    public static ArrayList<Admin> getAdminList() {
		ArrayList<Admin> adminList = new ArrayList<Admin>();
		try {
			String query = "SELECT * FROM " + TABLE;
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			ResultSet resultset = preparedstatement.executeQuery();

			while (resultset.next()) {
				// create variables to be used as arguments for object creation
				String current_id = resultset.getString("id");
				String current_first = resultset.getString("first");
				String current_middle = resultset.getString("middle");
				String current_last = resultset.getString("last");
				LocalDate current_startDate = resultset.getDate("startDate").toLocalDate();
				double current_salary = resultset.getDouble("salary");
				int current_phone = resultset.getInt("phone");

				// create a new player object for each database query result
				Admin currentAdmin = new Admin(
					current_id, current_first, current_middle, current_last, current_startDate, current_salary, current_phone
				);
				
				adminList.add(currentAdmin);
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return adminList;
	} // end getPlayers()

    public static void removeAdmin(Admin admin) {
        try {
			String query = "DELETE FROM  " + TABLE + " WHERE `id` = '" + admin.getId() + "'";
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
			System.out.println(admin.getId() + " from " + TABLE + " table");
		} 
        catch (SQLException sqlException) {
			sqlException.printStackTrace();
			// close();
		}
    }

	public static void giveRaise(Admin admin) {
		try {
			admin.giveRaise();
			String query = "UPDATE `" + TABLE + "` SET `salary` = '" + admin.getSalary() + "' WHERE (`id` = '" + admin.getId() + "')";
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
			System.out.println(admin.getId() + " salary updated to " + admin.getSalary() + " in " + TABLE);
		} 
        catch (SQLException sqlException) {
			sqlException.printStackTrace();
			// close();
		}
	}



}