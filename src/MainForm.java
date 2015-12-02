import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainForm extends Application {

	String user = "login";
	String pw = "password";
	String checkUser, checkPw;
	static Generator gen = new Generator();
	// Gods of programming please forgive me for this sin

	public static ArrayList<Room> mainRoomList = gen.makeRooms(5);
	public static ArrayList<Meeting> mainMeetingList = new ArrayList<Meeting>();

	public static void main(String[] args) {

		launch(args);

	}

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

		primaryStage.setScene(scene);

		// primaryStage.setResizable(false);
		primaryStage.show();

	}

	public Scene mainScene() {
		// Create gui elements
		ToggleGroup group = new ToggleGroup();
		VBox vBox = new VBox(10);
		Generator gen = new Generator();

		TextArea taDescription = new TextArea();

		ListView<String> meetingList = new ListView<String>();

		// Populate the meeting list with an initial value
		ObservableList<String> items = FXCollections
				.observableArrayList("No Room Selected");
		meetingList.setItems(items);

		// For each room create a radio button
		for (int i = 0; i < mainRoomList.size(); i++) {
			// Create a var for current room and create the button
			Room currentRoom = mainRoomList.get(i);
			RadioButton rb = new RadioButton(currentRoom.toString());
			rb.setToggleGroup(group);

			// When a button is clicked select the newest meeting
			rb.setOnAction(e -> {
				ObservableList<String> updatedMeetings = FXCollections
						.observableArrayList();
				for (int j = 0; j < currentRoom.getMeetings().size(); j++) {
					updatedMeetings.add(currentRoom.getMeetings().get(j)
							.toString());
				}
				meetingList.setItems(updatedMeetings);

				// I guess i'll leave this in for now since it's everyone's
				// favorite line
				taDescription.setText("MUFFINSAAAAAAA");

			});
			// Add the button to the box
			vBox.getChildren().add(rb);
		}

		// Organize the current scene then return it

		// Buttons
		Button reserveRoom = new Button("Reserve Room");
		Button deleteMeeting = new Button("Delete Meeting");
		Button editMeeting = new Button("Edit Meeting");
		Button createMeeting = new Button("Create Meeting");
		Button refresh = new Button("Refresh");
		Button scheduleMeeting = new Button("Schedule Meeting");

		scheduleMeeting.setOnAction(e -> {
			Stage stage = new Stage();
			stage.setScene(scheduleExistingScene());
			stage.show();

		});

		reserveRoom.setOnAction(e -> {
			Stage stage = new Stage();
			stage.setScene(reserveRoomScene());
			stage.show();
		});

		refresh.setOnAction(e -> {
			int meetingIndex = meetingList.getSelectionModel()
					.getSelectedIndex();
			int roomIndex = -1;
			// Go through each thing in the toggle group and find the selected
			// radio button.
			// There literally HAS TO be an easy way to do this but I can't find
			// it so i wrote it myself
			for (int i = 0; i < group.getToggles().size(); i++) {
				if (group.getToggles().get(i).isSelected()) {
					roomIndex = i;
				}
			}

			// Create a var holding the list of meetings for the selected room
			ArrayList<Meeting> currentList = mainRoomList.get(roomIndex)
					.getMeetings();

			meetingList.setItems(toObserveable(currentList));

		});
		// On click event for the meeting
		deleteMeeting.setOnAction(e -> {

			// Get the index of the currently selected meeting and room
				int meetingIndex = meetingList.getSelectionModel()
						.getSelectedIndex();
				int roomIndex = -1;
				// Go through each thing in the toggle group and find the
				// selected
				// radio button.
				// There literally HAS TO be an easy way to do this but I can't
				// find
				// it so i wrote it myself
				for (int i = 0; i < group.getToggles().size(); i++) {
					if (group.getToggles().get(i).isSelected()) {
						roomIndex = i;
					}
				}

				// Create a var holding the list of meetings for the selected
				// room
				ArrayList<Meeting> currentList = mainRoomList.get(roomIndex)
						.getMeetings();

				// If the meeting has already started you can't delete it
				if (currentList.get(meetingIndex).getStartDate()
						.before(new Date(System.currentTimeMillis()))) {

					System.out
							.println(currentList.get(meetingIndex).toString());
					System.out.println("Meeting Started "
							+ currentList.get(roomIndex).getStartDate());
					System.out.println("This is not a valid delete");
					return;
				}

				// Remove the selected meeting and notify attendees
				currentList.get(meetingIndex).emailAll(
						"Your meeting has been canceled!");
				currentList.remove(meetingIndex);

				// Add the new meeting list back to the room.
				mainRoomList.get(roomIndex).setMeetings(currentList);

				// Update the list of meetings gui (the same as on click for
				// radio
				// buttons)
				ObservableList<String> updatedMeetings = FXCollections
						.observableArrayList();
				for (int j = 0; j < mainRoomList.get(roomIndex).getMeetings()
						.size(); j++) {
					updatedMeetings.add(mainRoomList.get(roomIndex)
							.getMeetings().get(j).toString());
				}
				meetingList.setItems(updatedMeetings);

			});

		// Action for editing a meeting
		editMeeting.setOnAction(e -> {
			int roomIndex = -1;
			int meetingIndex = meetingList.getSelectionModel()
					.getSelectedIndex();

			for (int i = 0; i < group.getToggles().size(); i++) {
				if (group.getToggles().get(i).isSelected()) {
					roomIndex = i;
				}
			}

			Stage stage = new Stage();
			stage.setScene(editScene(roomIndex, meetingIndex, true));
			stage.show();

		});

		// Action for creating a meeting
		createMeeting.setOnAction(e -> {

			Stage stage = new Stage();
			stage.setScene(editScene(0, 0, false));
			stage.show();

		});

		GridPane buttonContainer = new GridPane();

		buttonContainer.add(deleteMeeting, 0, 0);
		buttonContainer.add(editMeeting, 1, 0);
		buttonContainer.add(createMeeting, 2, 0);
		buttonContainer.add(reserveRoom, 3, 0);
		buttonContainer.add(scheduleMeeting, 4, 0);
		buttonContainer.add(refresh, 5, 0);

		SplitPane content = new SplitPane();
		content.setOrientation(Orientation.HORIZONTAL);
		taDescription.setText("hi");
		content.getItems().addAll(vBox, meetingList);
		SplitPane sp = new SplitPane();
		sp.getItems().addAll(content, buttonContainer);
		sp.setOrientation(Orientation.VERTICAL);
		Scene scene = new Scene(sp, 600, 350);

		return scene;

	}

	public Scene reserveRoomScene() {

		// Create GUI elements
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:MM");
		GridPane gp = new GridPane();

		Button Schedule = new Button("Reserve Room");
		Label lblStart = new Label("Start Date");
		final TextField txtStart = new TextField();
		Label lblEnd = new Label("End Date");
		final TextField txtEnd = new TextField();
		Label lblNum = new Label("# Attending");
		final TextField txtNum = new TextField();

		// Populate the start / end date fields with todays info.
		txtStart.setText(format.format(new Date()));
		txtEnd.setText(format.format(new Date()));

		Schedule.setOnAction(e -> {
			Meeting m = new Meeting(gen.makePeople(Integer.parseInt(txtNum
					.getText())), new Date(), new Date());
			try {
				m.setStartDate(format.parse(txtStart.getText()));
				m.setEndDate(format.parse(txtEnd.getText()));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			boolean flag = false;

			for (int i = 0; i < mainRoomList.size(); i++) {
				if (mainRoomList.get(i).schedule(m)) {
					flag = true;
					break;
				}
			}

			if (!flag) {
				System.out.println("No suitable room");
			}
		});

		gp.add(lblNum, 0, 1);
		gp.add(txtNum, 1, 1);
		gp.add(lblStart, 0, 2);
		gp.add(txtStart, 1, 2);
		gp.add(lblEnd, 0, 3);
		gp.add(txtEnd, 1, 3);
		gp.add(Schedule, 0, 20);
		Scene scene = new Scene(gp);

		return scene;
	}

	public Scene scheduleExistingScene() {
		// If there are no pre-existing meetings create one.
		if (mainMeetingList.size() == 0) {

			Meeting m = new Meeting("Preexisting Meeting 1", gen.makePeople(5),
					new Date(System.currentTimeMillis() - (1000 * 60 * 60)),
					new Date(System.currentTimeMillis() + (1000 * 60 * 60)));

			m.setTitle("Premade Meeting 1");
			mainMeetingList.add(m);
		}

		// Create GUI elements
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:MM");
		GridPane gp = new GridPane();
		ComboBox<String> combo = new ComboBox<String>(
				toObserveable(mainMeetingList));
		Button Schedule = new Button("Schedule Meeting");
		Label lblStart = new Label("Start Date");
		final TextField txtStart = new TextField();
		Label lblEnd = new Label("End Date");
		final TextField txtEnd = new TextField();

		// Populate the start / end date fields with todays info.
		txtStart.setText(format.format(new Date()));
		txtEnd.setText(format.format(new Date()));

		Schedule.setOnAction(e -> {
			Meeting m = mainMeetingList.get(combo.getSelectionModel()
					.getSelectedIndex());
			try {
				m.setStartDate(format.parse(txtStart.getText()));
				m.setEndDate(format.parse(txtEnd.getText()));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			boolean flag = false;

			for (int i = 0; i < mainRoomList.size(); i++) {
				if (mainRoomList.get(i).schedule(m)) {
					flag = true;
					break;
				}
			}

			if (!flag) {
				System.out.println("No suitable room");
			}
		});

		gp.add(combo, 0, 1);
		gp.add(lblStart, 0, 2);
		gp.add(txtStart, 1, 2);
		gp.add(lblEnd, 0, 3);
		gp.add(txtEnd, 1, 3);
		gp.add(Schedule, 0, 20);
		Scene scene = new Scene(gp);

		return scene;
	}

	public Scene editScene(int rIndex, int mIndex, boolean editing) {

		Meeting meeting = mainRoomList.get(rIndex).meetings.get(mIndex);
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:MM");
		ArrayList<Person> peopleList = mainRoomList.get(rIndex).getMeetings()
				.get(mIndex).getPeople();

		// if not editing (creating new) don't load any people
		if (!editing)
			peopleList.clear();

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
		Label lblTitle = new Label("Meeting Title");
		final TextField txtTitle = new TextField();
		Label lblStart = new Label("Start Date");
		final TextField txtStart = new TextField();
		Label lblEnd = new Label("End Date");
		final TextField txtEnd = new TextField();
		Button btnSave = new Button("Save");
		ListView attendeeList = new ListView();
		Label lblName = new Label("Name");
		final TextField txtName = new TextField();
		Label lblEmail = new Label("Email");
		final TextField txtEmail = new TextField();
		Label lblJob = new Label("Job");
		final TextField txtJob = new TextField();
		Button btnDelete = new Button("Delete Person");
		Button btnAdd = new Button("Add Person");

		// Populate the fields with values
		if (editing) {

			txtTitle.setText(meeting.getTitle());
			txtStart.setText(format.format(meeting.getStartDate()));
			txtEnd.setText(format.format(meeting.getEndDate()));
			attendeeList.setItems(toObserveable(peopleList));
		} else {
			txtStart.setText(format.format(new Date()));
			txtEnd.setText(format.format(new Date()));
		}

		// Adding Nodes to GridPane layout
		gridPane.add(lblTitle, 0, 0);
		gridPane.add(txtTitle, 1, 0);
		gridPane.add(lblStart, 0, 1);
		gridPane.add(txtStart, 1, 1);
		gridPane.add(lblEnd, 0, 2);
		gridPane.add(txtEnd, 1, 2);
		gridPane.add(attendeeList, 1, 6);
		gridPane.add(lblName, 0, 8);
		gridPane.add(txtName, 1, 8);
		gridPane.add(lblEmail, 0, 9);
		gridPane.add(txtEmail, 1, 9);
		gridPane.add(lblJob, 0, 10);
		gridPane.add(txtJob, 1, 10);
		gridPane.add(btnAdd, 0, 11);
		gridPane.add(btnDelete, 1, 11);
		gridPane.add(btnSave, 1, 20);

		// Action for selecting a person
		attendeeList.setOnMouseClicked(e -> {
			int index = attendeeList.getSelectionModel().getSelectedIndex();
			txtName.setText(peopleList.get(index).getName());
			txtEmail.setText(peopleList.get(index).getEmail());
			txtJob.setText(peopleList.get(index).getRole());
		});
		// Action for adding a person
		btnAdd.setOnAction(e -> {

			ArrayList<Person> result = peopleList;
			result.add(new Person(txtName.getText(), txtEmail.getText(), txtJob
					.getText()));
			attendeeList.setItems(toObserveable(result));
			mainRoomList.get(rIndex).getMeetings().get(mIndex)
					.setPeople(result);
			txtName.setText("");
			txtEmail.setText("");
			txtJob.setText("");

		});

		// Action for deleting person
		btnDelete.setOnAction(e -> {
			int index = attendeeList.getSelectionModel().getSelectedIndex();
			ArrayList<Person> result = mainRoomList.get(rIndex).getMeetings()
					.get(mIndex).getPeople();
			mainRoomList.get(rIndex).getMeetings().get(mIndex).getPeople()
					.remove(index);
			attendeeList.setItems(toObserveable(result));
			txtName.setText("");
			txtEmail.setText("");
			txtJob.setText("");
		});

		// Action for saving changes
		btnSave.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				// If no attendees remove the meeting
				if (meeting.getPeople().size() == 0) {
					mainRoomList.get(rIndex).getMeetings().remove(mIndex);
				} 
				//Else update changes to the meeting.
				else {
					meeting.setTitle(txtTitle.getText());
					try {
						meeting.setStartDate(format.parse(txtStart.getText()));
						meeting.setEndDate(format.parse(txtEnd.getText()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					if (editing) {
						mainRoomList.get(rIndex).getMeetings()
								.set(mIndex, meeting);
					} else {
						mainMeetingList.add(meeting);
						mainMeetingList.get(mainMeetingList.size() - 1)
								.emailAll("Meeting Scheduled");
					}
				}
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

		return scene;
	}

	public ObservableList<String> toObserveable(ArrayList a) {
		ObservableList list = FXCollections.observableArrayList();
		for (int i = 0; i < a.size(); i++) {
			list.add(a.get(i).toString());
		}

		return list;
	}
}