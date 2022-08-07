// 

package view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

public class App extends Application {
	private final String ASSIGNMENT = "Staff Directory";
	private final String FAVICON = "file:mtu.png";
	private final int SCENEHEIGHT = 625;
	private final int SCENEWIDTH = 800;

	@Override
	public void start(Stage primaryStage) {
		try {
			// ---------------------------------------------------------------------------------
			// -------------------------------- Application Operations
			// ---------------------------------------------------------------------------------
			Button buttonQuit = new Button("Quit");
			buttonQuit.setMinWidth(64);
			buttonQuit.setOnAction(e -> {
				boolean response = AlertAgree.display(FAVICON, ASSIGNMENT, "Are you sure you want to quit?");
				if (response == true) {
					primaryStage.close();
				}
			});

			Button buttonAuthor = new Button("Author");
			buttonAuthor.setMinWidth(64);
			buttonAuthor.setOnAction(e -> {
				boolean response = AlertOk.display(FAVICON, ASSIGNMENT, "This amazing application was written by Lochlann O Neill. He can be found here: https://linktr.ee/lochlannoneill");
				if (response == true) {
					primaryStage.close();
				}
			});

			HBox hboxApplicationOperations = new HBox(buttonQuit, buttonAuthor);
			hboxApplicationOperations.setSpacing(16);
			hboxApplicationOperations.setPadding(new Insets(0, 0, 8, 8));

			// ---------------------------------------------------------------------------------
			// ------------------------------------- Layout
			// ---------------------------------------------------------------------------------
			TabPane tabs = new TabPane();
			tabs.setTabMinWidth(80);
			tabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
			tabs.getTabs().add(new PaneHome());
			tabs.getTabs().add(new PaneAdmin());
			tabs.getTabs().add(new PaneEngineer());
			tabs.getTabs().add(new PaneIntern());

			BorderPane currentPane = new BorderPane();
			Scene scene = new Scene(currentPane, SCENEWIDTH, SCENEHEIGHT);
			scene.getStylesheets().add(getClass().getResource("App.css").toExternalForm());

			currentPane.setBottom(hboxApplicationOperations);
			currentPane.setCenter(tabs);
			currentPane.prefHeightProperty().bind(scene.heightProperty());
			currentPane.prefWidthProperty().bind(scene.widthProperty());

			primaryStage.getIcons().add(new Image(FAVICON));
			primaryStage.setTitle(ASSIGNMENT);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

	}

}