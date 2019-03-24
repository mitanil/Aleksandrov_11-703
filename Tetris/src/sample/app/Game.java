package sample.app;

import com.sun.istack.internal.NotNull;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class Game extends Application {

    public static final int WIDTH_MAIN_CANVAS = 290;
    public static final int HEIGHT_MAIN_CANVAS = 580;
    public static final int WIDTH_NEXT_CANVAS = 80;
    public static final int HEIGHT_NEXT_CANVAS = 80;

    static AtomicBoolean moveLeft = new AtomicBoolean(false);
    static AtomicBoolean moveRight = new AtomicBoolean(false);
    static AtomicBoolean moveDown = new AtomicBoolean(false);
    static AtomicBoolean rotate = new AtomicBoolean(false);
    static AtomicBoolean isOver = new AtomicBoolean(false);


    public static void pMoveLeft() {
        Game.moveLeft.set(true);
    }

    public static void pMoveRight() {
        Game.moveRight.set(true);
    }

    public static void pRotate() {
        Game.rotate.set(true);
    }

    public static void pMoveDown() {
        Game.moveDown.set(true);
    }


    private Label scoreLabel;
    private Label levelLable;
    Canvas mainCanvas;
    Canvas nextCanvas;
    private static Client client;

    static int score;
    static int level;
    static int nextFigure;

    int figurePose = 0;
    int currentFigure = 0;

    private int delay = 600;


    static Integer[][] mainMap;
    private Integer[][] opponentMap;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        mainCanvas = (Canvas) root.lookup("#mainCanvas");
        nextCanvas = (Canvas) root.lookup("#nextCanvas");
        primaryStage.setScene(new Scene(root, 400, 600));
        scoreLabel = (Label) root.lookup("#scoreValue");
        levelLable = (Label) root.lookup("#levelValue");
        primaryStage.show();


        mainMap = new Integer[10][20];


        for(int i = 0; i < mainMap.length; i++){
            for(int j = 0; j < mainMap[i].length; j++){
                mainMap[i][j] = 0;
            }
        }
        client = new Client();
        client.startConnection("127.0.0.1", 6666);

        client.getIn().readObject();


        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(task);
    }

    private void gameOverPrint(GraphicsContext gc, int width, int height) {
        for (int k = 0; k < mainMap.length; k++) {
            for (int f = 0; f < mainMap[k].length; f++) {
                mainMap[k][f] = 1;
            }
        }
        gc.setFill(Color.WHITE);
        gc.fillRoundRect(0, 0, width, height, 0, 0);
        Image img = new Image("OPdf2Dd.png");
        gc.drawImage(img, 3, 200, 277,132);
    }


    Task<Void> task = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            boolean figureIsFall =false;
            Instant lastMove = Instant.now();
            nextFigure = (new Random()).nextInt(7);
            currentFigure = (new Random()).nextInt(7);
            spawnFigure(currentFigure);
            ArrayList<ArrayList<Integer>> arr1 = new ArrayList<>(10);
            for(int i = 0; i < mainMap.length; i++){
                arr1.add(new ArrayList<>(Arrays.asList(mainMap[i])));
            }
            client.getOut().writeObject(arr1);

            while (!isOver.get()){
                boolean isChanged = false;

                if(Game.moveLeft.get()){
                    moveLeft();
                    Game.moveLeft.set(false);
                    isChanged = true;

                }

                if(Game.moveRight.get()) {
                    moveRight();
                    Game.moveRight.set(false);
                    isChanged = true;
                }

                if(Game.moveDown.get()){
                    moveFigureDown(figureIsFall);
                    lastMove = Instant.now();
                    Game.moveDown.set(false);
                    isChanged = true;
                }

                if(Game.rotate.get()){
                    rotateFigure();
                    Game.rotate.set(false);
                    isChanged = true;
                }


                if(!lastMove.isAfter(Instant.now().minusMillis(delay))){
                    moveFigureDown(figureIsFall);
                    lastMove = Instant.now();
                    isChanged = true;
                }

                if(isChanged){
                    Platform.runLater(()->{
                        updateScore(score);
                        updateLevel(level);
                        try {
                            ArrayList<ArrayList<Integer>> arr = new ArrayList<>(10);
                            for(int i = 0; i < mainMap.length; i++){
                                arr.add(new ArrayList<>(Arrays.asList(mainMap[i])));
                            }
                            client.getOut().writeObject(arr);
                            arr = (ArrayList<ArrayList<Integer>>) client.getIn().readObject();
                            opponentMap = new Integer[arr.size()][arr.get(0).size()];
                            for (int i = 0; i < arr.size(); i++) {
                                for(int j = 0; j < arr.get(i).size(); j++){
                                    opponentMap[i][j] = arr.get(i).get(j);
                                }
                            }
//                            client.getOut().writeObject(mainMap);
//                            opponentMap = (Integer[][])client.getIn().readObject();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        drawFigures(mainCanvas.getGraphicsContext2D(), WIDTH_MAIN_CANVAS, HEIGHT_MAIN_CANVAS, mainMap, 27, 1, opponentMap);
                        drawFigures(nextCanvas.getGraphicsContext2D(), WIDTH_NEXT_CANVAS, HEIGHT_NEXT_CANVAS, getFigure(nextFigure, 0), 18, 1, null);
                    });
                }
                Thread.sleep(10);
            }

            Platform.runLater(()->{
                gameOverPrint(mainCanvas.getGraphicsContext2D(), WIDTH_MAIN_CANVAS, HEIGHT_MAIN_CANVAS);
            });

            client.stopConnection();
            return null;
        }
    };

    private void updateScore(int score) {
        scoreLabel.setText(String.valueOf(score * 100));
    }
    private void updateLevel(int level) {
        levelLable.setText(String.valueOf(level));
    }


    private void drawFigures(@NotNull GraphicsContext gc, int width, int height, Integer[][] matrix, int figureSize, int space, Integer[][] secondPlayerMap) {
        gc.setFill(Color.WHITE);
        gc.fillRoundRect(0, 0, width, height, 0, 0);
        if(secondPlayerMap != null){
            for(int i = 0; i < secondPlayerMap.length; i++){
                for(int j = 0; j < secondPlayerMap[i].length; j++){
                    if(secondPlayerMap[i][j] != 0) {
                        int leftPadd = (secondPlayerMap.length - i - 1) * (figureSize + space * 2) + space;
                        int upPadd = j * (figureSize + space * 2) + space;
                        gc.setLineWidth(4);
                        gc.setStroke(Color.PINK);
                        gc.strokeRoundRect(leftPadd, upPadd, figureSize, figureSize, 15, 15);
                    }
                }
            }
        }
        if(matrix != null){
            for(int i = 0; i < matrix.length; i++){
                for(int j = 0; j < matrix[i].length; j++){
                    if(matrix[i][j] != 0) {
                        int leftPadd = (matrix.length - i - 1) * (figureSize + space * 2) + space;
                        int upPadd = j * (figureSize + space * 2) + space;
                        gc.setLineWidth(4);
                        gc.setStroke(Color.BLACK);
                        gc.strokeRoundRect(leftPadd, upPadd, figureSize, figureSize, 15, 15);
                    }
                }
            }
        }
    }


    private Integer[][] getFigure(int figureNumber, int rotate){
        Integer[][] figure = null;
        switch (figureNumber){
            case 0:
                figure = new Integer[][]{
                        {0, 0, 0, 0},
                        {0, 2, 2, 0},
                        {0, 2, 3, 0},
                        {0, 0, 0, 0}
                };
                break;
            case 1:
                figure = new Integer[][]{
                        {0, 0, 0, 0},
                        {0, 2, 0, 0},
                        {2, 3, 2, 0},
                        {0, 0, 0, 0},
                };
                break;
            case 2:
                figure = new Integer[][]{
                        {0, 0, 0, 0},
                        {0, 2, 3, 2},
                        {0, 2, 0, 0},
                        {0, 0, 0, 0},
                };
                break;
            case 3:
                figure = new Integer[][]{
                        {0, 0, 0, 0},
                        {0, 2, 0, 0},
                        {0, 2, 3, 2},
                        {0, 0, 0, 0},
                };
                break;
            case 4:
                figure = new Integer[][]{
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                        {2, 3, 2, 2},
                        {0, 0, 0, 0}
                };
                break;
            case 5:
                figure = new Integer[][]{
                        {0, 0, 0, 0},
                        {0, 0, 2, 2},
                        {0, 2, 3, 0},
                        {0, 0, 0, 0}
                };
                break;
            case 6:
                figure = new Integer[][]{
                        {0, 0, 0, 0},
                        {0, 2, 2, 0},
                        {0, 0, 3, 2},
                        {0, 0, 0, 0}
                };
                break;
            default:
                figureNumber = 0;
                figure = new Integer[][]{
                        {0, 0, 0, 0},
                        {0, 2, 2, 0},
                        {0, 2, 3, 0},
                        {0, 0, 0, 0}
                };
        }


        if(rotate%4 != 0) {
            if (figureNumber != 0) {
                if(rotate%4 == 1){
                    figure = rotateOnce(figure);
                }
                if(rotate%4 == 2){
                    figure = rotateTwice(figure);
                }
                if(rotate%4 == 3){
                    figure = rotateTwice(figure);
                    figure = rotateOnce(figure);
                }
            }
        }


        return figure;
    }


    private Integer[][] rotateTwice(Integer[][] figure) {
        Integer[][] temp = new Integer[figure[0].length][figure.length];
        for(int i = 0; i < figure.length; i++){
            for(int j = 0; j < figure[i].length; j++){
                temp[figure.length-1-i][figure.length-1-j] = figure[i][j];
            }
        }
        return temp;
    }

    private Integer[][] rotateOnce(Integer[][] figure) {
        Integer[][] temp = new Integer[figure[0].length][figure.length];
        for (int i = 0; i < figure.length; i++) {
            for (int j = 0; j < figure[i].length; j++) {
                temp[j][figure.length - 1 - i] = figure[i][j];
            }
        }
        return temp;
    }


    public static void main(String[] args) {
        launch(args);
    }


    private void rotateFigure() {
        figurePose++;
        Integer [][] figure = getFigure(currentFigure, figurePose);
        printFigure(figure);
    }

    private void printFigure(Integer[][] figure){
        int figCenterInFieldY = 0;
        int figCenterInFieldX = 0;

        int figCenterY = 0;
        int figCenterX = 0;

        boolean isFigureExist = false;
        for(int i = 0; i < mainMap.length; i++){
            for(int j = 0; j < mainMap[i].length; j++){
                if(mainMap[i][j] == 2) mainMap[i][j] = 0;
                if(mainMap[i][j] == 3){
                    figCenterInFieldY = i;
                    figCenterInFieldX = j;
                    isFigureExist = true;
                }
            }
        }
        if(!isFigureExist) return;
        for(int i = 1; i < figure.length; i++){
            for(int j = 1; j < figure[i].length; j++){
                if(figure[i][j] == 3){
                    figCenterY = i;
                    figCenterX = j;
                }
            }
        }

        boolean isContionue = true;
        while (isContionue){
            boolean isBroken = false;
            for(int i = 0;i < figure.length && !isBroken; i++){
                for(int j = 0; j < figure[i].length; j++){
                    int y = i - figCenterY;
                    int x = j - figCenterX;
                    if((figure[i][j] == 2 || figure[i][j] == 3) && figCenterInFieldX + x < 0) {
                        Game.isOver.set(true);
                        System.out.println("Game Over");
                        return;
                    }
                    if ((figure[i][j] == 2 || figure[i][j] == 3) && figCenterInFieldY + y < 0) {
                        mainMap[figCenterInFieldY][figCenterInFieldX] = 0;
                        if(mainMap[figCenterInFieldY+1][figCenterInFieldX] == 0 ){
                            mainMap[figCenterInFieldY+1][figCenterInFieldX] = 3;
                            figCenterInFieldY++;
                        }else{
                            mainMap[figCenterInFieldY][figCenterInFieldX-1] = 3;
                            figCenterInFieldX--;
                        }
                        isBroken = true;
                        break;
                    }
                    if((figure[i][j] == 2 || figure[i][j] == 3) && figCenterInFieldY + y >= mainMap.length) {
                        mainMap[figCenterInFieldY][figCenterInFieldX] = 0;
                        if(mainMap[figCenterInFieldY-1][figCenterInFieldX]  == 0){
                            mainMap[figCenterInFieldY-1][figCenterInFieldX] = 3;
                            figCenterInFieldY--;
                        }else{
                            mainMap[figCenterInFieldY][figCenterInFieldX-1] = 3;
                            figCenterInFieldX--;
                        }
                        isBroken = true;
                        break;
                    }
                    if((figure[i][j] == 2 || figure[i][j] == 3) && mainMap[figCenterInFieldY+y][figCenterInFieldX+x] == 1){
                        mainMap[figCenterInFieldY][figCenterInFieldX] = 0;
                        if(figCenterInFieldX-1 < 0){
                            Game.isOver.set(true);
                            System.out.println("Game Over");
                            return;
                        }
                        mainMap[figCenterInFieldY][figCenterInFieldX-1] = 3;
                        figCenterInFieldX--;
                        isBroken = true;

                        break;
                    }
//                    mainMap[figCenterInFieldY+y][figCenterInFieldX+x] = 5;
//                    String str;
//                    for (int k = 0; k < mainMap.length; k++) {
//                        str = "";
//                        for (int f = 0; f < mainMap[k].length; f++) {
//                            str += mainMap[k][f] + " ";
//                        }
//                        System.out.println(str);
//                    }
//                    System.out.println("");


                }
            }
            if(!isBroken) isContionue = false;
        }

        for(int i = 0; i < figure.length; i++){
            for(int j = 0; j < figure[i].length; j++){
                if(figure[i][j] == 2){
                    int x = i - figCenterY;
                    int y = j - figCenterX;
                    mainMap[figCenterInFieldY + x][ figCenterInFieldX + y] = 2;
                }
            }
        }
    }

    private void moveRight() {
        boolean isMovable = true;
        int rowsChecked = 0;
        for (int i = 0; i < mainMap.length; i++){
            boolean isFigureFound = false;

            for(int j = 0; j < mainMap[i].length; j++){
                if(mainMap[i][j] == 2 || mainMap[i][j] == 3){
                    isFigureFound = true;
                    if(i == 0 || mainMap[i-1][j] == 1){
                        isMovable = false;
                        rowsChecked = 10;
                    }
                }
            }

            if (isFigureFound){
                rowsChecked++;
            }
            if (rowsChecked>1){
                break;
            }
        }
        if(!isMovable) return;

        for (int i = 0; i < mainMap.length; i++){
            for(int j = 0; j < mainMap[i].length; j++) {
                if (mainMap[i][j] == 2 || mainMap[i][j] == 3) {
                    mainMap[i-1][j] = mainMap[i][j];
                    mainMap[i][j] = 0;
                }
            }
        }
    }

    private void moveLeft(){
        boolean isMovable = true;
        int rowsChecked = 0;
        for (int i = mainMap.length-1; i >= 0; i--){
            boolean isFigureFound = false;

            for(int j = 0; j < mainMap[i].length; j++){
                if(mainMap[i][j] == 2 || mainMap[i][j] == 3){
                    isFigureFound = true;
                    if(i == mainMap.length-1 || mainMap[i+1][j] == 1){
                        isMovable = false;
                        rowsChecked = 10;
                    }
                }
            }

            if (isFigureFound){
                rowsChecked++;
            }
            if (rowsChecked>1){
                break;
            }
        }
        if(!isMovable) return;

        for (int i = mainMap.length-1; i >= 0; i--){
            for(int j = 0; j < mainMap[i].length; j++) {
                if (mainMap[i][j] == 2 || mainMap[i][j] == 3) {
                    mainMap[i+1][j] = mainMap[i][j];
                    mainMap[i][j] = 0;
                }
            }
        }
    }


    private void spawnFigure(Integer figureNumber){
        figurePose = 0;
        if(mainMap[5][1] != 1){
            mainMap[5][1] = 3;
            printFigure(getFigure(figureNumber, figurePose));
        }else {
            Game.isOver.set(true);
            System.out.println("Game Over");
        }
    }

    private void moveFigureDown(boolean figureIsFall){
        for (Integer[] ints : mainMap) {
            for (int j = 0; j < ints.length && !figureIsFall; j++) {
                if ((ints[j] == 2 || ints[j] == 3) && (j + 1 == ints.length || ints[j+1] == 1)) {
                    figureIsFall=true;
                    break;
                }
            }
        }
        if (figureIsFall) {
            Game.score += 2;
            Game.level = Game.score / 100 + 1;
            if(delay >= 100)
                delay -= 2;
            for (int i = 0; i < mainMap.length; i++) {
                for (int j = 0; j < mainMap[i].length; j++) {
                    if(mainMap[i][j] == 2 || mainMap[i][j] == 3)mainMap[i][j] = 1;
                }
            }


            for (int j = mainMap[0].length - 1; j >= 0; j--) {
                boolean isMove = false;
                int count = 0;
                for (int i = 0; i < mainMap.length; i++) {
                    if(mainMap[i][j] == 1){
                        count++;
                    }else break;
                }
                if(count == 10) {
                    Game.score += 5;
                    isMove = true;
                }
                if(isMove){
                    for(int k = j; k >= 0; k--) {
                        for (int i = 0; i < mainMap.length; i++){
                            if (k - 1 >= 0)
                                mainMap[i][k] = mainMap[i][k - 1];
                            else {
                                mainMap[i][k] = 0;
                            }
                        }
                    }
                }
                if(count == 10) j++;
            }
            currentFigure = Game.nextFigure;
            spawnFigure(currentFigure);
            Game.nextFigure = (new Random()).nextInt(7);
        } else {
            for (int i = 0; i < mainMap.length; i++) {
                for (int j = mainMap[i].length -1; j >= 0; j--) {
                    if(mainMap[i][j] == 2 || mainMap[i][j] == 3){
                        mainMap[i][j+1] = mainMap[i][j];
                        mainMap[i][j] = 0;
                    }
                }
            }

        }
    }

}
