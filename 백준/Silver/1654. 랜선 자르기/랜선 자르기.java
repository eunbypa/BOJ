import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int K, N;
    static long max;
    static int[] length; // K개 랜선의 길이
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken()); // 나무의 수
        N = Integer.parseInt(st.nextToken()); // 나무의 길이
        length = new int[K];
        for (int i = 0; i < K; i++) {
            length[i] = Integer.parseInt(br.readLine());
            if(max < length[i]) {
				max = length[i];
			}
        }
        //이분 탐색
        long s = 0, e = max+1, mid;
        while(s < e){
            mid = (s+e)/2;
            long cnt = 0L;
            for (int i = 0; i < K; i++) {
                cnt += (length[i] / mid); // l의 길이로 자른 개수
            }
            if(cnt >= N){ // mid보다 위쪽으로 이동
                s = mid+1;
            }else{ // mid보다 작은쪽으로 이동
                e = mid;
            }
        }
        System.out.println(s-1);
    }
}