import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        int k,n;
        int[][] arr = new int[15][15];
        for (int j = 1; j <= 14; j++) {
            arr[0][j] += arr[0][j-1] + j;
        }
        for (int i = 1; i <= 14; i++) {
            for (int j = 1; j <= 14; j++) {
                arr[i][j] += arr[i][j-1] + arr[i - 1][j];
            }
        }
        for (int i = 0; i < T; i++) {
            k = Integer.parseInt(br.readLine());
            n = Integer.parseInt(br.readLine());
            sb.append(arr[k-1][n]).append("\n");
        }
        System.out.println(sb.toString());
    }

}