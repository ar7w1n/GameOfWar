package ruleslogic;

public abstract class Fighter implements iPiece {
    int location;
    int attack;
    int defence;
    int weaponsRange;
    int movement;
    boolean inComms;
    int transmitComms; // 1 for near, 25 for far
    Board board; // the board on which it sits

    public int getLocation() { return location; }

    int getAttack() {
        if (inComms) return attack;
        else return 0;
    }

    int getDefence() {
        if (inComms) {
            int def = defence;
            // bonus if on mountainpass or fortress
            if (board.hasTerrainAt(this.location)) {
                def += board.getTerrainAt(this.location).getDefenceModifier();
            }
            return def;
        }
        else return 0;
    }

    int getMovement() {
        if (inComms) return movement;
        else return 0;
    }

    int getWeaponsRange() {
        return weaponsRange;
    }

    int getTransmitComms() {
        return transmitComms;
    }

    // needed for testing
    public boolean equals(Object o) {
        if (o.getClass().equals(this.getClass())) {
            Fighter comparator = (Fighter) o;
            return ( this.getLocation() == comparator.getLocation() );
        }
        return false;
    }
}
