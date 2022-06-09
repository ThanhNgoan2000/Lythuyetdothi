package graph;

public class Test {
public static void main(String[] args) {
	String path = "data1.txt";
	Graph g1 = new UnDirectedGraph();
	g1.loadData(path);
	g1.printMatrix(g1.arr);
//	System.out.println();
//	g1.addEdge(0, 2);
//	g1.printMatrix(g1.arr);
//	System.out.println();
//	g1.removeEdge(0, 2);
//	g1.printMatrix(g1.arr);
//	System.out.println(g1.addTop());
//	g1.printMatrix(g1.arr);
//	g1.DFS(1);
//	g1.BFS(1);
//	g1.print(g1.listVisited);
//	System.out.println();
//	System.out.println(((UnDirectedGraph)g1).isConnected());
	System.out.println(((UnDirectedGraph)g1).isEuler());
	System.out.println(((UnDirectedGraph)g1).isHalfEuler());

}
}
