import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] arr = new int[1000001][2]; // 0 : 양수 1 : 음수
        int n, type;
        for (int i = 0; i < N; i++) {
            n = Integer.parseInt(br.readLine());
            type = (n >= 0) ? 0 : 1;
            if(type == 0) arr[n][type]++;
            else arr[-n][type]++;
        }
        // 양수
        for (int i = 1000000; i >= 0; i--) {
            while(arr[i][0] > 0) {
                arr[i][0]--;
                sb.append(i).append("\n");
            }

        }
        // 음수 
        for (int i = 1; i < 1000001; i++) {
            while(arr[i][1] > 0) {
                arr[i][1]--;
                sb.append(-i).append("\n");
            }

        }
        System.out.println(sb.toString());
    }


}