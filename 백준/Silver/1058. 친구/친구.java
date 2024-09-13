import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static final char IS_FRIEND = 'Y';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] friends = new int[N][N];
        String friendState;
        for (int i = 0; i < N; i++) {
            friendState = br.readLine();
            for (int j = 0; j < N; j++) {
                friends[i][j] = (friendState.charAt(j) == IS_FRIEND) ? 1 : 0;
            }
        }

        System.out.println(getTheMostFamousPersonFriendCnt(N, friends));
    }

    // 가장 유명한 사람의 2-친구수 구하기
    static int getTheMostFamousPersonFriendCnt(int N, int[][] friends) {
        int maxFriendCnt = 0;

        for (int i = 0; i < N; i++) {
            maxFriendCnt = Math.max(maxFriendCnt, getFriendCnt(i, N, friends));
        }

        return maxFriendCnt;
    }

    // bfs
    static int getFriendCnt(int cur, int N, int[][] friends) {
        Queue<int[]> q = new LinkedList<>();
        int[] visited = new int[N];
        visited[cur] = 1;
        q.offer(new int[] {cur, 0});
        int friendCnt = 0;
        int[] next;
        while(!q.isEmpty()) {
            next = q.poll();
            for (int i = 0; i < N; i++) {
                // 친구관계가 아니거나, 이미 파악 완료한 경우 생략
                if(friends[next[0]][i] == 0 || visited[i] == 1) continue;
                friendCnt++;
                visited[i] = 1;
                if(next[1] < 1)
                    q.offer(new int[] {i, next[1]+1});
            }
        }

        return friendCnt;
    }


}