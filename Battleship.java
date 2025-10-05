import java.util.ArrayList;
import java.util.List;

public class Battleship
{
    private List<Player> players;

    public Battleship()
    {
        players = new ArrayList<>();
    }

    public Player getPlayer(int i)
    {
        return players.get(i);
    }

    public void addPlayer(Player player)
    {
        players.add(player);
    }

    /**
     * Remove any Players from the players List
     *   if all of their ships have been sunk.
     */
    public void upkeep()
    {
        for (Player p: players)
        {
            if(p.getNumberOfShips() == 0)
            {
                players.remove(p);
                break;
            }
        }
    }

    /**
     * Returns true if only one Player remains in
     *   the players List; false otherwise.
     *
     * @return
     */
    public boolean gameOver()
    {
        return players.size() == 1;
    }

    /**
     * Returns the only Player remaining in the
     *   players List if the game is over; returns
     *   null otherwise.
     *
     * @return
     */
    public Player getWinner()
    {
        if (gameOver())
            return players.get(0);
        return null;
    }
}
