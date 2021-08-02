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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.*;
import java.util.*;

public class PuzzleMain extends Application {
    private static Stage primaryStage;
    private static final int SCREEN_WIDTH = 900;
    private static final int SCREEN_HEIGHT = SCREEN_WIDTH + 20;
    private static final int ENTROPY = 6;

//792
    private static int numCount = 0;


    private static int x = 0;
    private static int y = 0;
    private static final int CYCLE_COUNT = 5;

    private static final int TARGET_VALUE = 16;
    private static GameDialog gameDialog;
 
    private static int[][] arr = {
        {1,2,3,4},
        {5,6,7,8},
        {9,10,11,12},
        {13,14,15,16}
    };
    // private static NumberButton [] arrButton;
    private static ArrayList<ImageButton> imageButtons;
    private static Map mapOfImageView;



    private void initButtons(){
        imageButtons = new ArrayList<ImageButton>();
        mapOfImageView = new HashMap();
        //String folder = "/Users/iamgroot/project/java/puzzle/img/";
        for(int n = 1; n <= 16; n++){
            String fname = "neo/image_part_00"+n+".png";
            String fileName = getClass().getResource(fname).toExternalForm();

            Image image = new Image(fileName, 181,180,true,true);
            ImageView imageView = new ImageView(image);
            if(n == 16){
                imageView.setOpacity(0.2);
            }
            ImageButton b = new ImageButton(""+n , imageView);
            mapOfImageView.put(""+n, imageView);
            imageButtons.add(b);  

        }
    }



    private static void updateImageReflection(){
       // System.out.println("updateImageReflection");
        int n = 0;
        for(int r = 0; r < 4 ; r++){
            for(int c = 0 ; c < 4 ; c++){
                int key  = arr[r][c];
                ImageView imageView = (ImageView)mapOfImageView.get(""+key);
                ImageButton ib = imageButtons.get(n);
                ib.setGraphic(imageView);
                //System.out.println("ImageView : " + imageView);
                //System.out.println("ImageButton : " + ib);
                //System.out.println("KEY : " + key + " HiddelVal : " + ib.getHiddenVal());
                //System.out.println("Hidden Number : " + n);
                ib.setHiddenVal("" + key);
                n++;
            }   
        }
    }


    // private static void updateReflection(){
    //     for(int r = 0 ; r < 4 ; r++){
    //         for(int c = 0 ; c < 4 ; c++){
    //             String v = "";
    //             if(arr[r][c] != TARGET_VALUE){
    //                 v = ""+arr[r][c];
    //             }
    //             arrButton[r*4 + c].setText(v);
    //         }
    //     }
    // }

    public static int getClickCount(){
        System.out.println("getClickCount : " + numCount);
        return numCount;
    }


    public static void handleButtonClick(String buttonText){
        //System.out.println(" >> "+ buttonText);
        numCount++;
        System.out.println("Click Count : " + numCount);
        if((""+TARGET_VALUE).equals(buttonText)){
            return;
        }
        Coordinate coor = findCoordinate(buttonText);
        if(coor.isCorner() == true){
            handleCornerClick(coor);
        } else if(coor.isSide() == true){
            handleSideClick(coor);
        } else{
            handleCentralClick(coor);
        }
        if(isGameComplete() == true){
            // Button l = new Button();
            // l.setText("CONGRATULATIONS !!! ");
            //GameDialog.showCompletion(primaryStage);
            gameDialog.displayDialog();

        }
    }
    public static Coordinate findCoordinate(String buttonText){
        System.out.println("findCoordinate() for "+buttonText);
        int i = Integer.parseInt(buttonText);
        for(int r = 0; r < 4; r++){
            for(int c = 0; c < 4; c++){
                if(arr[r][c] == i){
                    Coordinate coor = new Coordinate(r,c); 
                    System.out.print("arr["+r+"]["+c+"] = "+arr[r][c]);                    
                    System.out.println(" , buttonText = "+buttonText
                        + " , coor : "+coor.toString());                    
                    return coor;
                }
            }
        }
        return null;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }//getRandomNumber
    public static void shuffleTheGame(){
        numCount = 0;
        gameDialog.updateStatus();
        //status.setText("")
        for(int n = 0; n < ENTROPY; n++){
            int aRow = getRandomNumber(0,4);
            int aColum = getRandomNumber(0,4);

            int bRow = getRandomNumber(0,4);
            int bColum = getRandomNumber(0,4);

            //System.out.print("aRow = "+aRow+" , aColum="+aColum);
            //System.out.println("bRow = "+bRow+" , bColum="+bColum);

            if((aRow == bRow && aColum == bColum) == false){
                arr[bRow][bColum] = arr[aRow][aColum] + arr[bRow][bColum];
                arr[aRow][aColum] = arr[bRow][bColum] - arr[aRow][aColum];
                arr[bRow][bColum] = arr[bRow][bColum] - arr[aRow][aColum];
                updateImageReflection();

            }
             
        }
    }//shuffleTheGame

