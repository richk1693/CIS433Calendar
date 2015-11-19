import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 *
 * 
 * @web http://zoranpavlovic.blogspot.com/
 */

public class Login2 extends Application {

	String user = "login";
	String pw = "password";
	String checkUser, checkPw;

	public static void main(String[] args) {

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("433 Calendar Login");
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(10, 50, 50, 50));
		
		// Adding HBox
		HBox hb = new HBox();
		hb.setPadding(new Insets(20, 20, 20, 30));

		// Adding GridPane
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(20, 20, 20, 20));
		gridPane.setHgap(5);
		gridPane.setVgap(5);

		// Implementing Nodes for GridPane
		Label lblUserName = new Label("Username");
		final TextField txtUserName = new TextField();
		Label lblPassword = new Label("Password");
		final PasswordField pf = new PasswordField();
		Button btnLogin = new Button("Login");
		final Label lblMessage = new Label();

		// Adding Nodes to GridPane layout
		gridPane.add(lblUserName, 0, 0);
		gridPane.add(txtUserName, 1, 0);
		gridPane.add(lblPassword, 0, 1);
		gridPane.add(pf, 1, 1);
		gridPane.add(btnLogin, 2, 1);
		gridPane.add(lblMessage, 1, 2);

		// Reflection for gridPane
		Reflection r = new Reflection();
		r.setFraction(0.7f);
		gridPane.setEffect(r);

		// DropShadow effect
		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(5);
		dropShadow.setOffsetY(5);

		// Adding text and DropShadow effect to it
		Text text = new Text("433 Calendar Login");
		text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
		text.setEffect(dropShadow);

		// Adding text to HBox
		hb.getChildren().add(text);

		// Add ID's to Nodes
		bp.setId("bp");
		gridPane.setId("root");
		btnLogin.setId("btnLogin");
		text.setId("text");

		// Action for btnLogin
		btnLogin.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				checkUser = txtUserName.getText().toString();

				checkPw = pf.getText().toString();

				if (checkUser.equals(user) && checkPw.equals(pw)) {

					lblMessage.setText("Congratulations!");
					lblMessage.setTextFill(Color.GREEN);
					primaryStage.setScene(mainScene());

				}

				else {

					lblMessage.setText("Incorrect user or pw.");

					lblMessage.setTextFill(Color.RED);

				}

				txtUserName.setText("");

				pf.setText("");

			}

		});

		// Add HBox and GridPane layout to BorderPane Layout
		bp.setTop(hb);
		bp.setCenter(gridPane);

		// Adding BorderPane to the scene and loading CSS
		Scene scene = new Scene(bp);
		// scene.getStylesheets().add(
		// getClass().getClassLoader().getResource("login.css")
		// .toExternalForm());

		
		//TODO: Change this from mainScene() to scene when done testing main scene.
		primaryStage.setScene(scene);
		

		// primaryStage.setResizable(false);

		primaryStage.show();

	}

	public Scene mainScene() {
		
		//Create gui elements
		ToggleGroup group = new ToggleGroup();
		VBox vBox = new VBox(10);
		Generator gen = new Generator();
		ArrayList<Room> roomList = gen.makeRooms(5);
		TextArea taDescription = new TextArea();		
		ListView<String> meetingList = new ListView<String>();
		
		//Populate the meeting list with an initial value
		ObservableList<String> items =FXCollections.observableArrayList("No room Selected");
		meetingList.setItems(items);

	
		//For each room create a radio button
		for(int i = 0; i<roomList.size(); i++){
			//Create a var for current room and create the button
			Room currentRoom = roomList.get(i);
			RadioButton rb = new RadioButton(currentRoom.toString());
			rb.setToggleGroup(group);
			
			//When a button is clicked select the newest meeting
			rb.setOnAction(e ->{
					ObservableList<String> updatedMeetings = FXCollections.observableArrayList();
					for(int j = 0; j<currentRoom.getMeetings().size(); j++){
						updatedMeetings.add(currentRoom.getMeetings().get(j).toString()+ " in " + currentRoom.toString());
					}
					meetingList.setItems(updatedMeetings);
					taDescription.setText("MUFFINSAAAAAAA");
				
				
			});
			//Add the button to the box
			vBox.getChildren().add(rb);
		}

		
		//Organize the current scene then return it
		SplitPane content = new SplitPane();
		content.setOrientation(Orientation.HORIZONTAL);		
		taDescription.setText("hi");
		content.getItems().addAll(vBox, meetingList);
		SplitPane sp = new SplitPane();
		sp.getItems().addAll(content, new ScrollPane(taDescription));
		sp.setOrientation(Orientation.VERTICAL);
		Scene scene = new Scene(sp, 600, 350);

		return scene;

	}

}