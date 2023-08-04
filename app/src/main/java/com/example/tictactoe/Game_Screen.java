package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Game_Screen extends AppCompatActivity {
    int active=0;
    int[] grid = {2, 2 , 2, 2, 2, 2, 2, 2, 2};
    int win[][]={{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6},
                {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    int Score_O=0,Score_X=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        Button ResetButton=findViewById(R.id.ResetButton);
        Button NewGame=findViewById(R.id.newGameButton);
        String player_st=getIntent().getStringExtra("player_one");
        String player_nd=getIntent().getStringExtra("player_two");
        TextView disp_palyerOne=findViewById(R.id.playerViwe1);
        TextView disp_palyerTwo=findViewById(R.id.playerViwe2);
        disp_palyerOne.setText(player_st);
        disp_palyerTwo.setText(player_nd);
        NewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Game_Screen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameReset();
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
    public void gameReset()
    {
        int active=0;
        for (int i=0;i<grid.length;i++)
            grid[i]=2;
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        return;
    }
    public void tap(View view) {
        ImageView img = (ImageView) view;
        int tapped = Integer.parseInt(img.getTag().toString());
        if(grid[tapped-1] == 2) {
            grid[tapped-1] = active;
            if (active == 0) {
                img.setImageResource(R.drawable.ximage);
                active = 1;
            } else {
                img.setImageResource(R.drawable.oimage);
                active = 0;
            }
        }
        winning_check();
    }
    private void updatePlayerScore()
    {
        TextView score_o=findViewById(R.id.player_o_s);
        TextView score_t=findViewById(R.id.player_t_s);
        score_t.setText(Integer.toString(Score_X));
        score_o.setText(Integer.toString(Score_O));
    }
    private void winning_check()
    {
        for(int[] win: win){
            if(grid[win[0]] == grid[win[1]] &&
                    grid[win[1]] == grid[win[2]] &&
                    grid[win[0]]!=2){
                String winner;
                if(grid[win[0]] == 1) {
                    Score_O+=1;
                    Toast.makeText(getApplicationContext(), "!!! O Wins !!! \n X's Turn", Toast.LENGTH_SHORT).show();
                    updatePlayerScore();
                    dialog_call("!!! O Wins !!! \n X's Turn");
                }
                else if(grid[win[0]] == 0) {
                    Score_X+=1;
                    Toast.makeText(getApplicationContext(), "!!! X Wins !!! \n O's Turn", Toast.LENGTH_SHORT).show();
                    updatePlayerScore();
                    dialog_call("!!! X Wins !!! \n O's Turn");
                }
                if (grid[win[0]]!=0 && grid[win[0]]!=1)
                    Toast.makeText(getApplicationContext(), "NO One Wins !!!DRAW!!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void dialog_call(String s)
    {
        new AlertDialog.Builder(this)
                .setMessage(s)
                .setCancelable(false)
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gameReset();
                    }
                })
                .setNegativeButton("New Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Game_Screen.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .show();
    }
}
