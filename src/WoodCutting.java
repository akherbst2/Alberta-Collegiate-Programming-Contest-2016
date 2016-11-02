import java.util.*;

/**
 * Created by Alyssa on 10/15/2016.
 * https://open.kattis.com/problems/woodcutting
 */
public class WoodCutting {
    public static void main(String[] args) {


        //read in all people and time it takes per person

        //sort times smallest -> largest

        //long sumOfPrev = 0;
        //long total = total + sumOfPrev + curr
        //sumOfPrev += curr;

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int t = sc.nextInt();

        for(int i = 0; i < t; i++) {
            int n = sc.nextInt();
            List<Integer> times = new ArrayList<>();
            for(int k = 0; k < n; k++) {
                int time = 0;
                int w = sc.nextInt();
                for(int j = 0; j < w; j++) {
                    time += sc.nextInt();
                }
                times.add(time);

            }
            Collections.sort(times);
            long sumOfPrev = 0;
            long total = 0;
            for(Integer curr:times) {
                total += sumOfPrev + curr;
                sumOfPrev += curr;
            }
            sb.append(total / (n * 1.0));
            sb.append("\n");
        }

        System.out.print(sb);
    }

}
