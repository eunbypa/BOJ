import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        System.out.println(getAnswer(A, P));
    }

    // 중복 제외 남은 수 개수 출력
    static int getAnswer(int A, int P) {
        Map<Integer, Integer> map = new HashMap<>();
        boolean fin = false;
        int cur = A, next, cnt = 1;
        map.put(A, cnt++);
        while(!fin) {
            next = 0;
            while(cur > 0) {
                next += (int) Math.pow(cur % 10, P);
                cur /= 10;
            }
            if(map.containsKey(next)) {
                fin = true;
                cnt = map.get(next)-1;
                continue;
            }
            map.put(next, cnt++);
            cur = next;
        }

        return cnt;
    }






}