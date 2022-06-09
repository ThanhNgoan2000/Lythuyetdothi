package graph;

public class DirectedGraph extends Graph {

//	@Override
//	public void printAdjList() {
//		// TODO Auto-generated method stub
//		Set<Integer> keyset = adjList.keySet();
//		for (Integer key : keyset) {
//			System.out.println(key+":"+ adjList.get(key));
//		}
//	}

	@Override
	public boolean addEdge(Integer u, Integer v) {
		// TODO Auto-generated method stub
		int vertexCount = topNum();
		if (u >= 0 && u < vertexCount && v > u && v < vertexCount) {
			this.arr[u][v] = 1;
			return true;

		}
		return false;
	}

	@Override
	public boolean removeEdge(Integer u, Integer v) {
		// TODO Auto-generated method stub
		int vertexCount = topNum();
		if (u >= 0 && u < vertexCount && v > u && v < vertexCount) {
			this.arr[u][v] = 0;
			return true;

		}
		return false;
	}

}
