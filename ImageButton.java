/*
https://stackoverflow.com/questions/54876509/how-to-fit-the-background-size-into-the-window-size-in-javafx
*/

import javafx.application.Application;
import javafx.util.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.animation.*;
import javafx.event.*;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.ImageView;
import java.io.*;
import javafx.scene.image.Image;
import javafx.scene.paint.*;


//int val , ImageView imgView

public class ImageButton extends Button{
	private String hiddenNumber;
	private int numCount;





	public ImageButton(String n, ImageView imgView){
		super("", imgView);
		this.hiddenNumber = n;

       setOnAction(new EventHandler<ActionEvent>() {
    	   @Override public void handle(ActionEvent e) {
    		    ImageButton b = (ImageButton) e.getSource();
                PuzzleMain.handleButtonClick(b.getHiddenVal());
                //System.out.println("HiddenNum : " + hiddenNumber);
                //System.out.println("Image : " + imgView); 
                numCount++;
    			System.out.println(numCount);


            }
		});
	}



	public int getNumOfMoves() {                                         
    	numCount++;
    	System.out.println(numCount);
    	return numCount;
	} 

	public String getHiddenVal(){
		return hiddenNumber;
	}
	public void setHiddenVal(String hiddenNumber){
		this.hiddenNumber = hiddenNumber;
	}
}