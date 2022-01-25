package scenes.playerview;

import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
public class PlayerView extends BorderPane{
	Pane root;
	Pane cover;
	Pane controls;
	Pane progress;
	MenuBar mbar;
	Menu file;
	Menu view;
	MenuItem settings;
	MenuItem playlist;
	MenuItem otherplaylists;
	
	public PlayerView() throws IOException {
		
		root = new Pane();
		root.setPrefHeight(600.0);
		root.setPrefWidth(400.0);
		
		cover = new Pane();
		cover.setPrefHeight(370.0);
		cover.setPrefWidth(300.0);
		cover.setLayoutX(50.0);
		
		URL coverURL = new File("/home/mi/ojoch001/eclipse-workspace/mp3player/src/uicomponents/CoverView.fxml").toURI().toURL();
		HBox coverView = (HBox) FXMLLoader.load(coverURL);
		
		
		
		progress = new Pane();
		progress.setPrefHeight(50.0);
		progress.setPrefWidth(350.0);
		progress.setLayoutX(25.0);
		progress.setLayoutY(370.0);
		
		URL progressURL = new File("/home/mi/ojoch001/eclipse-workspace/mp3player/src/uicomponents/ProgressView.fxml").toURI().toURL();
		AnchorPane progressView = (AnchorPane) FXMLLoader.load(progressURL);
		
		
		
		controls = new Pane();
		controls.setPrefHeight(80.0);
		controls.setPrefWidth(400.0);
		controls.setLayoutY(430.0);
		
		URL controlURL = new File("/home/mi/ojoch001/eclipse-workspace/mp3player/src/uicomponents/ControlView.fxml").toURI().toURL();
		AnchorPane controlView = (AnchorPane) FXMLLoader.load(controlURL);
		
		
		mbar = new MenuBar();

		file = new Menu();
		file.setMnemonicParsing(false);
		file.setText("File");

		view = new Menu();
		view.setMnemonicParsing(false);
		view.setText("View");

		settings = new MenuItem();
		settings.setMnemonicParsing(false);
		settings.setText("Settings");

		playlist = new MenuItem();
		playlist.setMnemonicParsing(false);
		playlist.setText("Playlist");

		otherplaylists = new MenuItem();
		otherplaylists.setMnemonicParsing(false);
		otherplaylists.setText("Select other playlists");

		
		cover.getChildren().add(coverView);
		progress.getChildren().add(progressView);
		controls.getChildren().add(controlView);
		
		file.getItems().add(settings);
		
		view.getItems().addAll(playlist, otherplaylists);
		
		mbar.getMenus().addAll(file, view);
		
		root.getChildren().addAll(cover, progress, controls);
		
		this.setCenter(root);
		
		this.setTop(mbar);
		
	}

	public Pane getRoot() {
		return root;
	}

	public void setRoot(Pane root) {
		this.root = root;
	}

	public Pane getCover() {
		return cover;
	}

	public void setCover(Pane cover) {
		this.cover = cover;
	}

	public Pane getControls() {
		return controls;
	}

	public void setControls(Pane controls) {
		this.controls = controls;
	}

	public Pane getProgress() {
		return progress;
	}

	public void setProgress(Pane progress) {
		this.progress = progress;
	}

	public MenuBar getMbar() {
		return mbar;
	}

	public void setMbar(MenuBar mbar) {
		this.mbar = mbar;
	}

	public Menu getFile() {
		return file;
	}

	public void setFile(Menu file) {
		this.file = file;
	}

	public Menu getView() {
		return view;
	}

	public void setView(Menu view) {
		this.view = view;
	}

	public MenuItem getSettings() {
		return settings;
	}

	public void setSettings(MenuItem settings) {
		this.settings = settings;
	}

	public MenuItem getPlaylist() {
		return playlist;
	}

	public void setPlaylist(MenuItem playlist) {
		this.playlist = playlist;
	}

	public MenuItem getOtherplaylists() {
		return otherplaylists;
	}

	public void setOtherplaylists(MenuItem otherplaylists) {
		this.otherplaylists = otherplaylists;
	}
	
	
}
