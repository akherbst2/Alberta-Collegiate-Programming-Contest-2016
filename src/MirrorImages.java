import java.util.Scanner;

/**
 * Created by Alyssa on 10/15/2016.
 * https://open.kattis.com/problems/mirror
 */
public class MirrorImages {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.next());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < t; i++) {
            sb.append("Test ");
            sb.append(i + 1);
            sb.append("\n");
            int row = Integer.parseInt(sc.next());
            int col = Integer.parseInt(sc.next());
            char[][] in = new char[row][col];
            sc.nextLine();
            for(int r = 0; r < row; r++) {
                String line = sc.nextLine();
                in[r] = line.toCharArray();
            }

            for(int r = row - 1; r >= 0; r--) {
                for(int c = col - 1; c >= 0; c--) {
                    sb.append(in[r][c]);
                }
                sb.append("\n");
            }
        }

        System.out.print(sb);


    }
}
