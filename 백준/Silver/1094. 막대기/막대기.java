import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int X;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        X = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        list.add(64);
        int sum = 64;
        int half;
        int cnt = 0;
        while(sum > X){
            Collections.sort(list);
            half = list.get(0) / 2;
            list.remove(0);
            list.add(0, half);
            list.add(0, half);
            if(sum - half >= X) {
                list.remove(0);
                sum -= half;
            }
            if(sum == X) break;
        }
        System.out.println(list.size());
    }

}