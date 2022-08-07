package view;

import java.time.LocalDate;
import java.util.jar.Attributes.Name;

import controller.ControllerIntern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.Intern;
import controller.ControllerEngineer;

public class PaneIntern extends Tab {
	private final int TABLEWIDTH = 800;
	private final int TABLEHEIGHT = 250;
	private final int BUTTONWIDTH = 156;
	private final int BUTTONHEIGHT = 32;
	private Text actionResponse = new Text();

	@SuppressWarnings("unchecked")
	public PaneIntern() {

		// ---------------------------------------------------------------------------------
		// ------------------------- Labels and input
		// ---------------------------------------------------------------------------------
		Label labelFirstName = new Label("First Name:");
		TextField textfieldFirst = new TextField();
		textfieldFirst.setPromptText("First Name");

		Label labelMiddleName = new Label("Middle Name:");
		TextField textfieldMiddle = new TextField();
		textfieldMiddle.setPromptText("Middle Name");

		Label labelLastName = new Label("Last Name:");
		TextField textfieldLast = new TextField();
		textfieldLast.setPromptText("Last Name");

		Label labelStartDate = new Label("Start Date");
		DatePicker datepickerStartDate = new DatePicker();
		datepickerStartDate.setPromptText("Start Date");
		datepickerStartDate.getEditor().setDisable(true);

		Label labelSalary = new Label("Salary:");
		TextField textfieldSalary = new TextField();
		textfieldSalary.setPromptText("Salary (euro)");

        Label labelUniversity = new Label("University:");
		TextField textfieldUniversity = new TextField();
		textfieldUniversity.setPromptText("University");

        Label labelMentor = new Label("Mentor:");
        ComboBox comboboxMentor = new ComboBox();
		comboboxMentor.getItems().addAll(ControllerEngineer.getEngineerList());

		// ---------------------------------------------------------------------------------
		// ----------------------------------- Table
		// ---------------------------------------------------------------------------------
		// data obtained to put into gui table
		ObservableList<Intern> observablelist = FXCollections.observableArrayList(ControllerIntern.getInternList());

		// --NOTE--setCellValueFactory() uses the 'set' methods for the given class
		TableColumn<Intern, Name> columnName = new TableColumn<>("Name");
		columnName.setCellValueFactory(new PropertyValueFactory<Intern, Name>("name"));
		TableColumn<Intern, String> columnId = new TableColumn<>("Id");
		columnId.setCellValueFactory(new PropertyValueFactory<Intern, String>("id"));
		TableColumn<Intern, LocalDate> columnStartDate = new TableColumn<>("StartDate");
		columnStartDate.setCellValueFactory(new PropertyValueFactory<Intern, LocalDate>("startDate"));
		TableColumn<Intern, Double> columnSalary = new TableColumn<>("Salary");
		columnSalary.setCellValueFactory(new PropertyValueFactory<Intern, Double>("salary"));
		TableColumn<Intern, String> columnEmail = new TableColumn<>("Email");
		columnEmail.setCellValueFactory(new PropertyValueFactory<Intern, String>("Email"));
        TableColumn<Intern, String> columnUniversity = new TableColumn<>("University");
		columnUniversity.setCellValueFactory(new PropertyValueFactory<Intern, String>("university"));
        TableColumn<Intern, String> columnMentor = new TableColumn<>("Mentor");
		columnMentor.setCellValueFactory(new PropertyValueFactory<Intern, String>("mentor"));

		TableView<Intern> table = new TableView<Intern>();
		table.setMinWidth(TABLEWIDTH);
		table.setMaxHeight(TABLEHEIGHT);
		table.setMaxWidth(Region.USE_PREF_SIZE); // dynamic max width
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY); // sum of column widths equal table width
		table.getColumns().addAll(columnName, columnId, columnEmail, columnSalary, columnStartDate);
		table.setItems(observablelist);

