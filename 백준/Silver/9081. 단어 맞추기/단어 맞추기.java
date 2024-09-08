import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        String word, nextWord;
        int[] wordApbArr;
        for (int t = 0; t < T; t++) {
            word = br.readLine();
            wordApbArr = new int[word.length()];
            init(wordApbArr, word);
            nextWord = nextPerm(wordApbArr) ? getWord(wordApbArr) : word;
            sb.append(nextWord).append("\n");
        }
        System.out.println(sb.toString());
    }

    // nextPerm에 활용할 단어의 알파벳 순서대로 배열 세팅
    static void init(int[] wordApbArr, String word) {
        int l = wordApbArr.length;
        for (int i = 0; i < l; i++) {
            wordApbArr[i] = word.charAt(i) - 'A';
        }
    }

    // 배열된 순서에 따른 단어 생성
    static String getWord(int[] wordApbArr) {
        StringBuilder sb = new StringBuilder();
        int l = wordApbArr.length;
        for (int i = 0; i < l; i++) {
            sb.append((char)(wordApbArr[i] + 'A'));
        }

        return sb.toString();
    }

    // 사전순 다음 단어 배열
    static boolean nextPerm(int[] wordApbArr) {
        int i = wordApbArr.length - 1;
        int j = wordApbArr.length - 1;

        while(i > 0 && wordApbArr[i-1] >= wordApbArr[i]) i--;
        if(i == 0) return false;

        while(wordApbArr[i-1] >= wordApbArr[j]) j--;
        swap(wordApbArr, i-1, j);

        j = wordApbArr.length - 1;
        while(i < j) {
            swap(wordApbArr, i, j);
            i++;
            j--;
        }

        return true;
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}