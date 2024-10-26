import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Homework implements Comparable<Homework>{

        int deadline;
        int cupRamenCnt;

        public Homework(int deadline, int cupRamenCnt) {
            this.deadline = deadline;
            this.cupRamenCnt = cupRamenCnt;
        }


        @Override
        public int compareTo(Homework o) {
            return Integer.compare(o.cupRamenCnt, this.cupRamenCnt);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Homework[] homeworkArr = new Homework[N];
        int deadline, cupRamenCnt;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            deadline = Integer.parseInt(st.nextToken());
            cupRamenCnt = Integer.parseInt(st.nextToken());
            homeworkArr[i] = new Homework(deadline, cupRamenCnt);
        }

        System.out.println(getAnswer(homeworkArr, N));
    }

    // 유니온 파인드
    static long getAnswer(Homework[] homeworkArr, int N) {
        // 초기는 자기 자신을 가리킴
        // 해당 날짜 포함 이전 날짜들 중 아직 풀 숙제를 정하지 않은 날짜를 가리킴
        int[] availableDayArr = new int[N + 1];
        // 유니온 파인드를 위한 배열 초기화
        initUnionFind(availableDayArr, N+1);

        // 컵라면 개수 기준 내림차순 정렬
        Arrays.sort(homeworkArr);

        long maxCupRamenCnt = 0L;
        int availableTime;
        for (int i = 0; i < N; i++) {
            availableTime = find(availableDayArr, homeworkArr[i].deadline);
            if(availableTime != 0 && availableTime <= homeworkArr[i].deadline) {
                // 해당 숙제를 풀 수 있음
                // 현재 날짜는 이제 풀 숙제가 정해졌으므로 더 이상 사용 불가능 --> 이전 날짜 기준 아직 풀 숙제를 정하지 않은 날짜를 가리키도록 find 실행
                availableDayArr[availableTime] = find(availableDayArr, availableTime - 1);
                maxCupRamenCnt += homeworkArr[i].cupRamenCnt;
            }
        }

        return maxCupRamenCnt;
    }

    private static int find(int[] availableDayArr, int i) {
        if(availableDayArr[i] == i)
            return i;

        return availableDayArr[i] = find(availableDayArr, availableDayArr[i]);
    }

    static void initUnionFind(int[] arr, int len) {
        for (int i = 0; i < len; i++) {
            arr[i] = i;
        }
    }



}