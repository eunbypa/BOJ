import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, min = 2000000001, pt1, pt2;
    private static int[] potions;
    private static int[] sorted;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        potions = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            potions[i] = Integer.parseInt(st.nextToken());
        }
        sorted = Arrays.copyOf(potions, N);
        Arrays.sort(sorted);
        int one, result, s = 0, m = 0, e = N-1; // 선택한 한 용액
        for (int i = 0; i < N-1; i++) {
            // 하나 선택후 나머지 하나 이분탐색으로 제일 가까운 값 찾기
            one = sorted[i];
            s = i+1;
            e = N-1;
            while(s != e){
                m = (s + e) / 2;
                result = Math.abs(one + sorted[m]);
                if(result > Math.abs(one + sorted[m+1])){ // 0에 가까워지는 쪽으로 이분탐색 범위 좁히기
                    s = m + 1;
                }else if(result > Math.abs(one + sorted[m-1])){
                    e = m;
                }else{
                    s = m;
                    break;
                }
            }
            result = Math.abs(one + sorted[s]);
            if(min > result){
                min = result;
                pt1 = one;
                pt2 = sorted[s];
            }
            if(min == 0) break; // 0이 되는 경우를 찾았으므로 더이상 찾을 필요 없음
        }
        if(pt1 <= pt2) System.out.println(pt1 + " " + pt2);
        else System.out.println(pt2 + " " + pt1);
    }
}
