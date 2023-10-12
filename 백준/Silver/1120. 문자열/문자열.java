import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static String A, B;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = st.nextToken();
        B = st.nextToken();
        int min = A.length();
        int e = B.length() - A.length();
        int tmp;
        for (int i = 0; i <= e; i++) {
            tmp = 0;
            for (int j = 0; j < A.length(); j++) {
                if (A.charAt(j) != B.charAt(i+j)) {
                    tmp++;
                }
            }
            min = Math.min(min, tmp);
        }
        System.out.println(min);
    }


}