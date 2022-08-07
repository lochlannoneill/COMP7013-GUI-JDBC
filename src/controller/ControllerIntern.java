package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import model.Intern;

public class ControllerIntern {
	private static final String DATABASE = "staff_database";
    private static final String TABLE = "table_interns";
    private static Connection connection = DatabaseConnection.getConnection(DATABASE);

    public static void addIntern(String first, String middle, String last, LocalDate startDate, double salary, String university, String mentorId) {
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
					+ "` (`id`, `first`, `middle`, `last`, `startDate`, `salary`, `university`, `mentorId`) VALUES ('" 
					+ id + "','"
					+ (first.substring(0, 1).toUpperCase() + first.substring(1)) + "','"
					+ (middle.substring(0, 1).toUpperCase() + middle.substring(1)) + "','"
					+ (last.substring(0, 1).toUpperCase() + last.substring(1)) + "','"
					+ startDate + "','"
					+ salary + "','"
					+ university + "','"
					+ mentorId + "')";
			statement.executeUpdate(query);
            
			statement.close();
			System.out.println(first + " added to " + TABLE + " table");

        } catch
            (SQLException SQLException) {
                SQLException.printStackTrace();
                // close();
            }
    }

    public static ArrayList<Intern> getInternList() {
		ArrayList<Intern> internList = new ArrayList<Intern>();
		try {
			String query = "SELECT * FROM " + TABLE; // ! i think this is causing problem with current_mentor since it might be only selects plain text instead of object
			PreparedStatement preparedstatement = connection.prepareStatement(query);
			ResultSet resultset = preparedstatement.executeQuery();

			while (resultset.next()) {
				// create variables to be used as arguments for object creation
				String currentId = resultset.getString("id");
				String currentFirst = resultset.getString("first");
				String currentMiddle = resultset.getString("middle");
				String currentLast = resultset.getString("last");
				LocalDate currentStartDate = resultset.getDate("startDate").toLocalDate();
				double currentSalary = resultset.getDouble("salary");
				String currentUniversity = resultset.getString("university");
                String currentMentorId = resultset.getString("mentorId");

				// create a new player object for each database query result
				Intern currentIntern = new Intern(
					currentId,
					currentFirst,
					currentMiddle,
					currentLast,
					currentStartDate,
					currentSalary,
					currentUniversity,
					currentMentorId
				);
				internList.add(currentIntern);
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return internList;
	} // end getPlayers()

    public static void removeIntern(Intern intern) {
        try {
			String query = "DELETE FROM  " + TABLE + " WHERE `id` = '" + intern.getId() + "'";
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
			System.out.println(intern.getId() + " from " + TABLE + " table");
		} 
        catch (SQLException sqlException) {
			sqlException.printStackTrace();
			// close();
		}
    }

	public static void giveRaise(Intern intern) {
		try {
			intern.giveRaise();
			String query = "UPDATE `" + TABLE + "` SET `salary` = '" + intern.getSalary() + "' WHERE (`id` = '" + intern.getId() + "')";
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
			System.out.println(intern.getId() + " salary updated to " + intern.getSalary() + " in " + TABLE);
		} 
        catch (SQLException sqlException) {
			sqlException.printStackTrace();
			// close();
		}
	}



}