package view;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PaneHome extends Tab {
	
	public PaneHome() {
		Label labelName = new Label("Student name:");
        labelName.setStyle("-fx-font: 16 Verdana;");
        Text textName = new Text("Lochlann O Neill");
        textName.setStyle("-fx-font: 16 Verdana;");
		textName.setFill(Color.DARKGREY);

		Label labelId = new Label("Student ID:");
        labelId.setStyle("-fx-font: 16 Verdana;");
        Text textId = new Text("R00175741");
        textId.setStyle("-fx-font: 16 Verdana;");
		textId.setFill(Color.DARKGREY);

		Label labelAssignment = new Label("Assignment:");
        labelAssignment.setStyle("-fx-font: 16 Verdana;");
        Text textAssignment = new Text(" OOP - Java FX - Staff Directory");
		textAssignment.setFont(Font.font ("Verdana"));
        textAssignment.setStyle("-fx-font: 16 Verdana;");
		textAssignment.setFill(Color.DARKGREY);
		
		GridPane gridpane = new GridPane();
		gridpane.addRow(0, labelName, textName);
		gridpane.addRow(1,  labelId, textId);
		gridpane.addRow(2,  labelAssignment, textAssignment);
		gridpane.setMinWidth(300);
		gridpane.setVgap(8);
		gridpane.setHgap(32);



        // Label labelOutline = new Label("Project Outline - Staff Directory");
		
		// CheckBox checkBox0 = new CheckBox("•	You should have a base Staff object type that has a name (string), employment start date (date) and salary (numeric). ");
        // checkBox0.setSelected(true);
        
        // CheckBox checkBox1 = new CheckBox("•	Administration Staff objects also have a phone number (numeric)");
        // checkBox1.setSelected(true);
        
        // CheckBox checkBox2 = new CheckBox("•	Engineering Staff objects also have an email address (string with a specific format)");
        // checkBox2.setSelected(true);
        
        // CheckBox checkBox3 = new CheckBox("•	Student Intern Staff objects have the name of their university (string) as an attribute and they have a mentor (Engineering Staff object) from the engineering team");
        // checkBox3.setSelected(true);
        
        // CheckBox checkBox4 = new CheckBox("•	Administration Staff get a 5% salary increase each time they get a raise");
        // checkBox4.setSelected(true);
        
        // CheckBox checkBox5 = new CheckBox("•	Engineering Staff get a 7.5% salary increase each time they get a raise");
        // checkBox5.setSelected(true);
        
        // CheckBox checkBox6 = new CheckBox("•	Intern Staff get a 2% salary increase each time they get a raise");
        // checkBox6.setSelected(true);




        // Label labelrequirements = new Label("Label Requirements");
        
        // CheckBox checkBox7 = new CheckBox("1.   Use a GUI to add a new Staff object to the database. The user will need to select the type of Staff member before entering in their details. Your GUI should request the information that is associated with the selected staff type. ");
        // checkBox7.setSelected(true);
        
        // CheckBox checkBox8 = new CheckBox("2.   Use the GUI to remove an existing Staff member from the database");
        // checkBox8.setSelected(true);
        
        // CheckBox checkBox9 = new CheckBox("3.   Select a Staff member in the database and edit their details. Salary cannot be edited in this way");
        // checkBox9.setSelected(false);
        
        // CheckBox checkBox10 = new CheckBox("4.  The application should have a way of selecting an individual Staff member and giving them a raise based on their type. ");
        // checkBox10.setSelected(true);
        
        // CheckBox checkBox11 = new CheckBox("5.  Search for a particular Staff member in the database and display that person’s information ");
        // checkBox11.setSelected(true);
        
        // CheckBox checkBox12 = new CheckBox("6.  Display all of the Staff members who are paid more than €50,000 per year");
        // checkBox12.setSelected(true);
        
        // CheckBox checkBox13 = new CheckBox("7.  Display all of the Staff members sorted by Name, and the user has the option to display all of the Staff members sorted by EmployeeStartDate");
        // checkBox13.setSelected(true);
        
        // CheckBox checkBox14 = new CheckBox("8.  Quit ");
        // checkBox14.setSelected(true);
        



        // Label labeladditional = new Label("Label Requirements");

        // CheckBox checkBox15 = new CheckBox("1.	Consider how best to organize your GUI application. Should you separate functionality into different tabs? Should the GUI objects shown differ based on the option the user has selected? You will need to be very considered in your GUI design to ensure that the application is easy to use. ");
        // checkBox15.setSelected(true);
        
        // CheckBox checkBox16 = new CheckBox("2.	Use objects in the application, not just Strings. The methods of the class which connects to the database should take objects as parameters, where appropriate.  ");
        // checkBox16.setSelected(true);
        
        // CheckBox checkBox17 = new CheckBox("3.	Use the MVC pattern for this application and use a package structure to reflect the MVC pattern.");
        // checkBox17.setSelected(true);
        
        // CheckBox checkBox18 = new CheckBox("4.	Write Junit test cases (completely covering at least 3 classes) and a test-suite to test some elements of your code in your project.");
        // checkBox18.setSelected(false);
        
        // CheckBox checkBox19 = new CheckBox("5.	A good package structure; you should consider how your different classes can be separated into packages to improve code readability. Consider how this will help your MVC design pattern structure. ");
        // checkBox19.setSelected(true);
        
        // CheckBox checkBox20 = new CheckBox("6.	Document your project using JavaDoc, generate the JavaDoc documentation.  ");
        // checkBox20.setSelected(false);
        
        // CheckBox checkBox21 = new CheckBox("7.	15% of the marks for this assignment will be reserved for students who use the JPA API to interface with the database. This will require you to research, install, and utilize a Java API yourself. ");
        // checkBox21.setSelected(false);
        
        // CheckBox checkBox22 = new CheckBox("8.    Quit ");
        // checkBox22.setSelected(true);
        
        // VBox vboxCheckboxes = new VBox();
        // vboxCheckboxes.getChildren().addAll(
        //         labelOutline, checkBox0, checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6,
        //         labelrequirements, checkBox7, checkBox8, checkBox9, checkBox10, checkBox11, checkBox12, checkBox13, checkBox14, 
        //         labeladditional, checkBox15, checkBox16, checkBox17, checkBox18, checkBox19, checkBox20, checkBox21, checkBox22
        // );
        
		setText("Home");
		FlowPane flowpaneLayout = new FlowPane(Orientation.VERTICAL, gridpane);
		flowpaneLayout.setVgap(16);
        flowpaneLayout.setAlignment(Pos.CENTER);
        // hboxButtons.setSpacing(16);
		// hboxButtons.setPadding(new Insets(0, 0, 8, 8));
		setContent(flowpaneLayout);
	}
	
}