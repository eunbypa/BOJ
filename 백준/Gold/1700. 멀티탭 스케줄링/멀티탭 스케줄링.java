import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] record = new int[K];
        int[] modelArr = new int[K+1];
        int modelCnt = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            record[i] = Integer.parseInt(st.nextToken());
            if(modelArr[record[i]] == 0) {
                modelArr[record[i]] = 1;
                modelCnt++;
            }
        }
        int answer = (modelCnt <= N) ? 0 : getAnswer(N, K, modelCnt, record);
        System.out.println(answer);
    }

    static int getAnswer(int n, int k, int modelCnt, int[] record) {
        int answer = 0, usedCnt = 0;
        // 현재 전기용품이 플러그를 사용중인지 저장
        int[] plugUsed = new int[k+1];
        // 해당 플러그를 사용중인 모델 idx 저장
        int[] plugModelArr = new int[n+1];
        // 해당 전기용품이 가장 최근에 사용되는 시각 저장
        int[] recentUsedTimeArr;

        int removed, curPlug;
        for (int i = 0; i < k; i++) {
            if(plugUsed[record[i]] != 0) {
                // 플러그 사용 중인 경우
                continue;
            }
            // 플러그 사용 중이지 않은 경우
            if(usedCnt == n) {
                // 플러그 다 점유 중인 상황
                recentUsedTimeArr = setLeastRecentlyUsedModel(i, k, record);
                removed = getRemovedModelIdx(n, k, recentUsedTimeArr, plugModelArr);
                plugModelArr[plugUsed[removed]] = record[i];
                plugUsed[record[i]] = plugUsed[removed];
                plugUsed[removed] = 0;
                answer++;
            }else {
                // 플러그 남은 칸이 있는 경우
                curPlug = getEmptyPlugIdx(n, plugModelArr);
                plugModelArr[curPlug] = record[i];
                plugUsed[record[i]] = curPlug;
                usedCnt++;
            }
        }

        return answer;
    }

    static int getEmptyPlugIdx(int n, int[] plugModelArr) {
        for (int i = 1; i <= n; i++) {
            if(plugModelArr[i] == 0) return i;
        }
        return -1;
    }

    private static int getRemovedModelIdx(int n, int k, int[] recentUsedTimeArr, int[] plugModelArr) {
        int idx = 0;
        int latest = -1, time;
        for (int i = 1; i <= n; i++) {
            // 더 이상 사용하지 않는 가전제품을 찾은 상태 --> 삭제!!
            if(latest == k) return idx;
            if(latest == -1) {
                latest = recentUsedTimeArr[plugModelArr[i]] == 0 ? k : recentUsedTimeArr[plugModelArr[i]];
                idx = plugModelArr[i];
                continue;
            }
            time = recentUsedTimeArr[plugModelArr[i]] == 0 ? k : recentUsedTimeArr[plugModelArr[i]];
            if(latest < time) {
                latest = time;
                idx = plugModelArr[i];
            }
        }

        return idx;
    }

    static int[] setLeastRecentlyUsedModel(int cur, int k, int[] record) {
        int time;
        int[] recentUsedTimeArr = new int[k + 1];

        for (int i = cur+1; i < k; i++) {
            if(recentUsedTimeArr[record[i]] != 0) {
                continue;
            }
            recentUsedTimeArr[record[i]] = i;
        }

        return recentUsedTimeArr;
    }


}