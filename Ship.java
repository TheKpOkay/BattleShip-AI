import java.util.ArrayList;
import java.util.List;

public abstract class Ship
{
    private int length;
    private List<Location> locations = new ArrayList<>();
    private List<Location> hitsTaken = new ArrayList<>();

    public Ship(int length)
    {
        this.length = length;
    }

    public void addLocation(Location... loc)
    {
        for (int i = 0; i < loc.length; i++)
        {
            locations.add(loc[i]);
        }
    }

    public List<Location> getLocations()
    {
        return locations;
    }

    /**
     * Add Location loc to hitsTaken.
     *
     * @param loc
     */
    public void takeHit(Location loc)
    {
        hitsTaken.add(loc);
    }

    /**
     * Returns true if the number of hits taken is
     *   equal to the length of this ship.
     *
     * @return
     */
    public boolean isSunk()
    {
        return hitsTaken.size() == length;
    }
}
