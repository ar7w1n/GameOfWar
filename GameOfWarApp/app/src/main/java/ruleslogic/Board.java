package ruleslogic;

import com.example.gameofwarapp.PieceID;
import com.example.gameofwarapp.PiecePlacer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * Concrete object which contains all the necessary components to represent a game in play and
 * ensure the game proceeds according to the rules.
 */
public class Board implements iBoard {
    /**
     * A list of immovable objects which affect play but don't belong to a specific side.
     */
    ArrayList<Terrain> terrains;
    /**
     * @inheritDoc
     */
    public ArrayList<Terrain> getTerrains() { return terrains; }

    /**
     * An object which contains all of the pieces which are under the control of or help the
     * North army.
     */
    Army northArmy;
    /**
     * @inheritDoc
     */
    public Army getNorthArmy() { return northArmy; }

    Army southArmy;
    /**
     * @inheritDoc
     */
    public Army getSouthArmy() { return southArmy; }

    /**
     * Whose turn it is.  Can only be the strings "north" or "south".
     */
    String turn;
    /**
     * @inheritDoc
     */
    public String getTurn() { return turn; }

    /**
     * Integer representation of the location of the piece which must move first on this turn, or 0.
     * Only needed when an attack forces a retreat.
     */
    int locationMustMove;
    /**
     * @inheritDoc
     */
    public int getLocationMustMove() { return locationMustMove; }

    /**
     * the 8 straight-line directions from any integer location
     */
    static ArrayList<Integer> directions = new ArrayList<>(Arrays.asList(-22, -21, -20, -1, 1, 20, 21, 22));

    /**
     * Constructor taking a string which encodes the state of play, currently based on the
     * BoardState.toString() output.
     * @param s String representation of all the pieces in play, their types, and their locations
     */
    public Board(String s) {
        // uses the BoardState.toString() representation :(
        String[] fields = s.split("//");
        turn = fields[2];
        locationMustMove = Integer.parseInt(fields[3]);
        terrains = new ArrayList<>();

        ArrayList<PiecePlacer> armyNpp = new ArrayList<>();
        ArrayList<PiecePlacer> armySpp = new ArrayList<>();

        // deal with terrain
        String[] newTerrain = fields[0].trim().split(" ");
        for (int i = 0; i < newTerrain.length - 1; i+=2) {
            // take the next 2 integers, use them to make a PiecePlacer, then convert to corresponding Terrain
            PiecePlacer t = new PiecePlacer(Integer.parseInt(newTerrain[i]),
                    PieceID.values[Integer.parseInt(newTerrain[i+1])]);
            if (Integer.parseInt(newTerrain[i+1]) < 3) terrains.add(convertToTerrain(t));
            // or drop it in the appropriate army if it's an Arsenal
            else if (Integer.parseInt(newTerrain[i+1]) == 3) armyNpp.add(t);
            else armySpp.add(t);
        }

        // make list of fighters
        ArrayList<PiecePlacer>fighters = new ArrayList<>();
        String[] newFighters = fields[1].trim().split(" ");
        for (int i = 0; i < newFighters.length; i++) {
            fighters.add(new PiecePlacer(Integer.parseInt(newFighters[i]),
                    PieceID.values[Integer.parseInt(newFighters[i+1])]));
            i++;
        }
        // sort the fighters into their correct armies
        for (PiecePlacer pc: fighters) {
            if (pc.getPc().ordinal() < PieceID.ARSENAL_SOUTH.ordinal()) armyNpp.add(pc);
            else armySpp.add(pc);
        }

        ArrayList<iPiece> nArmy = new ArrayList<>();
        for (PiecePlacer pc: armyNpp) {
            nArmy.add(getPieceFromPP(pc, this));
        }
        northArmy = new Army(nArmy);

        ArrayList<iPiece> sArmy = new ArrayList<>();
        for (PiecePlacer pc: armySpp) sArmy.add(getPieceFromPP(pc, this));
        southArmy = new Army(sArmy);

        northArmy.setLocInComms();
        southArmy.setLocInComms();
    }

