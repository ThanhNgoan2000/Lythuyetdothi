package graph;

import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public abstract class Graph {
	public int[][] arr;
	public int visited[];
	public ArrayList<Integer> listVisited = new ArrayList<>();
	public Graph(int[][] arr) {
		super();
		this.arr = arr;
	}
public int topNum() {
	return this.arr.length;
}
	public Graph() {
		// TODO Auto-generated constructor stub
	}
	// duyet do thi vo huong theo chieu ngang
	public void DFS(int i) {
		visited[i] = 1;
		listVisited.add(i);
		for (int j = 0; j < arr.length; j++) {
			if(this.arr[i][j]>0 && visited[j]!=1) {
				DFS(j);
			}
		}
	}
	public void print(ArrayList<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i)+"==>");
		}
	}
	// duyet do thi theo chieu sau
	public void BFS(int i) {
		int num = topNum();
		ArrayList<Integer> queue = new ArrayList<>();
		ArrayList<Integer> listVisit = new ArrayList<>();
		int vs[] = new int[num];
		queue.add(0,1);
		while(queue.size()>0) {
			i= queue.get(queue.size()-1);
			queue.remove(queue.size()-1);
			listVisit.add(i);
			for (int j = 0; j < this.arr.length; j++) {
				if(this.arr[i][j]>0&& vs[j]!=1) {
					vs[j]=1;
					queue.add(0,j);
				}
			}
		}
		print(listVisit);
	}
	public boolean loadData(String fileData) {

		  Path path = Paths.get(fileData);
		  Charset charset = Charset.forName("US-ASCII");
		  try(BufferedReader reader = Files.newBufferedReader(path,charset)) {
			String line = null;
			int count = 0;
			while((line = reader.readLine())!=null) {
				String[] k = line.split(" ");
				if(k.length==1) {
					arr = new int[Integer.parseInt(k[0])][Integer.parseInt(k[0])];
					this.visited = new int[Integer.parseInt(k[0])];
				}else {
					for (int i = 0; i < k.length; i++) {
						arr[count][i] = Integer.parseInt(k[i]);
					}
					count++;
				}
			}
		} catch (Exception e) {
			return false;
		}
		  return true;
	  }
	public void printMatrix(int[][] arr) {
		  for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length; j++) {
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
		  }
	}
 public abstract boolean addEdge(Integer u,Integer v);
 public abstract boolean removeEdge(Integer u, Integer v);
 public boolean addTop() {
	 int array[][] = new int[topNum()+1][topNum()+1];
	 try {
		 for (int i = 0; i < this.arr.length; i++) {
			 for (int k = 0; k < this.arr[i].length; k++) {
				 array[i][k] = this.arr[i][k];

			}
		}
	} catch (Exception e) {
		// TODO: handle exception
		return false;
	}
	 this.arr = array;
	 return true;
 }
 public boolean removeTop() {
	 int array[][] = new int[topNum()-1][topNum()-1];
	 try {
		 for (int i = 0; i < this.arr.length; i++) {
			 for (int k = 0; k < this.arr[i].length; k++) {
				 array[i][k] = this.arr[i][k];

			}
		}
	} catch (Exception e) {
		// TODO: handle exception
		return false;
	}
	 this.arr = array;
	 return true;
 }
 public int coundEdge() {
	 int m = 0;
	 for (int i = 0; i < arr.length; i++) {
		for (int j = 0; j < arr.length; j++) {
			m+= arr[i][j]*0.5;
		}
	}
	 return m;

 }


}
