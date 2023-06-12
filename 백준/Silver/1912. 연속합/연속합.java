import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        int max = Integer.MIN_VALUE, sum = 0; // 합 최댓값, 합
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            max = Math.max(max, sum); // 최댓값 갱신
            if(sum < 0){ // 더해서 0보다 작아지면 절대 가장 큰 합이 될 수 없다!!
                sum = 0; // 합 초기화
            }
        }

        System.out.println(max);
    }

}