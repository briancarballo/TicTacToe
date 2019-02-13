package edu.quinnipiac.ser210.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        String value = getIntent().getStringExtra(MainActivity.myKey);
        TextView returnValue = (TextView) findViewById(R.id.welcome);
        returnValue.setText("Welcome to Tic Tac Toe, " + value + "!");
    }


}
