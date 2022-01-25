package uicomponents;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;

public class ProgressViewController extends AnchorPane{
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Slider timeSlider;

	@FXML
	private Label startTime;

	@FXML
	private Label endTime;

	public ProgressViewController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProgressView.fxml"));
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

	public Slider getTimeSlider() {
		return timeSlider;
	}

	public void setTimeSlider(Slider timeSlider) {
		this.timeSlider = timeSlider;
	}

	public Label getStartTime() {
		return startTime;
	}

	public void setStartTime(Label startTime) {
		this.startTime = startTime;
	}

	public Label getEndTime() {
		return endTime;
	}

	public void setEndTime(Label endTime) {
		this.endTime = endTime;
	}
	
	
}
