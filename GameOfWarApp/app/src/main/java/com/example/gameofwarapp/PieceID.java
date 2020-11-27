package com.example.gameofwarapp;

public enum PieceID {
    MOUNTAIN(R.drawable.ic_mountain), // 0
    MOUNTAIN_PASS(R.drawable.ic_mountain_pass), // 1
    FORTRESS(R.drawable.ic_fortress), // 2
    ARSENAL_NORTH(R.drawable.ic_arsenal_north), // 3
    TRANSMISSION_NORTH(R.drawable.ic_transmission_north), // 4
    TRANSMISSIONMOUNTED_NORTH(R.drawable.ic_transmissionmounted_north), // 5
    INFANTRY_NORTH(R.drawable.ic_infantry_north), // 6
    CAVALRY_NORTH(R.drawable.ic_cavalry_north), // 7
    ARTILLERY_NORTH(R.drawable.ic_artillery_north), // 8
    ARTILLERYMOUNTED_NORTH(R.drawable.ic_artillerymounted_north), // 9
    ARSENAL_SOUTH(R.drawable.ic_arsenal_south), // 10
    TRANSMISSION_SOUTH(R.drawable.ic_transmission_south), // 11
    TRANSMISSIONMOUNTED_SOUTH(R.drawable.ic_transmissionmounted_south), // 12
    INFANTRY_SOUTH(R.drawable.ic_infantry_south), // 13
    CAVALRY_SOUTH(R.drawable.ic_cavalry_south), // 14
    ARTILLERY_SOUTH(R.drawable.ic_artillery_south), // 15
    ARTILLERYMOUNTED_SOUTH(R.drawable.ic_artillerymounted_south); // 16


    private int img;

    PieceID(int i) { img = i; }

    int getPieceIDDraw() { return img; }

    public static final PieceID[] values = values();
}
