import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N, d, k, c;
    static int[] dishes;
    static int[] sushis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        dishes = new int[N]; // 접시의 초밥 번호 저장
        sushis = new int[d+1]; // k개를 골랐을 때, 초밥 번호 당 등장 횟수 저장
        for (int i = 0; i < N; i++) {
            dishes[i] = Integer.parseInt(br.readLine());
        }
        int max = findMaxCnt();
        System.out.println(max);
    }

    // 슬라이딩 윈도우
    public static int findMaxCnt() {
        int max = 0;
        int cnt = 0;
        // 초기값 세팅
        for (int i = 0; i < k; i++) {
            sushis[dishes[i]]++;
            if(sushis[dishes[i]] == 1){
                // 처음 등장한 초밥
                cnt++;
            }
        }
        // 쿠폰번호에 해당하는 초밥 번호가 등장하지 않은 경우 가짓수에 해당 초밥 추가
        if(sushis[c] == 0) max = Math.max(max, cnt + 1);
        else max = Math.max(max, cnt);
        // 한칸씩 이동
        int next = 0;
        for (int i = 1; i < N; i++) {
            // 바로 전칸 초밥 등장 횟수 1 감소
            sushis[dishes[i-1]]--;
            // 등장 횟수가 0이 된 상태면 가짓수 감소
            if(sushis[dishes[i-1]] == 0) cnt--;
            next = (i + k - 1) % N;
            // 다음칸 초밥 등장 횟수 1 증가
            sushis[dishes[next]]++;
            // 등장 횟수가 1이 된 상태면 가짓수 증가
            if(sushis[dishes[next]] == 1) cnt++;
            if(sushis[c] == 0){
                // 쿠폰 사용 가능한 상태이므로 가짓수에 해당 초밥 추가
                max = Math.max(max, cnt + 1);
            }else max = Math.max(max, cnt);
        }
        return max;
    }

}