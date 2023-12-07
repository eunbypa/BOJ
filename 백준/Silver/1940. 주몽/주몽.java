import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] arr;
    static List<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[100001];
        list = new ArrayList<>();
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n;
        for (int i = 0; i < N; i++) {
            n = Integer.parseInt(st.nextToken());
            arr[n]++;
            list.add(n);
        }
        int cnt = 0;
        if(M <= 200000) {
            for (int i = 0; i < N; i++) {
                if(arr[list.get(i)] == 0 || list.get(i) > M) continue;
                if (M - list.get(i) <= 100000) {
                    if(list.get(i) * 2 == M) {
                        if(arr[list.get(i)] >= 2) {
                            cnt++;
                            arr[list.get(i)] -= 2;
                        }
                    }else {
                        if(arr[M - list.get(i)] != 0) {
                            cnt++;
                            arr[M - list.get(i)]--;
                        }
                    }
                }
            }
        }
        System.out.println(cnt);
    }

}