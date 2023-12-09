import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int K;
    static List<Integer> scoreList;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int N;
        int gap = 0;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            scoreList = new ArrayList<>();
            gap = 0;
            N = Integer.parseInt(st.nextToken());
            for (int j = 0; j < N; j++) {
                scoreList.add(Integer.parseInt(st.nextToken()));
            }
            Collections.sort(scoreList);
            sb.append("Class ").append(i + 1).append("\n");
            sb.append("Max ").append(scoreList.get(N-1)).append(", ");
            sb.append("Min ").append(scoreList.get(0)).append(", ");
            for (int j = 0; j < N-1; j++) {
                if(scoreList.get(j+1)-scoreList.get(j) > gap) {
                    gap = scoreList.get(j + 1) - scoreList.get(j);
                }
            }
            sb.append("Largest gap ").append(gap).append("\n");
        }
        System.out.println(sb.toString());
    }

}