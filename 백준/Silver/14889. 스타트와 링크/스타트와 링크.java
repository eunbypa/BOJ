import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, min = 50000;
    static int[][] S;
    static int[] selected; // 1 : 스타트팀에 선택됨을 의미
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        selected = new int[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0);
        System.out.println(min);
    }
    // 조합 dfs
    // cur : 현재 사람 번호
    // cnt : 선택한 개수(N/2개 만큼 선택)
    static void dfs(int cur, int cnt){
        if(min == 0) return; // 이보다 더 최소가 될 수 없으므로 탐색 중단
        if(cnt == N/2){
            min = Math.min(min, getDiff());
            return;
        }
        for (int i = cur; i < N; i++) {
            selected[i] = 1;
            dfs(i+1, cnt+1);
            selected[i] = 0;
        }
    }

    // 두 팀의 능력치 차이를 구해서 반환하는 함수
    private static int getDiff() {
        int startSum = 0, linkSum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(selected[i] == 1 && selected[j] == 1){ // 스타트 팀
                    startSum += S[i][j];
                }
                if(selected[i] == 0 && selected[j] == 0){ // 링크 팀
                    linkSum += S[i][j];
                }
            }
        }
        return Math.abs(startSum - linkSum);
    }

}