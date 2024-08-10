import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static String[] words;
    static Map<String, Integer> dictionary;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        words = new String[N];
        dictionary = new HashMap<>();
        int idx = -1;
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
            dictionary.put(words[i], 1);
            if(words[i].equals("?")) idx = i;
        }
        char first = ' ', last = ' ';
        if(idx != 0) last = words[idx - 1].charAt(words[idx - 1].length() - 1);
        if(idx != N-1) first = words[idx + 1].charAt(0);
        M = Integer.parseInt(br.readLine());
        String word, answer = null;
        for (int i = 0; i < M; i++) {
            word = br.readLine();
            if(N == 1) {
                answer = word;
                break;
            }
            if(dictionary.containsKey(word)) continue;
            if(idx != 0 && idx != N-1) {
                if(word.charAt(0) == last && word.charAt(word.length()-1) == first) {
                    answer = word;
                }
            }else if(idx == 0) {
                if(word.charAt(word.length()-1) == first) {
                    answer = word;
                }
            }else if(idx == N-1){
                if(word.charAt(0) == last) {
                    answer = word;
                }
            }
        }

        System.out.println(answer);
    }


}