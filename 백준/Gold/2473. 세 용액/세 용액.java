import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static long min = Long.parseLong("3000000001");
    private static int[] potions;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        potions = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            potions[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(potions);
        int s, e;
        long one = 0L, two = 0L, three = 0L, result = 0L;
        long[] selected = new long[3];
        // 투 포인터 알고리즘
        for (int i = 0; i < N-2; i++) {
            one = potions[i];
            s = i+1;
            e = N-1;
            while(s < e){
                two = potions[s];
                three = potions[e];
                result = one + two + three; // 세 용액을 합한 값
                if (min > Math.abs(result)) {
                    min = Math.abs(result);
                    selected[0] = one;
                    selected[1] = two;
                    selected[2] = three;
                }
                if(result > 0) { // 0보다 큰 상황
                    e--;
                }else if(result < 0){
                    s++;
                }else{
                    break;
                }
            }
            if(min == 0) break; // 0이 되는 경우를 찾았으므로 더이상 찾을 필요 없음
        }
        Arrays.sort(selected);
        System.out.println(selected[0] + " " + selected[1] + " " + selected[2]);
    }
}
