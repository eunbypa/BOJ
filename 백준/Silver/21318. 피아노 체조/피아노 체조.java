import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] pianoSheetLevelArr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            pianoSheetLevelArr[i] = Integer.parseInt(st.nextToken());
        }

        int[] missedMusicSumArr = getMissedMusicSumArray(N, pianoSheetLevelArr);

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        int x,y;
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            // 마지막곡은 항상 성공하므로 y-1
            sb.append(missedMusicSumArr[y - 1] - missedMusicSumArr[x - 1]).append("\n");
        }
        System.out.println(sb.toString());
    }

    // 누적합 배열
    static int[] getMissedMusicSumArray(int N, int[] pianoSheetLevelArr) {
        int[] sumArr = new int[N+1];
        for (int i = 1; i < N; i++) {
            if(pianoSheetLevelArr[i] > pianoSheetLevelArr[i+1])
                sumArr[i] = sumArr[i - 1] + 1;
            else
                sumArr[i] = sumArr[i - 1];
        }

        // 마지막 곡은 항상 성공
        sumArr[N] = sumArr[N - 1];
        return sumArr;
    }

}