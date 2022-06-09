package graph;

import java.util.ArrayList;
import java.util.Stack;

public class UnDirectedGraph extends Graph {

	public UnDirectedGraph(int[][] arr) {
		// TODO Auto-generated constructor stub
		super(arr);
	}

	public UnDirectedGraph() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean addEdge(Integer u, Integer v) {
		int vertexCount = topNum();
		if (u >= 0 && u < vertexCount && v > u && v < vertexCount) {
			this.arr[u][v] = 1;
			this.arr[v][u] = 1;
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
			this.arr[v][u] = 0;
			return true;

		}
		return false;
	}

	public ArrayList<Integer> DFS(int[] visited) {
		int i = 0;
		int numVerTex = topNum();
		ArrayList<Integer> listvisit = new ArrayList<>(); // luu vet
//		int visited[] = new int[numVerTex];
		Stack<Integer> stack = new Stack<>();
		while (!stack.empty()) {
			i = stack.peek();
			int count = 0;
			for (int j = 0; j < visited.length; j++) {
				if (this.arr[i][j] > 0 && visited[j] != 1) {
					visited[j] = 1;
					listvisit.add(j);
					stack.push(j);
					break;

				} else {
					count++;
				}
			}
			if (count == visited.length) {
				stack.pop();
			}
		}
		return listvisit;
	}

	// -------------------------------------
	public int countComponent() {
		int count = 0;

		int[] visted = new int[arr.length];
		for (int i = 0; i < visted.length; i++) {
			if (visted[i] == 0) {
				DFS(visted);
				count++;
			}
		}
		return count;
	}

	public boolean isConnected() {// lien thong
		if (countComponent() == 1) {
			return true;
		} else
			return false;
	}
	// --------------------------------------------

	public boolean checkRoad(Integer u, Integer v) { // kiem tra co duong di
		int[] visted = new int[arr.length];
		ArrayList<Integer> listVisted = new ArrayList<>();
		listVisted = DFS(visited);
		if (listVisted.contains(v) && listVisted.contains(u)) {
			return true;
		}
		return false;
	}

	public ArrayList<Integer> roadBetween2Ver(Integer u, Integer v) { // lay ra duong di
		int[] visted = new int[arr.length];
		Stack<Integer> stack = new Stack<>();
		if (!checkRoad(u, v)) {
			return null;
		}
		findPathDFS(u, v, stack, visted);
		return new ArrayList<>(stack);
	}

	public void findPathDFS(int u, int v, Stack<Integer> stack, int[] visted) {
		stack.push(u);
		visted[u] = 1;
		if (!stack.contains(v)) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[u][j] >= 1 && visted[j] != 1) {
					findPathDFS(j, v, stack, visted);// *
					if (!stack.contains(v)) {
						stack.pop();
					} else
						return;
				}
			}
		}
	}

	// ----------------------------------
	public int degree(int vertex) { // bac cua 1 dinh
		int rs = 0;
		for (int i = 0; i < arr.length; i++) {
			rs += arr[vertex][i];
		}
		return rs;
	}

	public boolean isEuler() {
		for (int i = 0; i < arr.length; i++) {
			if (topNum() > 1 && isConnected() && degree(i) % 2 == 0) {
				return true;
			}
		}
		return false;
	}

	public boolean isHalfEuler() {
		int count = 0;
		if (!isConnected()) {
			return false;
		}
		if (topNum() <= 1) {
			return false;
		}
		for (int i = 0; i < arr.length; i++) {
			if (degree(i) % 2 != 0) {
				count++;
			}
		}
		if (count == 2) {
			return true;
		}
		return false;
	}

	public int[][] clone(int[][] matrix) {
		// sao chep mang
		int[][] clone = new int[arr.length][arr.length];
		for (int i = 0; i < clone.length; i++) {
			for (int j = 0; j < clone.length; j++) {
				clone[i][j] = matrix[i][j]; // sao chep
			}
		}
		return clone;
	}

	public boolean isBridgeEdge(int u, int v, int[][] matrix) {

		int[][] cloneMatrix1 = clone(matrix);
		removeEdge(u, v);// xoa canh(u,v) tren mang clone khong anh huong den mang chinh
		if (checkRoad(u, v)) {
			return false;
		}
		return true;
	}

public boolean isTree() {
	if(isConnected()&& SingleGraph()&& arr.length-1 == coundEdge()) {
		return true;
	}
	return false;
}

private boolean SingleGraph() {
	// TODO Auto-generated method stub
	for (int i = 0; i < arr.length; i++) {
		for (int j = 0; j < arr.length; j++) {
			if(arr[j][j]!=1&& arr[i][j]!=0) {
				return false;
			}else if(i==j&& arr[i][j]!=0) {
				return false;
			}
		}
	}
	return true;
}


}
