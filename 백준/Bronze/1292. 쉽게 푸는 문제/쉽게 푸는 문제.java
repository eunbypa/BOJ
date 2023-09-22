import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int A, B;
    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        arr = new int[B+1];
        int idx = 1;
        for (int i = 1; i <= B; i++) {
            for (int j = 0; j < i; j++) {
                arr[idx] = i;
                idx++;
                if(idx > B) break;
            }
            if(idx > B) break;
        }
        int sum = 0;
        for (int i = A; i <= B; i++) {
            sum += arr[i];
        }
        System.out.println(sum);
    }


}