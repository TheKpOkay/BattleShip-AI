public class HeatMap
{
    private boolean AC, DE, SU, CR, PB;
    private int[][] map;
    private int[][] ac;
    private int[][] de;
    private int[][] su;
    private int[][] cr;
    private int[][] pb;


    public HeatMap()
    {
        map = new int[10][10];
        ac = new int[10][10];
        de = new int[10][10];
        su = new int[10][10];
        cr = new int[10][10];
        pb = new int[10][10];
    }

    public int[][] getMap()
    {
        return map;
    }

    public void check()
    {
        checkOpenAC();
        checkOpenDE();
        checkOpenCR();
        checkOpenSU();
        checkOpenPB();
    }

    public void placeMiss(Location l)
    {
        map[l.getRow()][l.getCol()] = -1;
        ac[l.getRow()][l.getCol()] = -1;
        de[l.getRow()][l.getCol()] = -1;
        su[l.getRow()][l.getCol()] = -1;
        cr[l.getRow()][l.getCol()] = -1;
        pb[l.getRow()][l.getCol()] = -1;
        check();
    }

    public void addHM()
    {
        for (int r = 0; r < map.length; r++)
            for (int c = 0; c < map.length; c++)
                if (map[r][c] != -1)
                {
                    map[r][c] = 0;
                    if(!AC)
                        map[r][c] += ac[r][c];
                    if(!DE)
                        map[r][c] += de[r][c];
                    if(!CR)
                        map[r][c] += cr[r][c];
                    if(!SU)
                        map[r][c] += su[r][c];
                    if(!PB)
                        map[r][c] += pb[r][c];
                }
    }

    public Location min()
    {
        int row = 0;
        int col = 0;
        for (int r = 0; r < map.length; r++)
            for (int c = 0; c < map.length; c++)
                if(map[r][c] < map[row][col])
                {
                    row = r;
                    col = c;
                }
        return new Location(row,col);
    }

    public Location max()
    {
        int row = 0;
        int col = 0;
        for (int r = 0; r < map.length; r++)
            for (int c = 0; c < map.length; c++)
                if(map[r][c] > map[row][col])
                {
                    row = r;
                    col = c;
                }
        return new Location(row,col);
    }

    public int maxVal()
    {
        int rtn = 0;
        for (int r = 0; r < map.length; r++)
            for (int c = 0; c < map.length; c++)
                if(map[r][c] > rtn)
                {
                    rtn = map[r][c];
                }
        return rtn;
    }

    public int dupe()
    {
        int rtn =0;
        for (int r = 0; r < map.length; r++)
        {
            for (int c = 0; c < map.length; c++)
            {
                if(map[r][c] == map[max().getRow()][max().getCol()])
                    rtn ++;
            }
        }
        return rtn;
    }

    public void clear(int[][] i)
    {
        for (int r = 0; r < i.length; r++)
            for (int c = 0; c < i.length; c++)
                if(i[r][c] != -1)
                    i[r][c] = 0;
    }

    public void checkOpenAC()
    {
        clear(ac);
        for (int r = 0; r < ac.length; r++)
            for (int c = 0; c < ac.length - 5; c++)
                if (ac[r][c] != -1 && ac[r][c + 1] != -1 && ac[r][c + 2] != -1 && ac[r][c + 3] != -1 && ac[r][c + 4] != -1)
                {
                    ac[r][c] += 1;
                    ac[r][c + 1] += 1;
                    ac[r][c + 2] += 1;
                    ac[r][c + 3] += 1;
                    ac[r][c + 4] += 1;

                }
        for (int c = 0; c < ac.length; c++)
            for (int r = 0; r < ac.length - 5; r++)
                if(ac[r][c] != -1 && ac[r + 1][c] != -1 && ac[r + 2][c] != -1 && ac[r + 3][c] != -1 && ac[r + 4][c] != -1)
                {
                    ac[r][c] += 1;
                    ac[r + 1][c] += 1;
                    ac[r + 2][c] += 1;
                    ac[r + 3][c] += 1;
                    ac[r + 4][c] += 1;
                }
    }

    public void checkOpenDE()
    {
        clear(de);
        for (int r = 0; r < de.length; r++)
            for (int c = 0; c < de.length - 4; c++)
                if (de[r][c] != -1 && de[r][c + 1] != -1 && de[r][c + 2] != -1 && de[r][c + 3] != -1)
                {
                    de[r][c] += 1;
                    de[r][c + 1] += 1;
                    de[r][c + 2] += 1;
                    de[r][c + 3] += 1;
                }
        for (int c = 0; c < de.length; c++)
            for (int r = 0; r < de.length - 4; r++)
                if(de[r][c] != -1 && de[r + 1][c] != -1 && de[r + 2][c] != -1 && de[r + 3][c] != -1)
                {
                    de[r][c] += 1;
                    de[r + 1][c] += 1;
                    de[r + 2][c] += 1;
                    de[r + 3][c] += 1;
                }
    }

    public void checkOpenSU()
    {
        clear(su);
        for (int r = 0; r < su.length; r++)
            for (int c = 0; c < su.length - 3; c++)
                if (su[r][c] != -1 && su[r][c + 1] != -1 && su[r][c + 2] != -1)
                {
                    su[r][c] += 1;
                    su[r][c + 1] += 1;
                    su[r][c + 2] += 1;
                }
        for (int c = 0; c < su.length; c++)
            for (int r = 0; r < su.length - 3; r++)
                if(su[r][c] != -1 && su[r + 1][c] != -1 && su[r + 2][c] != -1)
                {
                    su[r][c] += 1;
                    su[r + 1][c] += 1;
                    su[r + 2][c] += 1;
                }
    }

    public void checkOpenCR()
    {
        clear(cr);
        for (int r = 0; r < cr.length; r++)
            for (int c = 0; c < cr.length - 3; c++)
                if (cr[r][c] != -1 && cr[r][c + 1] != -1 && cr[r][c + 2] != -1)
                {
                    cr[r][c] += 1;
                    cr[r][c + 1] += 1;
                    cr[r][c + 2] += 1;
                }
        for (int c = 0; c < cr.length; c++)
            for (int r = 0; r < cr.length - 3; r++)
                if(cr[r][c] != -1 && cr[r + 1][c] != -1 && cr[r + 2][c] != -1)
                {
                    cr[r][c] += 1;
                    cr[r + 1][c] += 1;
                    cr[r + 2][c] += 1;
                }
    }

    public void checkOpenPB()
    {
        clear(pb);
        for (int r = 0; r < pb.length; r++)
            for (int c = 0; c < pb.length - 2; c++)
                if (pb[r][c] != -1 && pb[r][c + 1] != -1)
                {
                    pb[r][c] += 1;
                    pb[r][c + 1] += 1;
                }
        for (int c = 0; c < pb.length; c++)
            for (int r = 0; r < pb.length - 2; r++)
                if(pb[r][c] != -1 && pb[r + 1][c] != -1)
                {
                    pb[r][c] += 1;
                    pb[r + 1][c] += 1;
                }
    }

    public void setAC(boolean AC)
    {
        this.AC = AC;
    }

    public void setDE(boolean DE)
    {
        this.DE = DE;
    }

    public void setSU(boolean SU)
    {
        this.SU = SU;
    }

    public void setCR(boolean CR)
    {
        this.CR = CR;
    }

    public void setPB(boolean PB)
    {
        this.PB = PB;
    }
}
