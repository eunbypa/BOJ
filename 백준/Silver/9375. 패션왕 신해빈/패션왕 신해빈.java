import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int cnt;
    static int tot;
    static Map<String, Integer> map;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            map = new HashMap<>();
            tot = 1;
            cnt = 0;
            arr = new int[30];
            n = Integer.parseInt(br.readLine());
            StringTokenizer st;
            String name, type;
            Integer num;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                name = st.nextToken();
                type = st.nextToken();
                num = map.get(type);
                if(num == null) {
                    num = cnt;
                    cnt++;
                }
                map.put(type, num);
                arr[num]++;
            }
            for (int i = 0; i < cnt; i++) {
                tot *= (arr[i] + 1);
            }
            tot--;
            sb.append(tot+"\n");
        }
        System.out.println(sb.toString());
    }



}