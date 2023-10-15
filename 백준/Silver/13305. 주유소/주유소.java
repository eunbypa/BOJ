import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] roads;
    static int[] gasStations;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        roads = new int[N-1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N-1; i++) {
            roads[i] = Integer.parseInt(st.nextToken());
        }
        gasStations = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            gasStations[i] = Integer.parseInt(st.nextToken());
        }
        // 출발지점에 반드시 주유해야 함
        long min = 0L, sum = 0L;
        int curCost = gasStations[0]; // 제일 왼쪽 도시의 리터당 가격
        for (int i = 0; i < N-1; i++) {
            // 현재 리터당 가격보다 작아지는 도시가 등장
            if(curCost > gasStations[i]) {
                min += sum * curCost;
                sum = 0;
                curCost = gasStations[i];
            }
            sum += roads[i];
        }
        // 마지막 도시의 전 도시에서 마지막 도시까지의 거리 비용 더하기
        min += sum * curCost;
        System.out.println(min);
    }
}
