import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, max;
    static int[] heights;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 나무의 수
        M = Integer.parseInt(st.nextToken()); // 나무의 길이
        heights = new int[N];
        st = new StringTokenizer(br.readLine());
        int maxH = 0; // 최대 높이
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
            maxH = Math.max(maxH, heights[i]);
        }
        //이분 탐색
        int s = 0, e = maxH, mid;
        while(s <= e){
            mid = (s+e)/2;
            if(success(mid)){ // mid보다 위쪽으로 이동
                s = mid+1;
                max = Math.max(max, mid);
            }else{ // mid보다 작은쪽으로 이동
                e = mid-1;
            }
        }
        System.out.println(max);
    }
    //해당 높이에서 자르면 잘라진 길이의 합이 M보다 크거나 같은지 검사
    static boolean success(int h){
        long sum = 0L;
        for (int i = 0; i < N; i++) {
            if(heights[i] < h) continue;
            sum += heights[i] - h;
        }
        if(sum >= M) return true;
        return false;
    }


}