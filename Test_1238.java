import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 다익스트라: 거리 갱신시 기존에 거리를 pq에서 제거하고 다시 삽입하는 방식
public class Test_1238 {
	
	static int n;
	static int m;
	static int x;
	static ArrayList<ArrayList<Node>> map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken()) - 1;
		map = new ArrayList<ArrayList<Node>>();
		for(int i = 0; i < n; i++)
			map.add(new ArrayList<Node>());
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken()) - 1;
			int n2 = Integer.parseInt(st.nextToken()) - 1;
			int value = Integer.parseInt(st.nextToken());
			map.get(n1).add(new Node(n2, value));
		}
		
		int[] total = new int[n];
		
		for(int j = 0; j < n; j++) {
			PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					return Integer.compare(o1.value, o2.value);
				}
			});
			Node[] dist = new Node[n];
			boolean[] visit = new boolean[n];
			for(int i = 0; i < n; i++) {
				if(i == j) {
					dist[i] = new Node(i, 0);
				} else {
					dist[i] = new Node(i, Integer.MAX_VALUE);
				}
				pq.add(dist[i]);
			}
			
			while(!pq.isEmpty()) {
				Node now = pq.poll();
				
				ArrayList<Node> link = map.get(now.node);
				for(int i = 0; i < link.size(); i++) {
					Node next = link.get(i);
					if(!visit[next.node] && dist[next.node].value > dist[now.node].value + next.value) {
						dist[next.node].value = dist[now.node].value + next.value;
						pq.remove(dist[next.node]);
						pq.add(dist[next.node]);
					}
				}
				visit[now.node] = true;
			}
			
			if(j != x) {
				total[j] += dist[x].value; 
			} else {
				for(int i = 0; i < n; i++) {
					if(i == x)
						continue;
					total[i] += dist[i].value;
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < n; i++) {
			if(total[i] > max)
				max = total[i];
		}
		System.out.println(max);
	}
	
	public static class Node{
		int node;
		int value;
		public Node(int node, int value) {
			this.node = node;
			this.value = value;
		}
	}
}

/*
// 더 짧은 거리 갱신할때마다 pq에 계속 삽입하는 방식
package com.ssafy.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main1753 {

	static ArrayList<ArrayList<Pair>> map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int v = Integer.parseInt(st.nextToken()); // 정점의 개수
		int e = Integer.parseInt(st.nextToken()); // 간선의 개수
		int start = Integer.parseInt(br.readLine()) - 1; // 시작 정점
		
		map = new ArrayList<ArrayList<Pair>>();
		for(int i = 0; i < v; i++)
			map.add(new ArrayList<Pair>());
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken()) - 1;
			int v2 = Integer.parseInt(st.nextToken()) - 1;
			int val = Integer.parseInt(st.nextToken());
			map.get(v1).add(new Pair(v2, val));
		}
		
		int[] distance = new int[v];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		boolean[] visit = new boolean[v];
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		pq.add(new Pair(start, 0));
		
		while(!pq.isEmpty()) {
			Pair now = pq.poll();
			
			if(visit[now.node])
				continue;
			
			ArrayList<Pair> link = map.get(now.node);
			for(int i = 0; i < link.size(); i++) {
				Pair next = link.get(i);
				if(!visit[next.node] && distance[next.node] > now.distance + next.distance) {
					distance[next.node] = now.distance + next.distance;
					pq.add(new Pair(next.node, distance[next.node]));
				}
			}
			
			visit[now.node] = true;
		}
		
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < v; i++) {
			if(distance[i] == Integer.MAX_VALUE)
				result.append("INF\n");
			else
				result.append(distance[i] + "\n");
		}
		System.out.println(result.toString());
	}
	
	public static class Pair implements Comparable<Pair>{
		int node;
		int distance;
		public Pair(int node, int val) {
			this.node = node;
			this.distance = val;
		}
		
		@Override
		public int compareTo(Pair o) {
			return Integer.compare(this.distance, o.distance);
		}
	}
}

*/