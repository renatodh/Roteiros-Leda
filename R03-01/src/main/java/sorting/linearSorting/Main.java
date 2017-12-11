package sorting.linearSorting;

import java.util.Scanner;

public class Main {

	
	public static void main(String[]args){
		
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
		
		int[] array = new int[n];
		
		for (int i = 0; i < n; i++) {
			array[i] = scan.nextInt();
		}
		
		CountingSort(array, 0, array.length - 1);
		
		String resultado = "";
		resultado += array[0];
		for (int i = 1; i < array.length; i++) {
			resultado += " " + array[i];
		}
		
		System.out.println(resultado);
		
	}
	
	public static void CountingSort(int[] array, int leftIndex, int rightIndex){
	      if (leftIndex >= 0 && leftIndex <= rightIndex && rightIndex < array.length) {
	          double numeroMagico = 1.25;
	          int gap = (int) (rightIndex - leftIndex + 1 / numeroMagico);
	          int index = 0;

	          while (index <= rightIndex) {

	             for (int j = leftIndex; j + gap <= rightIndex; j++) {
	                if (array[j] >array[j + gap]) {
	                   swap(array, j, j + gap);

	                }

	             }
	             gap = (int) (gap / numeroMagico);
	             index++;

	          }

	       }
	    }
	 

	private static void swap(int[] array, int pivot, int i) {
		if (array == null)
			throw new IllegalArgumentException();

		int temp = array[pivot];
		array[pivot] = array[i];
		array[i] = temp;
	}

}
