import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int n, start, end;
    static char[] p;
    static int[] x;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            p = br.readLine().toCharArray();
            n = Integer.parseInt(br.readLine());
            x = new int[n];
            String arr = br.readLine();
            if(n != 0) { // 빈 배열이 아니면
                arr = arr.substring(1, arr.length()- 1);
                StringTokenizer st = new StringTokenizer(arr, ",");
                for (int i = 0; i < n; i++) {
                    x[i] = Integer.parseInt(st.nextToken());
                }
            }
            int tmp;
            int removed = 0; // D 명령으로 제거된 숫자 개수
            start = 0;
            end = n-1;
            for (int i = 0; i < p.length; i++) {
                if(p[i] == 'R') { // 뒤집기
                    tmp = start;
                    start = end;
                    end = tmp;
                }
                if(p[i] == 'D') { // 맨 앞 버리기
                    removed++;
                    if(start <= end) start++; // 뒤집어진 상태가 아닌 경우
                    else start--; // 뒤집어진 상태

                }
                if(removed > n) { // 제거하려는 숫자 수가 전체 개수보다 큼
                    break;
                }
            }
            if(removed > n) { // 제거하려는 숫자 수가 전체 개수보다 큼
                sb.append("error");
                sb.append("\n");
                continue;
            }
            sb.append("[");
            if(removed < n) { // 전부 제거되지 않은 경우
                if (start <= end) {
                    for (int i = start; i <= end; i++) {
                        sb.append(x[i]);
                        if (i != end) sb.append(",");
                    }
                } else {
                    for (int i = start; i >= end; i--) {
                        sb.append(x[i]);
                        if (i != end) sb.append(",");
                    }
                }
            }
            sb.append("]");
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

}