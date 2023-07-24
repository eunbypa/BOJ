import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] lectures;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lectures = new int[N];
        int sum = 0, max = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            lectures[i] = Integer.parseInt(st.nextToken());
            sum += lectures[i];
            max = Math.max(max, lectures[i]);
        }
        // 가능한 시간은 개별 최대 시간 ~ 전체 강의 시간
        int s = max, e = sum, m;
        while(s < e){
            m = (s + e) / 2;
            if(check(m)){ // 통과
                e = m;
            }else{ // 통과 못함
                s = m + 1;
            }
        }
        System.out.println(s);
    }

    // 해당 시간으로 나눌 수 있는지 확인
    public static boolean check(int limit) {
        int sum = 0, cnt = 1;
        for (int i = 0; i < N; i++) {
            sum += lectures[i];
            if(sum > limit){
                sum = lectures[i];
                cnt++;
            }
        }
        if(cnt > M) return false;
        return true;
    }


}