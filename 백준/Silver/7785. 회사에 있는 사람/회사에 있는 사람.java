import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    static String[] names;
    static Map<String, Integer> map = new HashMap<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        names = new String[n];
        String name, state;
        StringTokenizer st;
        int cnt = 0;
        Integer idx;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            name = st.nextToken();
            state = st.nextToken();
            idx = map.get(name);
            if(idx == null) {
                idx = cnt;
                names[idx] = name;
                map.put(name, cnt++);
            }
            if(state.equals("enter")) arr[idx]++;
            else arr[idx]--;
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            if(arr[i] == 1){
                list.add(names[i]);
            }
        }
        Collections.sort(list);
        int size = list.size();
        for (int i = size-1; i >= 0; i--) {
            sb.append(list.get(i)+"\n");
        }
        System.out.println(sb.toString());
    }

}