    /**
     * Old constructor which depended on the front end's representation of piece types.  To be
     * removed once the remainder of tests are changed to use the string constructor.
     * @param listOfPieces a list of pieces which are on the board
     * @param turn a string indicating whose turn it is, either "north" or "south".
     * @param locationMustMove integer representation of location of the piec which must move first,
     *                         or 0.
     */
    public Board(ArrayList<PiecePlacer> listOfPieces, String turn, int locationMustMove) {
        this.turn = turn;
        this.locationMustMove = locationMustMove;
        this.terrains = new ArrayList<>();
        ArrayList<PiecePlacer> terra = new ArrayList<>();
        ArrayList<PiecePlacer> armyNpp = new ArrayList<>();
        ArrayList<PiecePlacer> armySpp = new ArrayList<>();

        // sort listOfPieces by which field of Board they belong to
        for (PiecePlacer pc: listOfPieces) {
            if (pc.getPc().ordinal() <= PieceID.FORTRESS.ordinal()) terra.add(pc);
            else if (pc.getPc().ordinal() < PieceID.ARSENAL_SOUTH.ordinal()) armyNpp.add(pc);
            else armySpp.add(pc);
        }

        // convert elements of each group into the correct type and add to the right field
        for (PiecePlacer pc: terra) terrains.add(convertToTerrain(pc));

        ArrayList<iPiece> nArmy = new ArrayList<>();
        for (PiecePlacer pc: armyNpp) nArmy.add(getPieceFromPP(pc, this));
        northArmy = new Army(nArmy);

        ArrayList<iPiece> sArmy = new ArrayList<>();
        for (PiecePlacer pc: armySpp) sArmy.add(getPieceFromPP(pc, this));
        southArmy = new Army(sArmy);

        northArmy.setLocInComms();
        southArmy.setLocInComms();
    }

