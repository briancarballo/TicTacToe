package edu.quinnipiac.ser210.tictactoe;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import static edu.quinnipiac.ser210.tictactoe.R.drawable;

/**
 * Gameboard Activity
 * Author: Brian Carballo
 * SER210
 *
 * Activity contains the main game board and allows user to play the game.
 */

public class GameBoard extends Activity implements View.OnClickListener {

    TicTacToe game;
    public ArrayList<ImageButton> buttons;

    //Holds all the button ID's to initialize them all in a for loop
    public static final int[] BUTTON_IDS = {
            R.id.TTT0,
            R.id.TTT1,
            R.id.TTT2,
            R.id.TTT3,
            R.id.TTT4,
            R.id.TTT5,
            R.id.TTT6,
            R.id.TTT7,
            R.id.TTT8,

    };
    //Variables for handling end of game
    public int moves, winner;
    public boolean runGame;

    //Other displayable elements
    public TextView turn;
    public String name;
    public Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);

        //Sets the introductory text at the top of screen
        name = getIntent().getStringExtra(MainActivity.myKey);
        TextView welcomeText = (TextView) findViewById(R.id.welcome);
        welcomeText.setText("Welcome to Tic Tac Toe, " + name + "!");
        //Initializes display elements
        turn = (TextView) findViewById(R.id.turn);
        reset = (Button) findViewById(R.id.resetButton);
        winner = 0;

        //Initializes all buttons and adds to ArrayList for identification
        buttons = new ArrayList<ImageButton>();
        for (int id : BUTTON_IDS) {
            ImageButton button = (ImageButton) findViewById(id);
            button.setOnClickListener(this); // maybe
            buttons.add(button);
        }

        //Handles bundle contents
        if (savedInstanceState != null) {
            moves = savedInstanceState.getInt("moves");
            game = new TicTacToe((int[][]) savedInstanceState.getSerializable("gameBoard"),moves);
            runGame = savedInstanceState.getBoolean("gameState");
            if (runGame == false) reset.setVisibility(View.VISIBLE);

        } else {
            game = new TicTacToe();
            moves = 0;
            runGame = true;

        }
        printBoard();
    }


    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //Resets game values
            case R.id.resetButton:
                game.clearBoard();
                printBoard();
                moves = 0;
                runGame = true;
                turn.setText("Your Turn!");
                reset.setVisibility(View.INVISIBLE);
                break;

            case R.id.quitButton:
                Intent backToStart = new Intent(this, MainActivity.class);
                startActivity(backToStart);
                break;

            //Handles all of the game buttons
            default:
                //Makes sure that game hasn't ended before doing anything
                if (moves < 9 && runGame) {
                    /* Identifies button pressed by taking the index of the button from the array
                     which is mapped according to position on the board*/
                    game.setMove(1, buttons.indexOf(findViewById(v.getId())));
                    moves++;
                    //Ends game if player wins
                    winner = game.checkForWinner();
                    if(winner != 0) {
                        runGame = false;
                        reset.setVisibility(View.VISIBLE);
                    }
                }
                if (moves < 9 && runGame) {
                    turn.setText("Computer's turn");
                    game.setMove(2, game.getComputerMove());
                    moves++;
                    winner = game.checkForWinner();
                    if (winner != 0) {
                        runGame = false;
                        reset.setVisibility(View.VISIBLE);
                    }
                    else turn.setText("Your turn, " + name);
                }


                //If there's a winner, display it under the board
                switch (winner) {
                    case 0:
                        break;
                    case 2:
                        turn.setText(name + " wins!");
                        runGame = false;
                        break;
                    case 3:
                        turn.setText("Computer wins! :(");
                        runGame = false;
                        break;
                    case 1:
                        turn.setText("Its a tie!");
                        runGame = false;
                        break;
                }
                printBoard();
                break;
        }

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Places the game array from the TicTacToe class, number of moves, and game state into bundle
        outState.putSerializable("gameBoard",game.getBoard());
        outState.putInt("moves", moves);
        outState.putBoolean("gameState",runGame);
    }

    public void printBoard() {
        //Grabs array from TicTacToe
        Integer[] state = game.printBoard();
        //Counter iterates through button array
        int counter = 0;
        //Determines what image is placed over the button based on current game state
        for (int i : state) {
            switch (i) {
                case 0:
                    buttons.get(counter).setBackgroundResource(android.R.color.darker_gray);
                    break;
                case 1:
                    buttons.get(counter).setBackgroundResource(drawable.ttto);
                    break;
                case 2:
                    buttons.get(counter).setBackgroundResource(drawable.tttx);

                    break;
            }
            counter++;
        }

    }
}



