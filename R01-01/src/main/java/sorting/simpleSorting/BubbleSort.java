package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	Util aux = new Util();
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for (int j = rightIndex; j > leftIndex; j--){

			for (int i = leftIndex; i < rightIndex; i++) {
					if(array[i].compareTo(array[i+1]) > 0){
						aux.swap(array, i, i+1);
					}
			}
			
		}
	}
}