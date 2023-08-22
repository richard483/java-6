import java.util.Optional;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import jfxtras.labs.scene.control.window.Window;

public class Main extends Application implements EventHandler<MouseEvent>{

	Scene scene;
	BorderPane borderPane, headerPane;
	GridPane formPane;
	Label nameLabel, announcementLabel, detailLabel, titleLabel, announcementTypelabel;
	TextField nameTF, announcementTF, detailTF;
	Button closeShowWindow, submitForm;
	Window anncouncementWindow;
	ListView<String> anncouncementList;
	Menu menu;
	MenuBar menuBar;
	MenuItem menuItem;
	ComboBox<String> announcemenType;
	ObservableList<String> announcement = FXCollections.observableArrayList();
	public static void main(String[] args) {
		launch(args);

	}
	
	private void initialize() {
		borderPane = new BorderPane();
		formPane = new GridPane();
		
		nameLabel = new Label("Name");
		announcementLabel = new Label("Announcement");
		detailLabel = new Label("Detail");
		titleLabel = new Label("Announcement generator");
		
		nameTF = new TextField();
		announcementTF = new TextField();
		detailTF = new TextField();
		
		closeShowWindow = new Button("Close Announcement Window");
		submitForm = new Button("Submit form");
		
//		headerPane = new BorderPane();
//		headerPane.setTop(menuBar);
//		headerPane.setBottom(titleLabel);
		
//		BorderPane.setAlignment(titleLabel, Pos.BOTTOM_CENTER);
//		BorderPane.setMargin(titleLabel, new Insets(25, 10, 25, 10));
//		titleLabel.setStyle("-fx-text-fill:BLACK; -fx-font-size: 32;");
//		

		
		anncouncementList = new ListView<String>();
		
		menu = new Menu("Action");
		menuBar = new MenuBar();
		menuItem = new MenuItem("Profile");
		
		announcementTypelabel = new Label("Announcement type");
		announcemenType = new ComboBox<String>();
		announcemenType.getItems().add("Important");
		announcemenType.getItems().add("Low prior");
		announcemenType.getSelectionModel().selectFirst();
		
		borderPane.setCenter(formPane);
		borderPane.setTop(menuBar);
		scene = new Scene(borderPane, 800, 600);
	}
	
	private void menuInit() {
		menuBar.getMenus().add(menu);
		menu.getItems().add(menuItem);
	}
	
	private void initForm() {
		formPane.add(nameLabel, 0, 0);
		formPane.add(nameTF, 1, 0);
		
		formPane.add(announcementLabel, 0, 1);
		formPane.add(announcementTF, 1, 1);
		
		formPane.add(detailLabel, 0, 2);
		formPane.add(detailTF, 1, 2);
		
		formPane.add(announcementTypelabel, 0, 3);
		formPane.add(announcemenType, 1, 3);
		
		formPane.add(closeShowWindow, 0, 4);
		formPane.add(submitForm, 1, 4);
		
		formPane.setVgap(10);
		formPane.setHgap(10);
		
		BorderPane.setMargin(formPane, new Insets(10));
	}
	
	private void initWindow() {
		anncouncementWindow = new Window("Announcement window");
		anncouncementWindow.setMovable(true);
		anncouncementWindow.setResizableWindow(true);
		anncouncementWindow.setMoveToFront(true);
		anncouncementWindow.setMaxHeight(200);
		anncouncementWindow.setMaxWidth(200);
		
		borderPane.setRight(anncouncementWindow);
		BorderPane.setMargin(anncouncementWindow, new Insets(10));
	}
	
	private void initListView() {
		announcement.add("Announcement 1");
		announcement.add("Announcement 2");
		announcement.add("Announcement 3");
		
		anncouncementList.setItems(announcement);
		
		anncouncementWindow.getContentPane().getChildren().setAll(anncouncementList);
	}
	
	private void addSubmitFormEventListener() {
		submitForm.setOnMouseClicked(this);
		submitForm.setOnMouseEntered((event) -> {
			// mouse event handling with lambda
			System.out.println("Mouse entered the button");
		});
	}
	
	private void addCloseShowWindowListener() {
		closeShowWindow.setOnMouseClicked((event) -> {
			if(anncouncementWindow.isVisible()) {
				anncouncementWindow.setVisible(false);
				closeShowWindow.setText("Show window");
			}else {
				anncouncementWindow.setVisible(true);
				closeShowWindow.setText("Close window");
			}
		});
	}
	
	private void addKeyListener() {
		nameTF.setOnKeyPressed((e) -> {
			if(e.getCode().equals(KeyCode.BACK_SPACE)) {
				System.out.println("Space bar detected");
			}
		});
		nameTF.setOnKeyReleased((e) -> {
			if(e.getCode().equals(KeyCode.BACK_SPACE)) {
				System.out.println("Space bar released");
			}
		});
		nameTF.setOnKeyTyped((e) -> {
			if(e.getCode().equals(KeyCode.BACK_SPACE)) {
				System.out.println("Space bar typed");
			}
		});
	}
	
	private void addComboboxListener() {
		announcemenType.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				System.out.println("Arg0 " + arg0);
				System.out.println("Arg1 " + arg1);
				System.out.println("Arg2 " + arg2);
			}
		});
	}

	@Override
	public void start(Stage stage) throws Exception {
		initialize();
		initForm();
		initWindow();
		initListView();
		addSubmitFormEventListener();
		addCloseShowWindowListener();
		menuInit();
		addKeyListener();
		addComboboxListener();
		stage.setScene(scene);
		stage.setTitle("Java-6");
		stage.show();
		
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent arg0) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setContentText("Are you sure want to close?");
				Optional<ButtonType> res = alert.showAndWait();
				
				if(res.get() == ButtonType.CANCEL) {
					arg0.consume();
				}
				
			}
		});
	}

	@Override
	public void handle(MouseEvent arg0) {
		if(arg0.getSource() == submitForm) {
			String name = nameTF.getText();
			String announcement = announcementTF.getText();
			String detail = detailTF.getText();
			
			String announcementRes = String.join(" ", name, announcement, detail);
			
			this.announcement.add(announcementRes);
			anncouncementList.setItems(this.announcement);
			
			anncouncementWindow.getContentPane().getChildren().setAll(anncouncementList);
		}
		
	}

}
