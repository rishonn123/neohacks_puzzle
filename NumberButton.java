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

public class NumberButton extends Button{
	public NumberButton(String label){
		super(label);
		super.setMinWidth(200);
	 	super.setMinHeight(200);
	 	super.setFont(new Font("Cambria", 20));

	 	

       setOnAction(new EventHandler<ActionEvent>() {
    	   @Override public void handle(ActionEvent e) {
    		    Button b = (Button) e.getSource();
                PuzzleMain.handleButtonClick(b.getText());
    		}
		});  
	}
}