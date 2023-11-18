import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, x;
    static int[] numbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 0;
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        numbers = new int[1000001];
        for (int i = 0; i < n; i++) {
            numbers[Integer.parseInt(st.nextToken())]++;
        }
        x = Integer.parseInt(br.readLine());
        int e = (x%2 == 0) ? x/2 -1 : x/2;
        for (int i = 1; i <= e; i++) {
            if(x-i > 1000000) continue;
            if(numbers[i] == 0 || numbers[x-i] == 0) continue;
            cnt++;
        }
        System.out.println(cnt);
    }
}