package businesslogik;

import java.util.ArrayList;
import java.util.Collections;

public class Playlist {
	private String name;
	private ArrayList<Track> tracklist;
	private int selectedIndex;

	public Playlist(String name) {
		this.name = name;

		tracklist = new ArrayList<Track>();
	}

	public ArrayList<Track> getTracklist() {
		return tracklist;
	}

	public String getName() {
		return name;
	}

	public void addTrack(String title) {
		Track t = new Track(title);

		tracklist.add(t);
	}

	public Track getTrack(String title) {
		for (Track track : tracklist) {
			if (title.equals(track.getTitle())) {
				return track;
			}
		}
		return null;
	}

	public void skip() {
		selectedIndex++;
		if (selectedIndex == tracklist.size()) {
			selectedIndex = 0;
		}

	}

	public void skipb() {
		selectedIndex--;
		if (selectedIndex == -1) {
			selectedIndex = tracklist.size() - 1;
		}

	}
	
	public void findSpecificIndex(Track selectedTrack) {
		for(int i = 0; i < tracklist.size(); i++) {
			if(tracklist.get(i) == selectedTrack) {
				selectedIndex = i;
			}
		}
	}

	public Track getSelectedTrack() {
		return tracklist.get(selectedIndex);
	}
	
	public void shuffle() {
		Collections.shuffle(tracklist);
	}
}
