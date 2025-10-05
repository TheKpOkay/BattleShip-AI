import java.util.Scanner;

public class V2Driver
{
    public static void main(String[] args)
    {
        int W1 =0;
        int W2 =0;
        Scanner inp = new Scanner(System.in);
        System.out.println("How many Threads?: ");
        int thread = inp.nextInt();
        System.out.println("How many splits?: ");
        int split = inp.nextInt();
        for (int i = 0; i < thread; i++)
        {
            Thread game = new Thread(new VsV2Driver(split));
            game.start();
        }
    }
}