    /**
     * Output all of the state of play regarding pieces and turn in a string.
     * @return a String which can be turned into either a BoardState or an iBoard.
     */
    public String toString() {
        StringBuilder boardSB = new StringBuilder();
        // terrains, arsenals
        for (Terrain t: this.terrains) {
            String cf = t.getClass().toString();
            switch (cf) {
                case "class ruleslogic.Mountain":
                    boardSB.append(" " + Integer.toString(t.getLocation()) + " " +
                            Integer.toString(PieceID.MOUNTAIN.ordinal()));
                    break;
                case "class ruleslogic.MountainPass":
                    boardSB.append(" " + Integer.toString(t.getLocation()) + " " +
                            Integer.toString(PieceID.MOUNTAIN_PASS.ordinal()));
                    break;
                case "class ruleslogic.Fortress":
                    boardSB.append(" " + Integer.toString(t.getLocation()) + " " +
                            Integer.toString(PieceID.FORTRESS.ordinal()));
                    break;
            }
        }
        for (Arsenal a: this.getNorthArmy().getArsenals()) {
            boardSB.append(" " + Integer.toString(a.getLocation()) + " " +
                    Integer.toString(PieceID.ARSENAL_NORTH.ordinal()));
        }
        for (Arsenal a: this.getSouthArmy().getArsenals()) {
            boardSB.append(" " + Integer.toString(a.getLocation()) + " " +
                    Integer.toString(PieceID.ARSENAL_SOUTH.ordinal()));
        }
        boardSB.append("//");

        // northArmy
        StringBuilder nArmySB = new StringBuilder();
        for (Fighter f: this.getNorthArmy().getFighters()) {
            String cf = f.getClass().toString();
            switch (cf) {
                case "class ruleslogic.Infantry":
                    nArmySB.append(" " + Integer.toString(f.getLocation()) + " " +
                            Integer.toString(PieceID.INFANTRY_NORTH.ordinal()));
                    break;
                case "class ruleslogic.Cavalry":
                    nArmySB.append(" " + Integer.toString(f.getLocation()) + " " +
                            Integer.toString(PieceID.CAVALRY_NORTH.ordinal()));
                    break;
                case "class ruleslogic.Artillery":
                    nArmySB.append(" " + Integer.toString(f.getLocation()) + " " +
                            Integer.toString(PieceID.ARTILLERY_NORTH.ordinal()));
                    break;
                case "class ruleslogic.ArtilleryMounted":
                    nArmySB.append(" " + Integer.toString(f.getLocation()) + " " +
                            Integer.toString(PieceID.ARTILLERYMOUNTED_NORTH.ordinal()));
                    break;
                case "class ruleslogic.Transmission":
                    nArmySB.append(" " + Integer.toString(f.getLocation()) + " " +
                            Integer.toString(PieceID.TRANSMISSION_NORTH.ordinal()));
                    break;
                case "class ruleslogic.TransmissionMounted":
                    nArmySB.append(" " + Integer.toString(f.getLocation()) + " " +
                            Integer.toString(PieceID.TRANSMISSIONMOUNTED_NORTH.ordinal()));
                    break;
            }
        }
        boardSB.append(nArmySB.toString().trim());

        // southArmy
        StringBuilder sArmySB = new StringBuilder();
        for (Fighter f: this.getSouthArmy().getFighters()) {
            String cf = f.getClass().toString();
            switch (cf) {
                case "class ruleslogic.Infantry":
                    sArmySB.append(" " + Integer.toString(f.getLocation()) + " " +
                            Integer.toString(PieceID.INFANTRY_SOUTH.ordinal()));
                    break;
                case "class ruleslogic.Cavalry":
                    sArmySB.append(" " + Integer.toString(f.getLocation()) + " " +
                            Integer.toString(PieceID.CAVALRY_SOUTH.ordinal()));
                    break;
                case "class ruleslogic.Artillery":
                    sArmySB.append(" " + Integer.toString(f.getLocation()) + " " +
                            Integer.toString(PieceID.ARTILLERY_SOUTH.ordinal()));
                    break;
                case "class ruleslogic.ArtilleryMounted":
                    sArmySB.append(" " + Integer.toString(f.getLocation()) + " " +
                            Integer.toString(PieceID.ARTILLERYMOUNTED_SOUTH.ordinal()));
                    break;
                case "class ruleslogic.Transmission":
                    sArmySB.append(" " + Integer.toString(f.getLocation()) + " " +
                            Integer.toString(PieceID.TRANSMISSION_SOUTH.ordinal()));
                    break;
                case "class ruleslogic.TransmissionMounted":
                    sArmySB.append(" " + Integer.toString(f.getLocation()) + " " +
                            Integer.toString(PieceID.TRANSMISSIONMOUNTED_SOUTH.ordinal()));
                    break;
            }
        }
        boardSB.append(sArmySB.toString());

        boardSB.append("//");
        // turn
        boardSB.append(turn + "//");
        // locationMustMove
        boardSB.append(locationMustMove + "//");

        return boardSB.toString().trim();
    }

    // helper method for constructor
    private static iPiece getPieceFromPP(PiecePlacer pp, Board b) {
        // turn enum into String
        String s = pp.getPc().toString();
        // sort by Arsenal/Fighter and return the piece
        if ( (pp.getPc().ordinal() <= PieceID.FORTRESS.ordinal()) || (s.startsWith("ARSENAL"))) {
            return convertToTerrain(pp);
        }
        else return convertToFighter(pp, b);
    }

    // helper method for constructor
    private static Terrain convertToTerrain(PiecePlacer pp) {
        // turn enum into String
        String s = pp.getPc().toString();
        // make object of that value (with pp.loc)
        Terrain piece = new Fortress(pp.getLocation());
        switch (s) {
            case "MOUNTAIN":
                piece = new Mountain(pp.getLocation());
                break;
            case "MOUNTAIN_PASS":
                piece = new MountainPass(pp.getLocation());
                break;
            case "ARSENAL_SOUTH":
            case "ARSENAL_NORTH":
                piece = new Arsenal(pp.getLocation());
                break;
        }
        return piece;
    }

