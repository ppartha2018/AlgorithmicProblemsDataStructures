import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class StronglyConnectedComponent {
	
	//finding strongly connected components in a directed graph
	//example graph in clrs
	//total number of vertices
	private int n;
	private LinkedList<Integer> original[];
	//private LinkedList<Integer> transpose[];
	
	public StronglyConnectedComponent(int n) {
		//n = number of vertices
		this.n= n;
		original = new LinkedList[n];
	}
	
	//connected graph
	public void addEdge(int node1, int node2) {
		node1 = node1 - 1;
		node2 = node2 - 1;
		if(original[node1] == null) {
			original[node1] = new LinkedList<Integer>();
			
			original[node1].add(node2);
		} else {
			original[node1].add(node2);
		}
	}
	
	public LinkedList<Integer>[] getTranspose() {
		LinkedList<Integer> transpose[] = new LinkedList[n];
		for(int i=0; i < n; i++) {
			if(original[i] != null) {
				for(int edge: original[i]) {
					if(transpose[edge] != null) {
						transpose[edge].add(i);
					} else {
						LinkedList<Integer> reverseEdges = new LinkedList<Integer>();
						reverseEdges.add(i);
						transpose[edge] = reverseEdges;
					}
				}
			}
		}
		return transpose;
	}
	
	public void dfs(LinkedList<Integer>[] graph, int s, boolean visited[]) {
		Stack<Integer> openSet = new Stack<Integer>();
		openSet.push(s);
		while(!openSet.isEmpty()) {
			int curr = openSet.pop();
			visited[curr] = true;
			System.out.print((curr+1)+",");
			if(graph[s] != null) {
				Iterator<Integer> adj = graph[curr].iterator();
				while(adj.hasNext()) {
					int neighbor = adj.next();
					if(!visited[neighbor]) {
						openSet.push(neighbor);
					}
				}
			}
		}
	}
	
	//finding the finishing times
	public void findOrderDFS(LinkedList<Integer>[] graph, int s, boolean visited[], Stack<Integer> vertexOrders) {
		
		visited[s] = true;
		Iterator<Integer> adj = graph[s].iterator();
		while(adj.hasNext()) {
			int neighbor = adj.next();
			if(!visited[neighbor]) {
				findOrderDFS(graph, neighbor, visited, vertexOrders);
			}
		}
		//note the order after finishing up with that vertex
		vertexOrders.push(s);
	}
	
	public void findSCCs() {
		LinkedList<Integer>[] transpose = getTranspose();
		
		Stack<Integer> vertexOrders = new Stack<Integer>();
		boolean[] visited = new boolean[n];
		
		//get finishing times of all vertices in the graph
		for(int i = 0; i < n; i++) {
			if(!visited[i]) {
				findOrderDFS(transpose, i, visited, vertexOrders);
			}
		}
		
		//now do a dfs on the original graph based on the finishing times on transpose
		for(int i = 0; i < n; i++) {
			visited[i] = false;
		}
		
		int numComponents = 0;
		while(!vertexOrders.isEmpty()) {
			int i = vertexOrders.pop();
			if(!visited[i]) {
				numComponents++;
				System.out.println("\nComponent: "+numComponents);
				dfs(original,i,visited);
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		StronglyConnectedComponent sc = new StronglyConnectedComponent(8);
		sc.addEdge(1, 2);
		sc.addEdge(2, 3);
		sc.addEdge(3, 4);
		sc.addEdge(4, 3);
		sc.addEdge(2, 5);
		sc.addEdge(5, 1);
		sc.addEdge(5, 6);
		sc.addEdge(6, 7);
		sc.addEdge(7, 6);
		sc.addEdge(7, 8);
		//sc.addEdge(8, 8);
		sc.addEdge(2, 6);
		sc.addEdge(3, 7);
		sc.addEdge(4, 8);
		
		sc.findSCCs();
	}

}
