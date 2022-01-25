package uicomponents;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CoverViewController extends HBox{

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView songImage;

	
	@FXML
	private Label titleLabel;

	@FXML
	private Label artistLabel;

	
	public CoverViewController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CoverView.fxml"));
		
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
	
	public ImageView getSongImage() {
		return songImage;
	}
	public void setSongImage(ImageView songImage) {
		this.songImage = songImage;
	}
	public Label getTitleLabel() {
		return titleLabel;
	}
	public void setTitleLabel(Label titleLabel) {
		this.titleLabel = titleLabel;
	}
	public Label getArtistLabel() {
		return artistLabel;
	}
	public void setArtistLabel(Label artistLabel) {
		this.artistLabel = artistLabel;
	}
}
