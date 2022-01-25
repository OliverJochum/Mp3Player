package businesslogik;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class PlaylistManager {
	
	private ArrayList<Playlist> playlistList;
	
	public PlaylistManager() {
		playlistList = new ArrayList<>();
	}
	
	public Playlist getPlaylist(String name, String fileName) {
		//default
		Playlist playlist = new Playlist(name);
		
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
			String line;
			while((line = br.readLine()) != null) {
				playlist.addTrack(line);
				System.out.println(playlist.getTrack(playlist.getTrack(line).getTitle()));
			} 
		}catch(IOException e) {
			e.printStackTrace();
		}
	
		playlistList.add(playlist);
		
		return playlist;
		
	}

	public ArrayList<Playlist> getPlaylistList() {
		return playlistList;
	}

	public void setPlaylistList(ArrayList<Playlist> playlistList) {
		this.playlistList = playlistList;
	}

	
	
	
	
}
