import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] V;
    static int[] resultArr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = new int[N];
        resultArr = new int[N];
        for (int i = 0; i < N; i++) {
            V[i] = Integer.parseInt(st.nextToken());
        }

        // 거꾸로 구해보기
        // 도착점 바로 전은 반드시 1이여야 함
        resultArr[N-1] = 1;
        for (int i = N-2; i >= 0; i--) {
            if(resultArr[i+1] < V[i]) { // 1만 줄어들 수 있다
                resultArr[i] = resultArr[i+1] + 1;
            }else { // 커지는 것은 맘대로 가능
                resultArr[i] = V[i];
            }
        }
        long sum = 0L;
        for (int i = 0; i < N; i++) {
            sum = sum + (long)resultArr[i];
        }

        System.out.println(sum);
    }


}