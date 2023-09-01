import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static Map<String, Integer> map = new HashMap<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        String name;
        for (int i = 0; i < N; i++) {
            name = br.readLine();
            map.put(name, 1);
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            name = br.readLine();
            if(map.get(name) != null){
                list.add(name);
            }
        }
        Collections.sort(list);
        int size = list.size();
        sb.append(size + "\n");
        for (int i = 0; i < size; i++) {
            sb.append(list.get(i) + "\n");
        }
        System.out.println(sb.toString());
    }

}