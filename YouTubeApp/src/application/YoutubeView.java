package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class YoutubeView extends Application {

	private Connection conn;

	int port = 9900;

	@Override
	public void start(Stage primaryStage) {
		try {

			// Check whether program is already running or not
			checkIfRunning();

			ListView<YoutubeModel> lv = new ListView<>();
			ObservableList<YoutubeModel> ov = FXCollections.observableArrayList();
			lv.setItems(ov);

			FilteredList<YoutubeModel> filteredList = new FilteredList<>(ov, item -> true);
			lv.setItems(filteredList);

			lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			TextField addTf = new TextField();
			addTf.setPromptText("Youtube Address");
			addTf.setPrefWidth(240);

			Button loadBt = new Button("Load");
			Button addBt = new Button("Add");
			Button removeBt = new Button("Remove");

			HBox hb = new HBox(addTf, loadBt, addBt, removeBt);
			hb.setSpacing(2);

			TextField searchTf = new TextField();
			searchTf.setPromptText("Search");
			searchTf.setPrefWidth(300);

			// event-tf
			searchTf.textProperty().addListener((a, oldvalue, newValue) -> {

				String search = searchTf.getText().toLowerCase().trim();

				filteredList.setPredicate(item -> {

					if (item.getTitle().toLowerCase().contains(search)) {
						return true;
					} else {
						return false;
					}

				});

			});

			CheckBox chromeCb = new CheckBox("To Chrome");

			HBox hb2 = new HBox(searchTf, chromeCb);
			hb2.setSpacing(2);
			hb2.setAlignment(Pos.CENTER_LEFT);

			// Stop focusing
			loadBt.setFocusTraversable(false);
			addBt.setFocusTraversable(false);
			removeBt.setFocusTraversable(false);
			chromeCb.setFocusTraversable(false);

			VBox vb = new VBox(hb, hb2, lv);
			vb.setSpacing(2);
			vb.setPadding(new Insets(2));

			// db
			conn = dbConnector();
			ov.addAll(new YoutubeDb().loadData());

			/**
			 * Dummy Fuction When a video link is copied, print title, url, and add the link
			 * into database.
			 */
			// Clipboard
//			Clipboard clipboard = Clipboard.getSystemClipboard();
//			
//			new ClipboardAssistance(com.sun.glass.ui.Clipboard.SYSTEM) {
//				
//				@Override
//				public void contentChanged() {
//					
//					try {
//						if(clipboard.hasString()) {
//							
//							if(chromeCb.isSelected()) {
//								String text = clipboard.getString();
//								System.out.println("Clipboard: " + text);
//								String title = text.split("\"title\":\"")[1].split("\",\"")[0];
//								String url = text.split("\"url\":\"")[1].split("\"}")[0];
//								System.out.println(title);
//								System.out.println(url);
//							
//								YoutubeModel data = new YoutubeModel(title, url);
//								ov.add(data);
//								//db
//								new YoutubeDb().insertData(data);
//							}
//						}
//					} catch(Exception e) {
//						
//					}					
//				}				
//			};

			// event-lv
			lv.setOnDragDetected(e -> {

				ObservableList<YoutubeModel> selecteds = lv.getSelectionModel().getSelectedItems();

				if (selecteds.size() == 1) {
					
					YoutubeModel selected = selecteds.get(0);

					if (selected != null) {
						Dragboard db = lv.startDragAndDrop(TransferMode.ANY);
						ClipboardContent cc = new ClipboardContent();
						cc.putUrl(selected.getUrl());
						db.setContent(cc);
					}
				}
				
				if(selecteds.size() > 1) {
					
					int size = selecteds.size();
					
					String asxText = "<asx version = \"3\">\n";
					
					for(int i = 0;i < size;i++) {
						YoutubeModel data = selecteds.get(i);
						
						String title = data.getTitle();
						String url = data.getUrl();
						
						asxText = asxText + "<entry><title>" + title + "</title><ref href=\"" + url + "\"/></entry>\n";
								
					}
					asxText = asxText + "</asx>";
					
					//write into the file
					saveContent("C:\\Users\\ojm56\\eclipse-workspace\\YouTubeApp\\src\\application\\assets\\list.asx", asxText);
					
					//Drag
					Dragboard db = lv.startDragAndDrop(TransferMode.ANY);
					ClipboardContent cc = new ClipboardContent();
					
					List<File> files = new ArrayList<>();
					File file = new File("C:\\Users\\ojm56\\eclipse-workspace\\YouTubeApp\\src\\application\\assets\\list.asx");
					files.add(file);
					cc.putFiles(files);
					db.setContent(cc);
				}

			});

			lv.setOnDragOver(e -> {

				if (e.getGestureSource() != lv) {
					e.acceptTransferModes(TransferMode.ANY);
				}

			});

			lv.setOnDragDropped(e -> {
				Dragboard db = e.getDragboard();

				if (db.hasUrl()) {
					String url = db.getUrl();
					String title = getYoutubeTitle(url);
					YoutubeModel data = new YoutubeModel(title, url);
					ov.add(data);

					// db
					new YoutubeDb().insertData(data);
				}
			});

			lv.setOnMouseClicked(e -> {

				if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
					loadBt.fire();
				}
			});

			// button
			addBt.setOnAction(e -> {

				String url = addTf.getText().trim();

				if (!url.isEmpty()) {

					String title = getYoutubeTitle(url);

					YoutubeModel ytData = new YoutubeModel(title, url);

					if (!title.trim().isEmpty()) {
						ov.add(ytData);
						addTf.clear();

						// db
						new YoutubeDb().insertData(ytData);
					}
				}

			});

			removeBt.setOnAction(e -> {

				YoutubeModel selected = lv.getSelectionModel().getSelectedItem();

				if (selected != null) {
					ov.remove(selected);

					// db
					new YoutubeDb().deleteData(selected);
				}

			});

			loadBt.setOnAction(e -> {

				YoutubeModel selected = lv.getSelectionModel().getSelectedItem();

				if (selected != null) {

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

			// textField
			addTf.setOnAction(e -> {

				addBt.fire();

			});

			Scene scene = new Scene(vb, 400, 400);

			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("YoutubeApp");
			primaryStage.setResizable(false);
			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("assets/youtube.png")));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Failed Function Check if the process has already been run and if so, force
	 * the program to exit. But it's not working, multiprocessing is still working
	 */
	public void checkIfRunning() {
		try {
			ServerSocket socket = new ServerSocket(port, 0, InetAddress.getByAddress(new byte[] { 127, 0, 0, 1 }));
		} catch (Exception e) {
			System.out.println("Already process running...");
			Alert alert = new Alert(AlertType.WARNING);
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image(getClass().getResourceAsStream("assets/warning.png")));
			alert.setTitle("Warning");
			alert.setHeaderText("Youtube Application is already running.");
			alert.showAndWait();
			System.exit(1);
		}
	}
	
	public void saveContent(String filePath, String text) {
		try {
			Files.write(Paths.get(filePath), text.getBytes());
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Can't save it");
		}
	}

	public Connection dbConnector() {

		try {

			if (conn == null) {
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

		} catch (IOException e) {

			e.printStackTrace();
			System.out.println("Title acquisition error");

			return "";
		}
	}

	class YoutubeModel {

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

		public ObservableList<YoutubeModel> loadData() {

			ObservableList<YoutubeModel> tempOv = FXCollections.observableArrayList();

			String query = "select * from Youtube";

			try {

				pst = conn.prepareStatement(query);
				rs = pst.executeQuery();

				while (rs.next()) {

					String title = rs.getString("Title");
					String url = rs.getString("Url");

					YoutubeModel data = new YoutubeModel(title, url);

					tempOv.add(data);
				}

				rs.close();
				pst.close();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Data acquisition error");
			}

			return tempOv;
		}

		public void insertData(YoutubeModel data) {

			String query = "Insert or Replace into Youtube (Title, Url) Values (?, ?)";

			try {

				pst = conn.prepareStatement(query);
				pst.setString(1, data.getTitle());
				pst.setString(2, data.getUrl());
				pst.execute();
				pst.close();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Database insertion error");
			}

		}

		public void deleteData(YoutubeModel data) {

			String query = "delete from Youtube where Url = ?";

			try {

				pst = conn.prepareStatement(query);
				pst.setString(1, data.getUrl());
				pst.execute();
				pst.close();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Database deletion error");
			}

		}
	}

}
