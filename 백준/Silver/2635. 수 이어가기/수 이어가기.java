import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        System.out.println(getAnswer(num));
    }

    static String getAnswer(int n) {
        int maxL = 0;
        List<Integer> answerList = null, list;
        for (int i = 1; i <= n; i++) {
            list = getMaxLen(n, i);
            if(maxL < list.size()) {
                maxL = list.size();
                answerList = list;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(maxL).append("\n");
        printList(sb, answerList);

        return sb.toString();
    }

    static void printList(StringBuilder sb, List<Integer> list) {
        int l = list.size();
        for (int i = 0; i < l; i++) {
            sb.append(list.get(i));
            if(i == l-1) break;
            sb.append(" ");
        }
    }

    // 최대 개수 반환
    static List<Integer> getMaxLen(int fst, int sec) {
        int tmp;
        List<Integer> list = new ArrayList<>();
        list.add(fst);
        list.add(sec);
        while(fst - sec >= 0) {
            tmp = fst;
            fst = sec;
            sec = tmp - sec;
            list.add(sec);
        }
        return list;
    }

}