import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final char EMPTY = ' ';
    static final char NOT_EMPTY = '-';

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s;
        int N;
        while((s = br.readLine()) != null) {
            N = Integer.parseInt(s);
            sb.append(getAnswer(N)).append("\n");
        }
        System.out.println(sb.toString());
    }

    static String getAnswer(int N) {
        StringBuilder sb = new StringBuilder();
        char[] arr = init(N);
        divideAndConquer(0, arr.length, arr);
        int l = arr.length;
        for (int i = 0; i < l; i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    // 초기 문자 배열 세팅
    static char[] init(int N) {
        int size = (int) Math.pow(3, N);
        char[] arr = new char[size];
        for (int i = 0; i < size; i++) {
            arr[i] = NOT_EMPTY;
        }
        return arr;
    }

    // 분할정복
    static void divideAndConquer(int idx, int cnt, char[] arr) {
        if(cnt == 1) return;
        int nextIdx, dividedCnt = cnt/3;
        for (int i = 0; i < 3; i++) {
            // 가운데만 공백으로 바꿈
            if(i == 1)
                empty(arr, idx + i*dividedCnt, idx + i*dividedCnt + dividedCnt);
            divideAndConquer(idx + i*dividedCnt, dividedCnt, arr);
        }

    }

    // 공백 변경
    static void empty(char[] arr, int s, int e) {
        for (int i = s; i < e; i++) {
            arr[i] = EMPTY;
        }
    }


}