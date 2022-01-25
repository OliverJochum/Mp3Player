package scenes.playlistview;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import businesslogik.Player;
import businesslogik.Playlist;
import businesslogik.Track;
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
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class PlaylistViewController extends BorderPane {
	private Main application;

	private Player player;

	private Playlist selectedPlaylist;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private MenuItem playerItem;

	@FXML
	private MenuItem otherPlaylistsItem;

	@FXML
	private ListView<Track> plview;

	@FXML
	private Pane controlPane;

	public PlaylistViewController(Main application, Player player) {
		this.application = application;
		this.player = player;
		selectedPlaylist = player.getPlaylist();

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlaylistView.fxml"));

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
		otherPlaylistsItem.addEventHandler(ActionEvent.ACTION, e -> showOtherPlaylistsView());

		List<Track> trackList = selectedPlaylist.getTracklist();

		// indicate, how a Cell is setup
		plview.setCellFactory(new Callback<ListView<Track>, ListCell<Track>>() {

			@Override
			public ListCell<Track> call(ListView<Track> param) {
				return new TrackCell();
			}

		});

		// recognizing the selected Track
		plview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Track>() {

			@Override
			public void changed(ObservableValue<? extends Track> observable, Track oldTrack, Track newTrack) {

				player.resetPlayer(newTrack);

			}

		});

		// set the content to be displayed
		ObservableList<Track> playlistModel = plview.getItems();
		playlistModel.addAll(trackList);

	}

	public void showPlayerView() {
		application.switchView("PlayerView");
	}

	public void showOtherPlaylistsView() {
		application.switchView("OtherPlaylistsView");
	}

}
