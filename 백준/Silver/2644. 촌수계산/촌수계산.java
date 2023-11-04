import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m, a, b, ans = -1;
    static int[] visited;
    static List<Integer>[] trees;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        trees = new ArrayList[n + 1];
        visited = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            trees[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        int x, y;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            // 부모
            x = Integer.parseInt(st.nextToken());
            // 자식
            y = Integer.parseInt(st.nextToken());
            trees[x].add(y);
            trees[y].add(x);
        }
        visited[a] = 1;
        find(a, 0, b);
        System.out.println(ans);
    }

    // dfs
    static void find(int cur, int cnt, int target) {
        if(cur == target) {
            ans = cnt;
            return;
        }
        int size = trees[cur].size();
        for (int i = 0; i < size; i++) {
            if(visited[trees[cur].get(i)] == 1) continue;
            visited[trees[cur].get(i)] = 1;
            find(trees[cur].get(i), cnt + 1, target);
        }
    }
}