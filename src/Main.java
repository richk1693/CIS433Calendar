import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	private String usDescription = "Description for US ...";
	private String ukDescription = "Description for UK ...";
	private String caDescription = "Description for CA ...";

	@Override
	// Override the start method in the Application class
	public void start(Stage primaryStage) {

		ToggleGroup group = new ToggleGroup();
		VBox vBox = new VBox(10);

		RadioButton rbUS = new RadioButton("Room 101");
		RadioButton rbUK = new RadioButton("Room 102");
		RadioButton rbCA = new RadioButton("Room 103");

		//vBox.getChildren().addAll(rbUS, rbUK, rbCA);
		for(int i =1; i< 5; i++){
			RadioButton rb = new RadioButton("Room " +i);
			rb.setToggleGroup(group);
			vBox.getChildren().add(rb);
		}
		SplitPane content = new SplitPane();
		content.setOrientation(Orientation.VERTICAL);
		StackPane imagePane = new StackPane();
		TextArea taDescription = new TextArea();
		taDescription.setText(usDescription);
		content.getItems().addAll(
		imagePane, new ScrollPane(taDescription));
		SplitPane sp = new SplitPane();
		sp.getItems().addAll(vBox, content);
		Scene scene = new Scene(sp, 300, 250);
		primaryStage.setTitle("SplitPaneDemo"); // Set the window title
		primaryStage.setScene(scene); // Place the scene in the window
		primaryStage.show(); // Display the window
		
		// Group radio buttons
		
		rbUS.setToggleGroup(group);
		rbUK.setToggleGroup(group);
		rbCA.setToggleGroup(group);
		rbUS.setSelected(true);
		
		
		rbUS.setOnAction(e -> {
			taDescription.setText(usDescription);

		});

		rbUK.setOnAction(e -> {

			taDescription.setText(ukDescription);

		});

		rbCA.setOnAction(e -> {

			taDescription.setText(caDescription);

		});

	}

}