import java.util.*;
import java.io.*;
public class Main {
	static String T,P;
	static int tl, pl, cnt;
	static List<Integer> list = new ArrayList<>();
	static int[] p; //i위치 까지의 접두사 접미사 최대 길이
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = br.readLine();
		P = br.readLine();
		tl = T.length();
		pl = P.length();
		p = new int[pl];
		makeTable();
		KMP();
		sb.append(cnt+"\n");
		int total = list.size();
		for (int i = 0; i < total-1; i++) {
			sb.append(list.get(i)+" ");
		}
		if(total != 0) sb.append(list.get(total-1));
		System.out.println(sb.toString());
	}
	// 부분일치 테이블 배열 만들기
	static void makeTable() {
		int i = 1, j = 0; // 접미사 접두사 포인터
		while(i < pl) {
			if(P.charAt(i) == P.charAt(j)) {
				j++;
				p[i] = j;
				i++;
			}else {
				if(j == 0) {
					p[i] = 0;
					i++;
				}
				else if(p[j-1] != 0) {
					j = p[j-1];
				}
				else {
					j = 0;
				}
			}
		}
		//System.out.println(Arrays.toString(p));
	}
	static void KMP() {
		int i =0,j=0; 
		while(i < tl) {
			if(T.charAt(i) == P.charAt(j)) {
				i++;
				j++;
			}else {
				if(j != 0) { 
					if(p[j-1] != 0) j = p[j-1];
					else {
						j = 0;
					}
				}else {
					i++;
				}
			}
			if(j == pl) { // 같은 문자열을 찾음
				//System.out.println("같은 문자열을 찾은 위치 i : "+i+T.substring(i-pl, i));
				cnt++;
				list.add(i-pl+1);
				j = p[j-1];
			}
		}
	}
}