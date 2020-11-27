package com.example.gameofwarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import playmanager.PlayManager;
import playmanager.iPlayManager;

public class GamePlayActivity extends AppCompatActivity {
    int[] editTextIDs;  // array for holding the ids of the fields that collect move data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        // Get the Intent that started this activity and extract the BoardState that it contained
        Intent intent = getIntent();
        BoardState boardToDraw = intent.getParcelableExtra("board to pass");

        // debug
//        BoardState boardToDraw = StartNewGameActivity.makePumpHouse();

        //  into Manifest
        TableLayout gameBoardTable = (TableLayout) findViewById(R.id.table_layout);

        int numCols = 21;
        int numRows = 26;

    //        // Here's something that might eventually be useful to get on more screens? (if debugged):
//            DisplayMetrics displayMetrics = new DisplayMetrics();
//            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//            int cellWidth = displayMetrics.widthPixels / (numCols + 1);

        int k = 0;
        // assert boardToDraw != null;
        int nextLoc = boardToDraw.terrain.get(k).getLocation();
        int m = 0;
        int nextFighter = boardToDraw.fighters.get(m).getLocation();


        // Draw the game board
        for (int i = 0; i < numRows; i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setPadding(0, 0, 0, 0);
            for (int j = 0; j < numCols; j++) {
                TableRow.LayoutParams rowParams = new TableRow.LayoutParams(0,0);
                rowParams.column = j;
                rowParams.span = 1;
                rowParams.setMargins(0, 0, 0, 0);
                tableRow.setLayoutParams(rowParams);

                // define the base layer (the grid, basically)
                // put the grid labeling on the sides in this part eventually
                Drawable layer0 = getResources().getDrawable(R.drawable.ic_empty_small);

                // layer the terrain on
                Drawable layer1;
                if (i * 21 + j == nextLoc) {
                    layer1 = getResources().getDrawable(boardToDraw.terrain.get(k).getPc().getPieceIDDraw());
                    if (k < boardToDraw.terrain.size() -1 ) {
                        k += 1;
                        nextLoc = boardToDraw.terrain.get(k).getLocation();
                    } else { k = -1; }
                } else {
                    layer1 = getResources().getDrawable(R.drawable.ic_empty_terrain);
                }

                // add other pieces
                Drawable layer2;
                if (i * 21 + j == nextFighter) {
                    layer2 = getResources().getDrawable(boardToDraw.fighters.get(m).getPc().getPieceIDDraw());
                    if (m < boardToDraw.fighters.size() - 1 ) {
                        m += 1;
                        nextFighter = boardToDraw.fighters.get(m).getLocation();
                    } else { m = -1; }
                } else {
                    layer2 = getResources().getDrawable(R.drawable.ic_empty_terrain);
                }

                Drawable[] layers = {layer0, layer1, layer2};
                LayerDrawable totalTile = new LayerDrawable(layers);

                // build the imageview and add it to the table
                ImageView imgV = new ImageView(this);
                imgV.setImageDrawable(totalTile);
                imgV.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imgV.setPadding(0, 0, 0, 0);

                FrameLayout frame = new FrameLayout(this);
                frame.addView(imgV);

                TextView txt = new TextView(this);
                txt.setHeight(19);
                txt.setWidth(19);
                txt.setGravity(Gravity.CENTER);  // center the text
                if ((i == 0) && !(j == 0)) {
                    txt.setText(String.valueOf((char)(j + 64)));
                    frame.addView(txt);
                }
                if (j == 0) {
                    txt.setText(String.valueOf(i));
                    frame.addView(txt);
                }

                tableRow.addView(frame);
                // tableRow.addView(imgV);
            }
            gameBoardTable.addView(tableRow);
        }

        for (int i = 0; i < numCols; i++) gameBoardTable.setColumnShrinkable(i, true);
        gameBoardTable.setPadding(0,0,0,0);

