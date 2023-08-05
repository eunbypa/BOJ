import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();
        int length = word.length();
        int[] cnt = new int[26];
        int idx;
        char c;
        for (int i = 0; i < length; i++) {
            c = word.charAt(i);
            idx = (c >= 'a') ? c - 'a' : c - 'A';
            cnt[idx]++;
        }
        int max = 0;
        char maxC = 0;
        for (int i = 0; i < 26; i++) {
            if(max < cnt[i]){
                max = cnt[i];
                maxC = (char)(i + 'A');
            }else if(max == cnt[i]){
                maxC = '?';
            }
        }
        System.out.println(maxC);
    }


}