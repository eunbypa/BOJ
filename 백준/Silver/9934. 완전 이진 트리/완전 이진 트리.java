import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int cnt = 1;
    static int[] bst;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        int size = (int) Math.pow(2, K);
        int[] arr = new int[size];
        bst = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < size; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        inOrder(1, size, arr);
        System.out.println(print_bfs(size));

    }

    // 중위순회
    static void inOrder(int idx, int size, int[] arr) {
        // 왼쪽
        if(idx*2 < size) inOrder(idx*2, size, arr);
        // 부모
        bst[idx] = arr[cnt++];
        // 오른쪽
        if(idx*2+1 < size) inOrder(idx*2+1, size, arr);
    }

    static String print_bfs(int size) {
        StringBuilder sb = new StringBuilder();
        Queue<int[]> q = new LinkedList<>();
        int[] cur;
        int level = 1;
        q.offer(new int[]{1, 1});
        while(!q.isEmpty()) {
            cur = q.poll();
            if(level != cur[1]) {
                sb.append("\n");
                level++;
            }
            sb.append(bst[cur[0]]).append(" ");
            // 왼쪽
            if(cur[0]*2 < size) {
                q.offer(new int[]{cur[0] * 2, cur[1] + 1});
            }
            // 오른쪽
            if(cur[0]*2+1 < size) {
                q.offer(new int[]{cur[0] * 2 + 1, cur[1] + 1});
            }
        }
        return sb.toString();
    }


}