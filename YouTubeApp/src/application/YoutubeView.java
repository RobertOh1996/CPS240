package application;
	
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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


public class YoutubeView extends Application {
	
	private Connection conn;
	
	public Connection getConn() {
		return conn;
	}
	
	public YoutubeView() {
		
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			
			ListView<YoutubeModel> lv = new ListView<>();
			ObservableList<YoutubeModel> ov = FXCollections.observableArrayList();
			lv.setItems(ov);
			
			TextField addTf = new TextField();
			addTf.setPromptText("Youtube Address");
			addTf.setPrefWidth(240);
			
			Button loadBt = new Button("Load");
			Button addBt = new Button("Add");
			Button removeBt = new Button("Remove");
			
			HBox hb = new HBox(addTf, loadBt, addBt, removeBt);
			hb.setSpacing(2);
			
			VBox vb = new VBox(hb, lv);
			vb.setSpacing(2);
			vb.setPadding(new Insets(2));
			
			//db
			conn = dbConnector();
			ov.addAll(new YoutubeDb().loadData());
			
			//button
			addBt.setOnAction(e -> {
				
				String url = addTf.getText().trim();
				
				if(!url.isEmpty()) {
					
					String title = getYoutubeTitle(url);
					
					YoutubeModel ytData = new YoutubeModel(title, url);
					
					if(!title.trim().isEmpty()) {
						ov.add(ytData);
						addTf.clear();
						
						//db
						new YoutubeDb().insertData(ytData);
					}				
				}
				
			});
			
			removeBt.setOnAction(e -> {
				
				YoutubeModel selected = lv.getSelectionModel().getSelectedItem();
				
				if(selected != null) {
					ov.remove(selected);
					
					//db
					new YoutubeDb().deleteData(selected);
				}
				
			});
			
			loadBt.setOnAction(e -> {
				
				YoutubeModel selected = lv.getSelectionModel().getSelectedItem();
				
				if(selected != null) {
					
					try {
						Desktop.getDesktop().browse(new URI(selected.getUrl()));
					} catch (IOException e1) {
						e1.printStackTrace();
						System.out.println("Web load error from IOException");
					} catch (URISyntaxException e1) {
						e1.printStackTrace();
						System.out.println("Web load error from URISyntaxException");
					}
					
				}
				
			});
			
			//textField
			addTf.setOnAction(e -> {
				
				addBt.fire();
				
			});
			
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
	
	public Connection dbConnector(){		
		
		try {
			
			if(conn == null) {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:sql/SimpleYoutube.sqlite");
				System.out.println("Database successfully connected!");
				return conn;
			} else {
				return conn;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("SQL connection error");
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
			
		} catch(IOException e){
			
			e.printStackTrace();			
			System.out.println("Titile acquisition error");
			
			return "";
		}
	}
	
	class YoutubeModel{
		
		private String title;
		private String url;
		
		public YoutubeModel(String title, String url) {
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
	
	public class YoutubeDb {
		
		YoutubeView yt = new YoutubeView();
		
		private PreparedStatement pst;
		private ResultSet rs;
		
		public ObservableList<YoutubeModel> loadData(){
			
			ObservableList<YoutubeModel> tempOv = FXCollections.observableArrayList();
			
			String query = "select * from Youtube";
			
			try {
				
				pst = conn.prepareStatement(query);
				rs = pst.executeQuery();
				
				while(rs.next()) {
					
					String title = rs.getString("Title");
					String url = rs.getString("Url");
					
					YoutubeModel data = new YoutubeModel(title, url);
					
					tempOv.add(data);
				}
				
				rs.close();
				pst.close();
				
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("Data acquisition error");
			}
			
			return tempOv;		
		}
		
		public void insertData(YoutubeModel data) {
			
			String query = "Insert or Replace into Youtube (Title, Url) Values (?, ?)";
			
			try {
				
				pst = yt.getConn().prepareStatement(query);
				pst.setString(1, data.getTitle());
				pst.setString(2, data.getUrl());
				pst.execute();
				pst.close();
				
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("Database insertion error");
			}
			
		}
		
		public void deleteData(YoutubeModel data) {
			
			String query = "delete from Youtube where Url = ?";
			
			try {
				
				pst = yt.getConn().prepareStatement(query);
				pst.setString(1, data.getUrl());
				pst.execute();
				pst.close();
				
			} catch(Exception e) {
				e.printStackTrace();
				System.out.println("Database deletion error");
			}
			
		}
	}

}