    // helper method for constructor
    private static Fighter convertToFighter(PiecePlacer pp, Board b) {
        // turn enum into String
        String s = pp.getPc().toString();
        s = s.substring(0, s.length() - 6);
        Fighter piece;
        // get the right kind of Fighter
        switch (s) {
            case "INFANTRY":
                piece = new Infantry(pp.getLocation(), b);
                break;
            case "CAVALRY":
                piece = new Cavalry(pp.getLocation(), b);
                break;
            case "ARTILLERY":
                piece = new Artillery(pp.getLocation(), b);
                break;
            case "ARTILLERYMOUNTED":
                piece = new ArtilleryMounted(pp.getLocation(), b);
                break;
            case "TRANSMISSION":
                piece = new Transmission(pp.getLocation(), b);
                break;
            default:
                piece = new TransmissionMounted(pp.getLocation(), b);
        }
        return piece;
    }

    static boolean isOnBoard(int location) {
        // needs to be static because of use in static testing context
        // reject values out of range, or that are on the edge-defining column
        return !(location < 22 || location > 545 || location % 21 == 0);
    }

    /**
     * Helper method used in propagating each army's communications, and for getting the locations
     * which can participate in attack/defence
     * @param sourceLocation integer representation of location being attacked or from where
     *                       communication propagates
     * @param distance integer indicating how far the operation carries across the board in a
     *                 straight line
     * @param opponentBlocks True for communication operations, False during battle
     * @param queryArmy string, "north" or "south", indicating which army the locations it returns
     *                  pertain to (to work out who the opponent is)
     * @return HashSet of integers which represent locations to check for pieces which participate
     * in an operation.
     */
    HashSet<Integer> getCandidateLocations(int sourceLocation,
                                                  int distance,
                                                  boolean opponentBlocks,
                                                  Army queryArmy) {
        Army opponent = (queryArmy == northArmy) ? southArmy : northArmy;
        HashSet<Integer> candidates = new HashSet<>();
        for (int d: directions) {
            int i = 0;
            int tryLoc = sourceLocation;
            while ((i <= distance) && (isOnBoard(tryLoc))) {
                candidates.add(tryLoc);
                i += 1;
                tryLoc += d;
                if ((this.hasMountainAt(tryLoc)) ||
                    (opponentBlocks &&
                            (opponent.hasArsenalAt(tryLoc) ||
                             opponent.hasFighterAt(tryLoc)))) break;
            }
        }
        return candidates;
    }

    boolean hasMountainAt(int location) {
        if (isOnBoard(location) && this.hasTerrainAt(location)) {
            try {
                Mountain m = (Mountain) this.getTerrainAt(location);
                return true;
            }
            catch (Exception ex) {
                return false;
            }
        }
        else return false;
    }

    boolean hasFortressAt(int location) {
        return isOnBoard(location) && this.hasTerrainAt(location) &&
                (this.getTerrainAt(location).getClass() == Fortress.class);
    }

    boolean hasTerrainAt(int location) {
        for (Terrain t: getTerrains()) if (t.getLocation() == location) return true;
        return false;
    }

    Terrain getTerrainAt(int location) {
        for (Terrain t: getTerrains()) if (t.getLocation() == location) return t;
        return new Mountain(0);
    }

