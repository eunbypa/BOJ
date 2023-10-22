import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int A, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer(st.nextToken());
        A = Integer.parseInt(sb.reverse().toString());
        sb = new StringBuffer(st.nextToken());
        B = Integer.parseInt(sb.reverse().toString());
        int result = (A>B) ? A : B;
        System.out.println(result);
    }

}