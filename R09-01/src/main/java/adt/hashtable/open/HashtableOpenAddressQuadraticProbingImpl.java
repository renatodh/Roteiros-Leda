package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}
	
	private int getHash(T element, int i) {
		return ((HashFunctionQuadraticProbing<T>) super.hashFunction).hash(element, i);
	}
	
	@Override
	public void insert(T element) {
		if (super.isFull()) {
			throw new HashtableOverflowException();
		}

		if (element != null && this.search(element) == null) {

			int i = 0;
			while (i < super.capacity()) {
				int hash = getHash(element, i);
				
				if(this.table[hash] == null || this.table[hash].equals(deletedElement)){
					this.table[hash] = element;
					break;
					
				}
				
				i++;
				COLLISIONS++;
			}

			elements++;

		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !super.isEmpty()) {
			int i = 0;
			
			
			while (i < super.capacity()) {
				int hash = getHash(element, i);
				
				if(this.table[hash] != null && this.table[hash].equals(element)){
					table[hash] = new DELETED();
					elements --;
					break;
				}
				
				i++;
				COLLISIONS--;
			}
			

		}
	}

	@Override
	public T search(T element) {
		if(element != null){
			if(indexOf(element) != -1){
				return element;
			}
			
		}
		return null;
	}

	@Override
	public int indexOf(T element) {
		
		int indice = -1;
		if(element == null){
			return indice;
		}
		
		int i = 0;
		while (i < table.length){
			int hash = getHash(element, i);
			if (table[hash] == null){
				return -1;
			}
			if(table[hash].equals(element)){
				return hash;
			}
			
			i++;
		}
		return -1;
		
	}
}
