import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Alyssa on 10/15/2016.
 * https://open.kattis.com/problems/flowergarden
 */
public class FlowerGarden {

    static boolean[] isPrime;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int dLimit = sc.nextInt();
            Point prev = new Point(0, 0);
            double dTotal = 0;
            int flowerTotal = 0;
            int lowestPrime = 0;
            boolean isOver = false;
            for(int j = 0; j < n; j++) {
                //all points must be "read"
                Point curr = new Point(sc.nextInt(), sc.nextInt());
                double dist = prev.distance(curr);
                if((dist + dTotal <= dLimit)&&!isOver) {
                    dTotal += dist;
                    prev = curr;
                    flowerTotal++;
                    if(isPrime[flowerTotal]) {
                        lowestPrime = flowerTotal;
                    }
                }
                else isOver = true;
            }
            sb.append(lowestPrime);
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static {
        isPrime = new boolean[20001];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for(int i = 2; i < isPrime.length; i++) {
            if(isPrime[i]) {
                for(int j = 2*i; j < isPrime.length; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }
}
