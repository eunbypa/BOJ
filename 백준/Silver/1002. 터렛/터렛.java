import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        int x1, y1, r1, x2, y2, r2;
        StringTokenizer st;
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            r1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            r2 = Integer.parseInt(st.nextToken());
            sb.append(findPossibleLocation(x1, y1, r1, x2, y2, r2)+"\n");
        }

        System.out.println(sb.toString());
    }

    // 각 좌표를 원의 중심점으로 해서
    // 두 원이 만나는 교점의 개수를 반환하는 함수
    private static int findPossibleLocation(int x1, int y1, int r1, int x2, int y2, int r2) {
        int cnt = 0;
        if(x1 == x2 && y1 == y2){ // 중심점이 같을 때
            if(r1 == r2) {
                if(r1 == 0) return 1; // 반지름이 0..?
                return cnt = -1; // 교점이 무한대
            }
            else return cnt = 0; // 교점이 없음
        }
        int xDiff = x1-x2;
        int yDiff = y1-y2;
        int dist = xDiff * xDiff + yDiff * yDiff;
        int rSum = (r1 + r2) * (r1 + r2); // 반지름의 합
        int rSub = (r1 - r2) * (r1 - r2); // 반지름의 차
        // 두 원의 반지름의 길이 합 보다 두 중심점 사이 거리가 더 가까우면
        // 두 원의 반지름의 길이 차 보다 두 중심점 사이 거리가 더 멀면
        // 교점은 2개
        if(dist < rSum || dist > rSub) cnt = 2;
        // 두 원의 반지름의 길이 합 과 두 중심점 사이 거리가 같은 경우 or
        // 두 원의 반지름의 길이 차 와 두 중심점 사이 거리가 같은 경우
        // 교점은 1개
        if(dist == rSum || dist == rSub) cnt = 1;
        // 두 원의 반지름의 길이 합 보다 두 중심점 사이 거리가 먼 경우 or
        // 두 원의 반지름의 길이 차 보다 두 중심점 사이 거리가 가까운 경우
        // 교점은 0개
        if(dist > rSum || dist < rSub) cnt = 0;
        return cnt;
    }

}