		// ---------------------------------------------------------------------------------
		// -------------------------------- ActivityButtons
		// ---------------------------------------------------------------------------------
		Button buttonAdd = new Button("Add");
		buttonAdd.setMinWidth(BUTTONWIDTH);
		buttonAdd.setMinHeight(BUTTONHEIGHT);
		buttonAdd.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				actionResponse.setFill(Color.RED);
				if (textfieldFirst.getText().isEmpty()) {
					actionResponse.setText("First name required");
				} else if (textfieldMiddle.getText().isEmpty()) {
					actionResponse.setText("Middle name required");
				} else if (textfieldLast.getText().isEmpty()) {
					actionResponse.setText("Last name required");
				} else if (textfieldSalary.getText().isEmpty()) {
					actionResponse.setText("Salary required");
				} else {

					Intern.addIntern(
						textfieldFirst.getText(),
						textfieldMiddle.getText(),
						textfieldLast.getText(),
						datepickerStartDate.getValue(),
						Double.parseDouble(textfieldSalary.getText()),
						textfieldUniversity.getText(),
                        comboboxMentor.getId()
					);

					// update gui table using updated database
					ObservableList<Intern> observablelist = FXCollections
							.observableArrayList(ControllerIntern.getInternList());
					table.setItems(observablelist);

					// display success to user and console
					actionResponse.setFill(Color.GREEN);
					actionResponse.setText("'" + textfieldFirst.getText() + "' added to the intern list");

					// clear text inputs
					textfieldFirst.clear();
					textfieldMiddle.clear();
					textfieldLast.clear();
					datepickerStartDate.getEditor().clear();
					textfieldSalary.clear();
				}
			}
		}); // end buttonRemove.setOnAction()

		Button buttonRemove = new Button("Remove");
		buttonRemove.setMinWidth(BUTTONWIDTH);
		buttonRemove.setMinHeight(BUTTONHEIGHT);
		buttonRemove.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// user mouse click determines the selected item of the table
				Object selectedTableItem = table.getSelectionModel().getSelectedItem();

				// cast selected table item into a 'Player' object
				Intern selectedIntern = (Intern) table.getSelectionModel().getSelectedItem();

				if (selectedTableItem == null) {
					actionResponse.setFill(Color.RED);
					actionResponse.setText("You must select a record from the table to delete");
				} else {
					// remove entity from database using the controller
					ControllerIntern.removeIntern(selectedIntern);

					// update gui table using updated database
					table.getItems().remove(selectedTableItem);

					// display success to user and console
					actionResponse.setFill(Color.GREEN);
					actionResponse.setText("'" + selectedIntern.getName() + "' removed from database");
				}
			}
		}); // end buttonRemove.setOnAction()

		Button buttonRaise = new Button("Give 2% Raise");
		buttonRaise.setMinWidth(BUTTONWIDTH);
		buttonRaise.setMinHeight(BUTTONHEIGHT);
		buttonRaise.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// user mouse click determines the selected item of the table
				Object selectedTableItem = table.getSelectionModel().getSelectedItem();

				// cast selected table item into a 'Player' object
				Intern selectedIntern = (Intern) table.getSelectionModel().getSelectedItem();

				if (selectedTableItem == null) {
					actionResponse.setFill(Color.RED);
					actionResponse.setText("You must select an employee to give a raise");
				} else {
					ControllerIntern.giveRaise(selectedIntern);
					// update gui table using updated database
					ObservableList<Intern> observablelist = FXCollections.observableArrayList(ControllerIntern.getInternList());
					table.setItems(observablelist);

					// display success to user and console
					actionResponse.setFill(Color.GREEN);
					actionResponse.setText("'" + (selectedIntern.getId() + "' salary updated to " + selectedIntern.getSalary()));
				}
			}
		}); // end buttonRemove.setOnAction()

		// ---------------------------------------------------------------------------------
		// ------------------------- Layout
		// ---------------------------------------------------------------------------------
		GridPane gridpaneInput = new GridPane();
		gridpaneInput.addRow(0, labelFirstName, textfieldFirst);
		gridpaneInput.addRow(1, labelMiddleName, textfieldMiddle);
		gridpaneInput.addRow(2, labelLastName, textfieldLast);
		gridpaneInput.addRow(3, labelStartDate, datepickerStartDate);
		gridpaneInput.addRow(4, labelSalary, textfieldSalary);
        gridpaneInput.addRow(5, labelUniversity, textfieldUniversity);
        gridpaneInput.addRow(6, labelMentor, comboboxMentor);
		gridpaneInput.setMinWidth(400);
		gridpaneInput.setMaxWidth(400);
		gridpaneInput.setVgap(8);
		gridpaneInput.setHgap(32);

		HBox hboxButtonsActivities = new HBox();
		hboxButtonsActivities.getChildren().addAll(buttonAdd, buttonRemove, buttonRaise);
		hboxButtonsActivities.setSpacing(16);
		hboxButtonsActivities.setPadding(new Insets(16, 0, 16, 0));

		setText("Interns");
		VBox vboxLayout = new VBox(gridpaneInput, hboxButtonsActivities, table, actionResponse);
		setContent(vboxLayout);

	}
}