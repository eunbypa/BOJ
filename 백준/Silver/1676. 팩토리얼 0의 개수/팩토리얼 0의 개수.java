import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int two = 0, five = 0;
        int tmp = 0;
        for (int i = 1; i <= N; i++) {
            tmp = i;
            while(tmp % 2 == 0){
                two++;
                tmp /= 2;
            }
            tmp = i;
            while(tmp % 5 == 0){
                five++;
                tmp /= 5;
            }
        }
        int result = (two >= five) ? five : two;
        System.out.println(result);
    }

}