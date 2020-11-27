package com.example.gameofwarapp;

public class PiecePlacer implements Comparable<PiecePlacer> {
    private int location;
    private PieceID pc;

    public PiecePlacer(int i, PieceID p) {
        location = i;
        pc = p;
    }

    public int getLocation() {
        return location;
    }

    public PieceID getPc() {
        return pc;
    }

    public String toString() {
        return Integer.toString(location) + " " + pc.ordinal();
    }

    @Override
    public int compareTo(PiecePlacer other) {
        if ( this.location > other.location ) return 1;
        else if ( this.location < other.location ) return -1;
        else {
            Integer i = this.getPc().ordinal();
            Integer j = other.getPc().ordinal();
            return i.compareTo(j);
        }
    }
}
