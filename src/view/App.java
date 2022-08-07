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
			Button buttonExit = new Button("Quit");
			buttonExit.setMinWidth(64);
			buttonExit.setOnAction(e -> {
				boolean response = AlertAgree.display(FAVICON, ASSIGNMENT, "Are you sure you want to quit?");
				if (response == true) {
					primaryStage.close();
				}
			});
			HBox hboxApplicationOperations = new HBox(buttonExit);
			hboxApplicationOperations.setSpacing(16);
			hboxApplicationOperations.setPadding(new Insets(0, 0, 8, 8));

			// ---------------------------------------------------------------------------------
			// ------------------------------------- Layout
			// ---------------------------------------------------------------------------------
			TabPane tabs = new TabPane();
			tabs.setTabMinWidth(80);
			tabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
			tabs.getTabs().add(new PaneIntro());
			tabs.getTabs().add(new PaneAdmin());
			tabs.getTabs().add(new PaneEngineer());
			tabs.getTabs().add(new PaneIntern());

			BorderPane currentPane = new BorderPane();
			Scene scene = new Scene(currentPane, SCENEWIDTH, SCENEHEIGHT);

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