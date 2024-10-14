import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int M;
        for (int i = 0; i < T; i++) {
            M = Integer.parseInt(br.readLine());
            sb.append(getAnswer(M, br));
        }
        System.out.println(sb.toString());
    }


    // 왼쪽 큐는 최대힙, 오른쪽 큐는 최소 힙
    // 크기는 왼쪽이 오른쪽 큐보다 최대 1 많음
    // 왼쪽 큐 제일 앞에 있는 값이 중앙값
    static String getAnswer(int M, BufferedReader br) throws IOException {
        int r = (M % 10 == 0) ? M / 10 : M / 10 + 1;
        StringBuilder sb = new StringBuilder();
        int cnt = (M % 2 == 1) ? M / 2 + 1 : M / 2;
        sb.append(cnt).append("\n");
        PriorityQueue<Integer> rightMin = new PriorityQueue<>();
        PriorityQueue<Integer> leftMax = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });

        StringTokenizer st;
        int c = 0, n, mid;
        for (int i = 0; i < r; i++) {
            c = Math.min(M, 10);
            M -= 10;
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= c; j++) {
                n = Integer.parseInt(st.nextToken());
                setMid(leftMax, rightMin, n);

                if(j % 2 == 1) {
                    mid = leftMax.peek();
                    sb.append(mid).append(" ");
                }
            }
            if(i % 2 == 1)
                sb.append("\n");
        }

        if(r % 2 == 1)
            sb.append("\n");

        return sb.toString();
    }

    static void setMid(PriorityQueue<Integer> leftMax, PriorityQueue<Integer> rightMin, int n) {
        if(leftMax.isEmpty()) {
            leftMax.offer(n);
            return;
        }

        int lm = leftMax.peek();

        if(rightMin.isEmpty()) {
            if(n < lm) {
                rightMin.offer(lm);
                leftMax.poll();
                leftMax.offer(n);
            }else
                rightMin.offer(n);
            return;
        }

        int rm = rightMin.peek();


        if(leftMax.size() == rightMin.size()) {
            if(n > rm) {
                leftMax.offer(rm);
                rightMin.poll();
                rightMin.offer(n);
            }else {
                leftMax.offer(n);
            }
        }else if(leftMax.size() == rightMin.size() + 1) {
            if(n >= rm) {
                rightMin.offer(n);
            }else {
                if(n <= lm) {
                    rightMin.offer(lm);
                    leftMax.poll();
                    leftMax.offer(n);
                }else {
                    rightMin.offer(n);
                }
            }
        }

    }
}