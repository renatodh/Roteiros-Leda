package sorting.linearSorting;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {

		int tamanhoArray = 0;

		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i] > tamanhoArray) {
				tamanhoArray = array[i];
			}
		}

		int[] arrayAux = new int[tamanhoArray + 1];

		for (int j = leftIndex; j <= rightIndex; j++) {
			arrayAux[array[j]] = arrayAux[array[j]] + 1;
		}

		for (int k = 1; k < arrayAux.length; k++) {
			arrayAux[k] += arrayAux[k - 1];
		}

		int[] result = new int[rightIndex - leftIndex + 1];

		for (int l = rightIndex; l >= leftIndex; l--) {
			result[arrayAux[array[l]] - 1] = array[l];
			arrayAux[array[l]]--;
		}

		for (int i = 0; i < result.length; i++) {
			array[i] = result[i];
		}

	}
}