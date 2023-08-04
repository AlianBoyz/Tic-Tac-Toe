package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Start_Game =  findViewById(R.id.startGameButton);
        EditText playerOne = findViewById(R.id.playerOne);
        EditText playerOne2 = findViewById(R.id.playerOne2);

        Start_Game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getplayer_one = playerOne.getText().toString();
                String getplayer_two = playerOne2.getText().toString();
                if (getplayer_one.isEmpty()||getplayer_two.isEmpty()||getplayer_two.isBlank()||getplayer_one==" ") {
                  Toast.makeText(getApplicationContext(), "Fill the Player details", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(MainActivity.this,Game_Screen.class);
                    intent.putExtra("player_one",getplayer_one);
                    intent.putExtra("player_two",getplayer_two);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to EXIT?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }
}