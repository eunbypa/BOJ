import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Node implements Comparable<Node> {
        int idx;
        int num;

        public Node(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }


        @Override
        public int compareTo(Node o) {
            if(this.num == o.num) return this.idx - o.idx;
            return this.num - o.num;
        }
    }

    static class Node2 implements Comparable<Node2> {
        int idx;
        int idx2;

        public Node2(int idx, int idx2) {
            this.idx = idx;
            this.idx2 = idx2;
        }


        @Override
        public int compareTo(Node2 o) {
            return this.idx - o.idx;
        }
    }

    static int N;
    static Node[] arr;
    static Node2[] arr2;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new Node[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = new Node(i, Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(arr);
        arr2 = new Node2[N];
        for (int i = 0; i < N; i++) {
            arr2[i] = new Node2(arr[i].idx, i);
        }
        Arrays.sort(arr2);
        for (int i = 0; i < N; i++) {
            sb.append(arr2[i].idx2);
            if(i == N-1) break;
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }


}