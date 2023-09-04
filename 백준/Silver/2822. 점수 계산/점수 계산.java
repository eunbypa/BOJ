import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] scores;
    static int[] idx;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        scores = new int[8];
        idx = new int[151];
        for (int i = 0; i < 8; i++) {
            scores[i] = Integer.parseInt(br.readLine());
            idx[scores[i]] = i+1;
        }
        Arrays.sort(scores);
        List<Integer> list = new ArrayList<>();
        int sum = 0;
        for (int i = 7; i >= 3; i--) {
            sum += scores[i];
            list.add(idx[scores[i]]);
        }
        sb.append(sum + "\n");
        Collections.sort(list);
        for (int i = 0; i < 5; i++) {
            sb.append(list.get(i));
            if(i == 4) break;
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }


}