    /**
     * @inheritDoc
     */
    public String validateMove(int[] locations) {
        // "valid", or an error message: "Problem with move. Try again."
        Army mover = turn.equals("north") ? northArmy : southArmy;
        Army opponent = turn.equals("north") ? southArmy : northArmy;

        // if there's a specific location that must more first, it must be in the first box
        if (locationMustMove != 0 && locations[0] != locationMustMove) return "Problem with move. Attacked piece must move first";

        HashSet<Fighter> priorMovedFighters = new HashSet<>();
        HashSet<Integer> moverOccupies = new HashSet<>();
        for (Fighter f: mover.fighters) moverOccupies.add(f.getLocation());

        // loop over the 5 moves
        for (int i = 0; i < 15; i+=3) {
            // ignore a row that doesn't refer to a piece
            if (locations[i] == 0) continue;
            // check the start location is valid given the turn and piece locations
            if (!(mover.hasFighterAt(locations[i]))) return "No fighter at location: " +
                    Board.locationToString(locations[i]);
            Fighter movingFighter = mover.getFighterAt(locations[i]);

            // find out how far the fighter can move
            int places = movingFighter.getMovement();
            // reject move if fighter out of comms
            if (places == 0) return "Fighter at " + Board.locationToString(locations[i]) +
                    " not in communication";
            // reject move if tries to move twice with movement of 1
            if (places == 1 && locations[i+2] > 0) return "Fighter at " +
                    Board.locationToString(locations[i]) + " can't move 2";
            // check that fighter hasn't already moved this turn
            if (priorMovedFighters.contains(movingFighter)) return "Fighter at " +
                    Board.locationToString(locations[i]) + " can't move twice";

            int origin = locations[i];
            int destination = locations[i+1];

            // reject if origin and destination aren't adjacent
            if ( !(directions.contains(origin - destination)) ) return "Non-adjacent origin and destination.";

            // reject a destination that's occupied
            if ( moverOccupies.contains(destination) ||
                    opponent.hasFighterAt(destination) ||
                    this.hasMountainAt(destination) ) return Board.locationToString(destination) +
                    " already occupied.";

            moverOccupies.remove(origin);
            moverOccupies.add(destination);

            if (places == 2 && locations[i+2] != 0) {
                // process the second part of a given fighter's move, if needed
                origin = destination;
                destination = locations[i+2];
                // reject if origin and destination aren't adjacent
                if ( !(directions.contains(origin - destination)) ) return "Non-adjacent second destination.";
                // reject a destination that's occupied
                if ( moverOccupies.contains(destination) ||
                        opponent.hasFighterAt(destination) ||
                        this.hasMountainAt(destination) ) return "Second destination already occupied.";

                moverOccupies.remove(origin);
                moverOccupies.add(destination);
            }
            priorMovedFighters.add(movingFighter);
        }
        return "valid";
    }

    /**
     * @inheritDoc
     */
    public String processMove(int[] locations) {
        // call to validateMove() in PlayManager done first
        Army attacker = turn.equals("north") ? northArmy : southArmy;
        Army defender = turn.equals("north") ? southArmy : northArmy;

        // move the pieces on the board
        int flag = attacker.moveFighters(locations);
        if (flag == 0) return "oops, something went wrong";

        // setLocInComms for both sides
        attacker.setLocInComms();
        defender.setLocInComms();

        // if a fighter had to retreat, exclude them from the attack by setting their inComms to false
        // (this condition should maybe be in Army.attack()?  Cleaner this way but...)
        if (this.locationMustMove > 0) {
            int locationToExclude = (locations[2] == 0) ? locations[1] : locations[2];
            attacker.getFighterAt(locationToExclude).inComms = false;
        }

        // process attack, capture message to add to with other effects of move
        String msg0 = resolveAttack(attacker, defender, locations[15]);

        // reset locationMustMove if the attack didn't result in it being needed
        if (!msg0.endsWith("move first.")) locationMustMove = 0;

        // remove arsenal if necessary
        String msg1 = "";
        ArrayList<Integer> arsenalLocations = new ArrayList<>();
        for (Arsenal a: defender.arsenals) arsenalLocations.add(a.getLocation());

        for (int loc: arsenalLocations) {
            for (Fighter f : attacker.fighters) {
                if (loc == f.getLocation()) {
                    msg1 = "\nArsenal detroyed at " + locationToString(loc);
                    defender.removeArsenalAt(loc);
                    break;
                }
            }
        }
        // check win condition and build the message for the user;
        String msgToUser = new String();
        String side = (attacker == northArmy) ? "North" : "South";
        if (defender.arsenals.size() == 0 || defender.fighters.size() == 0) {
            msgToUser = side + " wins!";
        } else {
            msgToUser = msg0 + msg1;
        }

        return this.toString() + msgToUser;
    }

