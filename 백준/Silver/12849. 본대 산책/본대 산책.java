import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static final int BUILDING_CNT = 8;
    static final int MOD_VAL = 1000000007;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int D = Integer.parseInt(br.readLine());
        List<Integer>[] edgeList = initEdge();
        int[][] dp = new int[D+1][BUILDING_CNT];
        
        System.out.println(getAnswer(D, dp, edgeList));
    }

    static List<Integer>[] initEdge() {
        List<Integer>[] edgeList = new ArrayList[BUILDING_CNT];
        for (int i = 0; i < BUILDING_CNT; i++) {
            edgeList[i] = new ArrayList<>();
        }

        edgeList[0].add(1);
        edgeList[0].add(2);

        edgeList[1].add(0);
        edgeList[1].add(2);
        edgeList[1].add(3);

        edgeList[2].add(0);
        edgeList[2].add(1);
        edgeList[2].add(3);
        edgeList[2].add(4);

        edgeList[3].add(1);
        edgeList[3].add(2);
        edgeList[3].add(4);
        edgeList[3].add(5);

        edgeList[4].add(2);
        edgeList[4].add(3);
        edgeList[4].add(5);
        edgeList[4].add(7);

        edgeList[5].add(3);
        edgeList[5].add(4);
        edgeList[5].add(6);

        edgeList[6].add(5);
        edgeList[6].add(7);

        edgeList[7].add(4);
        edgeList[7].add(6);

        return edgeList;
    }

    static int getAnswer(int D, int[][] dp, List<Integer>[] edgeList) {
        int tmp, size;
        dp[0][0] = 1;

        for (int i = 1; i <= D; i++) {
            for (int j = 0; j < BUILDING_CNT; j++) {
                size = edgeList[j].size();
                tmp = 0;
                for (int k = 0; k < size; k++) {
                    tmp = (tmp + dp[i-1][edgeList[j].get(k)]) % MOD_VAL;
                }
                dp[i][j] = tmp;
            }
        }

        return dp[D][0];
    }

}