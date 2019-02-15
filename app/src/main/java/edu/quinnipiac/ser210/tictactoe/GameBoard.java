package edu.quinnipiac.ser210.tictactoe;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static edu.quinnipiac.ser210.tictactoe.R.drawable;

public class GameBoard extends Activity implements View.OnClickListener {

    TicTacToe game;
    public ArrayList<ImageButton> buttons;
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



    public int moves, winner;
    public boolean runGame;
    public TextView turn;
    public String name;
    public Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        name = getIntent().getStringExtra(MainActivity.myKey);
        TextView returnValue = (TextView) findViewById(R.id.welcome);
        returnValue.setText("Welcome to Tic Tac Toe, " + name + "!");
        turn = (TextView) findViewById(R.id.turn);
        reset = (Button) findViewById(R.id.resetButton);
        buttons = new ArrayList<ImageButton>();
        for (int id : BUTTON_IDS) {
            ImageButton button = (ImageButton) findViewById(id);
            button.setOnClickListener(this); // maybe
            buttons.add(button);
        }
        winner = 0;
        if (savedInstanceState != null) {
            moves = savedInstanceState.getInt("moves");
            game = new TicTacToe((int[][]) savedInstanceState.getSerializable("gameBoard"),moves);
            runGame = savedInstanceState.getBoolean("gameState");

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
            case R.id.resetButton:
                game.clearBoard();
                printBoard();
                moves = 0;
                runGame = true;
                turn.setText("Your Turn!");
                reset.setVisibility(View.INVISIBLE);
                break;
            //Log.d("Index of button", String.valueOf(buttons.indexOf(findViewById(v.getId()))));
            case R.id.quitButton:
                Intent backToStart = new Intent(this, MainActivity.class);
                startActivity(backToStart);
                break;
            default:
                if (moves < 9 && runGame) {

                    game.setMove(1, buttons.indexOf(findViewById(v.getId())));
                    moves++;
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
        outState.putSerializable("gameBoard",game.getBoard());
        outState.putInt("moves", moves);
        outState.putBoolean("gameState",runGame);
    }

    public void printBoard() {
        Integer[] state = game.printBoard();
        int counter = 0;
        for (int i : state) {
            switch (i) {
                case 0:
                    buttons.get(counter).setBackgroundResource(android.R.color.darker_gray);
                    //buttons.get(counter).setBackgroundResource(drawable.square);
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



