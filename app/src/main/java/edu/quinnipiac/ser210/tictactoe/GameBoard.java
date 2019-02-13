package edu.quinnipiac.ser210.tictactoe;

import android.app.Activity;
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

    TicTacToe game = new TicTacToe();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        String value = getIntent().getStringExtra(MainActivity.myKey);
        TextView returnValue = (TextView) findViewById(R.id.welcome);
        returnValue.setText("Welcome to Tic Tac Toe, " + value + "!");
        buttons = new ArrayList<ImageButton>();
        for(int id : BUTTON_IDS) {
            ImageButton button = (ImageButton)findViewById(id);
            button.setOnClickListener(this); // maybe
            buttons.add(button);
        }
    }


    @Override
    public void onClick(View v) {
        Log.d("Index of button", String.valueOf(buttons.indexOf(findViewById(v.getId()))));
       game.setMove(1,buttons.indexOf(findViewById(v.getId())));
       game.setMove(2,game.getComputerMove());

       printBoard();
    }

    public void printBoard(){
        Integer[] state = game.printBoard();
        int counter = 0;
        for (int i: state) {
            switch (i){
                case 0: buttons.get(counter).setBackgroundResource(drawable.square); break;
                case 1: buttons.get(counter).setBackgroundResource(drawable.o); break;
                case 2: buttons.get(counter).setBackgroundResource(drawable.x); break;


            }
            counter++;
        }

    }
}



