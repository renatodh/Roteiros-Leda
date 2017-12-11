package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
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

	private int getHash(T element, int i) {
		return ((HashFunctionLinearProbing<T>) super.hashFunction).hash(element, i);
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
