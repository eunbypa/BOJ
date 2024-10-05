import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {


    static final int WORD_MAX_COUNT = 100;
    static final String POSSIBLE = "Possible";
    static final String IMPOSSIBLE = "Impossible";

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] wordCntArr = new int[N];
        // 해당 단어가 몇번째 문장에서 나온 단어인지 기록
        Map<String, Integer> map = new HashMap<>();
        StringTokenizer st;
        String word;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cnt = 0;
            while(st.hasMoreTokens()) {
                word = st.nextToken();
                map.put(word, i*WORD_MAX_COUNT+cnt++);
            }
            wordCntArr[i] = cnt;
        }
        String L = br.readLine();

        System.out.println(getAnswer(N, wordCntArr, map, L));
    }

    static String getAnswer(int N, int[] wordCntArr, Map<String, Integer> map, String L) {
        boolean result = true;
        // 앵무새가 현재 몇번째 문장의 몇번째 단어까지 말했는지 저장
        Map<Integer, Integer> orderMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            // 초기값 세팅
            orderMap.put(i, -1);
        }
        StringTokenizer st = new StringTokenizer(L);
        String cur;
        int wordIdx, idx, order;
        while(st.hasMoreTokens()) {
            cur = st.nextToken();
            if(!map.containsKey(cur)) {
                result = false;
                break;
            }
            wordIdx = map.get(cur);
            idx = wordIdx / WORD_MAX_COUNT;
            order = orderMap.get(idx);
            if(order != -1 && order + 1 != wordIdx){
                // 첫 단어가 아니고, 문장에서 다음에 등장할 단어가 아님
                result = false;
                break;
            }
            if(wordIdx%WORD_MAX_COUNT+1 == wordCntArr[idx]) {
                // 문장이 끝남
                wordCntArr[idx] = 0;
            }
            orderMap.put(idx, wordIdx);
        }

        // 모든 문장을 다 끝마쳤는지 확인
        for (int i = 0; i < N; i++) {
            if(wordCntArr[i] != 0) {
                result = false;
                break;
            }
        }

        return result ? POSSIBLE : IMPOSSIBLE;
    }


}