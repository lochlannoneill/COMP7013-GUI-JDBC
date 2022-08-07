package view;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.geometry.*;

public class AlertOk {
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

		Text prompt = new Text();
		prompt.setText(message);
		
		Button buttonOk = new Button("Yes");
		buttonOk.setOnAction(e -> {
			window.close();
		});
		
		HBox hboxButtons = new HBox(buttonOk);
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