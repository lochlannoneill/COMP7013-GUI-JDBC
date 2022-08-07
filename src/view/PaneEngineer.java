package view;

import java.time.LocalDate;
import java.util.jar.Attributes.Name;

import controller.ControllerEngineer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
import model.Engineer;

public class PaneEngineer extends Tab {
	private final int TABLEWIDTH = 800;
	private final int TABLEHEIGHT = 250;
	private final int BUTTONWIDTH = 156;
	private final int BUTTONHEIGHT = 32;
	private Text actionResponse = new Text();

	@SuppressWarnings("unchecked")
	public PaneEngineer() {

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

		// ---------------------------------------------------------------------------------
		// ----------------------------------- Table
		// ---------------------------------------------------------------------------------
		// data obtained to put into gui table
		ObservableList<Engineer> observablelist = FXCollections.observableArrayList(ControllerEngineer.getEngineerList());

		// --NOTE--setCellValueFactory() uses the 'set' methods for the given class
		TableColumn<Engineer, Name> columnName = new TableColumn<>("Name");
		columnName.setCellValueFactory(new PropertyValueFactory<Engineer, Name>("name"));
		TableColumn<Engineer, String> columnId = new TableColumn<>("Id");
		columnId.setCellValueFactory(new PropertyValueFactory<Engineer, String>("id"));
		TableColumn<Engineer, LocalDate> columnStartDate = new TableColumn<>("StartDate");
		columnStartDate.setCellValueFactory(new PropertyValueFactory<Engineer, LocalDate>("startDate"));
		TableColumn<Engineer, Double> columnSalary = new TableColumn<>("Salary");
		columnSalary.setCellValueFactory(new PropertyValueFactory<Engineer, Double>("salary"));
		TableColumn<Engineer, String> columnEmail = new TableColumn<>("Email");
		columnEmail.setCellValueFactory(new PropertyValueFactory<Engineer, String>("email"));

		TableView<Engineer> table = new TableView<Engineer>();
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
				}
				
				// ! how do i target the empty datepicker???
				// else if (datepickerStartDate.getValue() == null) {
				// 	actionResponse.setText("Start date required");
				// } 
				
				else if (textfieldSalary.getText().isEmpty()) {
					actionResponse.setText("Salary required");
				} else {

					ControllerEngineer.addEngineer(
						textfieldFirst.getText(),
						textfieldMiddle.getText(),
						textfieldLast.getText(),
						datepickerStartDate.getValue(),
						Double.parseDouble(textfieldSalary.getText())
					);

					// update gui table using updated database
					ObservableList<Engineer> observablelist = FXCollections
							.observableArrayList(ControllerEngineer.getEngineerList());
					table.setItems(observablelist);

					// display success to user and console
					actionResponse.setFill(Color.GREEN);
					actionResponse.setText("'" + textfieldFirst.getText() + "' added to the engineer list");

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
				Engineer selectedEngineer = (Engineer) table.getSelectionModel().getSelectedItem();

				if (selectedTableItem == null) {
					actionResponse.setFill(Color.RED);
					actionResponse.setText("You must select a record from the table to delete");
				} else {
					// remove entity from database using the controller
					ControllerEngineer.removeEngineer(selectedEngineer);

					// update gui table using updated database
					table.getItems().remove(selectedTableItem);

					// display success to user and console
					actionResponse.setFill(Color.GREEN);
					actionResponse.setText("'" + selectedEngineer.getName() + "' removed from database");
				}
			}
		}); // end buttonRemove.setOnAction()

		Button buttonRaise = new Button("Give 7.5% Raise");
		buttonRaise.setMinWidth(BUTTONWIDTH);
		buttonRaise.setMinHeight(BUTTONHEIGHT);
		buttonRaise.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// user mouse click determines the selected item of the table
				Object selectedTableItem = table.getSelectionModel().getSelectedItem();

				// cast selected table item into a 'Player' object
				Engineer selectedEngineer = (Engineer) table.getSelectionModel().getSelectedItem();

				if (selectedTableItem == null) {
					actionResponse.setFill(Color.RED);
					actionResponse.setText("You must select an employee to give a raise");
				} else {
					ControllerEngineer.giveRaise(selectedEngineer);
					// update gui table using updated database
					ObservableList<Engineer> observablelist = FXCollections.observableArrayList(ControllerEngineer.getEngineerList());
					table.setItems(observablelist);

					// display success to user and console
					actionResponse.setFill(Color.GREEN);
					actionResponse.setText("'" + (selectedEngineer.getId() + "' salary updated to " + selectedEngineer.getSalary()));
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
		gridpaneInput.setMinWidth(400);
		gridpaneInput.setMaxWidth(400);
		gridpaneInput.setVgap(8);
		gridpaneInput.setHgap(32);

		HBox hboxButtonsActivities = new HBox();
		hboxButtonsActivities.getChildren().addAll(buttonAdd, buttonRemove, buttonRaise);
		hboxButtonsActivities.setSpacing(16);
		hboxButtonsActivities.setPadding(new Insets(16, 0, 16, 0));
		hboxButtonsActivities.setAlignment(Pos.CENTER);

		setText("Engineers");
		VBox vboxLayout = new VBox(gridpaneInput, hboxButtonsActivities, table, actionResponse);
		setContent(vboxLayout);

	}
}