    public static boolean isGameComplete(){
        int n = 1;
        for(int r = 0; r < 4; r++){
            for(int c = 0; c < 4; c++){
                if(arr[r][c] != n){
                   return false; 
                } 
                n++;
            }
        }
        return true;
    }//isGameComplete


    private static void handleCornerClick(Coordinate coor){
        ArrayList<Coordinate> poss = new ArrayList<Coordinate>();

        if(coor.getRow() == 0 && coor.getCol() == 0){
            poss.add(new Coordinate(0,1));
            poss.add(new Coordinate(1,0));
        } else if(coor.getRow() == 0 && coor.getCol() == 3){
            poss.add(new Coordinate(0,2));
            poss.add(new Coordinate(1,3));
        } else if(coor.getRow() == 3 && coor.getCol() == 0){
            poss.add(new Coordinate(2,0));
            poss.add(new Coordinate(3,1));
        } else if(coor.getRow() == 3 && coor.getCol() == 3){
            poss.add(new Coordinate(2,3));
            poss.add(new Coordinate(3,2));
        }
        findAndSwap(coor, poss);
    }
    private static void handleSideClick(Coordinate coor){
        ArrayList<Coordinate> poss = new ArrayList<Coordinate>();
        if(coor.getRow() == 0 && coor.getCol() == 1){
            poss.add(new Coordinate(0,0));
            poss.add(new Coordinate(0,2));
            poss.add(new Coordinate(1,1));
        } else if(coor.getRow() == 0 && coor.getCol() == 2){
            poss.add(new Coordinate(0,1));
            poss.add(new Coordinate(0,3));
            poss.add(new Coordinate(1,2));
        } else if(coor.getRow() == 1 && coor.getCol() == 0){
            poss.add(new Coordinate(0,0));
            poss.add(new Coordinate(1,1));
            poss.add(new Coordinate(2,0));
        } else if(coor.getRow() == 2 && coor.getCol() == 0){
            poss.add(new Coordinate(1,0));
            poss.add(new Coordinate(2,1));
            poss.add(new Coordinate(3,0));
        } else if(coor.getRow() == 3 && coor.getCol() == 1){
            poss.add(new Coordinate(3,0));
            poss.add(new Coordinate(2,1));
            poss.add(new Coordinate(3,2));
        } else if(coor.getRow() == 3 && coor.getCol() == 2){
            poss.add(new Coordinate(3,1));
            poss.add(new Coordinate(2,2));
            poss.add(new Coordinate(3,3));
        } else if(coor.getRow() == 2 && coor.getCol() == 3){
            poss.add(new Coordinate(1,3));
            poss.add(new Coordinate(2,2));
            poss.add(new Coordinate(3,3));
        } else if(coor.getRow() == 1 && coor.getCol() == 3){
            poss.add(new Coordinate(0,3));
            poss.add(new Coordinate(1,2));
            poss.add(new Coordinate(2,3));
        }
        findAndSwap(coor, poss);
    }


