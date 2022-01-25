package businesslogik;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player {
	private SimpleMinim minim;
	private SimpleAudioPlayer audioPlayer;
	private Playlist playlist;
	private Thread playerThread;
	private SimpleIntegerProperty currentTime;
	private SimpleIntegerProperty songLength;
	private SimpleBooleanProperty playing;
	private boolean looping;

	public Player(Playlist playlist) {
		this.playlist = playlist;

		minim = new SimpleMinim(true);
		currentTime = new SimpleIntegerProperty();
		songLength = new SimpleIntegerProperty();
		playing = new SimpleBooleanProperty();
		
		
		looping = false;

		loadSong();
	}

	public void savePlaylist() {

	}

	public void loadPlaylist() {

	}

	public void loadSong() {
		audioPlayer = minim.loadMP3File(playlist.getSelectedTrack().getTitle());
	}

	// this can only be called if a playerThread is running
	public synchronized void play() {

		playerThread = new Thread(() -> {
			try {
				while (!Thread.currentThread().isInterrupted()) {
					playing.set(true);
					int selectedTrackLength = playlist.getSelectedTrack().getLength();

					songLength.setValue(selectedTrackLength);

					System.out.println("Before: " + audioPlayer.position());
					audioPlayer.play();
					System.out.println("After: " + audioPlayer.position());

					long selectedLength = selectedTrackLength - audioPlayer.position();

					System.out.println(selectedLength);

					System.out.println(currentTime.getValue());

					while (currentTime.getValue() < selectedTrackLength) {
						currentTime.setValue(currentTime.getValue() + 1000);

						Thread.sleep(1000);
					}

					currentTime.setValue(0);

					if (!looping) {
						playlist.skip();
					}
					// else play that same song again

					loadSong();

				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

		});

		playerThread.start();
	}

	
	public synchronized void resetPlayer(Track track) {
		if(audioPlayer.isPlaying()) {
			pause();
		}
		audioPlayer = null;
		playlist.findSpecificIndex(track);
		loadSong();
		currentTime.setValue(0);
		play();
	}
	
	public void stop() {
		audioPlayer.pause();
		
	}
	
	public synchronized void pause() {
		System.out.println("Before: " + audioPlayer.position());
		audioPlayer.pause();
		System.out.println("After: " + audioPlayer.position());

		playing.set(false);
		
		playerThread.interrupt();
	}

	public void skip() {
		if (audioPlayer.isPlaying()) {
			pause();
		}

		playlist.skip();
		loadSong();
		currentTime.setValue(0);
		play();
	}

	public void skipb() {
		if (audioPlayer.isPlaying()) {
			pause();
		}

		playlist.skipb();
		loadSong();
		currentTime.setValue(0);
		play();
	}

	public void volume(float value) {
		if(value == 0) {
			audioPlayer.mute();
		}
		if(audioPlayer.isMuted() && value > 0) {
			audioPlayer.unmute();
		}
		audioPlayer.setGain(value);
		System.out.println(audioPlayer.getGain());
	}
	

	// Shuffle
	public void shuffle() {
		playlist.shuffle();
	}

	public boolean isSongOver() {
		Track selectedSong = playlist.getSelectedTrack();
		if (selectedSong.getLength() == audioPlayer.position()) {
			return true;
		}
		return false;
	}

	public SimpleAudioPlayer getAudioPlayer() {
		return audioPlayer;
	}

	public void setAudioPlayer(SimpleAudioPlayer audioPlayer) {
		this.audioPlayer = audioPlayer;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	public int getPosition() {
		return audioPlayer.position();
	}

	public int getEndTime() {
		return audioPlayer.length();
	}

	public SimpleIntegerProperty CurrentTimeProperty() {
		return currentTime;
	}

	public int getCurrentTime() {
		return currentTime.get();
	}

	public SimpleIntegerProperty SongLengthProperty() {
		return songLength;
	}
	
	public SimpleBooleanProperty playingProperty() {
		return playing;
	}
	
	public boolean isPlaying() {
		return playing.get();
	}

	public int getSongLength() {
		return songLength.get();
	}

	public boolean isLooping() {
		return looping;
	}

	public void setLooping(boolean looping) {
		this.looping = looping;
	}

}
