package adt.hashtable.closed;

import java.util.LinkedList;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;
import util.Util;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

	/**
	 * A hash table with closed address works with a hash function with closed
	 * address. Such a function can follow one of these methods: DIVISION or
	 * MULTIPLICATION. In the DIVISION method, it is useful to change the size
	 * of the table to an integer that is prime. This can be achieved by
	 * producing such a prime number that is bigger and close to the desired
	 * size.
	 * 
	 * For doing that, you have auxiliary methods: Util.isPrime and
	 * getPrimeAbove as documented bellow.
	 * 
	 * The length of the internal table must be the immediate prime number
	 * greater than the given size. For example, if size=10 then the length must
	 * be 11. If size=20, the length must be 23. You must implement this idea in
	 * the auxiliary method getPrimeAbove(int size) and use it.
	 * 
	 * @param desiredSize
	 * @param method
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
		int realSize = desiredSize;

		if (method == HashFunctionClosedAddressMethod.DIVISION) {
			realSize = this.getPrimeAbove(desiredSize); // real size must the
			// the immediate prime
			// above
		}
		initiateInternalTable(realSize);
		HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
		this.hashFunction = function;
	}

	// AUXILIARY
	/**
	 * It returns the prime number that is closest (and greater) to the given
	 * number. You can use the method Util.isPrime to check if a number is
	 * prime.
	 */
	int getPrimeAbove(int number) {
		int n = number;
		if (n % 2 == 0) {
			n++;
		}
		while (!Util.isPrime(n)) {
			n += 2;
		}
		return n;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			int hash = ((HashFunctionClosedAddress<T>) this.getHashFunction()).hash(element);

			if (this.table[hash] == null) {
				this.table[hash] = new LinkedList<T>();
				this.getCell(hash).add(element);

			}else if(this.getCell(hash).contains(element)){
				this.getCell(hash).remove(element);
				this.getCell(hash).add(element);
			}
			else{
				this.getCell(hash).add(element);
				this.COLLISIONS ++;
			}
			this.elements ++;
		}
	}

	@Override
	public void remove(T element) {
		if(element != null && !isEmpty()){
			
			int hash = ((HashFunctionClosedAddress<T>) this.getHashFunction()).hash(element);
			if(this.getCell(hash) != null && !this.getCell(hash).isEmpty()){
				this.getCell(hash).remove(element);
				this.COLLISIONS--;
				this.elements--;
			}
		}
	}

	@Override
	public T search(T element) {
		T elemento = null;
		if(element != null){
			int hash = ((HashFunctionClosedAddress<T>) this.getHashFunction()).hash(element);
			if(this.getCell(hash) != null){
				if(this.getCell(hash).contains(element)){
					elemento = element;
				}
			}
		}
		return elemento;
	}

	@Override
	public int indexOf(T element) {
		int index = - 1;
		
		if(element != null){
			int hash = ((HashFunctionClosedAddress<T>) this.getHashFunction()).hash(element);
			if(this.getCell(hash) != null){
				if(this.getCell(hash).contains(element)){
					index = hash;
				}
			}
		}
		return index;
	}
	
	//Metodo auxiliar para pegar a LinkedList em determinado indice
	@SuppressWarnings("unchecked")
	private LinkedList<T> getCell(int hash) {
		return (LinkedList<T>) this.table[hash];
	}

}
