package practice.maTranLienThuoc;

public abstract class Graph {

	public Graph() {
		// TODO Auto-generated constructor stub
	}
 public abstract void addEdge(Integer u,Integer v);
 public abstract void removeEdge(Integer u, Integer v);
 public abstract void printAdjList();
 public abstract int degree(Integer u);
 public abstract int numOfEdge();
}
