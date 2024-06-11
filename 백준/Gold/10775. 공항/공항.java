import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int G, P;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        arr = new int[G+1]; // union find
        for (int i = 1; i <= G; i++) {
            arr[i] = i;
        }
        int g;
        int max = 0, loc;
        for (int i = 0; i < P; i++) {
            g = Integer.parseInt(br.readLine());
            loc = find(g);
            if(loc == 0) { // 0이면 더 이상 도킹 가능한 게이트가 없음
                break;
            }
            max++;
            union(loc, loc-1); // 가장 인근의 도킹 가능한 게이트를 표시
        }

        System.out.println(max);
    }

    // 유니온 파인드
    public static int find(int x) {
        if(arr[x] == x) return x;
        return arr[x] = find(arr[x]);
    }

    public static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			arr[x] = y;
		}
	}
}