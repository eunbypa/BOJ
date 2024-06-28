import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int idx;
        Node left;
        Node right;

        public Node(int idx) {
            this.idx = idx;
            this.left = null;
            this.right = null;
        }
    }
    static Node[] nodes;
    static int N,M;
    static int curIdx;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nodes = new Node[N+1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i);
        }
        int l, r;
        for (int i = 1; i <= N; i++) {
            l = i > 1 ? i-1 : N;
            r = i < N ? i+1 : 1;
            nodes[i].left = nodes[l];
            nodes[l].right = nodes[i];
            nodes[i].right = nodes[r];
            nodes[r].left = nodes[i];
        }
        st = new StringTokenizer(br.readLine());
        int order;
        int min = 0;
        curIdx = 1;
        for (int i = 0; i < M; i++) {
            order = Integer.parseInt(st.nextToken());
            min += getMinMovingCnt(order);
        }
        System.out.println(min);
    }

    // 최소 이동 횟수 구해서 반환
    public static int getMinMovingCnt(int target) {
        int leftCnt = 0, rightCnt = 0;
        int leftIdx = curIdx, rightIdx = curIdx, nextIdx = curIdx;
        // 왼쪽 이동 횟수 탐색
        while(nodes[nextIdx].idx != target) {
            nextIdx = nodes[nextIdx].right.idx;
            leftIdx = nextIdx;
            leftCnt++;
        }

        // 오른쪽 이동 횟수 탐색
        nextIdx = curIdx;
        while(nodes[nextIdx].idx != target) {
            nextIdx = nodes[nextIdx].left.idx;
            rightIdx = nextIdx;
            rightCnt++;
        }

        if(leftCnt < rightCnt) curIdx = leftIdx;
        else curIdx = rightIdx;
        remove(curIdx);
        curIdx = nodes[curIdx].right.idx;
        return Math.min(leftCnt, rightCnt);
    }

    public static void remove(int idx) {
        nodes[idx].left.right = nodes[idx].right;
        nodes[idx].right.left = nodes[idx].left;
    }


}