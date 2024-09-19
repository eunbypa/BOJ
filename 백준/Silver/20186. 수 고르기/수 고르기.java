import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Score implements Comparable<Score> {
        int idx;
        int score;

        public Score(int idx, int score) {
            this.idx = idx;
            this.score = score;
        }

        @Override
        public int compareTo(Score o) {
            return this.score - o.score;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Score[] scores = new Score[N];
        int score;
        for (int i = 0; i < N; i++) {
            score = Integer.parseInt(st.nextToken());
            scores[i] = new Score(i, score);
        }

        System.out.println(getMaxScore(N, K, scores));
    }

    // 그리디
    static int getMaxScore(int N, int K, Score[] scores) {
        // 점수 기준으로 오름차순 정렬
        Arrays.sort(scores);
        int[] sortedIdxArr = new int[N];
        for (int i = 0; i < N; i++) {
            sortedIdxArr[scores[i].idx] = i;
        }

        // K개의 큰 수 선택
        int[] selected = new int[N];
        for (int i = 0; i < K; i++) {
            selected[scores[N-1-i].idx] = 1;
        }

        int sum = 0, cnt = 0;
        for (int i = 0; i < N; i++) {
            if(selected[i] == 0) continue;
            sum += (scores[sortedIdxArr[i]].score - cnt);
            cnt++;
        }

        return sum;
    }

}