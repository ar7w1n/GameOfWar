package ruleslogic;

public abstract class Terrain implements iPiece {
    int location;
    int defenceModifier;

    public int getLocation() { return location; }

    int getDefenceModifier() {
        return defenceModifier;
    }

    @Override
    public boolean equals(Object o) {
        //only needed for testing
        if (o instanceof Terrain) {
            Terrain comparator = (Terrain) o;
            return ( this.getLocation() == comparator.getLocation() );
        }
        return false;
    }
}
