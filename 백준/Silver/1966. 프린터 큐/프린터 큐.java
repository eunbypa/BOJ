import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, M, max, ans;
    static int[] ranks; // 각 중요도를 가지는 문서 개수
    static Queue<int[]> q; // 중요도, 정답에 필요한 문서 여부
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T, rank;
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 0; t < T; t++) {
            q = new LinkedList<>();
            ranks = new int[10];
            max = 0;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                rank = Integer.parseInt(st.nextToken()); // 중요도
                ranks[rank]++;
                max = Math.max(max, rank); // 중요도 최댓값을 저장
                if(i == M) {
                    q.offer(new int[]{rank, 1}); // 정답에 필요한 문서
                } else q.offer(new int[]{rank, 0});
            }
            int[] cur;
            int cnt = 0; // 인쇄 순서
            while(!q.isEmpty()){
                cur = q.poll();
                if(cur[0] == max){ // 중요도가 일치함 -> 인쇄
                    cnt++;
                    ranks[max]--;
                    if(ranks[max] == 0) max--;
                    max = findMaxRank(); // 가장 높은 중요도 갱신
                    if(cur[1] == 1){ // 현재 인쇄한 문서가 정답인 경우
                        ans = cnt;
                        break;
                    }
                }else{ // 중요도 일치하지 않음 -> 큐에 재삽입
                    q.offer(cur);
                }
            }
            sb.append(ans+"\n");
        }
        System.out.println(sb.toString());
    }
    // 현재 남은 문서들 중 가장 높은 중요도를 반환
    static int findMaxRank(){
        int rank = 0;
        for (int i = max; i >= 1; i--) {
            if(ranks[i] == 0) continue;
            rank = i;
            break;
        }
        return rank;
    }

}