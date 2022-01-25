package scenes.playlistview;

import java.io.IOException;
import businesslogik.Track;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.geometry.*;

public class PlaylistView extends BorderPane {
	Pane root;
	Pane controlPane;
	Pane listPane;
	ScrollBar sbar;
	MenuBar mbar;
	Menu file;
	Menu view;
	MenuItem settings;
	MenuItem playlist;
	MenuItem otherplaylists;
	ListView<Track> playListView;
	

	public PlaylistView() throws IOException {
		
		root = new Pane();
		root.setPrefHeight(600.0);
		root.setPrefWidth(400.0);

		controlPane = new Pane();
		controlPane.setPrefHeight(80.0);
		controlPane.setPrefWidth(400.0);
		controlPane.setLayoutY(430.0);
		controlPane.setId("controlPane");
		
		listPane = new Pane();
		listPane.setPrefHeight(430.0);
		listPane.setPrefWidth(380.0);
		
		playListView = new ListView<>();
		playListView.setPrefHeight(430.0);
		playListView.setPrefWidth(380.0);
		
		
		
		
		sbar = new ScrollBar();
		sbar.setOrientation(Orientation.VERTICAL);
		sbar.setPrefHeight(600.0);
		sbar.setPrefWidth(20.0);
		sbar.setLayoutX(380.0);
		

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

		
		//controlPane.getChildren().add(controlView);
		listPane.getChildren().add(playListView);
		
		
		file.getItems().add(settings);
		
		view.getItems().addAll(playlist, otherplaylists);
		
		mbar.getMenus().addAll(file, view);
		
		root.getChildren().addAll(sbar, controlPane, listPane);
		
		this.setCenter(root);
		this.setTop(mbar);
		

	}
}
