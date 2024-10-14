import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int MAX_VALUE = 1000000;

    static class Node {
        int[] numArr;
        int cnt;

        public Node(int[] numArr, int cnt) {
            this.numArr = numArr;
            this.cnt = cnt;
        }

        public int convert() {
            int num = 0;
            int l = numArr.length;
            for (int i = 0; i < l; i++) {
                num = num * 10 + numArr[i];
            }

            return num;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());


        System.out.println(getAnswer(N, K));

    }

    // bfs
    static int getAnswer(int N, int K) {
        int answer = -1;
        int[] numArr = getNumArr(N);
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(numArr, 0));
        int[][] visited = new int[MAX_VALUE+1][K + 1];
        Node cur;
        int l = numArr.length;
        while (!q.isEmpty()) {
            cur = q.poll();
            if(cur.cnt == K) {
                answer = Math.max(answer, cur.convert());
                continue;
            }

            for (int i = 0; i < l-1; i++) {
                for (int j = i+1; j < l; j++) {
                    swap(cur.numArr, i, j);
                    if(cur.numArr[0] != 0 && visited[cur.convert()][cur.cnt+1] == 0) {
                        visited[cur.convert()][cur.cnt + 1] = 1;
                        q.offer(new Node(Arrays.copyOf(cur.numArr, cur.numArr.length), cur.cnt + 1));
                    }
                    swap(cur.numArr, i, j);
                }
            }
        }

        return answer;
    }

    static int[] getNumArr(int N) {
        int[] arr = new int[7];
        int idx = 0;
        while(N > 0) {
            arr[idx++] = N % 10;
            N /= 10;
        }

        int[] arr2 = new int[idx];
        for (int i = 0; i < idx; i++) {
            arr2[i] = arr[idx-1-i];
        }

        return arr2;
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }





}