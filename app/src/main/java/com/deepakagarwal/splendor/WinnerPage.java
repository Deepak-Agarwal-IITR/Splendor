package com.deepakagarwal.splendor;

import static com.deepakagarwal.splendor.AskNumberOfPlayers.game;
import static com.deepakagarwal.splendor.MainActivity.ringBackground;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WinnerPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_page);
        TextView winnerName = (TextView)findViewById(R.id.winnerName);

        winnerName.setText("Congratulations "+getWinner()+"!\nYou won.");
        LinearLayout name = (LinearLayout)findViewById(R.id.nameLayout);
        LinearLayout score = (LinearLayout)findViewById(R.id.scoreLayout);
        LinearLayout cards = (LinearLayout)findViewById(R.id.cardsLayout);
        for(int x = 0;x<game.numOfPlayers;x++){
            TextView nameText = new TextView(this);
            TextView scoreText = new TextView(this);
            TextView cardsText = new TextView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            nameText.setText(""+game.playerName[x]);
            scoreText.setText(""+game.playerTable[x][0][5]);
            cardsText.setText(""+getCards(x));
            nameText.setTextSize(30);
            scoreText.setTextSize(30);
            cardsText.setTextSize(30);
            nameText.setGravity(Gravity.CENTER);
            scoreText.setGravity(Gravity.CENTER);
            cardsText.setGravity(Gravity.CENTER);
            nameText.setLayoutParams(layoutParams);
            scoreText.setLayoutParams(layoutParams);
            cardsText.setLayoutParams(layoutParams);
            name.addView(nameText);
            score.addView(scoreText);
            cards.addView(cardsText);
        }

        ringBackground.start();
        ringBackground.setLooping(true);
        ringBackground.setVolume(100,100);
    }

    public String getWinner(){
        String winnerName = game.playerName[0];
        int winner = 0;
        int maxScore = game.playerTable[0][0][5];

        for(int x = 1 ;x<game.numOfPlayers;x++){
            if(maxScore == game.playerTable[x][0][5]){
                if(getCards(winner) > getCards(x)){
                    winner = x;
                    maxScore = game.playerTable[x][0][5];
                    winnerName = game.playerName[x];
                }
                else if(getCards(winner) == getCards(x)){
                    winnerName+= ", "+game.playerName[x];
                }
            }
            else if(maxScore < game.playerTable[x][0][5]){
                winner = x;
                maxScore = game.playerTable[x][0][5];
                winnerName = game.playerName[x];
            }
        }
        return winnerName;
    }

    public int getCards(int x){
        int numOfTotalCards = 0;
        for(int y = 0;y<5;y++){
            numOfTotalCards += game.playerTable[x][0][y];
        }
        return numOfTotalCards;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ringBackground.start();
        ringBackground.setLooping(true);
        ringBackground.setVolume(100,100);
    }

    public void backToGame(View view){
        Intent intent = new Intent(this,AskNumberOfPlayers.class);
        startActivity(intent);
        finish();
    }
}