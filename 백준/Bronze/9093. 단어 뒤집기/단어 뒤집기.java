import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            while(st.hasMoreTokens()) {
                StringBuffer sb2 = new StringBuffer(st.nextToken());
                sb.append(sb2.reverse());
                if(st.hasMoreTokens()) sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

}