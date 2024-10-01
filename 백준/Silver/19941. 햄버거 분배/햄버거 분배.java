import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static final char PEOPLE = 'P';
    static final char BURGER = 'H';

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String peopleAndBurgers = br.readLine();

        System.out.println(getMaxPeopleWhoEatBurger(peopleAndBurgers, N, K));
    }

    static int getMaxPeopleWhoEatBurger(String peopleAndBurgers, int N, int K) {
        int[] visited = new int[N];
        int cnt = 0;
        int idx;
        for (int i = 0; i < N; i++) {
            if(peopleAndBurgers.charAt(i) == BURGER) continue;
            // 왼쪽부터 제일 먼 거리에 있는 버거 선점
            // 없으면 오른쪽 중 제일 가까운 버거 선점
            idx = getPossibleBurgerLoc(i, N, K, peopleAndBurgers, visited);
            // 먹을 수 있는 버거 없음
            if(idx == -1) continue;
            visited[idx] = 1;
            cnt++;
        }

        return cnt;
    }

    // 그리디
    // 현재 위치에서 먹을 수 있는 버거 위치 찾아서 반환
    static int getPossibleBurgerLoc(int cur, int N, int K, String peopleAndBurgers, int[] visited) {
        int lIdx = -1, rIdx = -1;
        int l = cur - K, r = cur + 1;
        for (int i = 0; i < K; i++) {
            if(lIdx == -1 && l >= 0 && l < N && visited[l] == 0 && peopleAndBurgers.charAt(l) == BURGER) {
                lIdx = l;
            }
            l++;
            if(rIdx == -1 && r >= 0 && r < N && visited[r] == 0 && peopleAndBurgers.charAt(r) == BURGER) {
                rIdx = r;
            }
            r++;
        }
        int idx = -1;

        if(lIdx == -1 && rIdx == -1) idx = -1;
        else if(lIdx != -1) idx = lIdx;
        else if(rIdx != -1) idx = rIdx;

        return idx;
    }

}