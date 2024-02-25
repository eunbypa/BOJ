import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node{
        int idx, cnt;
        Node(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }
    static List<Node> list[];
    static int n;
    static int max = 0;
    static boolean visited[];
    static int maxIdx = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n+1];
        for(int i=0; i<=n; i++) {
            list[i] = new ArrayList<>();
        }
        StringTokenizer st;
        int p,c,w;
            for(int i=0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            list[p].add(new Node(c,w));
            list[c].add(new Node(p,w));
        }
        visited = new boolean[n+1];
        visited[1] = true;
        dfs(1,0);
        visited = new boolean[n+1];
        visited[maxIdx] = true;
        dfs(maxIdx,0);
        System.out.println(max);
    }
    public static void dfs(int idx, int cnt) {
        if(max < cnt) {
            max = cnt;
            maxIdx = idx;
        }
        for(Node a : list[idx]) {
            if(!visited[a.idx]) {
                visited[a.idx] = true;
                dfs(a.idx, cnt + a.cnt);
            }
        }
    }
}