    /**
     * Helper method which determines the result of a given attack, mutates state if necessary, and
     * returns either an empty string, or a message about the result.
     * @param attacker the attacking army object
     * @param defender the defending army object
     * @param location the integer representation of the location under attack
     * @return either an empty string or a message that a specific location must move first on the
     * next turn
     */
    String resolveAttack(Army attacker, Army defender, int location) {
        // no need to do anything if the defender has no fighter at the attacked location
        if ( !defender.hasFighterAt(location) ) return "";

        int diff = attacker.attack(location) - defender.defend(location);
        if (diff >= 2) defender.removeFighterAt(location);
        if (diff == 1) {
            boolean hasRetreat = false;
            for (int loc: getCandidateLocations(location, 1, true, defender)) {
                if (!defender.hasFighterAt(loc)) {
                    hasRetreat = true;
                    break;
                }
            }
            if (hasRetreat) {
                // if the attacked fighter can retreat it must
                this.locationMustMove = location;
                return "Retreat! Piece at " + locationToString(location) + " must move first.";
            } else {
                // if it can't, it's destroyed
                defender.removeFighterAt(location);
            }
        }

        return "";
    }

    /**
     * Helper method which turns integer location into grid reference that is player-readable,
     * only used in producing move error messages
     * @param location integer representation of location on board
     * @return string, grid reference eg. "d18"
     */
    static String locationToString(int location) {
        int row = (location % 21) + 96;
        int col = location / 21;
        return Character.toString((char) row) + col;
    }

    /**
     * The object which contains all the state about which pieces remain as resources for a
     * particular player, their type and location.
     */
    public class Army {
        private ArrayList<Arsenal> arsenals;
        ArrayList<Arsenal> getArsenals() { return arsenals; }
        private ArrayList<Fighter> fighters;
        ArrayList<Fighter> getFighters() { return fighters; }
        private HashSet<Integer> locInComms;

        /**
         * Constructor only called by Board, using the piece objects which it previously instantiated
         * @param listOfPieces the pieces in the army
         */
        Army(ArrayList<iPiece> listOfPieces) {
            arsenals = new ArrayList<>();
            fighters = new ArrayList<>();
            for (iPiece iP: listOfPieces) {
                // handle Arsenals first
                int i = 0;  // changed to 1 if iP is a Fighter
                try {
                    Arsenal as = (Arsenal) iP;
                    i = as.defenceModifier;
                }
                catch (Exception ex) {
                    i = 1;
                }
                if (i == 0) arsenals.add((Arsenal) iP);
                // then handle the Fighters
                else {
                    // the annotation on the next line is bogus because of the flag set in the catch clause above
                    Fighter f = (Fighter) iP;
                    fighters.add(f);
                }
            }
        }

        /**
         * Method which determines which locations on the board are in communication for a
         * particular army.  Called in the Board constructor since communication also depends on
         * the location of opposing pieces.
         */
        void setLocInComms() {
            // way too much data entry to test individually; is effectively tested by checking the
            // inComms field on all the Fighters in BoardTest.getCommsTestObject1()
            locInComms = new HashSet<Integer>();

            // initialise fighter state to avoid holdover
            for (Fighter f : this.fighters) f.inComms = false;

            // add all the reachable squares from the Arsenals
            for (Arsenal a: arsenals) {
                locInComms.addAll( Board.this.getCandidateLocations(a.getLocation(), 25,
                        true, this) );
            }

            // now do the Fighters
            // keep track of which Fighters have already been marked as inComms to avoid repeated
            // method calls
            ArrayList<Fighter> checked = new ArrayList<>();
            // stop the loop after one check of all Fighters with no changes
            boolean flag = true;
            while (flag) {
                flag = false;
                // loop over the Fighters; if newly inComms, set fields and propagate from them
                for (Fighter f: fighters) {
                    if (locInComms.contains(f.getLocation()) && !(checked.contains(f))) {
                        flag = true;
                        f.inComms = true;
                        locInComms.addAll(Board.this.getCandidateLocations(f.getLocation(),
                                f.getTransmitComms(), true, this));
                        checked.add(f);
                    }
                }
            }
        }

