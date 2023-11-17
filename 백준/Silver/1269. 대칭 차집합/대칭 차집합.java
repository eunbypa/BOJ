import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int a,b;
    static Map<Integer, Integer> map = new HashMap();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        int cnt = 0;
        Integer n;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < a; i++) {
            map.put(Integer.parseInt(st.nextToken()), 1);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < b; i++) {
            n = map.get(Integer.parseInt(st.nextToken()));
            if(n == null) n = 0;
            else if(n > 0) {
                cnt++;
            }
        }
        System.out.println(a+b-cnt*2);
    }


}