import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] difficulties = new int[n];
        int difficulty;
        for (int i = 0; i < n; i++) {
            difficulty = Integer.parseInt(br.readLine());
            difficulties[i] = difficulty;
        }
        int avg = (n == 0) ? 0 : getAvg(difficulties);
        System.out.println(avg);
    }

    // 절사평균 구하기
    public static int getAvg(int[] difficulties) {
        int n = difficulties.length;
        int pick = Math.round((n * 15) / (float) 100);
        Arrays.sort(difficulties);
        int e = n - pick;
        int sum = 0;
        for (int i = pick; i < e; i++) {
            sum += difficulties[i];
        }
        return Math.round(sum / (float) (n - 2 * pick));
    }


}