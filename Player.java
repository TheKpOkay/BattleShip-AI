import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Player
{
    private String name;
    private List<Ship> ships;
    private int[][] guessBoard;

    public Player(String name)
    {
        this.name = name;
        ships = new ArrayList<>();
        guessBoard = new int[10][10];
        for (int r = 0; r < 10; r++)
            for (int c = 0; c < 10; c++)
                guessBoard[r][c] = 0;
    }

    public String getName()
    {
        return name;
    }

    /**
     * Returns how many ships are currently in this
     *   Player's ships List.
     *
     * @return
     */
    public int getNumberOfShips()
    {
        return ships.size();
    }

    /**
     * Returns the ship that occupies the specified
     *   Location loc.  Returns null if this Player
     *   does not control a ship at Location loc.
     *
     * @param loc
     * @return
     */
    public Ship getShip(Location loc)
    {
        for (Ship s: ships)
        {
            for (int i = 0; i < s.getLocations().size(); i++)
            {
                if(s.getLocations().get(i).equals(loc))
                    return s;
            }
        }
        return null;

    }

    public void addShip(Ship ship)
    {
        ships.add(ship);
    }

    public void removeShip(Ship ship)
    {
        ships.remove(ship);
    }

    /**
     * Returns true if this Player controls a ship
     *   at the specified Location loc; false
     *   otherwise.
     *
     * @param loc
     * @return
     */
    public boolean hasShipAtLocation(Location loc)
    {
        for (Ship s: ships)
        {
            for (int i = 0; i < s.getLocations().size(); i++)
            {
                if(s.getLocations().get(i).equals(loc))
                    return true;
            }
        }
        return false;
    }

    public int[][] getGuessBoard()
    {
        return guessBoard;
    }

    /**
     * Returns true if obj is an instanceof Player and
     *   instance variables are equivalent.
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Player)
        {
            Player other = (Player) obj;
            return name.equals(other.getName()) && ships == other.ships && guessBoard == other.guessBoard;
        }
            return false;
    }

    /**
     * Attack the specified Player at the specified Location.
     *
     * Return true if the attack resulted in a ship sinking;
     *   false otherwise.
     *
     * @param enemy
     * @param loc
     * @return
     */
    public abstract boolean attack(Player enemy, Location loc);

    /**
     * Construct an instance of
     *
     *   AircraftCarrier,
     *   Destroyer,
     *   Submarine,
     *   Cruiser, and
     *   PatrolBoat
     *
     * and add them to this Player's list of ships.
     */
    public abstract void populateShips();
}
