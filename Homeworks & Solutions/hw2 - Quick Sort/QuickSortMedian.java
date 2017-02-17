import java.io.*;
import java.util.*;

// Quick Sort using the median element of the array as the pivot element,
// the median element is determined by "median-of-three" algorithm
public class QuickSortMedian {
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
      int med = medianOfThree(A, left, right);
      swap(A, left, med);
      int pivot = A[left];  
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
   
   /* the normal way to do median-of-three is:
      int mid = (lo+hi)/2;
      if(A[lo] > A[hi])
         swap(A, lo, hi);
      if(A[mid] > A[hi])
         swap(A, mid, hi);
      if(A[lo] > A[mid])
         swap(A, lo, mid);
         
      But we do not want to change the original ordering of A,
      what we want to know is just the INDEX of the median
   */
   private static int medianOfThree(int[] A, int lo, int hi) {
      int mid = (lo+hi)/2;
      if(A[lo] > A[mid]) {
         if(A[mid] > A[hi])   return mid;
         else {
            if(A[lo] < A[hi]) return lo;
            else   return hi;
         }
      } else {
         if(A[hi] > A[mid])   return mid;
         else {
            if(A[hi] < A[lo]) return lo;
            else   return hi;
         }
      }
   }
}