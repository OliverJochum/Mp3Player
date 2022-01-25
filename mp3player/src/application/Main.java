package application;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import businesslogik.Player;
import businesslogik.Playlist;
import businesslogik.PlaylistManager;
import javafx.application.Application;
import javafx.stage.Stage;
import scenes.otherplaylistsview.OtherPlaylistsViewController;
import scenes.playerview.PlayerViewController;
import scenes.playlistview.PlaylistViewController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Main extends Application {
	Player player;
	Map<String, Pane> scenes;
	Pane root;
	Stage primaryStage;
	Scene playerScene;
	Scene playlistScene;
	Scene otherPlaylistsScene;
	PlaylistManager playlistManager;

	@Override
	public void start(Stage primaryStage) throws IOException {

		playlistManager = new PlaylistManager();

		Playlist defaultPlaylist = playlistManager.getPlaylist("Default","/home/mi/ojoch001/EIBO/Project/Playlists/default.m3u");
		
		 
		
		this.primaryStage = primaryStage;
		
		root = new Pane();
		

		try {
			init(defaultPlaylist);
			
			//Pane root;

			try {
				switchView("PlayerView");
				
				

			} catch (NullPointerException npe) {
				System.out.println("Root cannot be null. Please enter a valid scene name");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void switchView(String view) {
		
		if(view.equals("PlayerView")) {
			primaryStage.setScene(playerScene);
		}
		else if(view.equals("PlaylistView")) {
			primaryStage.setScene(playlistScene);
		}
		else if(view.equals("OtherPlaylistsView")) {
			primaryStage.setScene(otherPlaylistsScene);
		}
		
		
		primaryStage.show();
	}
	
	public void init(Playlist playlist) {
		
		player = new Player(playlist);

		scenes = new HashMap<String, Pane>();

		Pane view = new PlayerViewController(this, player);
		scenes.put("PlayerView", view);

		view = new PlaylistViewController(this, player);
		scenes.put("PlaylistView", view);
		
		view = new OtherPlaylistsViewController(this);
		scenes.put("OtherPlaylistsView", view);

		playerScene = new Scene(scenes.get("PlayerView"));
		
		playlistScene = new Scene(scenes.get("PlaylistView"));
		
		otherPlaylistsScene = new Scene(scenes.get("OtherPlaylistsView"));
	}
	
	public Scene switchToPlayerView() {
		Scene playerScene = new Scene(scenes.get("PlayerView"));
		return playerScene;
	}
	
	
	
	public void switchToPlaylistView() {
		
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Map<String, Pane> getScenes() {
		return scenes;
	}

	public void setScenes(Map<String, Pane> scenes) {
		this.scenes = scenes;
	}

	public Pane getRoot() {
		return root;
	}

	public void setRoot(Pane root) {
		this.root = root;
	}


	public PlaylistManager getPlaylistManager() {
		return playlistManager;
	}

	public void setPlaylistManager(PlaylistManager playlistManager) {
		this.playlistManager = playlistManager;
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
	

}
