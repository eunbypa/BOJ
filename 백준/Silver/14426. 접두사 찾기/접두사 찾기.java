import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[] S = readStringAsArr(br, N);
        String[] testStringArr = readStringAsArr(br, M);

        System.out.println(getAnswer(S, testStringArr, M));
    }

    // 검사 결과 반환
    static int getAnswer(String[] S, String[] testArr, int l) {
        Map<String, Integer> prefixMap = getPrefixMap(S);
        int cnt = 0;
        for (int i = 0; i < l; i++) {
            if(prefixMap.containsKey(testArr[i])) cnt++;
        }
        return cnt;
    }

    // 읽은 문자열 배열 반환
    static String[] readStringAsArr(BufferedReader br, int l) throws IOException {
        String[] arr = new String[l];
        for (int i = 0; i < l; i++) {
            arr[i] = br.readLine();
        }
        return arr;
    }

    // S에 포함된 모든 접두사를 구해서 해시맵에 저장
    static Map<String, Integer> getPrefixMap(String[] arr) {
        Map<String, Integer> prefixMap = new HashMap<>();
        int l = arr.length, sl;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < l; i++) {
            sl = arr[i].length();
            for (int j = 0; j < sl; j++) {
                sb.append(arr[i].charAt(j));
                prefixMap.put(sb.toString(), 1);
            }
            sb.delete(0, sl);
        }

        return prefixMap;
    }


}