import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static String number;
    static int[][] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); 
        number = br.readLine();
        nums = new int[N][10];
        for (int i = 0; i < N; i++) {
            nums[i][number.charAt(i) - '0']++;
            if(i == 0) continue;
            for (int j = 0; j < 10; j++) {
                nums[i][j] += nums[i-1][j];
            }
        }
        StringBuilder sb = new StringBuilder();
        int max = 0, idx = 0;
        while(K != 0) {
            for (int i = 9; i >= 0; i--) {
                if(idx + K >= N) break;
                if((idx == 0 && nums[idx+K][i] > 0) || (idx != 0 && nums[idx+K][i] - nums[idx-1][i] > 0)) {
                    max = i;
                    break;
                }
            }
            if(idx + K >= N) {
                idx = N;
                break; // 남은 숫자들은 모두 삭제하면 되므로 종료
            }
            for (int i = idx; i < N; i++) {
                if(number.charAt(i) - '0' == max) {
                    idx = i+1;
                    sb.append(max);
                    break;
                }
                K--;
            }
        }
        for (int i = idx; i < N; i++) {
            sb.append(number.charAt(i));
        }
        System.out.println(sb.toString());
    }


}