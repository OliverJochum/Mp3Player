package scenes.otherplaylistsview;

import businesslogik.Playlist;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlaylistCell extends ListCell<Playlist>{
	HBox root;
	VBox infoPane;
	Label name;
	
	public PlaylistCell() {
		infoPane = new VBox();
		name = new Label();
		infoPane.getChildren().add(name);
		
		root = new HBox();
		root.getChildren().add(infoPane);
	}
	
	@Override
	protected void updateItem(Playlist item, boolean empty) {
		super.updateItem(item, empty);
		
		if(!empty) {
			name.setText(item.getName());
			
			this.setGraphic(root);
		}
		else {
			this.setGraphic(null);
		}
	}
}
