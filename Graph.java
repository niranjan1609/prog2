package graphs;

import java.util.*;


class Edge
{
	int source, dest;

	public Edge(int source, int dest) {
		this.source = source;
		this.dest = dest;
	}
};


class Graph
{
	List<List<Integer>> adjList = null;

	
	List<Integer> indegree = null;

	
	Graph(List<Edge> edges, int N) {
		adjList = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			adjList.add(i, new ArrayList<>());
		}

	
		indegree = new ArrayList<>(Collections.nCopies(N, 0));

	
		for (int i = 0; i < edges.size(); i++)
		{
			int src = edges.get(i).source;
			int dest = edges.get(i).dest;

	
			adjList.get(src).add(dest);

		
			indegree.set(dest, indegree.get(dest) + 1);
		}
	}
}

class TopologicalSort
{
	
	public static List<Integer> doTopologicalSort(Graph graph, int N)
	{
		
		List<Integer> L = new ArrayList<>();

		
		List<Integer> indegree = graph.indegree;

	
		Stack<Integer> S = new Stack<>();
		for (int i = 0; i < N; i++) {
			if (indegree.get(i) == 0) {
				S.add(i);
			}
		}

		while (!S.isEmpty())
		{
			
			int n = S.pop();

			
			L.add(n);

			for (int m : graph.adjList.get(n))
			{
				
				indegree.set(m, indegree.get(m) - 1);

				
				if (indegree.get(m) == 0) {
					S.add(m);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			if (indegree.get(i) != 0) {
				return null;
			}
		}

		return L;
	}
	public static void main(String[] args)
	{
		
		List<Edge> edges = Arrays.asList(
							new Edge(0, 6), new Edge(1, 2), new Edge(1, 4),
							new Edge(1, 6), new Edge(3, 0), new Edge(3, 4),
							new Edge(5, 1), new Edge(7, 0), new Edge(7, 1)
						);

		
		final int N = 8;

		
		Graph graph = new Graph(edges, N);

	
		List<Integer> L = doTopologicalSort(graph, N);

		if (L != null) {
			System.out.print(L); 
		} else {
			System.out.println("Graph has at least one cycle. " +
							  "Topological sorting is not possible");
		}
	}

	
}