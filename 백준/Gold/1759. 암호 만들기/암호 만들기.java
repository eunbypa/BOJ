import java.util.*;
import java.io.*;

class Main
{
	static int L,C;
	//static char[] nouns = {'a','e','i','o','u'};
	static List<Integer> apbs = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        String s;
        for (int i = 0; i < C; i++) {
        	s = st.nextToken();
			apbs.add(s.charAt(0)-'a');
		}
        apbs.sort((o1,o2)->(o1-o2));
        //System.out.println(apbs);
        dfs(0,0, new char[L]);
		System.out.println(sb.toString());
	}
	public static void dfs(int idx, int idx2, char[] word) {
		//System.out.println(word);
		if(idx2 == L) {
			if(isPos(word)) {
				sb.append(word);
				sb.append('\n');
			}
			return;
		}
		for (int i = idx; i < C; i++) {
			if(idx2 > 0 && word[idx2-1] >= (char)(apbs.get(i)+'a')) continue;
			word[idx2] = (char)(apbs.get(i)+'a');
			dfs(idx+1, idx2+1, word);
			word[idx2] = 0;
		}
	}
	public static boolean isPos(char[] word) {
		int nCnt = 0; // 모음 개수
		for (int i = 0; i < L; i++) {
//			if(i < L-1) {
//				if(word[i] >= word[i+1]) return false;
//			}
			if(word[i] == 'a' ||word[i] == 'e'||word[i] == 'i'
					||word[i] == 'o'||word[i] == 'u') {
				nCnt++;
			}
		}
		if(nCnt > 0 && L - nCnt > 1) return true;
		return false;
	}
}