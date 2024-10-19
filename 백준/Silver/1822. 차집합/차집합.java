import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int A = 1;
    static final int B = 2;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> map = new HashMap<>();
        int[] arr = getArr(br, a);
        setMap(map, B, br, b);
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < a; i++) {
            if(map.containsKey(arr[i])) continue;
            list.add(arr[i]);
        }

        int size = list.size();
        sb.append(size).append("\n");
        for (int i = 0; i < size; i++) {
            sb.append(list.get(i));
            if(i == size-1) break;
            sb.append(" ");
        }

        System.out.println(sb.toString());
    }

    static int[] getArr(BufferedReader br, int l) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[l];
        int n;
        for (int i = 0; i < l; i++) {
            n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }
        return arr;
    }


    static void setMap(Map<Integer, Integer> map, int mark, BufferedReader br, int l) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n;

        for (int i = 0; i < l; i++) {
            n = Integer.parseInt(st.nextToken());
            map.put(n, mark);
        }
    }

}