import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[][] people;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        people = new int[N][2];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                people[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int rank = 1;
        for (int i = 0; i < N; i++) {
            rank = 1;
            for (int j = 0; j < N; j++) {
                if(i == j) continue;
                if(people[i][0] < people[j][0] && people[i][1] < people[j][1])
                    rank++;
            }
            sb.append(rank);
            if(i == N-1) break;
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}