import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static Map<String, Integer> map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x,y;
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new HashMap<>();
        String word;
        for (int i = 0; i < N; i++) {
            word = br.readLine();
            map.put(word, 1);
        }
        int cnt= 0;
        for (int i = 0; i < M; i++) {
            word = br.readLine();
            if(map.containsKey(word)) cnt++;
        }
        System.out.println(cnt);
    }



}