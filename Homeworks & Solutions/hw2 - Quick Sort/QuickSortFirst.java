import java.io.*;
import java.util.*;

// Quick Sort using the first element of the array as the pivot element
public class QuickSortFirst {
   public static void main(String[] args) {
      File f = new File("QuickSort.txt");
      try (Scanner input = new Scanner(f)) {
         int[] nums = new int[10000];
         int index = 0;
         while(input.hasNext()) {
            nums[index++] = input.nextInt();
         }
         int n = nums.length;
         int count = quickSort(nums, 0, n-1);
         System.out.println(count);
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
   }
   
   // the main idea of quick sort is partition the array as:
   // [elements < pivot, pivot, elements > pivot]
   public static int quickSort(int[] A, int left, int right) {
      if(left >= right) 
         return 0;
      int result = 0;
      result += right-left;
      int pivot = A[left];    //choose the first element as pivot
      int i = left+1;   //i is always the first index on pivot's right side
      for(int j = left+1; j <= right; j++) {
         if(A[j] < pivot) {
            swap(A, i, j);
            i++;
         }
      }
      swap(A, left, i-1);  //i-1 is the position of pivot
      result += quickSort(A, left, i-2);
      result += quickSort(A, i, right);
      return result;
   }
   
   private static void swap(int[] A, int i, int j) {
      int temp = A[i];
      A[i] = A[j];
      A[j] = temp;
   }
}