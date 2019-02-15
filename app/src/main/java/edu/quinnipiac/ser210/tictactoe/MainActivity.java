package edu.quinnipiac.ser210.tictactoe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Main Activity
 * Author: Brian Carballo
 * SER210
 *
 * Activity is mostly for navigation and sets name of player.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    public static final String myKey = "ID_ONE";
    public String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){

        switch(v.getId())
        {
            case R.id.Start_Button:
                final Intent startGame = new Intent(this,GameBoard.class);
                //Asks for name if one isn't already given;
                if (name == null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("What is your name?");

                    final EditText input = new EditText(this);
                    builder.setView(input);

                    builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            name = input.getText().toString();
                            startGame.putExtra(myKey, name);
                            startActivity(startGame);
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();
                    break;
                }
                //Passes name to next activity
                startGame.putExtra(myKey, name);
                startActivity(startGame);
                break;
            case R.id.Help_Button:
                Intent help = new Intent(this,Help_Screen.class);
                startActivity(help);
                break;
                //Changes name if user wants to
            case R.id.Name_Button:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("What is your name?");

                final EditText input = new EditText(this);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        name = input.getText().toString();
                        System.out.print(name);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                break;

            default:
                throw new RuntimeException("Unknown button ID");
        }
    }






}