    private static void handleCentralClick(Coordinate coor){
        ArrayList<Coordinate> poss = new ArrayList<Coordinate>();
        poss.add(new Coordinate(coor.getRow() - 1, coor.getCol()));
        poss.add(new Coordinate(coor.getRow(), coor.getCol() + 1));
        poss.add(new Coordinate(coor.getRow() + 1, coor.getCol()));
        poss.add(new Coordinate(coor.getRow(), coor.getCol() - 1));

        findAndSwap(coor, poss);
    }

    


    private static void findAndSwap(Coordinate clickCoord , ArrayList<Coordinate> poss){
        for(int ctr = 0; ctr < poss.size(); ctr++){
            Coordinate c = poss.get(ctr);
            System.out.println("Poss : "+c.toString() 
                + ", Clicked Co: " + clickCoord.toString());
            if(arr[c.getRow()][c.getCol()] == TARGET_VALUE){
                arr[c.getRow()][c.getCol()] = arr[clickCoord.getRow()][clickCoord.getCol()];
                arr[clickCoord.getRow()][clickCoord.getCol()] = TARGET_VALUE;
                updateImageReflection();
                return;
            } 
        }
        shakeStage();
    }

    private static void shakeStage() {
        Timeline timelineX = new Timeline(
            new KeyFrame(Duration.seconds(0.1), 
                new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    if (x == 0) {
                        primaryStage.setX(primaryStage.getX() + 10);
                        x = 1;
                    } else {
                        primaryStage.setX(primaryStage.getX() - 10);
                        x = 0;
                    }
                }
            }
        ));

        timelineX.setCycleCount(CYCLE_COUNT);
        timelineX.setAutoReverse(false);
        timelineX.play();


        Timeline timelineY = new Timeline(new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (y == 0) {
                    primaryStage.setY(primaryStage.getY() + 10);
                    y = 1;
                } else {
                    primaryStage.setY(primaryStage.getY() - 10);
                    y = 0;
                }
            }
        }));

        timelineY.setCycleCount(CYCLE_COUNT);
        timelineY.setAutoReverse(false);
        timelineY.play();
    }
    
    public MenuBar createMenu(){
        MenuBar menuBar = new MenuBar();
        menuBar.useSystemMenuBarProperty().set(false);
        //menuBar.setMinWidth(SCREEN_WIDTH);
        //menuBar.setMinHeight(50);
        Menu controls = new Menu("Themes");
        MenuItem menuItem1 = new MenuItem("Viper Theme");
        // menuItem1.setOnAction(new EventHandler<ActionEvent>() {
        //    @Override public void handle(ActionEvent e) {
        //          imageButtons.setUserAgentStyleSheets("Viper.css");


        //     }
        // });
        MenuItem menuItem2 = new MenuItem("Screenshot");
        MenuItem menuItem3 = new MenuItem("Share");
        MenuItem dash = new MenuItem("-");
        controls.getItems().add(menuItem1);
        controls.getItems().add(menuItem2);
        controls.getItems().add(dash);
        controls.getItems().add(menuItem3);

        menuBar.getMenus().add(controls);
        return menuBar;
    }

    @Override
    public void start(Stage primaryStageLocal) throws Exception {
        primaryStage = primaryStageLocal;
        gameDialog = new GameDialog(primaryStage);

        primaryStage.setTitle("PUZZLE");
        primaryStage.setResizable(false);

        //Label label = new Label("The content inside the TitledPane");
        //Button button1 = new Button("Shuffle");
        MenuBar mb = createMenu();
        TitledPane titledPane = new TitledPane("Controls" , mb);
        //titledPane.setTitle("Controls");
        titledPane.setAnimated(true);
        titledPane.setExpanded(false);
        titledPane.setVisible(true);
        //titledPane.getChildren().addAll(label,button1);



        TilePane tile = new TilePane();
        BorderPane spainWithNoS = new BorderPane();

        spainWithNoS.setTop(titledPane);
        spainWithNoS.setBottom(tile);

        //VBox root = new VBox(tile);

    Scene scene = new Scene(spainWithNoS, SCREEN_WIDTH, SCREEN_HEIGHT);
        scene.getStylesheets().add("Viper.css");
        primaryStage.setScene(scene);        
        initButtons();        
        //tile.getChildren().add(mb);
        tile.getChildren().addAll(imageButtons);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }

}