        // set the message to display under the board
        TextView msgToPlayer = (TextView) findViewById(R.id.textViewMsg);
        String msg = (boardToDraw.messageToUser.contains("wins")) ? boardToDraw.messageToUser :
                "It's " + boardToDraw.turn + "'s turn.\n" + boardToDraw.messageToUser;
        msgToPlayer.setText(msg);


        // build the table that collects the move

        TableLayout moveInfoTable = (TableLayout) findViewById(R.id.table_for_move_collection);
        TableRow tableRow = new TableRow(this);
        tableRow.setPadding(0, 3, 0, 3);
        TableRow.LayoutParams rowParams = new TableRow.LayoutParams(0,0);
        rowParams.span = 1;
        rowParams.setMargins(0, 0, 0, 0);
        tableRow.setLayoutParams(rowParams);
        String[] headings = {"location of        \npiece to move ", " destination        ",
                " 2nd destination \n (if range=2) "};
        for (int i = 0; i < 3; i++) {
            TextView txt = new TextView(this);
            // txt.setHeight(19);
            txt.setText(headings[i]);
            tableRow.addView(txt);
        }
        moveInfoTable.addView(tableRow);

        // array for holding all the edittext ids, 16 so attackLocation can go in too
        this.editTextIDs = new int[16];
        // for i rows
        for (int i = 0; i < 5; i++) {
            TableRow editRow = new TableRow(this);
            editRow.setPadding(0, 3, 0, 3);
            TableRow.LayoutParams rowParam = new TableRow.LayoutParams(0,0);
            rowParam.span = 1;
            rowParam.setMargins(0, 0, 0, 0);
            editRow.setLayoutParams(rowParam);
            // for j columns
            for (int j = 0; j < 3; j++) {
                // make a edittext
                EditText moveField = new EditText(this);
                // give it an id
                int id = ViewCompat.generateViewId();
                moveField.setId(id);
                // store the id in an array to be able to get the values out later
                editTextIDs[i*3 + j] = id;
                // add the necessary info/formatting to edittext
                moveField.setText("a0");
                // add to the row
                editRow.addView(moveField);
            }
            // add the row to the tablelayout
            moveInfoTable.addView(editRow);
        }

        // add a field for optional attack
        EditText attackLocation = (EditText) findViewById(R.id.attack_text);
        editTextIDs[15] = attackLocation.getId();

    }

    public void makeMove(View view) {
        // make a PlayManager to handle the checks
        iPlayManager pm = new PlayManager();
        // collect all the move info (including attack location)
        String[] moveCoords = new String[16];
        for (int i = 0; i < this.editTextIDs.length; i++) {
            EditText moveField = (EditText) findViewById(this.editTextIDs[i]);
            moveCoords[i] = moveField.getText().toString();
        }

        // Get the Intent that started this activity and extract the BoardState that it contained
        Intent intent = getIntent();
        BoardState boardToCheck = intent.getParcelableExtra("board to pass");

        // validate the move
        String check = pm.validateMove(boardToCheck, moveCoords);
        System.out.println(check);
        // if not valid, set the message returned from PlayManager as messageToUser,
        if (!check.equals("valid")) {
            boardToCheck.messageToUser = check;
            Intent newIntent = new Intent(this, GamePlayActivity.class);
            newIntent.putExtra("board to pass", (Parcelable) boardToCheck);
            // make new Activity with the same old boardstate
            startActivity(newIntent);
        }
        else {
            // if valid make the move (call to PlayManager)
            // make new Activity with the boardstate that PlayManager returns
            System.out.println("got to else statement again!"); //debug
            BoardState newBoardState = pm.makeMove(boardToCheck, moveCoords);
            System.out.println("turn: " + newBoardState.turn);  //debug
            Intent newIntent = new Intent(GamePlayActivity.this, GamePlayActivity.class);
            newIntent.putExtra("board to pass", (Parcelable) newBoardState);
            startActivity(newIntent);
            // finish();  // left in, this destroys an Activity once left, so the back key goes all
            // the way back to StartNewGameActivity, which might be desirable eventually
        }
    }

}