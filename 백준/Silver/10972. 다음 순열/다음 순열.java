import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        if(nextPerm()) {
            for (int i = 0; i < N; i++) {
                sb.append(arr[i]);
                if(i == N-1) break;
                sb.append(" ");
            }
        } else sb.append(-1);
        System.out.println(sb.toString());
    }

    public static boolean nextPerm() {
        int i = N-1;
        int j = N-1;
        while(i > 0 && arr[i-1] > arr[i]) i--;
        if(i == 0) return false;
        while(arr[i-1] > arr[j]) j--;
        swap(i-1, j);
        j = N-1;
        while(i < j) {
            swap(i, j);
            i++;
            j--;
        }

        return true;
    }

    public static void swap(int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


}