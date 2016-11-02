import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Alyssa on 10/16/2016.
 * https://open.kattis.com/problems/palindromic
 */
public class PalindromicNaming {
    static long MOD = 1000000007;
    static long[][] inner;
    static char[] ch;
    public static void main(String[] args) {
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        int t = 0;
        try {
            t = Integer.parseInt(sc.readLine());

            for(int i = 0; i < t; i++) {
                String s = sc.readLine();
                ch = s.toCharArray();
                inner = new long[ch.length][ch.length];
                long out = recur(0, ch.length - 1);

                System.out.println(Math.floorMod(out, MOD));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //s = start index (inclusive), e = end index (inclusive)
    static long recur(int s, int e) {
        if(s == e) {
            inner[s][e] = 1;
            return 1;
        } else if (s > e) {
            inner[s][e] = 0;
            return 0;
        }
        else if (inner[s][e] == 0) {
            long total = 0;
            long up = recur(s + 1, e);
            long low = recur(s, e - 1);
            long mid = recur(s + 1, e - 1);
            total += up + low - mid;
            if(ch[s] == ch[e]) {
                total += mid + 1;
            }
            inner[s][e] = Math.floorMod(total, MOD);
            return Math.floorMod(total, MOD);
        }
        return Math.floorMod(inner[s][e], MOD);
    }
}