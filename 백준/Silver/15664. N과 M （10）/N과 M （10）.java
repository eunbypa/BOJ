import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M;

    static int[] numberArr;

    static StringBuilder sb;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numberArr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numberArr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numberArr);
        sb = new StringBuilder();
        nPr(0, 0, new int[M]);
        System.out.println(sb.toString());
    }

    // n개에서 m개를 고른다
    public static void nPr(int cur, int cnt, int[] selected) {
        if(cnt == M) {
            for (int i = 0; i < M; i++) {
                sb.append(selected[i]);
                if(i == M-1) break;
                sb.append(" ");
            }
            sb.append("\n");
            return;
        }

        int before = 0;
        for (int i = cur; i < N; i++) {
            if(before == numberArr[i]) continue;
            before = numberArr[i];
            selected[cnt] = numberArr[i];
            nPr(i+1, cnt + 1, selected);
        }

    }

}