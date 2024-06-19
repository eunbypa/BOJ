import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static class Node implements Comparable<Node> {
        int num;
        int idx;

        public Node(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }

        @Override
        public int compareTo(Node o) {
            return this.num - o.num;
        }
    }
    static int N;
    static Node[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new Node[N + 1];
        int n;
        for (int i = 1; i <= N; i++) {
            n = Integer.parseInt(br.readLine());
            arr[i] = new Node(n, i);
        }

        Arrays.sort(arr, 1, N+1);

        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, arr[i].idx - i);
        }

        System.out.println(max+1);

    }

}