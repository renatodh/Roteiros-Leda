package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		int valorMinimo = 0;
		int valorMaximo = 0;
		
		for (int i = 0; i < rightIndex - leftIndex + 1; i++) {
			if(array[i] < valorMinimo ){
				valorMinimo = array[i];
			}
			if(array[i] > valorMaximo){
				valorMaximo = array[i];
			}
		}
		
		int[] arrayAux = new int[valorMaximo - valorMinimo + 1];
		
		for(int j = leftIndex; j <= rightIndex ; j++) {
			arrayAux[array[j] - valorMinimo] ++;
		}
		
		for(int k = 1; k < arrayAux.length; k++) {
			arrayAux[k] += arrayAux[k-1];
		}
		
		int[] result = new int[rightIndex - leftIndex + 1];
		
		for(int l = rightIndex; l >= leftIndex; l-- ) {
			result[arrayAux[array[l] - valorMinimo]-1] = array[l];
			arrayAux[array[l] - valorMinimo]--;
		}
		
		for (int i = 0; i < result.length; i++) {
			array[i] = result[i];
		}
		
	}

}
