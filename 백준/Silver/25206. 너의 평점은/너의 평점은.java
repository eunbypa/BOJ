import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String subject;
        int tot = 0, num, sum = 0;
        for (int i = 0; i < 20; i++) {
            st = new StringTokenizer(br.readLine());
            subject = st.nextToken();
            num = (int)Double.parseDouble(st.nextToken());
            tot += num;
            switch (st.nextToken()){
                case "A+":
                    sum += num * 45;
                    break;
                case "A0":
                    sum += num * 40;
                    break;
                case "B+":
                    sum += num * 35;
                    break;
                case "B0":
                    sum += num * 30;
                    break;
                case "C+":
                    sum += num * 25;
                    break;
                case "C0":
                    sum += num * 20;
                    break;
                case "D+":
                    sum += num * 15;
                    break;
                case "D0":
                    sum += num * 10;
                    break;
                case "F":
                    break;
                case "P":
                    tot -= num;
                    break;
            }
        }

        System.out.println(Math.round(sum * 100000 / (double)tot) / (double)1000000);
    }
}