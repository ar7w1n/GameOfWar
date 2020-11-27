package ruleslogic;

class Artillery extends Fighter {
    Artillery(int loc, Board b) {
        location = loc;
        attack = 5;
        defence = 8;
        weaponsRange = 3;
        movement = 1;
        inComms = false;
        transmitComms = 1;
        board = b;
    }
}
