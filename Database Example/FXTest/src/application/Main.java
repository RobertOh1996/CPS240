package application;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
	
	private Connection conn;
	
	@Override
	public void start(Stage primaryStage) {
		
		conn = dbConnector();
		
		Button loadBt = new Button("loadBt");
		Button addBt = new Button("Add");
		Button updateBt = new Button("Update");
		Button removeBt = new Button("Remove");
		
		VBox vb = new VBox(loadBt, addBt, removeBt, updateBt);
		
		vb.setSpacing(2);
		vb.setPadding(new Insets(2));
		
		loadBt.setOnAction(e -> {

			ObservableList<String> ov = new MyDb().loadData();
			
			int size = ov.size();
			
			for(int i = 0;i < size;i++) {
				String data = ov.get(i);
				System.out.println(data);
			}
		});
		
		addBt.setOnAction(e -> {
			new MyDb().insertData("Good");
		});
		
		removeBt.setOnAction(e -> {
			try {
				new MyDb().deleteData("Good");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		updateBt.setOnAction(e -> {
			new MyDb().updateData("Good", "Great");
		});
		
		Scene scene = new Scene(vb, 500, 500);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("TodoList");
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public Connection dbConnector() {
		try {
			
			if(conn == null) {
			
				Class.forName("org.sqlite.JDBC");
			
				conn = DriverManager.getConnection("jdbc:sqlite:sql/MyData.sqlite");
				System.out.println("Database connecting...");
			
			return conn;
			} else {
				return conn;
			}
		} catch(Exception e) {
			
			System.out.println("Database connection failed.");
			return null;
		}		
	}
	
	public class MyDb {
		private PreparedStatement pst;
		private ResultSet rs;
		
		public void insertData(String data) {
			String query = "Insert or Replace into Datas (Text) Values (?)";
			
			try {
				pst = conn.prepareStatement(query);
				pst.setString(1, data);
				pst.execute();
				pst.close();
			} catch (SQLException e) {
				System.out.println("Data insertion error");
			}		
			
		}
		
		public void deleteData(String data) throws SQLException {
			String query = "delete from Datas where Text = ?";
			
			try {
				pst = conn.prepareStatement(query);
				pst.setString(1, data);
				pst.execute();
				pst.close();
			} catch(SQLException e) {
				System.out.println("Data deletion error");
			}
		}
		
		public void updateData(String oldValue, String newValue) {
			
			String query = "update Datas set Text = ? where Text = ?";
			
			try {
				pst = conn.prepareStatement(query);
				pst.setString(1, newValue);
				pst.setString(2, oldValue);
				pst.execute();
				pst.close();
			} catch(SQLException e) {
				System.out.println("Data update error");
			}
			
		}
		
		public ObservableList<String> loadData(){
			ObservableList<String> tempOv = FXCollections.observableArrayList();
			
			String query = "select * from Datas";
			
			try {
				pst = conn.prepareStatement(query);
				rs = pst.executeQuery();
				
				while(rs.next()) {
					String data = rs.getString("Text");
					tempOv.add(data);
				}
				
				rs.close();
				pst.close();
			} catch(SQLException e) {
				
			}
			
			return tempOv;
		}
	}
}





