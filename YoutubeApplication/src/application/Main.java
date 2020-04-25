package application;

import java.awt.Desktop;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class Main extends Application {
	
	private Connection conn;

	@Override
	public void start(Stage primaryStage) {
		try {
			ListView<YoutubeData> lv = new ListView<>();
			ObservableList<YoutubeData> ov = FXCollections.observableArrayList();
			lv.setItems(ov);
			
			TextField addTf = new TextField();
			addTf.setPromptText("Youtube Address");
			addTf.setPrefWidth(240);
			
			Button loadBt = new Button("Load");
			Button addBt = new Button("Add");
			Button removeBt = new Button("Remove");
			
			//Button
			addBt.setOnAction(e -> {
				
				String url = addTf.getText().trim();
				
				if(!url.isEmpty()) {
					String title = getYoutubeTitle(url);
					
					YoutubeData ytData = new YoutubeData(title, url);
					
					if(!title.trim().isEmpty()) {
						ov.add(ytData);
						addTf.clear();
					}
				}
				
			});
			
			removeBt.setOnAction(e -> {
				
				YoutubeData selected = lv.getSelectionModel().getSelectedItem();
				
				if(selected != null) {
					ov.remove(selected);
				}
				
			});

			loadBt.setOnAction(e -> {
				
				YoutubeData selected = lv.getSelectionModel().getSelectedItem();
				
				if(selected != null) {
					try {
						Desktop.getDesktop().browse(new URI(selected.getUrl()));
					} catch(Exception e2) {
						System.out.println("Web load error");
					}
				}
	
			});
			
			//TextField
			addTf.setOnAction(e -> {
				addBt.fire();
			});
			
			HBox hb = new HBox(addTf, loadBt, addBt, removeBt);
			hb.setSpacing(2);
			
			VBox vb = new VBox(hb, lv);
			vb.setSpacing(2);
			vb.setPadding(new Insets(2));
			
			//db
			conn = dbConnector();
			
			Scene scene = new Scene(vb, 400, 400);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("YoutubeApp");
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("assets/youtube.png")));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public Connection dbConnector() {		
		
		try {
			if(conn == null) {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:sql/SimpleYooutube.sqlite");
				System.out.println("Successfully connected to database");
				return conn;
			} else {
				return conn;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
			return null;
		}
	}
	
	public String getYoutubeTitle(String youtubeUrl) {
		youtubeUrl = "https://www.youtube.com/oembed?url=" + youtubeUrl + "&format=xml";
		
		try {
			Document document = Jsoup.connect(youtubeUrl).get();
			
			Element titleEle = document.getElementsByTag("title").get(0);
			
			String title = titleEle.text();
			
			return title;
		} catch(Exception e) {
			System.out.println("Error");
			return null;
		}
	}
	
	class YoutubeData{
		
		private String title;
		private String url;
		
		public YoutubeData(String title, String url) {
			super();
			this.title = title;
			this.url = url;
		}

		public String getTitle() {
			return title;
		}

		public String getUrl() {
			return url;
		}

		@Override
		public String toString() {
			return title;
		}
	}
	
	public class YoutubeDb{
		
		private PreparedStatement pst;
		private ResultSet rs;
		
		public ObservableList<YoutubeData> loadData(){
			
			ObservableList<YoutubeData> tempOv = FXCollections.observableArrayList();
			
			String query = "select * from Youtube";
			
			try {
				
				pst = conn.prepareStatement(query);
				rs = pst.executeQuery();
				
				while(rs.next()) {
					String title = rs.getString("Title");
					String url = rs.getString("Url");
					
					YoutubeData data = new YoutubeData(title, url);
					
					tempOv.add(data);
				}
				
				rs.close();
				pst.close();
				
			} catch(Exception e) {
				System.out.println("Data acquisition error");
			}
			
			return tempOv;
			
		}
		
		public void insertData(YoutubeData data) {
			
		}
		
		public void deleteData(YoutubeData data) {
			
		}
	}
}
