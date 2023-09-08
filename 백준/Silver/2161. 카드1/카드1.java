import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<Integer> list = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }
        int size = N;
        while(size != 0){
            // 제일 위에 있는 카드 버리기
            sb.append(list.remove(0));
            size--;
            if(size == 0) break;
            sb.append(" ");
            list.add(list.remove(0));
        }

        System.out.println(sb.toString());
    }


}