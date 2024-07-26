import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static final String ENTER = "ENTER";

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> map = new HashMap<>();
        int N = Integer.parseInt(br.readLine());
        String s;
        int user = 0, cnt = 0;
        for (int i = 0; i < N; i++) {
            s = br.readLine();
            if(s.equals(ENTER)) {
                user++;
                continue;
            }
            if(map.containsKey(s)) {
                if(map.get(s) != user) {
                    cnt++;
                    map.put(s, user);
                }
            }else {
                cnt++;
                map.put(s, user);
            }

        }
        System.out.println(cnt);
    }


}