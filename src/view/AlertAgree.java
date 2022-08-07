package view;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.geometry.*;

public class AlertAgree {
	static boolean response;
	static int STAGEWIDTH = 450;
	static int STAGEHEIGHT = 150;

	public static boolean display(String favicon, String title, String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL); // Block events to other windows
		window.getIcons().add(new Image(favicon));
		window.setTitle(title);
		window.setMinWidth(STAGEWIDTH);
		window.setMinHeight(STAGEHEIGHT);

		// ---------------------------------------------------------------------------------
		// ------------------------------------- Nodes
		// ---------------------------------------------------------------------------------
		Text prompt = new Text();
		prompt.setText(message);
		
		Button buttonAccept = new Button("Yes");
		buttonAccept.setOnAction(e -> {
			response = true;
			window.close();
		});
		Button buttonDecline = new Button("No");
		buttonDecline.setOnAction(e -> {
			response = false;
			window.close();
		});
		
		HBox hboxButtons = new HBox(buttonAccept, buttonDecline);
		hboxButtons.setSpacing(16);
		hboxButtons.setPadding(new Insets(0, 0, 8, 8));
		hboxButtons.setAlignment(Pos.CENTER);

		// ---------------------------------------------------------------------------------
		// ------------------------------------- Layout
		// ---------------------------------------------------------------------------------
		VBox vboxLayout = new VBox(16);
		vboxLayout.getChildren().addAll(prompt, hboxButtons);
		vboxLayout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(vboxLayout);
		window.setScene(scene);
		window.showAndWait(); // wait for it to be closed before returning
		return response;
	}
}