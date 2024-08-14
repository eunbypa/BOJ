import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N, A, B, C;

    static Integer[] D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(br.readLine());
        D = new Integer[N];

        double curCalPerOne = C / (double) A;
        int curCal = C, curCost = A;
        double newCalPerOne = 0.f;
        for (int i = 0; i < N; i++) {
            D[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(D, Collections.reverseOrder());
        for (int i = 0; i < N; i++) {
            newCalPerOne = (curCal + D[i]) / (double) (curCost + B);
            if(newCalPerOne > curCalPerOne) {
                curCalPerOne = newCalPerOne;
                curCost += B;
                curCal += D[i];
            }
        }
        int maxCalPerOne = curCal / curCost;
        System.out.println(maxCalPerOne);
    }

}