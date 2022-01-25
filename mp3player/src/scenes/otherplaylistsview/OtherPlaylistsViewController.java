package scenes.otherplaylistsview;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import businesslogik.Playlist;
import businesslogik.PlaylistManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

public class OtherPlaylistsViewController extends BorderPane{
	private Main application;
	
	private PlaylistManager selectedPLManager;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	
	@FXML
	private ListView<Playlist> playlistLV;
	
	@FXML
    private MenuItem playerItem;
	
	@FXML
	private MenuItem playlistItem;

	public OtherPlaylistsViewController(Main application) {
		this.application = application;
		selectedPLManager = application.getPlaylistManager();

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OtherPlaylistsView.fxml"));

		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		
	}

	@FXML
	void initialize() {
		playerItem.addEventHandler(ActionEvent.ACTION, e -> showPlayerView());
		playlistItem.addEventHandler(ActionEvent.ACTION, e -> showPlaylistView());
		
		List<Playlist> plList = selectedPLManager.getPlaylistList();
		
		playlistLV.setCellFactory(new Callback<ListView<Playlist>, ListCell<Playlist>>(){

			@Override
			public ListCell<Playlist> call(ListView<Playlist> param) {
				return new PlaylistCell();
			}
			
		});
		
		playlistLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Playlist>() {

			@Override
			public void changed(ObservableValue<? extends Playlist> observable, Playlist oldValue, Playlist newValue) {
				application.init(newValue);
			}
			
		});
		
		ObservableList<Playlist> playlistListModel = playlistLV.getItems();
		playlistListModel.addAll(plList);
		
	}
	
	public void showPlayerView() {
		application.switchView("PlayerView");
	}
	
	public void showPlaylistView() {
		application.switchView("PlaylistView");
	}
}
