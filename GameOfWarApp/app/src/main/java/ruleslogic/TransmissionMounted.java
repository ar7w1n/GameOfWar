package ruleslogic;

class TransmissionMounted extends Fighter {
    TransmissionMounted(int loc, Board b) {
        location = loc;
        attack = 0;
        defence = 1;
        weaponsRange = 2;
        movement = 2;
        inComms = false;
        transmitComms = 25;
        board = b;
    }

    int getAttack() { return attack; }

    int getDefence() { return defence; }

    int getMovement() { return movement; }
}
