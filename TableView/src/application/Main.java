package application;
	
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			TableView tableView = new TableView<>();
			Button button1 = new Button("1");
			button1.setCursor(Cursor.HAND);
			button1.setFont(new Font("Arial", 12));
			Button button2 = new Button("2");
			button2.setCursor(Cursor.HAND);
			button2.setFont(new Font("Arial", 12));
			
			TableColumn<String, Person> column1 = new TableColumn<>("First Name");
			column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
			
			TableColumn<String, Person> column3 = new TableColumn<>("Middle Name");
			column3.setCellValueFactory(new PropertyValueFactory<>("middleName"));
			
			TableColumn<String, Person> column2 = new TableColumn<>("Last Name");
			column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
			
			TableColumn<String, Person> column4 = new TableColumn<>("Action");
			column4.setCellValueFactory(new PropertyValueFactory<>("button"));
			
			tableView.getColumns().addAll(column1, column3, column2, column4);
			
			tableView.getItems().add(new Person("John", "von", "Neumann", button1));
			tableView.getItems().add(new Person("Immanuel", null, "Kant", button2));			
			
			VBox vbox = new VBox(tableView);
			Scene scene = new Scene(vbox);
			//Scene 1 ended
			Label label = new Label("Information: \n");
			Label info1 = new Label("He was a Hugarian-American mathematician, physicist, computer scientist, engineer and polymath.\nHe was generally regarded as the foremost mathematician of this time and said to be 'the last representative of the great mathematicians'.\n");
			label.setFont(new Font("Arial", 16));
			info1.setFont(new Font("Arial", 13));
			FileInputStream input = new FileInputStream("resources/von.gif");
			Image image = new Image(input);
			ImageView imageView = new ImageView(image);
			Button button =  new Button("Back");
			VBox vbox2 = new VBox();
			vbox2.getChildren().addAll(label, info1, imageView, button);			
			vbox2.setAlignment(Pos.CENTER);
			Scene scene2 = new Scene(vbox2);
			button.setOnAction(e -> primaryStage.setScene(scene));
			//Scene 2 ended
			Label label2 = new Label("Information: \n");
			Label info2 = new Label("He was an influential German philosopher in the Age of Enlightenment.\nIn his doctrine of transcendential idelaism, he argued that space, time, and cuasation are mere sensibilities.");
			label2.setFont(new Font("Arial", 16));
			info2.setFont(new Font("Arial", 13));
			FileInputStream input2 = new FileInputStream("resources/kant.jpg");
			Image image2 = new Image(input2);
			ImageView imageView2 = new ImageView(image2);
			VBox vbox3 = new VBox();
			Button button3 =  new Button("Back");
			vbox3.getChildren().addAll(label2,info2, imageView2, button3);
			vbox3.setAlignment(Pos.CENTER);
			button3.setOnAction(e -> primaryStage.setScene(scene));
			Scene scene3 = new Scene(vbox3);
			//Scene 3 ended
			button1.setOnMouseClicked((MouseEvent event) -> {
				if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1) {
					primaryStage.setScene(scene2);
				}
			});
			button2.setOnMouseClicked((MouseEvent event) -> {
				if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 1) {
					primaryStage.setScene(scene3);
				}
			});
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) { 
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
