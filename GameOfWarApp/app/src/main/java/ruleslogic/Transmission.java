package ruleslogic;

class Transmission extends Fighter {
    Transmission(int loc, Board b) {
        location = loc;
        attack = 0;
        defence = 1;
        weaponsRange = 2;
        movement = 1;
        inComms = false;
        transmitComms = 25;
        board = b;
    }

    int getAttack() { return attack; }

    int getDefence() { return defence; }

    int getMovement() { return movement; }
}
