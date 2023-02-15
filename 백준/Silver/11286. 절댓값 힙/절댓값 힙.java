import java.util.*;
import java.io.*;
public class Main {
	static int N;
	static class MyComp implements Comparator<Integer>{
		@Override
		public int compare(Integer o1, Integer o2) {
			int i1 = Math.abs(o1);
			int i2 = Math.abs(o2);
			if(Integer.compare(i1, i2) == 0) return Integer.compare(o1, o2);
			else return Integer.compare(i1, i2);
		}
		
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Queue<Integer> pq = new PriorityQueue<>(new MyComp());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		StringBuilder sb= new StringBuilder();
		int n;
		for (int i = 0; i < N; i++) {
			n =Integer.parseInt(br.readLine());
			if(n == 0) {
				if(pq.isEmpty()) sb.append(0+"\n");
				else sb.append(pq.poll()+"\n");
			}else {
				pq.add(n);
			}
		}
		System.out.println(sb.toString());
	}

}