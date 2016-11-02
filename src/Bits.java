import java.util.Scanner;

/**
 * Created by Alyssa on 10/15/2016.
 * https://open.kattis.com/problems/bits
 */
public class Bits {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(sc.next());

        for(int i = 0; i < t; i++) {
            String in = sc.next();
            int len = in.length();
            long num = Integer.parseInt(in);
            int max = 0;
            for(int k = len - 1; k >= 0; k--) {
                long tens = (long) Math.pow(10, k);
                char[] bin = Long.toBinaryString(num / tens).toCharArray();
                int total = 0;
                for(int n = 0; n < bin.length; n++) {
                    if(bin[n] == '1') total++;
                }
                max = Math.max(total, max);
            }
            sb.append(max);
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
