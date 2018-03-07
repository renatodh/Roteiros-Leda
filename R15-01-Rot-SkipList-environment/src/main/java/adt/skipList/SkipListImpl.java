package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void insert(int key, T newValue, int height) {
		
		SkipListNode[] update = new SkipListNode[maxHeight];
		SkipListNode<T> aux = this.root;
		
		for (int i = maxHeight-1; i >= 0; i--) {
			while(aux.getForward(i).getKey() < key){
				aux = aux.getForward(i);
			}
			update[i] = aux;
		}
		aux = aux.getForward(0);
		if(aux.getKey() == key){
			aux.setValue(newValue);
		}else{
			aux = new SkipListNode<T>(key,height,newValue);
			for (int i = 0; i < height; i++) {
				aux.getForward()[i] = update[i].getForward(i);
				update[i].getForward()[i] = aux;
			}
		}
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void remove(int key) {
		
		SkipListNode[] update = new SkipListNode[maxHeight];
		SkipListNode<T> aux = this.root;
		
		for (int i = maxHeight-1; i >= 0; i--) {
			while(aux.getForward(i).getKey() < key){
				aux = aux.getForward(i);
			}
			update[i] = aux;
		}
		aux = aux.getForward(0);
		if(aux.getKey() == key){
			int i = 0;
			while(i < maxHeight && update[i].getForward(i) == aux){
				update[i].getForward()[i] = aux.getForward(i);
				i++;
			}
		}
	}

	@Override
	public int height() {
		int h = 0;
		SkipListNode<T> foward = root.getForward(0);
		
		while(!foward.equals(NIL)){
			if(foward.height() > h){
				h = foward.height();
			}
			
			foward = foward.getForward(0);
			
		}
		
		return h;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> aux = this.root;
		for (int i = this.maxHeight - 1; i >= 0; i--) {
			
			while(aux.forward[i].getKey() < key){
				aux = aux.forward[i];
			}
		}
		
		aux = aux.forward[0];
		
		if(aux.getKey() != key){
			aux = null;
		}
		
		return aux;
	}

	@Override
	public int size() {
		int size = 0;
		SkipListNode<T> aux = this.root.getForward(0);
		while (!aux.equals(NIL)) {
			size++;
			aux = aux.getForward(0);
		}

		return size;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SkipListNode<T>[] toArray() {
		SkipListNode<T>[] array = new SkipListNode[size() + 2];
		int index = 0;
		SkipListNode<T> aux = this.root;

		while (aux != null) {
			array[index++] = aux;
			aux = aux.getForward(0);
		}

		return array;
	}

}
