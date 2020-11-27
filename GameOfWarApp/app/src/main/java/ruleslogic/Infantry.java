package ruleslogic;

class Infantry extends Fighter {

    Infantry(int loc, Board b) {
        location = loc;
        attack = 4;
        defence = 6;
        weaponsRange = 2;
        movement = 1;
        inComms = false;
        transmitComms = 1;
        board = b;
    }
}
