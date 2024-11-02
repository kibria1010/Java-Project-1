package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class HelloController {

    @FXML
    private Button b00;

    @FXML
    private Button b01;

    @FXML
    private Button b02;

    @FXML
    private Button b10;

    @FXML
    private Button b11;

    @FXML
    private Button b12;

    @FXML
    private Button b20;

    @FXML
    private Button b21;

    @FXML
    private Button b22;

    @FXML
    private Button playAgain;

    @FXML
    private GridPane board;

    @FXML
    private Label name;

    @FXML
    private Label gameOver;

    @FXML
    private Label winningMassage;

    static int[][] B = new int[3][3];
    static int i, j;
    Button b=new Button();
    int flag=1;
    @FXML
    void onClickBoard(ActionEvent e) {
        i=e.toString().charAt(43)-48;
        j=e.toString().charAt(44)-48;

        b = (Button) e.getSource();
        if(flag==1) {
            b.setText("P1");
            b.setStyle("-fx-background-color: #00ff00");
            B[i][j]=1;
            flag=2;
        } else if (flag==2) {
            b.setText("P2");
            b.setStyle("-fx-background-color: yellow");
            B[i][j]=2;
            flag=1;
        }
        b.setDisable(true);
        name.setText("P"+flag);
        checkWinner();
    }

    public void checkWinner(){
        String flag = "notDetected";
        int rowCountP1=0, rowCountP2=0, colCountP1=0, colCountP2=0, majorDiagP1=0, majorDiagP2=0, minorDiagP1=0, minorDiagP2=0, draw=0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //System.out.print(board[i][j]+" ");
                ///detectWiner along with row
                if(B[i][j]==1)     { rowCountP1++; }
                else if(B[i][j]==2){ rowCountP2++; }
                ///detectWiner along with column
                if(B[j][i]==1)     { colCountP1++; }
                else if(B[j][i]==2){ colCountP2++; }
                ///detectWiner along with major diagonal
                if(i==j && B[i][j]==1)     { majorDiagP1++; }
                else if(i==j && B[i][j]==2){ majorDiagP2++; }
                ///detectWiner along with minor diagonal
                if(i+j==2 && B[i][j]==1)     { minorDiagP1++; }
                else if(i+j==2 && B[i][j]==2){ minorDiagP2++; }
                ///detectDraw
                if((B[i][j]==1 || B[i][j]==2))     { draw++; }

            }
            //System.out.println("");
            if(rowCountP1==3 || colCountP1==3 || majorDiagP1==3 || minorDiagP1==3) { flag = "p1 won...!"; break; }
            if(rowCountP2==3 || colCountP2==3 || majorDiagP2==3 || minorDiagP2==3) { flag = "p2 won...!"; break; }
            if(draw==9) { flag = "Draw...!"; break; }
            rowCountP1=0; rowCountP2=0;
            colCountP1=0; colCountP2=0;
        }
        //System.out.println("-------------------------");
        if(flag.endsWith("!")) {

            winningMassage.setText(flag);
            gameOver.setText("Game Over!");
            makeDisable();
        }
    }

    public void makeDisable() {
        b00.setDisable(true);
        b01.setDisable(true);
        b02.setDisable(true);
        b10.setDisable(true);
        b11.setDisable(true);
        b12.setDisable(true);
        b20.setDisable(true);
        b21.setDisable(true);
        b22.setDisable(true);

        playAgain.setDisable(false);
        playAgain.setStyle("-fx-background-color: green");
        playAgain.setText("PLAY AGAIN");
    }

    @SuppressWarnings("exports")
	public void play_again(ActionEvent e){

        playAgain.setStyle("-fx-background-color: none");
        playAgain.setText("");
        playAgain.setDisable(true);

        winningMassage.setText("");
        gameOver.setText("");
        flag = 1;
        name.setText("P1");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                B[i][j] = 0;
            }
        }

        b00.setDisable(false);
        b00.setText("");
        b00.setStyle("-fx-background-color: none");

        b01.setDisable(false);
        b01.setText("");
        b01.setStyle("-fx-background-color: none");

        b02.setDisable(false);
        b02.setText("");
        b02.setStyle("-fx-background-color: none");

        b10.setDisable(false);
        b10.setText("");
        b10.setStyle("-fx-background-color: none");

        b11.setDisable(false);
        b11.setText("");
        b11.setStyle("-fx-background-color: none");

        b12.setDisable(false);
        b12.setText("");
        b12.setStyle("-fx-background-color: none");

        b20.setDisable(false);
        b20.setText("");
        b20.setStyle("-fx-background-color: none");

        b21.setDisable(false);
        b21.setText("");
        b21.setStyle("-fx-background-color: none");

        b22.setDisable(false);
        b22.setText("");
        b22.setStyle("-fx-background-color: none");

    }

}
