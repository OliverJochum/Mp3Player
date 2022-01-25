package scenes.playerview;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import businesslogik.Player;
import businesslogik.Playlist;
import businesslogik.Track;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import uicomponents.ControlViewController;
import uicomponents.CoverViewController;
import uicomponents.ProgressViewController;

public class PlayerViewController extends BorderPane {
	private Main application;

	private Player player;

	private Playlist selectedPlaylist;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	
	@FXML
	private VBox playerVBox;

	@FXML
	private Pane coverPane;

	@FXML
	private Pane progressPane;

	@FXML
	private Pane controlPane;

	@FXML
	private MenuItem playlistItem;

	@FXML
	private MenuItem otherPlaylistsItem;

	@FXML
	private ControlViewController controlView;

	@FXML
	private ProgressViewController progressView;

	@FXML
	private CoverViewController coverView;

	// PlayerViewController acts as both the root and the controller of the scene

	public PlayerViewController(Main application, Player player) {
		this.application = application;
		this.player = player;
		selectedPlaylist = player.getPlaylist();

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlayerView.fxml"));

		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		
		try {
			fxmlLoader.load();

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		this.getStylesheets().add(getClass().getResource("playerview.css").toExternalForm());
		
		playerVBox.setSpacing(10);
		playerVBox.setAlignment(Pos.BASELINE_CENTER);
		
		
		
		controlView.getPlaypauseButton().setSelected(true);
		
		progressView.getTimeSlider().setMin(0);
		
		controlView.getVolumeSlider().setMin(0);
		controlView.getVolumeSlider().setMax(6.0206);
		controlView.getVolumeSlider().setValue(3);
		
		
		coverView.getSongImage().setPreserveRatio(true);
		
		updatePlayer();
	}

	@FXML
	void initialize() {
		
		controlView.getPlaypauseButton().addEventHandler(ActionEvent.ACTION,
				e -> startPausePlayer(controlView.getPlaypauseButton()));
		controlView.getSkipButton().addEventHandler(ActionEvent.ACTION, e -> skipPlayer());
		controlView.getSkipBButton().addEventHandler(ActionEvent.ACTION, e -> skipbPlayer());
		controlView.getRepeatButton().addEventHandler(ActionEvent.ACTION,
				e -> repeatPlayer(controlView.getRepeatButton()));

		playlistItem.addEventHandler(ActionEvent.ACTION, e -> showPlaylistView());
		otherPlaylistsItem.addEventHandler(ActionEvent.ACTION, e -> showOtherPlaylistsView());

		controlView.getVolumeSlider().valueProperty().addListener(
			(observable, oldValue, newValue) -> {
				player.volume(newValue.floatValue());
			}

		);

		player.SongLengthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				Platform.runLater(() -> {
					progressView.getTimeSlider().setMin(0);
					progressView.getTimeSlider().setMax(newValue.intValue());
					progressView.getEndTime().setText(convertToTime(newValue.intValue()));
					updatePlayer();
				});

			}
		});

		player.CurrentTimeProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				Platform.runLater(() -> {
					progressView.getTimeSlider().valueProperty().set(newValue.intValue());
					progressView.getStartTime().setText(convertToTime(newValue.intValue()));
				});
			}
		});
		
		player.playingProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				Platform.runLater(() -> {
					if(newValue) {						
						controlView.getPlaypauseButton().setSelected(false);
						controlView.getPlaypauseButton().setText("Pause");
					}
				});
				
			}
			
		});

	}

	public int convertToSeconds(int millis) {
		int seconds = millis / 1000;

		return seconds;
	}

	public int increment(int max) {
		return max / 100;
	}

	private void repeatPlayer(ToggleButton button) {
		if (button.isSelected()) {
			player.setLooping(true);
		} else {
			player.setLooping(false);
		}
	}

	private void startPausePlayer(ToggleButton button) {
		if (button.isSelected()) {
			button.setText("Play");
			player.pause();
		} else if (!button.isSelected()) {
			button.setText("Pause");
			player.play();
		}

	}

	private void skipPlayer() {
		player.skip();
		updatePlayer();
	}

	private void skipbPlayer() {
		player.skipb();
		updatePlayer();
	}

	public void showPlaylistView() {
		application.switchView("PlaylistView");
	}

	public void showOtherPlaylistsView() {
		application.switchView("OtherPlaylistsView");
	}
	
	public String convertToTime(int millis) {
		int seconds = millis/1000;
		
		int min = seconds/60;
		int sec = seconds%60;
		
		String minu = null;
		String seco = null;
		
		if(min > 10) {
			minu = min + "";
		}
		else {
			minu = "0" + min;
		}
		if(sec >= 10) {
			seco = sec + "";
		}
		else {
			seco = "0" + sec;
		}
		
		return minu + ":" + seco;
	}

	public void updatePlayer() {
		Track selectedTrack = selectedPlaylist.getSelectedTrack();

		coverView.getSongImage().setImage(selectedTrack.getImage());

		coverView.getArtistLabel().setText(selectedTrack.getArtist());

		coverView.getTitleLabel().setText(selectedTrack.getName());

	}

}
