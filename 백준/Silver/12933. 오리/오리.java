import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static class Duck {
        char lastVoice; // 마지막으로 내뱉은 울음소리 철자 저장

        public Duck(char lastVoice) {
            this.lastVoice = lastVoice;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String duckVoice = br.readLine();
        int l = duckVoice.length();
        List<Duck> duckList = new ArrayList<>();
        int cur, cnt;
        boolean fail = false;
        for (int i = 0; i < l; i++) {
            // 현재 철자가 오리들 중 어떤 오리가 내뱉어야하는 순서에 있는지 확인
            cur = getDuckIdx(duckList, duckVoice.charAt(i));
            if(cur == -1) {
                // 새로 울음소리를 낼 때 시작 철자가 q가 아니면 오류
                if(duckVoice.charAt(i) != 'q') {
                    fail = true;
                    break;
                }
                duckList.add(new Duck(duckVoice.charAt(i)));
            }
        }

        // 각 오리들이 모두 울음을 끝마친 건지 확인
        cnt = duckList.size();
        for (int i = 0; i < cnt; i++) {
            if(duckList.get(i).lastVoice != 'k') {
                fail = true;
                break;
            }
        }

        int ans = fail ? -1 : duckList.size();

        System.out.println(ans);

    }

    static int getDuckIdx(List<Duck> duckList, char c) {
        int cnt = duckList.size();
        Duck cur;
        for (int i = 0; i < cnt; i++) {
            cur = duckList.get(i);
            if(isNextVoice(cur.lastVoice, c)) {
                // 마지막 철자 갱신
                cur.lastVoice = c;
                return i;
            }
        }
        // 리스트에 있는 오리들 중에서는 해당 철자를 내뱉을 오리가 없음
        // -> 새로 오리 생성해야 함
        return -1;
    }

    // 다음으로 오는 철자가 오리 울음소리 순서에 맞는지 확인
    static boolean isNextVoice(char cur, char next) {
        switch (cur) {
            case 'q':
                return next == 'u';
            case 'u':
                return next == 'a';
            case 'a':
                return next == 'c';
            case 'c':
                return next == 'k';
            case 'k':
                return next == 'q';
            default:
                return false;
        }
    }



}