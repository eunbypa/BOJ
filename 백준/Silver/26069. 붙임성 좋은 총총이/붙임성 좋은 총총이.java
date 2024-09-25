import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static final String FIRST_DANCER = "ChongChong";

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        // 춤추고 있는 사람들의 이름 저장
        Map<String, Integer> danceStateMap = new HashMap<>();
        danceStateMap.put(FIRST_DANCER, 1);
        String a, b;
        int aDanceState, bDanceState;
        int dancingPeopleNum = 1;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = st.nextToken();
            b = st.nextToken();
            if((danceStateMap.containsKey(a) && danceStateMap.containsKey(b)) ||
                    (!danceStateMap.containsKey(a) && !danceStateMap.containsKey(b))) {
                continue;
            }
            // 둘중 한명만 춤추고 있는 상태인 경우
            // 상대의 춤 상태 갱신
            if(danceStateMap.containsKey(a)) {
                danceStateMap.put(b, 1);
            }
            if(danceStateMap.containsKey(b)) {
                danceStateMap.put(a, 1);
            }
            dancingPeopleNum++;
        }

        System.out.println(dancingPeopleNum);
    }


}