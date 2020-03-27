import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Example extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {

		// Start your JavaFx Journey here

		primaryStage.setTitle("ICA13");// Set the title to ICA12
		HBox hbox1 = new HBox();
		Label label1 = new Label("Number 1: ");
		TextField textfield = new TextField();
		String int1 = textfield.getText();
		int number1 = Integer.parseInt(int1);
		hbox1.getChildren().addAll(label1, textfield); //Add a label telling the user what to do
		HBox hbox2 = new HBox();
		Label label2 = new Label("Number 2: ");
		TextField textfield2 = new TextField();
		String int2 = textfield2.getText();
		int number2 = Integer.parseInt(int2);
		hbox2.getChildren().addAll(label2, textfield2);// Create two textfields aligned vertically
		BorderPane borderPane1 = new BorderPane();
		Button myButton = new Button("Click to calculate sum");
		int result = number1 + number2;
		borderPane1.setCenter(myButton);// Add a button that prints the sum of the two numbers entered to console. (You will need to look up how to getText()) and convert to int)
		myButton.setOnAction(new EventHandler<ActionEvent>() { // We need tell the button what to do when it is pressed
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Sum of Number is : " + result);
			}
		});
		
		int width = 300;
		int height = 300;
		Scene scene = new Scene(borderPane1, width, height);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String args[]) {
		launch(args); // called implicitly
	}
}
