package adt.btree;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root) - 1;
	}

	private int height(BNode<T> node) {
		int height = 0;
		if (!node.isEmpty()) {
			height++;
			int subTreeHeight = 0;
			for (BNode<T> child : node.children) {
				subTreeHeight = Math.max(subTreeHeight, this.height(child));
			}
			height += subTreeHeight;
		}
		return height;
	}

	@Override
	public BNode<T>[] depthLeftOrder() {
		int index = 0;
		BNode<T>[] array = new BNode[this.size()];
		this.depthLeftOrder(index, array, this.getRoot());
		return array;
	}

	private int depthLeftOrder(int index, BNode<T>[] array, BNode<T> node) {
		array[index++] = node;
		for (BNode<T> child : node.getChildren()) {
			index = this.depthLeftOrder(index, array, child);
		}
		return index;
	}

	@Override
	public int size() {
		return this.size(this.getRoot());
	}

	private int size(BNode<T> node) {
		int size = node.getElements().size();
		for (BNode<T> child : node.getChildren()) {
			size += this.size(child);
		}
		return size;
	}

	@Override
	public BNodePosition<T> search(T element) {
		return this.search(this.getRoot(), element);
	}

	private BNodePosition<T> search(BNode<T> node, T element) {
		if (node.getElements().isEmpty()) {
			return new BNodePosition<T>(null, -1);
		}
		for (int index = 0; index < node.getElements().size(); index++) {
			if (node.getElementAt(index).equals(element)) {
				return new BNodePosition<T>(node, index);
			} else if (node.getElementAt(index).compareTo(element) > 0) {
				if (node.getChildren().size() <= index) {
					return new BNodePosition<T>(null, -1);
				} else {
					return this.search(node.getChildren().get(index), element);
				}
			}
		}
		return this.search(node.getChildren().get(node.getElements().size()), element);
	}

	@Override
	public void insert(T element) {
		this.insert(this.root, element);
	}

	/*
	 * Como n�o foi especificado o algoritmo de inser��o, fiz da forma que o
	 * professor Jo�o Arthur explicou em sala. Algoritmo que abre espa�o nos n�s
	 * acima da folha em que ser� inserido o n�, se o mesmo estiver cheio para
	 * que se precisar fazer promote, o n� acima ter� espa�o.
	 */
	private void insert(BNode<T> node, T element) {
		if (!node.isFull() && node.isLeaf()) {
			node.addElement(element);
		} else {
			if (node.isFull()) {
				if (this.root.equals(node)) {
					node.split();
				} else {
					node.promote();
				}
			}
			int index;
			for (index = 0; index < node.getElements().size(); index++) {
				if (node.getElementAt(index).compareTo(element) > 0) {
					this.insert(node.getChildren().get(index), element);
					break;
				}
			}
			if (index == node.getElements().size()) {
				this.insert(node.getChildren().get(index), element);
			}
		}
	}

	private void split(BNode<T> node) {
		node.split();
	}

	private void promote(BNode<T> node) {
		node.promote();
	}

	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}
}