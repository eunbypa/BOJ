import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Homework implements Comparable<Homework> {
        int idx;
        int deadLine;
        int score;


        public Homework(int idx, int deadLine, int score) {
            this.idx = idx;
            this.deadLine = deadLine;
            this.score = score;
        }

        @Override
        public int compareTo(Homework o) {
            return o.score - this.score;
        }
    }

    static int N;
    static Homework[] homeworks;
    static int[] schedule; // 해당 날짜에 수행한 과제의 점수 기록용

    /**
     * 과제 가치 내림차순 으로 정렬
     * 마감일 당일에 아직 수행한 과제가 없을 경우, 해당 날짜에 해당 과제 수행하는 것으로 결정
     * 수행 과제가 있는 경우, 그 전 날짜로 1씩 감소시켜가면서 확인
     */

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        homeworks = new Homework[N];
        StringTokenizer st;
        int maxDeadLine = 0, deadLine, score;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            deadLine = Integer.parseInt(st.nextToken());
            score = Integer.parseInt(st.nextToken());
            homeworks[i] = new Homework(i, deadLine, score);
            maxDeadLine = Math.max(maxDeadLine, deadLine);
        }
        schedule = new int[maxDeadLine + 1];
        Arrays.sort(homeworks);
        int maxScoreSum = 0, cur;
        for (int i = 0; i < N; i++) {
            cur = homeworks[i].deadLine;
            while(cur > 0 && schedule[cur] != 0) {
                cur--;
            }
            if(cur == 0) continue;
            schedule[cur] = homeworks[i].score;
        }
        for (int i = 1; i <= maxDeadLine; i++) {
            maxScoreSum += schedule[i];
        }
        System.out.println(maxScoreSum);
    }
}
