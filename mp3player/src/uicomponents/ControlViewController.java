package uicomponents;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;

public class ControlViewController extends AnchorPane{

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ToggleButton repeatButton;

	


	@FXML
	private Button skipBButton;

	@FXML
	private ToggleButton playpauseButton;

	@FXML
	private Button skipButton;

	@FXML
	private ToggleButton shuffleButton;
	
	@FXML
	private Slider volumeSlider;

	public ControlViewController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ControlView.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		try {
			fxmlLoader.load();
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@FXML
	void initialize() {
		
	}
	
	public ToggleButton getRepeatButton() {
		return repeatButton;
	}


	public void setRepeatButton(ToggleButton repeatButton) {
		this.repeatButton = repeatButton;
	}


	public Button getSkipBButton() {
		return skipBButton;
	}


	public void setSkipBButton(Button skipBButton) {
		this.skipBButton = skipBButton;
	}


	public ToggleButton getPlaypauseButton() {
		return playpauseButton;
	}


	public void setPlaypauseButton(ToggleButton playpauseButton) {
		this.playpauseButton = playpauseButton;
	}


	public Button getSkipButton() {
		return skipButton;
	}


	public void setSkipButton(Button skipButton) {
		this.skipButton = skipButton;
	}


	public ToggleButton getShuffleButton() {
		return shuffleButton;
	}


	public void setShuffleButton(ToggleButton shuffleButton) {
		this.shuffleButton = shuffleButton;
	}

	public Slider getVolumeSlider() {
		return volumeSlider;
	}

	public void setVolumeSlider(Slider volumeSlider) {
		this.volumeSlider = volumeSlider;
	}
	
	
}
