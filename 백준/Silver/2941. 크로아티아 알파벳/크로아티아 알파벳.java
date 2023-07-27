import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static char[] words;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        words = br.readLine().toCharArray();
        int length = words.length;
        int s = 0, e = 0;
        while(s < length){
            cnt++;
            if(s == length-1) break;
            switch (words[s]){
                case 'c':
                    if(words[s+1] == '=' || words[s+1] == '-') s++;
                    break;
                case 'd':
                    if(words[s+1] == '-') s++;
                    else if(words[s+1] == 'z') {
                        if(s < length-2 && words[s+2] == '=') s += 2;
                    }
                    break;
                case 'l':
                    if( words[s+1] == 'j') s++;
                    break;
                case 'n':
                    if(words[s+1] == 'j') s++;
                    break;
                case 's':
                    if(words[s+1] == '=') s++;
                    break;
                case 'z':
                    if(words[s+1] == '=') s++;
                    break;
            }
            s++;
        }
        System.out.println(cnt);
    }

}