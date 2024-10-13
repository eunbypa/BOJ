import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        System.out.println(getAnswer(n, d));
    }

    static int getAnswer(int n, int d) {
        int[] cntArr = new int[10];
        for (int i = 1; i <= n; i++) {
            setCnt(cntArr, i);
        }
        return cntArr[d];
    }
    
    static void setCnt(int[] cntArr, int n) {
        while(n > 0) {
            cntArr[n % 10]++;
            n /= 10;
        }
    }

}