        /**
         * Change the locations of all the pieces which are moving on a turn.
         * @param locations list of the starting locations and destinations of the moving pieces
         * @return 1 if carried out properly, 0 if there was an error.
         */
        int moveFighters(int[] locations) {
            // assuming the move was already validated
            for (int i = 0; i < 5; i++) {
                if (locations[3*i] == 0) continue;
                // check whether final destination is in 2nd box or 3rd
                int destination = (locations[3*i + 2] == 0) ? locations[3*i + 1] : locations[3*i + 2];
                int j = this.moveFighter(locations[3*i], destination);
                if (j == 0) return 0;
            }
            return 1;
        }

        int moveFighter(int source, int destination) {
            // reject if the Fighter at source is not in comms, (keep this check for structure)
            // destination is more than one square away,
            // destination occupied by same army,
            // destination occupied by other army,
            // (these checks are redundant with validateMove(), but could be implemented here as
            // well for safety, which is why the if statement is kept)
            if (!(this.getFighterAt(source).inComms))  return 0;
            // and if it passes all that then do the move
            else {
                this.getFighterAt(source).location = destination;
                return 1;
            }
        }

        boolean hasFighterAt(int location) {
            for (Fighter f: fighters) if (f.getLocation() == location) return true;
            return false;
        }

        Fighter getFighterAt(int location) {
            // helper method for testing on Board objects, also used in moveFighter()
            for (Fighter f: fighters) if (f.getLocation() == location) return f;
            return new Infantry(0, Board.this);
        }

        boolean hasArsenalAt(int location) {
            for (Arsenal a: arsenals) if (a.getLocation() == location) return true;
            return false;
        }

