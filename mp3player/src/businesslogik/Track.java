package businesslogik;

import java.io.IOException;
import java.io.RandomAccessFile;
import com.mpatric.mp3agic.*;

import javafx.scene.image.Image;

public class Track {
	private Mp3File mp3file;
	private String title;
	private String track;
	private String album;
	private String artist;
	private String name;
	private int length;
	private ID3v2 id3v2Tag;
	private byte[] albumImageData;


	public Track(String title) {
		this.title = title;

		try {
			mp3file = new Mp3File(title);
			if (mp3file.hasId3v2Tag()) {
				id3v2Tag = mp3file.getId3v2Tag();
				this.track = id3v2Tag.getTrack();
				this.album = id3v2Tag.getAlbum();
				this.artist = id3v2Tag.getArtist();
				this.albumImageData = id3v2Tag.getAlbumImage();
				this.name = id3v2Tag.getTitle();
				this.length = (int) mp3file.getLengthInMilliseconds();

				System.out.println(name);
				createImage(albumImageData);

			}
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Mp3File getMp3file() {
		return mp3file;
	}

	public void setMp3file(Mp3File mp3file) {
		this.mp3file = mp3file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ID3v2 getId3v2Tag() {
		return id3v2Tag;
	}

	public void setId3v2Tag(ID3v2 id3v2Tag) {
		this.id3v2Tag = id3v2Tag;
	}

	public byte[] getAlbumImageData() {
		return albumImageData;
	}

	public void setAlbumImageData(byte[] albumImageData) {
		this.albumImageData = albumImageData;
	}

	public Mp3File getMp3File() {
		return mp3file;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void createImage(byte[] imageData) {
		if (imageData != null) {

			try {
				RandomAccessFile file = new RandomAccessFile(
						"/home/mi/ojoch001/EIBO/Project/Songs/" + name.replaceAll("\\s+", "-") + "-album-artwork.jpg", "rw");
				file.write(imageData);
				file.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Image getImage() {

		Image myImage = new Image("file:/home/mi/ojoch001/EIBO/Project/Songs/" + name.replaceAll("\\s+", "-") + "-album-artwork.jpg");

		return myImage;
	}

}