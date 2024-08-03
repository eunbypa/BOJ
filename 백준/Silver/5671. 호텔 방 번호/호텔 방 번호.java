import javax.swing.table.TableRowSorter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N, M;
        StringBuilder sb = new StringBuilder();
        int[] numCntArr = new int[10];
        do {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            sb.append(getRoomCnt(N, M, numCntArr)).append("\n");
        } while (br.ready());

        System.out.println(sb.toString());
    }

    public static int getRoomCnt(int n, int m, int[] numCntArr) {
        int cur, cnt = 0;
        for (int i = n; i <= m; i++) {
            cur = i;
            init(numCntArr);
            while(cur > 0) {
                numCntArr[cur % 10]++;
                cur /= 10;
            }
            if(!hasSameNums(numCntArr)) cnt++;
        }

        return cnt;
    }

    public static void init(int[] arr) {
        int l = arr.length;
        for (int i = 0; i < l; i++) {
            arr[i] = 0;
        }
    }

    public static boolean hasSameNums(int[] numCntArr) {
        for (int i = 0; i < 10; i++) {
            if(numCntArr[i] > 1) return true;
        }
        return false;
    }

}