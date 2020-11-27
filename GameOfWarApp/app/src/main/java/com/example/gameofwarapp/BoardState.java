package com.example.gameofwarapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;

public class BoardState implements Parcelable {
    public ArrayList<PiecePlacer> terrain;
    public ArrayList<PiecePlacer> fighters;
    public String turn;
    public int locationMustMove;
    public String messageToUser;

    public BoardState (ArrayList<PiecePlacer> terrain, ArrayList<PiecePlacer> fighters, String turn) {
        Collections.sort(terrain);
        this.terrain = terrain;
        Collections.sort(fighters);
        this.fighters = fighters;
        this.turn = turn;
        locationMustMove = 0;
        messageToUser = "";
    }

    public BoardState (ArrayList<PiecePlacer> terrain, ArrayList<PiecePlacer> fighters,
                       String turn, int locationMustMove, String messageToUser) {
        Collections.sort(terrain);
        this.terrain = terrain;
        Collections.sort(fighters);
        this.fighters = fighters;
        this.turn = turn;
        this.locationMustMove = locationMustMove;
        this.messageToUser = messageToUser;
    }

    public BoardState (String bsAsString)   {
        // for retrieving from persistent storage, and useful to convert to Board
        String[] fields = bsAsString.split("//");

        terrain = new ArrayList<>();
        String[] newTerrain = fields[0].trim().split(" ");
        for (int i = 0; i < newTerrain.length - 1; i++) {
             terrain.add(new PiecePlacer(Integer.parseInt(newTerrain[i]),
                    PieceID.values[Integer.parseInt(newTerrain[i+1])]));
            i++;
        }
        Collections.sort(terrain);

        fighters = new ArrayList<>();
        String[] newFighters = fields[1].trim().split(" ");
        for (int i = 0; i < newFighters.length; i++) {
            fighters.add(new PiecePlacer(Integer.parseInt(newFighters[i]),
                    PieceID.values[Integer.parseInt(newFighters[i+1])]));
            i++;
        }
        Collections.sort(fighters);

        turn = fields[2];
        locationMustMove = Integer.parseInt(fields[3]);
        messageToUser = (fields.length > 4) ? fields[4] : "";
    }

    public static ArrayList<PiecePlacer> getTerrain() {
        ArrayList<PiecePlacer> terrain = new ArrayList<>();
        int[] mountains = {222, 223, 224, 226, 227, 228, 238, 249, 259, 270, 280, 291, 301, 322,
                339, 340, 341, 343};
        for (int i : mountains) { terrain.add(new PiecePlacer(i, PieceID.MOUNTAIN)); }
        int[] fortresses = {71, 187, 285, 324, 454, 489};
        for (int i : fortresses) { terrain.add(new PiecePlacer(i, PieceID.FORTRESS)); }
        terrain.add(new PiecePlacer(225, PieceID.MOUNTAIN_PASS));
        terrain.add(new PiecePlacer(342, PieceID.MOUNTAIN_PASS));
        Collections.sort(terrain);
        return terrain;
    }

    @Override
    public String toString () {
        // also used for persistent storage
        String boardStateAsString = getTerrainAsString() + "//" +
                getFightersAsString() + "//" +
                turn + "//" +
                locationMustMove + "//" +
                messageToUser;
        return boardStateAsString;
    }

    public boolean hasFighterAt(int location) {
        for (PiecePlacer pp: this.fighters) {
            if (pp.getLocation() == location) return true;
        }
        return false;
    }

    // some static methods for getting particular starting states until persistent storage is implemented

    public static BoardState one_piece_moves_onto_arsenal() {
        ArrayList<PiecePlacer> listOfFighters = new ArrayList<>();
        // add North pieces
        listOfFighters.add(new PiecePlacer(185, PieceID.ARSENAL_NORTH));
        listOfFighters.add(new PiecePlacer(334, PieceID.ARSENAL_NORTH));
        listOfFighters.add(new PiecePlacer(164, PieceID.INFANTRY_NORTH));
        // add South pieces
        listOfFighters.add(new PiecePlacer(64, PieceID.ARSENAL_SOUTH));
        listOfFighters.add(new PiecePlacer(184, PieceID.INFANTRY_SOUTH));
        listOfFighters.add(new PiecePlacer(169, PieceID.TRANSMISSION_SOUTH));
        listOfFighters.add(new PiecePlacer(180, PieceID.CAVALRY_SOUTH));
        listOfFighters.add(new PiecePlacer(181, PieceID.CAVALRY_SOUTH));
        listOfFighters.add(new PiecePlacer(182, PieceID.CAVALRY_SOUTH));
        listOfFighters.add(new PiecePlacer(183, PieceID.CAVALRY_SOUTH));
        Collections.sort(listOfFighters);
        BoardState bS = new BoardState(getTerrain(), listOfFighters, "south",
                0, "");
        return bS;
    }

    public static BoardState one_piece_moves_onto_last_arsenal() {
        ArrayList<PiecePlacer> listOfFighters = new ArrayList<>();
        // add North pieces
        listOfFighters.add(new PiecePlacer(185, PieceID.ARSENAL_NORTH));
        listOfFighters.add(new PiecePlacer(164, PieceID.INFANTRY_NORTH));
        // add South pieces
        listOfFighters.add(new PiecePlacer(64, PieceID.ARSENAL_SOUTH));
        listOfFighters.add(new PiecePlacer(184, PieceID.INFANTRY_SOUTH));
        listOfFighters.add(new PiecePlacer(169, PieceID.TRANSMISSION_SOUTH));
        listOfFighters.add(new PiecePlacer(180, PieceID.CAVALRY_SOUTH));
        listOfFighters.add(new PiecePlacer(181, PieceID.CAVALRY_SOUTH));
        listOfFighters.add(new PiecePlacer(182, PieceID.CAVALRY_SOUTH));
        listOfFighters.add(new PiecePlacer(183, PieceID.CAVALRY_SOUTH));
        Collections.sort(listOfFighters);
        BoardState bS = new BoardState(getTerrain(), listOfFighters, "south",
                0, "");
        return bS;
    }

