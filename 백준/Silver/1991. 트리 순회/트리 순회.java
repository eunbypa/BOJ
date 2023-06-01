import java.util.*;
import java.io.*;
public class Main {
	static class Node{
		char name;
		Node left; // 왼쪽 자식
		Node right; // 오른쪽 자식
		
		public Node() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Node(char name, Node left, Node right) {
			super();
			this.name = name;
			this.left = left;
			this.right = right;
		}
	}
	static int N;
	static Node[] nodes;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nodes = new Node[N];
		for (int i = 0; i < N; i++) {
			nodes[i] = new Node((char)(i+'A'), null, null);
		}
		StringTokenizer st;
		char cur, lc, rc; // 현재 노드, 왼쪽 자식, 오른쪽 자식
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cur = st.nextToken().charAt(0);
			lc = st.nextToken().charAt(0);
			rc = st.nextToken().charAt(0);
			if(lc != '.') { // 왼쪽 자식이 존재하는 경우
				nodes[cur-'A'].left = nodes[lc-'A'];
			}
			if(rc != '.') { // 오른쪽 자식이 존재하는 경우
				nodes[cur-'A'].right = nodes[rc-'A'];
			}
		}
		preorder(0);
		sb.append("\n");
		inorder(0);
		sb.append("\n");
		postorder(0);
		System.out.println(sb.toString());
	}
	//전위 순회 -> 루왼오
	static void preorder(int cur) {
		//루트 방문
		sb.append(nodes[cur].name);
		//왼쪽 자식 방문
		if(nodes[cur].left != null) preorder(nodes[cur].left.name-'A');
		//오른쪽 자식 방문
		if(nodes[cur].right != null) preorder(nodes[cur].right.name-'A');
	}
	//중위 순회 -> 왼루오
	static void inorder(int cur) {
		//왼쪽 자식 방문
		if(nodes[cur].left != null) inorder(nodes[cur].left.name-'A');
		//루트 방문
		sb.append(nodes[cur].name);
		//오른쪽 자식 방문
		if(nodes[cur].right != null) inorder(nodes[cur].right.name-'A');
	}
	//후위 순회
	static void postorder(int cur) {
		//왼쪽 자식 방문
		if(nodes[cur].left != null) postorder(nodes[cur].left.name-'A');
		//오른쪽 자식 방문
		if(nodes[cur].right != null) postorder(nodes[cur].right.name-'A');
		//루트 방문
		sb.append(nodes[cur].name);
	}
}
