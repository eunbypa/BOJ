import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int k;
    private static int[] S;
    private static int[] selected;
    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        boolean fin = false;
        int cnt = 0;
        while(!fin){
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if(k == 0) {
                fin = true;
                continue;
            }
            S = new int[k];
            for (int i = 0; i < k; i++) {
                S[i] = Integer.parseInt(st.nextToken());
            }
            selected = new int[6];
            combi(0, 0);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
    // 조합 구하기
    static void combi(int cur, int cnt){
        if(cnt == 6){
            for (int l = 0; l < 6; l++) {
                sb.append(selected[l]);
                if(l == 5) break;
                sb.append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = cur; i < k; i++) {
            selected[cnt] = S[i];
            combi(i + 1, cnt + 1);
        }
    }
}