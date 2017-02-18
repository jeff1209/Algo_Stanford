import java.io.*;
import java.util.*;

// This assignment is using a hashmap to represent the graph, and
// applying the random contraction algorithm to compute its minimium cut

public class MinCut {
   public static void main(String[] args) {
      HashMap<Integer, List<Integer>> graph = convertFileToMap();
      int result = calculateMinCut(graph);
      System.out.println(result);
   }
   
   private static HashMap<Integer, List<Integer>> convertFileToMap() {
      HashMap<Integer, List<Integer>> g = new HashMap<>();
      File f = new File("MinCut.txt");
      
      try (Scanner input = new Scanner(f)) {
      
         while(input.hasNext()) {
            String[] line = input.nextLine().split("\t"); //get the vertices array
            
            int vertex = Integer.parseInt(line[0]);
            List<Integer> adjList = new ArrayList<Integer>();
            
            for(int i = 1; i < line.length; i++) {
               adjList.add(Integer.parseInt(line[i]));
            }
            
            g.put(vertex, adjList);
         }
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
      
      return g;
   }
   
   private static int calculateMinCut(Map<Integer, List<Integer>> g) {
      
      // merge vertices until two final vertices left
      while(g.keySet().size() > 2) {
         mergeVertices(g);
      }
      
      int minCut = Integer.MAX_VALUE;
      
      for(int key : g.keySet()) {
         minCut = Math.min(minCut, g.get(key).size());
      }
      
      return minCut;
   }
   
   private static void mergeVertices(Map<Integer, List<Integer>> g) {
      // get a list of all vertices
      List<Integer> vertices = new ArrayList<>(g.keySet());
      
      Random r = new Random();
      
      // randomly pick a vertex
      int r1 = r.nextInt(vertices.size());
      int v1 = vertices.get(r1);
      List<Integer> list = g.get(v1);
      
      // randomly pick a vertex from v1's adjacent list
      int r2 = r.nextInt(list.size());
      int v2 = list.get(r2);
      
      // merge v1 and v2 => remove the edge (v1, v2)
      removeEdge(g, v1, v2);
   }
   
   private static void removeEdge(Map<Integer, List<Integer>> g, int v1, int v2) {
   
      List<Integer> list1 = g.get(v1);
      List<Integer> list2 = g.get(v2);
      
      g.remove(v1);  //remove one of two vertices
      
      // remove self loops
      list1 = removeSelfLoops(list1, v2);
      list2 = removeSelfLoops(list2, v1);
      
      for(int v : list1) {
         List<Integer> l = g.get(v);
         // let all v1's adjacent vertices be adjacent to v2
         for(int i = 0; i < l.size(); i++) {
            if(l.get(i) == v1) {
               l.set(i, v2);
            }
         }
      }
      
      list2.addAll(list1);
      g.put(v2, list2);
   }
   
   private static List<Integer> removeSelfLoops(List<Integer> list, int v) {
      List<Integer> res = new ArrayList<>();
      
      for(int vertex : list) {
         if(vertex != v)
            res.add(vertex);
      }
      return res;
   }
}