import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0;
        int max = 0;
        arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
            if(Math.abs(100-max) > Math.abs(100-sum)) {
                max = sum;
            }else if(Math.abs(100-max) == Math.abs(100-sum)) {
                max = Math.max(max, sum);
            }
        }
        System.out.println(max);
    }

}