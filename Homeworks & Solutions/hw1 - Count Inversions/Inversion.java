import java.util.*;
import java.io.*;


public class Inversion {
   public static void main(String[] args) {
      File f = new File("IntegerArray.txt");
      try (Scanner input = new Scanner(f);) {
         int[] A = new int[100000];
         int index = 0;
         while(input.hasNext()) {
            A[index++] = input.nextInt();
         }
         int n = A.length;
      //System.out.println(n);
      //System.out.println(A[99999]);
      System.out.println(countInversions(A, n));
      } catch (FileNotFoundException e) {
         // tell the complier that there is a chance to throw a FileNotFoundException
         e.printStackTrace();
      }
      
   }
   private static long countInversions(int[] A, int n) {
      if(n == 1)  return 0;
      int[] left = Arrays.copyOfRange(A, 0, n/2);
      int[] right = Arrays.copyOfRange(A, n/2, n);
      return countInversions(left,n/2)+countInversions(right,n-n/2)+merge(left,right,A);
   }
   private static long merge(int[] left, int[] right, int[] A) {
      int i = 0, j = 0, k = 0;
      long inversions = 0;
      while(i < left.length && j < right.length) {
         if(left[i] <= right[j]) {
            A[k] = left[i];
            i++;
         } else {
            A[k] = right[j];
            j++;
            inversions += left.length-i;
         }
         k++;
      }
      if(i == left.length) {
         System.arraycopy(right, j, A, k, right.length-j);
      } else {
         System.arraycopy(left, i, A, k, left.length-i);
      }
      return inversions;
   }
}