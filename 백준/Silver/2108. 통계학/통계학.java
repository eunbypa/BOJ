import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static int N;
    static List<Integer> list;
    static int[][] cnt;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        cnt = new int[4001][2]; // 0 : 양수, 1 : 음수
        int sum = 0;
        int n, tmp;
        int maxCnt = 0;
        List<Integer> maxCntList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            n = Integer.parseInt(br.readLine());
            list.add(n);
            sum += n;
            if(n >= 0) {
                cnt[n][0]++;
                tmp = cnt[n][0];
            }
            else {
                cnt[-n][1]++;
                tmp = cnt[-n][1];
            }
            if(maxCnt < tmp){
                maxCnt = tmp;
                maxCntList = new ArrayList<>();
                maxCntList.add(n);
            }else if(maxCnt == tmp){
                maxCntList.add(n);
            }
        }
        // 산술평균
        int avg = (int) Math.round(sum / (double) N);
        sb.append(avg + "\n");
        // 중앙값
        Collections.sort(list);
        int mid = list.get(N / 2);
        sb.append(mid + "\n");
        // 최빈값
        int maxCntNum;
        if(maxCntList.size() == 1) {
            maxCntNum = maxCntList.get(0);
        }else {
            Collections.sort(maxCntList);
            maxCntNum = maxCntList.get(1);
        }
        sb.append(maxCntNum + "\n");
        // 범위
        int diff = list.get(list.size() - 1) - list.get(0);
        sb.append(diff + "\n");
        System.out.println(sb.toString());
    }

}