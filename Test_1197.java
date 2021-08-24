import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
// 크루스칼 알고리즘
public class Test_1197 {
	
	static int v;
	static int[] parent;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken()); // 정점의 개수
		int e = Integer.parseInt(st.nextToken()); // 간선의 개수
		
		Node[] lines = new Node[e]; // 간선
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken()) - 1;
			int v2 = Integer.parseInt(st.nextToken()) - 1;
			int value = Integer.parseInt(st.nextToken());
			lines[i] = new Node(v1, v2, value);
		}
		
		Arrays.sort(lines, (o1, o2) -> Integer.compare(o1.val, o2.val));
		int result = 0;
		int cnt = 0;
		make();
		for(Node node : lines) {
			if(union(node.node1, node.node2)) {
				result += node.val;
				cnt++;
				if(cnt == v - 1)
					break;
			}
		}
		System.out.println(result);
	}
	
	// 각각의 원소를 집합으로 만들기
	public static void make() {
		parent = new int[v];
		for(int i = 0; i < v; i++)
			parent[i] = i;
	}
	
	// 대표자 찾기
	public static int find(int a) {
		if(a == parent[a])
			return a;
		return parent[a] = find(parent[a]);
	}
	
	// 대표자끼리 합치기
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot)
			return false;
		parent[aRoot] = bRoot;
		return true;
	}
	
	public static class Node{
		int node1;
		int node2;
		int val;
		public Node(int node1, int node2, int val) {
			this.node1 = node1;
			this.node2 = node2;
			this.val = val;
		}
	}
}

/*
// 프림 알고리즘
public class Test_1197 {

static ArrayList<ArrayList<Node>> link;
static int v;

public static void main(String[] args) throws NumberFormatException, IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());
	v = Integer.parseInt(st.nextToken()); // 정점의 개수
	int e = Integer.parseInt(st.nextToken()); // 간선의 개수
	link = new ArrayList<ArrayList<Node>>();
	for(int i = 0; i < v; i++) {
		link.add(new ArrayList<Node>());
	}
	for(int i = 0; i < e; i++) {
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken()) - 1;
		int v2 = Integer.parseInt(st.nextToken()) - 1;
		int value = Integer.parseInt(st.nextToken());
		link.get(v1).add(new Node(v2, value));
		link.get(v2).add(new Node(v1, value));
	}
	
	prime();
}

public static void prime() {
	Queue<Integer> q = new LinkedList<Integer>();
	q.add(0);
	boolean[] visit = new boolean[v];
	visit[0] = true;
	
	PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
		@Override
		public int compare(Node o1, Node o2) {
			return Integer.compare(o1.val, o2.val);
		}
	});
	long sum = 0L;
	while(!q.isEmpty()) {
		int nowIdx = q.poll();
		ArrayList<Node> now = link.get(nowIdx);
		
		for(int i = 0; i < now.size(); i++) {
			if(!visit[now.get(i).node]) {
				pq.add(now.get(i));
			}
		}
		
		while(!pq.isEmpty()) {
			Node next = pq.poll();
			if(visit[next.node])
				continue;
			else {
				sum += next.val;
				visit[next.node] = true;
				q.add(next.node);
				break;
			}
		}
	}
	
	System.out.println(sum);
}

public static class Node{
	int node;
	int val;
	public Node(int node, int val) {
		this.node = node;
		this.val = val;
	}
}
}
*/