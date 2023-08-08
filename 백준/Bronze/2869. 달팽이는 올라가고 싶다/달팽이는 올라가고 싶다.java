import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int A, B, V;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        int up = A - B;
        int result = (V-A) / up;
        int rem = (V-A) % up;
        if(rem == 0) result++;
        else {
            result++;
            rem -= up;
            if(rem != 0) result++;
        }
        System.out.println(result);
    }

}