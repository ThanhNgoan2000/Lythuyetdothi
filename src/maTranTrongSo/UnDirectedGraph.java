package maTranTrongSo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import maTranKe.Tree;

public class UnDirectedGraph extends Graph {
	final static int VCL = Integer.MAX_VALUE;
	int[][] wArr;

	public UnDirectedGraph(int[][] wArr) {
		// khoi tao = truyen vao 1 do thi trong so la 2 chieu
		this.wArr = wArr;
		fillWeight(wArr);
	}

	public void fillWeight(int[][] arr) {
		// do day mang 2 chieu bang VCL
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = VCL;
			}
		}
	}

	@Override
	public void addEdge(Edge e) {
		// TODO Auto-generated method stub
		wArr[e.getStart()][e.getDest()] = e.getWeightValue();
		wArr[e.getDest()][e.getStart()] = e.getWeightValue();
	}

	@Override
	public void removeEdge(Edge e) {
		// TODO Auto-generated method stub
		wArr[e.getStart()][e.getDest()] = VCL;
		wArr[e.getDest()][e.getStart()] = VCL;
	}

	@Override
	public int degree(Integer u) {
		// tinh bac cua dinh u
		int count = 0;
		for (int i = 0; i < wArr.length; i++) {
			if (wArr[u][i] != VCL) {
				count++;
			}
		}
		return count;
	}

	@Override
	public int numOfEdge() {
		// TODO Auto-generated method stub
		int count = 0;
		for (int i = 0; i < wArr.length; i++) {
			for (int j = i; j < wArr.length; j++) {

				if (wArr[i][j] != VCL) {
					count++;
				}
			}
		}
		return count;
	}

	@Override
	public boolean isComponent() {
		// TODO Auto-generated method stub
		if (countComponent() == 1) {
			return true;
		}
		return false;
	}

	private int countComponent() { // dem so thanh phan lien thong
		int count = 0;
		int[] visted = new int[wArr.length];
		for (int i = 0; i < visted.length; i++) {
			if (visted[i] == 0) {
				DFS(i, visted);
				count++;
			}
		}
		return count;
	}

	public ArrayList<Integer> repareDFS(int i) {
		// duyet do thi theo dfs bat dau tu i
		ArrayList<Integer> listVisted = new ArrayList<>();
		int[] visted = new int[wArr.length];
		DFS(i, listVisted, visted);
		return listVisted;
	}

	public void DFS(int i, ArrayList<Integer> listVisted, int[] visted) {
		// phuong thuc chuan bi
		visted[i] = 1;
		listVisted.add(i);
		for (int j = 0; j < wArr.length; j++) {
			if (wArr[i][j] < VCL && visted[j] != 1) {
				DFS(j, listVisted, visted);
			}
		}
	}

	public void DFS(int i, int[] visted) {
		// phuong thuc chuan bi
		visted[i] = 1;
		for (int j = 0; j < wArr.length; j++) {
			if (wArr[i][j] < VCL && visted[j] != 1) {
				DFS(j, visted);
			}
		}
	}

	public boolean hasPathBetweenTwoVertices(int x, int y, int[][] matrix) {
		ArrayList<Integer> arr = repareDFS(x);
		return arr.contains(y);
	}

	@Override
	public boolean isTree() {
		// kiem tra xem do thi co phai la 1 cay khong
		if (isComponent() && wArr.length - 1 == numOfEdge()) {
			// do thi lien thong && so dinh -1 = so canh
			return true;
		}
		return false;
	}

	public Tree getMimimunTreeWithKRUSKAL() {
		int countEdge = 0;
		int[][] MTtree = new int[wArr.length][wArr.length];
		fillWeight(MTtree);// do day mang MTtree bang VCL
		PriorityQueue<Edge> edges = new PriorityQueue<>(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				// TODO Auto-generated method stub
				return o1.getWeightValue() - o2.getWeightValue();
			}
		});
		for (int i = 0; i < MTtree.length; i++) {
			for (int j = i; j < MTtree.length; j++) {
				if (wArr[i][j] < VCL) {
					edges.add(new Edge(i, j, wArr[i][j]));
				}
			}
		}
		while (countEdge < wArr.length - 1) {
			Edge e = edges.poll();
			if (!hasPathBetweenTwoVertices(e.getStart(), e.getDest(), MTtree)) {
				MTtree[e.getStart()][e.getDest()] = wArr[e.getStart()][e.getDest()];
				MTtree[e.getDest()][e.getStart()] = wArr[e.getDest()][e.getStart()];
				countEdge++;
				removeEdge(e);
			} else {
				removeEdge(e);
			}
		}

		return new Tree(MTtree);
	}

	public Tree getMimimunTreeWithPRIM(int begin) {
		int[][] MTtree = new int[wArr.length][wArr.length];
//		int[] visted = new int[wArr.length];
		ArrayList<Integer> vertices = new ArrayList<>();
		vertices.add(begin);

		while (vertices.size() < wArr.length - 1) {
			PriorityQueue<Edge> queue = getAllEdge(vertices);
			Edge e = queue.poll();
			MTtree[e.getStart()][e.getDest()] = wArr[e.getStart()][e.getDest()];
			MTtree[e.getDest()][e.getStart()] = wArr[e.getStart()][e.getDest()];
			if(vertices.contains(e.getStart()))
				vertices.add(e.getDest());
			vertices.add(e.getStart());

		}

		return new Tree(MTtree);

	}

	private PriorityQueue<Edge> getAllEdge(ArrayList<Integer> verticles) {
		PriorityQueue<Edge> result = new PriorityQueue<Edge>();
		for (int i = 0; i < wArr.length; i++) {
			for (int j = 0; j < wArr.length; j++) {
				if (wArr[i][j] < VCL) {
					if (verticles.contains(i) && !verticles.contains(j)) {
						result.add(new Edge(i, j, wArr[i][j]));
					} else if (verticles.contains(j) && !verticles.contains(i)) {
						result.add(new Edge(j, i, wArr[j][i]));
					}
				}
			}
		}
		return result;
	}


	public void dijkstra(int start) {
		Lable[] lables = new Lable[wArr.length];
		List<Integer> list = new ArrayList<Integer>();
		int point = start;
		// khoi tao
		for (int i = 0; i < lables.length; i++) {
			lables[i] = new Lable(VCL, -1);
			list.add(i);
		}
		lables[start].setL(0);
		//
		while (!list.isEmpty()) {
//			point = getMinLabel(list, lables);
			int min = VCL;
			for (Integer i : list) {
				if (lables[i].getL() < min) {
					min = lables[i].getL();
					point = i;
				}
			}

			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) == point) {
					list.remove(i);

				}
			}

			for (Integer i : list) {
				if (wArr[point][i] < VCL)
					if (lables[i].getL() > lables[point].getL() + wArr[point][i]) {
						lables[i].setL(lables[point].getL() + wArr[point][i]);
						lables[i].setP(point);
					}
			}
		}
		for (int i = 0; i < lables.length; i++) {
			System.out.println(lables[i].toString());
		}

		for (int i = 0; i < lables.length; i++) {

			if (start != i) {
				System.out.println(start + " ==> " + (i) + " ,  co do dai la  " + lables[i].getL() + " : ");
				//

				List<Integer> path = new ArrayList<Integer>();
				path.add(i);
				int pre = lables[i].getP();
				while (pre != -1) {
					path.add(0, pre);
					if (pre != -1)
						pre = lables[pre].getP();

				}

				System.out.println(path.toString());
			}
		}
	}

	public static void main(String[] args) {

		UnDirectedGraph WMatrix = new UnDirectedGraph(new int[8][8]);
		WMatrix.addEdge(new Edge(0, 1, 2));
		WMatrix.addEdge(new Edge(0, 5, 1));

		WMatrix.addEdge(new Edge(1, 2, 2));
		WMatrix.addEdge(new Edge(1, 7, 4));
		WMatrix.addEdge(new Edge(1, 6, 2));

		WMatrix.addEdge(new Edge(2, 3, 1));
		WMatrix.addEdge(new Edge(2, 7, 3));

		WMatrix.addEdge(new Edge(3, 4, 6));

		WMatrix.addEdge(new Edge(4, 5, 7));
		WMatrix.addEdge(new Edge(4, 7, 1));

		WMatrix.addEdge(new Edge(5, 6, 3));

		WMatrix.addEdge(new Edge(6, 7, 4));

		WMatrix.getMimimunTreeWithPRIM(3);
	}

}