        /**
         * Calculates the strength of an Army's attack on a particular location
         * @param targetLocation integer representation of the location on the board under attack
         * @return integer value of the attack which is the sum of all the attack coefficient of
         * all fighters which can participate in the attack
         */
        int attack(int targetLocation) {
            // collect cavalry to process separately
            ArrayList<Fighter> attackingCavalry = new ArrayList<>();
            // accumulator for the attack point total
            int attackAccumulator = 0;
            // collect all the locations that might contain Fighters involved in the attack
            // distance of 4 to include possibility of cavalry charge, but necessitates range check
            HashSet<Integer> attackersLocations =
                    Board.this.getCandidateLocations(targetLocation, 4,
                            false, this);
            // accumulate the attack attribute from each Fighter which:
            //   - is at a location in attackersLocations
            //   - is in range
            for (Fighter f: fighters) {
                if (attackersLocations.contains(f.getLocation())) {
                    // deal with cavalry separately
                    if (f.getClass() == Cavalry.class) {
                        attackingCavalry.add(f);
                        continue;
                    }
                    // check within row first
                    if (Math.abs(targetLocation - f.getLocation()) <= f.getWeaponsRange()) {
                        attackAccumulator += f.getAttack();
                    }
                    // then the other 6 directions
                    else {
                        int[] directions = {20, 21, 22};
                        for (int dir : directions) {
                            if ((Math.abs(targetLocation - f.getLocation()) / dir <= f.getWeaponsRange()) &&
                                    ((Math.abs(targetLocation - f.getLocation()) % dir) == 0)) {
                                attackAccumulator += f.getAttack();
                            }
                        }
                    }
                }
            }
            ArrayList<Integer> chargingLocations = new ArrayList<>();
            // only process the charging logic if the target is NOT a fortress or mountain pass
            if (!(Board.this.hasTerrainAt(targetLocation))) {
                for (Fighter c: attackingCavalry) {
                    // check if adjacent
                    if (directions.contains(c.getLocation() - targetLocation) &&
                            !(Board.this.hasFortressAt(c.getLocation()))) {
                        chargingLocations.add(c.getLocation());
                    }
                }
                boolean changed = true;
                while (changed) {
                    changed = false;
                    // next 6 lines: compare each not-yet-charging cavalry to the ones that are already
                    // categorised as charging
                    for (Fighter c : attackingCavalry) {
                        if (chargingLocations.contains(c.getLocation())) {
                            for (Fighter potential : attackingCavalry) {
                                if (!(chargingLocations.contains(potential.getLocation())) &&
                                        (directions.contains(c.getLocation() - potential.getLocation())) &&
                                        !(Board.this.hasFortressAt(potential.getLocation()))) {
                                    // check that the potential charger and the already-charging are in
                                    // a straight line with the target location
                                    int pieceVector = c.getLocation() - potential.getLocation();
                                    if ((Math.abs(targetLocation - c.getLocation()) % pieceVector) == 0) {
                                        chargingLocations.add(potential.getLocation());
                                        changed = true;
                                    }
                                }
                            }
                        }
                    }
                }
                attackAccumulator += chargingLocations.size() * 7;
            }
            //then deal with the ones that aren't charging, as for other pieces
            for (Fighter c: attackingCavalry) {
                if (!(chargingLocations.contains(c.getLocation()))) {
                    // check within row first
                    if (Math.abs(targetLocation - c.getLocation()) <= c.getWeaponsRange()) {
                        attackAccumulator += c.getAttack();
                    }
                    // then the other 6 directions
                    else {
                        int[] directions = {20, 21, 22};
                        for (int dir : directions) {
                            if ((Math.abs(targetLocation - c.getLocation()) / dir <= c.getWeaponsRange()) &&
                                    ((Math.abs(targetLocation - c.getLocation()) % dir) == 0)) {
                                attackAccumulator += c.getAttack();
                            }
                        }
                    }
                }
            }
            return attackAccumulator;
        }

        /**
         * Calculates the strength of an Army's defence of a location.
         * @param targetLocation the location under attack
         * @return integer value, the sum of the defence coefficients of all pieces which can
         * defend that location
         */
        int defend(int targetLocation) {
            int def = 0;
            // collect all the locations that might contain Fighters to support the defence
            // distance of 3 to include artillery, but necessitates range check
            HashSet<Integer> defendersLocations =
                    Board.this.getCandidateLocations(targetLocation, 3,
                            false, this);
            // accumulate the defence attribute from each Fighter which is:
            //   - at a location in defendersLocations
            //   - is in range
            for (Fighter f: fighters) {
                if (defendersLocations.contains(f.getLocation())) {
                    // check within row first
                    if (Math.abs(targetLocation - f.getLocation()) <= f.getWeaponsRange()) {
                        def += f.getDefence();
                    }
                    // then the other 6 directions
                    else {
                        int[] directions = {20, 21, 22};
                        for (int dir : directions) {
                            if ((Math.abs(targetLocation - f.getLocation()) / dir <= f.getWeaponsRange()) &&
                                    ((Math.abs(targetLocation - f.getLocation()) % dir) == 0)) {
                                def += f.getDefence();
                            }
                        }
                    }
                }
            }
            return def;
        }

        void removeFighterAt(int location) {
            for (Fighter f: fighters) {
                if (f.getLocation() == location) {
                    fighters.remove(f);
                    break;
                }
            }
        }

        void removeArsenalAt(int location) {
            for (Arsenal a: arsenals) {
                if (a.getLocation() == location) {
                    arsenals.remove(a);
                    break;
                }
            }
        }
    }
}
