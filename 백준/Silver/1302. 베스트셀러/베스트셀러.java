import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static Map<String, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String title;
        Integer cnt;
        int max = 0;
        for (int i = 0; i < N; i++) {
            title = br.readLine();
            cnt = map.remove(title);
            if(cnt == null) cnt = 0;
            cnt++;
            max = Math.max(max, cnt);
            map.put(title, cnt);
        }
        String ans = null;
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            if(e.getValue() == max) {
                list.add(e.getKey());
            }
        }
        Collections.sort(list);
        System.out.println(list.get(0));
    }

}