import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static String S;
    static List<String> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        int l = S.length();
        for (int i = 0; i < l; i++) {
            list.add(S.substring(i, l));
        }
        Collections.sort(list);
        for (String s : list) {
            sb.append(s).append("\n");
        }
        System.out.println(sb.toString());
    }

}