package sorting.variationsOfBubblesort;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex >= 0 && leftIndex <= rightIndex && rightIndex < array.length) {

			double fator = 1.25;

			int gap = (rightIndex - leftIndex + 1);
			

			boolean swapped = false;
			while (gap > 1 || swapped) {
				if(gap > 1){
					gap = (int) (gap / fator);
					
				}
				swapped = false;
				for (int j = leftIndex; j + gap <= rightIndex; j++) {
					if (array[j].compareTo(array[j + gap]) > 0) {
						Util.swap(array, j, j + gap);
						swapped = true;
					}

				}
				
			}
			

		}
	}
}
