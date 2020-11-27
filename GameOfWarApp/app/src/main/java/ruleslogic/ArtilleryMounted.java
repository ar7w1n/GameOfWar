package ruleslogic;

class ArtilleryMounted extends Fighter {
    ArtilleryMounted(int loc, Board b) {
        location = loc;
        attack = 5;
        defence = 8;
        weaponsRange = 3;
        movement = 2;
        inComms = false;
        transmitComms = 1;
        board = b;
    }
}
