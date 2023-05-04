import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[] choices;
    static boolean[] visited;
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        choices = new int[N];
        visited = new boolean[N + 1];

        dfs(0);

        System.out.println(result);
    }

    static void dfs(int cnt) {
        if (cnt == N){
            for (int i = 0; i < N; i++) {
                result.append(choices[i]).append(" ");
            }
            result.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            choices[cnt] = i;
            dfs(cnt + 1);
            visited[i] = false;
        }
    }
}