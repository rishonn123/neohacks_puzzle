import javafx.application.Application;
import javafx.util.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.animation.*;
import javafx.event.*;
import javafx.scene.text.*;
import java.util.ArrayList;



public class GameDialog extends Stage{
	private Label status;
	public GameDialog(Stage primaryStage){
		init(primaryStage);
	}

	private void init(Stage primaryStage){
		initModality(Modality.APPLICATION_MODAL);
		initOwner(primaryStage);

		BorderPane layoutManager = new BorderPane();
		Scene dialogScene = new Scene(layoutManager, 300, 200);
		Label l = new Label("CONGRATULATIONS");
		l.setFont(new Font("Cambria", 20));
		status = new Label("");
		// layoutManager.setAlignment(m, Pos.TOP_BOTTOM);
		layoutManager.setBottom(status);
		layoutManager.setAlignment(status, Pos.BOTTOM_CENTER);
		status.setFont(new Font("Cambria", 20));

		
		layoutManager.setAlignment(l, Pos.TOP_CENTER);
		layoutManager.setTop(l);

		Button shuffle = initShuffleButton();
		layoutManager.setCenter(shuffle);

		setResizable(false);
		setScene(dialogScene);		
	}

		
	// private Button initNumberButton(){
	// 	Button m = new Button("Number of Moves : ");
	// 	m.setFont(new Font("Cambria", 20));
	// 	m.setOnAction(new EventHandler<ActionEvent>() {
 //    	   @Override public void handle(ActionEvent e) {
 //    	   		ImageButton.getNumOfMoves();
                
 //    		}
	// 	}); 
	// 	return m;
	// }

	private Button initShuffleButton(){
		Button b = new Button("Shuffle");
		b.setFont(new Font("Cambria", 20));
		b.setOnAction(new EventHandler<ActionEvent>() {
    	   @Override public void handle(ActionEvent e) {
    	   		PuzzleMain.shuffleTheGame();
                
    		}
		}); 
		return b;
	}
	public void updateStatus(){
		status.setText("Number of Moves : " + PuzzleMain.getClickCount());
	}

	public void displayDialog(){
		updateStatus();
		super.show();			
	}
}

