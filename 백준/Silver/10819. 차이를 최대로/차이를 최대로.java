import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        int n;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            n = Integer.parseInt(st.nextToken());
            A[i] = n;
        }
        int max = 0;
        Arrays.sort(A);
        do {
            max = Math.max(max, getAnswer());
        }
        while (nextPerm());
        max = Math.max(max, getAnswer());
        System.out.println(max);
    }

    public static int getAnswer() {
        int sum = 0;
        for (int i = 0; i < N-1; i++) {
            sum += Math.abs(A[i] - A[i + 1]);
        }
        return sum;
    }

    // 브루트 포스로 모든 순열 탐색
    public static boolean nextPerm() {
        int i = N-1;
        int j = N-1;
        while(i > 0 && A[i-1] >= A[i]) i--;
        if(i == 0) return false;
        while(A[i-1] >= A[j]) j--;
        swap(i-1, j);
        j = N-1;
        while(i < j) {
            swap(i, j);
            i++;
            j--;
        }

        return true;
    }

    public static void swap(int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }



}