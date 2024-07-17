import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final int MAX_HEIGHT = 256;
    static int N, M, B;
    static int[] heightBlockArr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        heightBlockArr = new int[MAX_HEIGHT+1];
        int maxH = 0, h;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                h = Integer.parseInt(st.nextToken());
                heightBlockArr[h]++;
                maxH = Math.max(maxH, h);
            }
        }

        System.out.println(solve(maxH));
    }

    public static String solve(int maxH) {
        int min = Integer.MAX_VALUE;
        int height = 0;
        int time;
        while(maxH >= 0) {
            time = getTotalTime(maxH, min);
            if(time == -1) {
                maxH--;
                continue;
            }

            if(time < min) {
                min = time;
                height = maxH;
            }
            else if(time == min) {
                height = Math.max(height, maxH);
            }
            maxH--;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(min).append(" ").append(height);
        return sb.toString();
    }

    public static int getTotalTime(int h, int min) {
        int sum = 0;
        int remBlock = B;
        int cur;
        for (int i = 256; i >= h; i--) {
            if(heightBlockArr[i] == 0) continue;
            cur = (i - h) * heightBlockArr[i];
            remBlock += cur;
            sum += cur * 2;
        }
        for (int i = h-1; i >= 0; i--) {
            if(heightBlockArr[i] == 0) continue;
            cur = (h - i) * heightBlockArr[i];
            if(remBlock < cur) return -1;
            remBlock -= cur;
            sum += cur;
        }
        return sum;
    }

}