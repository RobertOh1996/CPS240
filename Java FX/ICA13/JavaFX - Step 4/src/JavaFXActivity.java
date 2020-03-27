import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class JavaFXActivity extends Application {
		

		// Start your JavaFx Journey here


		public void start(Stage primaryStage) throws Exception {
			

			primaryStage.setTitle("ICA13");
			BorderPane borderPane1 = new BorderPane();
			HBox hbox1 = new HBox();
			Label label1 = new Label("Number 1: ");	
			TextField textfield = new TextField();
			Label label2 = new Label("Number 2: ");
			TextField textfield2 = new TextField();
			hbox1.getChildren().addAll(label1, textfield, label2, textfield2);			
			Button myButton = new Button("Press for calculate sum");
			borderPane1.setTop(hbox1);
			borderPane1.setCenter(myButton);
			myButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					String s = textfield.getText();
					String s2 = textfield2.getText();
					int num1 = Integer.parseInt(s);
					int num2 = Integer.parseInt(s2);
					int result = num1 + num2;
					System.out.println("The sum is: " + result);
				}
			});
			int width = 800;
			int height = 500;
			Scene scene = new Scene(borderPane1, width, height);
			primaryStage.setScene(scene);
			primaryStage.show();
		}

		public static void main(String[] args) {
			launch(args); //called implicitly 
		}
}