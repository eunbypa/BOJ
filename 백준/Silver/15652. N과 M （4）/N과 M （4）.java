import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int N, M;
    static int[] selected;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selected = new int[M];
        dfs(1, 0);
        System.out.println(sb.toString());
    }
    // 중복 조합
    static void dfs(int cur, int cnt){
        if(cnt == M){
            for (int i = 0; i < M; i++) {
                sb.append(selected[i]);
                if(i == M-1) break;
                sb.append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = cur; i <= N; i++) {
            selected[cnt] = i;
            dfs(i, cnt + 1);
        }
    }
}