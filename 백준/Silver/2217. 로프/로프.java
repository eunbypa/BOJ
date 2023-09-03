import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static int N;
    static List<Integer> weights;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        weights = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            weights.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(weights);
        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, weights.get(i) * (N - i));
        }
        System.out.println(max);
    }
}