import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Dot implements Comparable<Dot> {
        int x;
        int y;

        public Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Dot o) {
            if(this.x != o.x) return Integer.compare(this.x, o.x);
            return Integer.compare(this.y, o.y);
        }
    }
    static int N;
    static Dot[] dots;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 도시(정점)의 개수
        dots = new Dot[N];
        StringTokenizer st;
        int x, y;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            dots[i] = new Dot(x, y);
        }
        Arrays.sort(dots);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(dots[i].x + " " + dots[i].y+"\n");
        }
        System.out.println(sb.toString());
    }

}