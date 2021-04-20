/* online sources
https://developer.android.com/reference/android/widget/LinearLayout
https://www.codota.com/code/java/methods/android.widget.Button/setOnClickListener*/

/*My Virtual Device: Pixel_3a_API_30_x86*/
package com.zahra.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //array for creating game board
    private Button[][] buttons = new Button[3][3];

    //count rounds
    private int countRound;

    //player one will start
    private boolean playerOneTurn = true;

    //player one and two points
    private int playerOnePoint;
    private int playerTwoPoint;

    //display points of player
    private TextView txtViewPlayerOne;
    private TextView txtViewPlayerTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //reference to text view
        txtViewPlayerOne = findViewById(R.id.txtViewPlayer1);
        txtViewPlayerTwo = findViewById(R.id.txtViewPlayer2);

        //go through all rows and columns in button array
        for(int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                String btnID = "btn" + i + j;

                int resID = getResources().getIdentifier(btnID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        //Reset Game Button
        Button btnReset = findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetGame();

            }
        });
    }

    @Override
    public void onClick(View v) {

        //Check if reset button was clicked or not
        if(!((Button) v).getText().toString().equals("")){
            return;
        }

        if(playerOneTurn){
            ((Button) v).setText("x");
        }
        //player two turn
        else{
            ((Button) v).setText("o");
        }

        countRound++;
        if(checkWinner()){
            if (playerOneTurn){
                playerOneWins();
            }
            else{
                playerTwoWins();
            }
        }
        // no winners but there is draw
        else if (countRound == 9){
            draw();
        }
        //no winners and no draw then switch turns
        else {
            playerOneTurn = !playerOneTurn;

        }
    }

    //check winner by going through all rows and columns
    private boolean checkWinner() {

        //record the text of button to string array
        String[][] btnText = new String[3][3];
        for(int i=0; i<3; i++) {
            for (int j = 0; j < 3; j++) {

                btnText[i][j] = buttons[i][j].getText().toString();
            }
        }

        //check rows
        for(int i=0; i<3; i++) {
            if (btnText[i][0].equals(btnText[i][1]) && btnText[i][0].equals(btnText[i][2])
                    && !btnText[i][0].equals("")){
                return true;
            }
        }

        //check columns
        for(int i=0; i<3; i++) {
            if (btnText[0][i].equals(btnText[1][i]) && btnText[0][i].equals(btnText[2][i])
                    && !btnText[0][i].equals("")){
                return true;
            }
        }

        //check diagonals from top right to the bottom left
        if (btnText[0][2].equals(btnText[1][1]) && btnText[0][2].equals(btnText[2][0])
                && !btnText[0][2].equals("")){
            return true;
        }

        //check diagonals from top left to the bottom right
        if (btnText[0][0].equals(btnText[1][1]) && btnText[0][0].equals(btnText[2][2])
                && !btnText[0][0].equals("")){
            return true;
        }
        return false;
    }

    private void playerOneWins(){
        playerOnePoint++;
        Toast.makeText(this, "Player One Wins (:", Toast.LENGTH_LONG).show();
        updatePointText();
        resetBoard();
    }

    private void playerTwoWins(){
        playerTwoPoint++;
        Toast.makeText(this, "Player Two Wins (:", Toast.LENGTH_LONG).show();
        updatePointText();
        resetBoard();
    }

    private void draw(){
        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointText(){
       txtViewPlayerOne.setText("Player One" + playerOnePoint);
        txtViewPlayerTwo.setText("Player Two" + playerTwoPoint);
    }

    //Reset Board Game
    private void resetBoard(){
        //reset all buttons
        for(int i=0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        countRound = 0;
        playerOneTurn = true;
    }

    //Reset Game
    private void resetGame(){

        playerOnePoint = 0;
        playerTwoPoint = 0;
        updatePointText();
        resetBoard();

    }
}