    public static BoardState five_pieces_move_and_attack_a_piece() {
        ArrayList<PiecePlacer> listOfFighters = new ArrayList<>();
        // add North pieces
        listOfFighters.add(new PiecePlacer(185, PieceID.ARSENAL_NORTH));
        listOfFighters.add(new PiecePlacer(334, PieceID.ARSENAL_NORTH));
        listOfFighters.add(new PiecePlacer(164, PieceID.INFANTRY_NORTH));
        listOfFighters.add(new PiecePlacer(165, PieceID.INFANTRY_NORTH));
        // add South pieces
        listOfFighters.add(new PiecePlacer(64, PieceID.ARSENAL_SOUTH));
        listOfFighters.add(new PiecePlacer(184, PieceID.INFANTRY_SOUTH));
        listOfFighters.add(new PiecePlacer(169, PieceID.TRANSMISSION_SOUTH));
        listOfFighters.add(new PiecePlacer(180, PieceID.CAVALRY_SOUTH));
        listOfFighters.add(new PiecePlacer(181, PieceID.CAVALRY_SOUTH));
        listOfFighters.add(new PiecePlacer(182, PieceID.CAVALRY_SOUTH));
        listOfFighters.add(new PiecePlacer(183, PieceID.CAVALRY_SOUTH));
        Collections.sort(listOfFighters);
        BoardState bS = new BoardState(getTerrain(), listOfFighters, "south",
                0, "");
        return bS;
    }

    public static BoardState five_pieces_move_and_attack_last_piece() {
        ArrayList<PiecePlacer> listOfFighters = new ArrayList<>();
        // add North pieces
        listOfFighters.add(new PiecePlacer(185, PieceID.ARSENAL_NORTH));
        listOfFighters.add(new PiecePlacer(334, PieceID.ARSENAL_NORTH));
        listOfFighters.add(new PiecePlacer(164, PieceID.INFANTRY_NORTH));
        // add South pieces
        listOfFighters.add(new PiecePlacer(64, PieceID.ARSENAL_SOUTH));
        listOfFighters.add(new PiecePlacer(184, PieceID.INFANTRY_SOUTH));
        listOfFighters.add(new PiecePlacer(169, PieceID.TRANSMISSION_SOUTH));
        listOfFighters.add(new PiecePlacer(180, PieceID.CAVALRY_SOUTH));
        listOfFighters.add(new PiecePlacer(181, PieceID.CAVALRY_SOUTH));
        listOfFighters.add(new PiecePlacer(182, PieceID.CAVALRY_SOUTH));
        listOfFighters.add(new PiecePlacer(183, PieceID.CAVALRY_SOUTH));
        Collections.sort(listOfFighters);
        BoardState bS = new BoardState(getTerrain(), listOfFighters, "south",
                0, "");
        return bS;
    }

    /* everything below here is for implementing Parcelable */
    /* based on the basic one at https://developer.android.com/reference/android/os/Parcelable.html */

    @Override
    public int describeContents() {
        return 0;
    }

    // write object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.getTerrainAsString());
        out.writeString(this.getFightersAsString());
        out.writeString(turn);
        out.writeInt(locationMustMove);
        out.writeString(messageToUser);
    }

    // to regenerate the object
    public static final Parcelable.Creator<BoardState> CREATOR = new Parcelable.Creator<BoardState>() {
        public BoardState createFromParcel(Parcel in) {
            return new BoardState(in);
        }
        public BoardState[] newArray(int size) {
            return new BoardState[size];
        }
    };

    // constructor that takes a Parcel and gives BoardState from its values
    private BoardState(Parcel in) {
        String[] newTerrain = in.readString().split(" ");
        ArrayList<PiecePlacer> terrainBuilder = new ArrayList<>();
        for (int i = 0; i < newTerrain.length; i++) {
            terrainBuilder.add(new PiecePlacer(Integer.parseInt(newTerrain[i]),
                    PieceID.values[Integer.parseInt(newTerrain[i+1])]));
            i++;
        }
        terrain = terrainBuilder;

        String[] newFighters = in.readString().split(" ");
        ArrayList<PiecePlacer> fightersBuilder = new ArrayList<>();
        for (int i = 0; i < newFighters.length; i++) {
            fightersBuilder.add(new PiecePlacer(Integer.parseInt(newFighters[i]),
                    PieceID.values[Integer.parseInt(newFighters[i+1])]));
            i++;
        }
        fighters = fightersBuilder;

        turn = in.readString();
        locationMustMove = in.readInt();
        messageToUser = in.readString();
    }

    private String getTerrainAsString() {
        StringBuilder terrainAsStr = new StringBuilder();
        for (int i = 0; i < terrain.size(); i++) {
            // add the location
            terrainAsStr.append(" " + terrain.get(i).getLocation());
            // add the PieceID
            terrainAsStr.append(" " + terrain.get(i).getPc().ordinal());
        }
        return terrainAsStr.toString().substring(1);
    }

    private String getFightersAsString() {
        StringBuilder fightersAsStr = new StringBuilder();
        for (int i = 0; i < fighters.size(); i++) {
            // add the location
            fightersAsStr.append(" " + fighters.get(i).getLocation());
            // add the PieceID
            fightersAsStr.append(" " + fighters.get(i).getPc().ordinal());
        }
        return fightersAsStr.toString().substring(1);
    }
}
