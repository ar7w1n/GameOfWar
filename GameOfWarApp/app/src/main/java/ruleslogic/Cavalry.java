package ruleslogic;

class Cavalry extends Fighter {
    Cavalry(int loc, Board b) {
        location = loc;
        attack = 4;
        defence = 5;
        weaponsRange = 2;
        movement = 2;
        inComms = false;
        transmitComms = 1;
        board = b;
    }

    int getDefence() {
        // overrides method in Fighter because doesn't need the terrain modifier
        if (inComms) return defence;
        else return 0;
    }

}
