import java.util.Scanner;

public class VsV2Driver implements Runnable
{
    private int p1W;
    private int p2W;
    private int games;

    public int getP1W()
    {
        return p1W;
    }

    public int getP2W()
    {
        return p2W;
    }

    public VsV2Driver(int games)
    {
        p1W = 0;
        p2W = 0;
        this.games = games;
    }


    public void run()
    {
        while(games > 0)
        {
            Battleship battleship = new Battleship();
            battleship.addPlayer(new ExpertComp("R"));        // construct player1's AI here
            battleship.addPlayer(new IloWawa("Blue"));       // construct player2's AI here
            Player p1 = battleship.getPlayer(0);
            Player p2 = battleship.getPlayer(1);

            boolean p1Turn = true;
            while (!battleship.gameOver())
            {
                if (p1Turn)
                    p1.attack(p2, new Location(0, 0));
                else
                    p2.attack(p1, new Location(0, 0));

                p1Turn = !p1Turn;

                battleship.upkeep();
            }

            if (battleship.getPlayer(0) == p1)
                p1W++;
            else
                p2W++;

            battleship = new Battleship();
            battleship.addPlayer(new ExpertComp("R"));        // construct player1's AI here
            battleship.addPlayer(new IloWawa("Blue"));       // construct player2's AI here
            p1 = battleship.getPlayer(0);
            p2 = battleship.getPlayer(1);

            boolean p2Turn = true;
            while (!battleship.gameOver())
            {
                if (p1Turn)
                    p2.attack(p1, new Location(0, 0));
                else
                    p1.attack(p2, new Location(0, 0));

                p2Turn = !p2Turn;

                battleship.upkeep();
            }

            if (battleship.getPlayer(0) == p1)
                p1W++;
            else
                p2W++;
            games--;
        }
        System.out.println(p1W + " wins of 1, " + p2W + " wins of 2");
    }
}

