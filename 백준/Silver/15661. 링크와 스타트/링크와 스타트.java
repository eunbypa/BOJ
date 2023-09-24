import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int min;
    static int[] selected;
    static int[][] S;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        min = 40001;
        selected = new int[N];
        dfs(0, 0);
        System.out.println(min);
    }
    // 부분집합
    static void dfs(int cur, int cnt){
        if(cur == N){
            if(cnt != 0) // 한 팀에는 적어도 1명 이상 있어야 함
                // selected에 들어있는 사람 -> 스타트 팀 / 아닌 사람 -> 링크 팀
                min = Math.min(min, calculate());
            return;
        }
        // 현재 cnt번째 사람을 스타트 팀에 영입
        selected[cur] = 1;
        dfs(cur+1,cnt + 1);
        // 링크 팀에 영입
        selected[cur] = 0;
        dfs(cur+1, cnt);
    }

    // 두 팀의 능력치 차이 계산
    static int calculate() {
        int startSum = 0, linkSum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(i==j) continue;
                if(selected[i] == selected[j]){
                    if(selected[i] == 1){
                        // 스타트 팀에 속한 선수들
                        startSum += S[i][j];
                    }else{
                        // 링크 팀에 속한 선수들
                        linkSum += S[i][j];
                    }
                }
            }
        }
        return Math.abs(startSum - linkSum);
    }

}