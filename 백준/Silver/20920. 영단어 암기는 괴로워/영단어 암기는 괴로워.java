import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Word implements Comparable<Word> {
        String word;

        public Word(String word) {
            this.word = word;
        }

        @Override
        public int compareTo(Word o) {
            int thisCnt = wordCntMap.get(this.word), oCnt = wordCntMap.get(o.word);
            // 자주 나오는 단어일수록 앞에 배치
            if(thisCnt > oCnt) return -1;
            else if(thisCnt == oCnt) {
                // 해당 단어의 길이가 길수록 앞에 배치
                if(this.word.length() > o.word.length()) return -1;
                else if(this.word.length() == o.word.length()) {
                    return this.word.compareTo(o.word);
                }
                return 1;
            }
            return 1;
        }
    }
    static int N,M;

    static Map<String, Integer> wordCntMap;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        wordCntMap = new HashMap<>();
        List<Word> wordList = new ArrayList<>();
        Word word;
        String s;
        int cnt;
        for (int i = 0; i < N; i++) {
            s = br.readLine();
            if(s.length() < M) continue;
            word = new Word(s);
            if(wordCntMap.containsKey(s)) {
                cnt = wordCntMap.get(s);
                wordCntMap.put(s, cnt + 1);
            }else {
                cnt = 1;
                wordCntMap.put(s, cnt);
                wordList.add(word);
            }
        }

        Collections.sort(wordList);

        StringBuilder sb = new StringBuilder();
        int size = wordList.size();
        for (int i = 0; i < size; i++) {
            sb.append(wordList.get(i).word).append("\n");
        }
        System.out.println(sb.toString());
    }

}