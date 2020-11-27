package com.example.gameofwarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

public class StartNewGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_new_game);
    }

    public void playTheGame(BoardState boardToPass) {
        // random determination of which player goes first
        int num = (int) (Math.random() * 10);
        if (num % 2 == 0) boardToPass.turn = "north";

        Intent intent = new Intent(this, GamePlayActivity.class);
        intent.putExtra("board to pass", (Parcelable) boardToPass);
        startActivity(intent);
    }

    public void usePumpHouse(View view) {
        BoardState boardToPass = StockStates.makePumpHouse();
        playTheGame(boardToPass);
    }

    public void useRioDeJaneiro(View view) {
        BoardState boardToPass = StockStates.makeRioDeJaneiro();
        playTheGame(boardToPass);
    }

    public void use1800Marengo(View view) {
        BoardState boardToPass = StockStates.make1800Marengo();
        playTheGame(boardToPass);
    }

    public void use1804Austerlitz(View view) {
        BoardState boardToPass = StockStates.make1804Austerlitz();
        playTheGame(boardToPass);
    }

    public void useDemoForWins(View view) {
        BoardState boardToPass = StockStates.makeDemoForWins();
        playTheGame(boardToPass);
    }

    public void useDemoForRetreat(View view) {
        BoardState boardToPass = StockStates.makeDemoForRetreat();
        playTheGame(boardToPass);
    }

    public void useDemoForNoRetreat(View view) {
        BoardState boardToPass = StockStates.makeDemoForNoRetreat();
        playTheGame(boardToPass);
    }
}