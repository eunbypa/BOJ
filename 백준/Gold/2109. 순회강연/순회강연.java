import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Lecture implements Comparable<Lecture> {
        int p;
        int d;

        public Lecture(int p, int d) {
            this.p = p;
            this.d = d;
        }

        @Override
        public int compareTo(Lecture o) {
            // p 값이 큰 순으로 내림차순 정렬
            return Integer.compare(o.p, this.p);
        }
    }

    static final int MAX_DAY = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Lecture[] lectures = new Lecture[n];
        int p, d;
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            lectures[i] = new Lecture(p, d);
        }

        System.out.println(getAnswer(n, lectures));
    }

    // 유니온 파인드 활용
    static int getAnswer(int n, Lecture[] lectures) {
        int maxCost = 0;
        int[] parents = new int[MAX_DAY+1];
        init(parents);
        Arrays.sort(lectures);
        int parent;
        for (int i = 0; i < n; i++) {
            parent = findParent(lectures[i].d, parents);
            // 강연을 d일 안에 할 수 없는 경우
            if(parent == 0) continue;
            parents[parent] = parents[parent - 1];
            maxCost += lectures[i].p;
        }

        return maxCost;
    }

    static void init(int[] arr) {
        int l = arr.length;
        for (int i = 0; i < l; i++) {
            arr[i] = i;
        }
    }

    static int findParent(int cur, int[] parents) {
        if(parents[cur] == cur) return cur;
        return parents[cur] = findParent(parents[cur], parents);
    }

}