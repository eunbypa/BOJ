import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<Integer> weights;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        weights = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        int w;
        for (int i = 0; i < N; i++) {
            w = Integer.parseInt(st.nextToken());
            weights.add(w);
        }
        Collections.sort(weights);
        int sum = 0, cur;
        for (int i = 0; i < N; i++) {
            cur = weights.get(i);
            // 누적합 + 1 보다 현재 무게가 더 크면 해당 무게는 절대 측정 불가능!!!
            // 이유 : 누적합 + 1 무게를 만드려면 누적합보다 작은 값의 무게가 있어야 하는데
            // 이미 정렬을 한 상태라 이 이후로는 누적합 + 1 보다 큰 무게의 추밖에 없기 때문!!!
            if(sum + 1 < cur) break;
            sum += weights.get(i);
        }
        System.out.println(sum+1);
    }

}