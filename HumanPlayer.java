import jdk.swing.interop.DropTargetContextWrapper;

import javax.sql.rowset.RowSetWarning;
import javax.swing.*;
import java.util.Random;
import java.util.RandomAccess;
import java.util.concurrent.locks.Lock;

public class HumanPlayer extends Player
{
    public HumanPlayer(String name)
    {
        super(name);
        populateShips();
    }

    /**
     * Attack the specified Location loc.  Marks
     *   the attacked Location on the guess board
     *   with a positive number if the enemy Player
     *   controls a ship at the Location attacked;
     *   otherwise, if the enemy Player does not
     *   control a ship at the attacked Location,
     *   guess board is marked with a negative number.
     *
     * If the enemy Player controls a ship at the attacked
     *   Location, the ship must add the Location to its
     *   hits taken.  Then, if the ship has been sunk, it
     *   is removed from the enemy Player's list of ships.
     *
     * Return true if the attack resulted in a ship sinking;
     *   false otherwise.
     *
     * @param enemy
     * @param loc
     * @return
     */
    @Override
    public boolean attack(Player enemy, Location loc)
    {
        if(enemy.hasShipAtLocation(loc))
        {
            super.getGuessBoard()[loc.getRow()][loc.getCol()] = 1;
            enemy.getShip(loc).takeHit(loc);
            if (enemy.getShip(loc).isSunk())
            {
                enemy.removeShip(enemy.getShip(loc));
                return true;
            }
        }
        else
            super.getGuessBoard()[loc.getRow()][loc.getCol()] = -1;
        return false;
    }

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
    @Override
    public void populateShips()
    {
        Random ran = new Random();

        if (ran.nextInt(2) == 0)
        {
            int rAC = ran.nextInt(10);
            int cAC = ran.nextInt(6);
            addShip(new AircraftCarrier(new Location(rAC, cAC), new Location(rAC, cAC + 1), new Location(rAC, cAC + 2), new Location(rAC, cAC + 3), new Location(rAC, cAC + 4)));
        } else
        {
            int rAC = ran.nextInt(6);
            int cAC = ran.nextInt(10);
            addShip(new AircraftCarrier(new Location(rAC, cAC), new Location(rAC + 1, cAC), new Location(rAC + 2, cAC), new Location(rAC + 3, cAC), new Location(rAC + 4, cAC)));
        }


        if (ran.nextInt(2) == 0)
        {
            int rDE = ran.nextInt(10);
            int cDE = ran.nextInt(7);
            while (hasShipAtLocation(new Location(rDE, cDE)) || hasShipAtLocation(new Location(rDE, cDE + 1)) || hasShipAtLocation(new Location(rDE, cDE + 2)) || hasShipAtLocation(new Location(rDE, cDE + 3)))
            {
                rDE = ran.nextInt(10);
                cDE = ran.nextInt(7);
            }
            addShip(new Destroyer(new Location(rDE, cDE), new Location(rDE, cDE + 1), new Location(rDE, cDE + 2), new Location(rDE, cDE + 3)));
        } else
        {
            int rDE = ran.nextInt(7);
            int cDE = ran.nextInt(10);
            while (hasShipAtLocation(new Location(rDE, cDE)) || hasShipAtLocation(new Location(rDE + 1, cDE)) || hasShipAtLocation(new Location(rDE + 2, cDE)) || hasShipAtLocation(new Location(rDE + 3, cDE)))
            {
                rDE = ran.nextInt(7);
                cDE = ran.nextInt(10);
            }
            addShip(new Destroyer(new Location(rDE, cDE), new Location(rDE + 1, cDE), new Location(rDE + 2, cDE), new Location(rDE + 3, cDE)));
        }

        if (ran.nextInt(2) == 0)
        {
            int rSU = ran.nextInt(10);
            int cSU = ran.nextInt(8);
            while (hasShipAtLocation(new Location(rSU, cSU)) || hasShipAtLocation(new Location(rSU, cSU + 1)) || hasShipAtLocation(new Location(rSU, cSU + 2)))
            {
                rSU = ran.nextInt(10);
                cSU = ran.nextInt(8);
            }
            addShip(new Submarine(new Location(rSU, cSU), new Location(rSU, cSU + 1), new Location(rSU, cSU + 2)));
        } else
        {
            int rSU = ran.nextInt(8);
            int cSU = ran.nextInt(10);
            while (hasShipAtLocation(new Location(rSU, cSU)) || hasShipAtLocation(new Location(rSU + 1, cSU)) || hasShipAtLocation(new Location(rSU + 2, cSU)))
            {
                rSU = ran.nextInt(8);
                cSU = ran.nextInt(10);
            }
            addShip(new Submarine(new Location(rSU, cSU), new Location(rSU + 1, cSU), new Location(rSU + 2, cSU)));
        }

        if (ran.nextInt(2) == 0)
        {
            int rCR = ran.nextInt(10);
            int cCR = ran.nextInt(8);
            while (hasShipAtLocation(new Location(rCR, cCR)) || hasShipAtLocation(new Location(rCR, cCR + 1)) || hasShipAtLocation(new Location(rCR, cCR + 2)))
            {
                rCR = ran.nextInt(10);
                cCR = ran.nextInt(8);
            }
            addShip(new Cruiser(new Location(rCR, cCR), new Location(rCR, cCR + 1), new Location(rCR, cCR + 2)));
        } else
        {
            int rCR = ran.nextInt(8);
            int cCR = ran.nextInt(10);
            while (hasShipAtLocation(new Location(rCR, cCR)) || hasShipAtLocation(new Location(rCR + 1, cCR)) || hasShipAtLocation(new Location(rCR + 2, cCR)))
            {
                rCR = ran.nextInt(8);
                cCR = ran.nextInt(10);
            }
            addShip(new Cruiser(new Location(rCR, cCR), new Location(rCR + 1, cCR), new Location(rCR + 2, cCR)));
        }

        if (ran.nextInt(2) != 0)
        {
            int rPB = ran.nextInt(10);
            int cPB = ran.nextInt(9);
            while (hasShipAtLocation(new Location(rPB, cPB)) || hasShipAtLocation(new Location(rPB, cPB + 1)))
            {
                rPB = ran.nextInt(10);
                cPB = ran.nextInt(9);
            }
            addShip(new PatrolBoat(new Location(rPB, cPB), new Location(rPB, cPB + 1)));
        } else
        {
            int rPB = ran.nextInt(9);
            int cPB = ran.nextInt(10);
            while (hasShipAtLocation(new Location(rPB, cPB)) || hasShipAtLocation(new Location(rPB + 1, cPB)))
            {
                rPB = ran.nextInt(9);
                cPB = ran.nextInt(10);
            }
            addShip(new PatrolBoat(new Location(rPB, cPB), new Location(rPB + 1, cPB)));
        }
    }
}
