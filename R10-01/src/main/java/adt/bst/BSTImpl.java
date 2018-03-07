package adt.bst;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height(BSTNode<T> node) {
		int height = -1;
		if (!node.isEmpty()) {
			height = Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight())) + 1;
		}
		return height;
	}

	@Override

	public BSTNode<T> search(T element) {
		return search(this.getRoot(), element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		BSTNode<T> returnNode = new BSTNode<T>();

		if (!node.isEmpty()) {
			if (element.compareTo(node.getData()) == 0) {
				returnNode = node;

			} else if (element.compareTo(node.getData()) > 0) {
				returnNode = search((BSTNode<T>) node.getRight(), element);

			} else {
				returnNode = search((BSTNode<T>) node.getLeft(), element);
			}
		}
		return returnNode;
	}

	public void insert(T element) {
		if (element != null) {
			this.insert(this.getRoot(), element);
		}
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			this.setNewNode(node, element);

		} else if (node.getData().compareTo(element) < 0) {
			this.insert((BSTNode<T>) node.getRight(), element);

		} else if (node.getData().compareTo(element) > 0) {
			this.insert((BSTNode<T>) node.getLeft(), element);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setNewNode(BSTNode<T> node, T element) {
		node.setParent(node.getParent());
		node.setData(element);
		node.setLeft(new BSTNode.Builder().parent(node).build());
		node.setRight(new BSTNode.Builder().parent(node).build());
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(getRoot());
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		BSTNode<T> returnNode = null;
		if (!node.isEmpty()) {
			if (node.isLeaf() || node.getRight().isEmpty()) {
				returnNode = node;
			} else {
				returnNode = maximum((BSTNode<T>) node.getRight());
			}
		}
		return returnNode;
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(getRoot());
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		BSTNode<T> returnNode = null;
		if (!node.isEmpty()) {
			if (node.isLeaf() || node.getLeft().isEmpty()) {
				returnNode = node;
			} else {
				returnNode = minimum((BSTNode<T>) node.getLeft());
			}

		}
		return returnNode;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> returnNode = null;
		BSTNode<T> node = this.search(element);

		if (!node.isEmpty()) {
			if (node.getRight().isEmpty()) {
				returnNode = sucessor(node);

			} else {
				returnNode = minimum((BSTNode<T>) node.getRight());
			}
		}
		return returnNode;

	}

	private BSTNode<T> sucessor(BSTNode<T> node) {

		BSTNode<T> returnNode = null;
		if (node.getParent() != null) {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			if (node.getData().compareTo(parent.getData()) > 0) {
				returnNode = sucessor(parent);
			} else {
				returnNode = parent;
			}
		}
		return returnNode;

	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> returnNode = null;
		BSTNode<T> node = this.search(element);

		if (!node.isEmpty()) {
			if (node.getLeft().isEmpty()) {
				returnNode = predecessor(node);

			} else {
				returnNode = maximum((BSTNode<T>) node.getLeft());
			}
		}
		return returnNode;
	}

	private BSTNode<T> predecessor(BSTNode<T> node) {
		BSTNode<T> returnNode = null;

		if (node.getParent() != null) {
			BSTNode<T> parent = ((BSTNode<T>) node.getParent());
			if (node.getData().compareTo(parent.getData()) < 0) {
				returnNode = predecessor(parent);
			} else {
				returnNode = parent;
			}
		}
		return returnNode;
	}

	@Override
	public void remove(T element) {
		if (this.size() == 1) {
			this.root = new BSTNode<T>();
		} else {
			BSTNode<T> node = this.search(element);
			this.remove(node);
		}

	}

	private void remove(BSTNode<T> node) {
		if (!node.isEmpty()) {

			if (!node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
				BSTNode<T> newNode = sucessor(node.getData());
				node.setData(newNode.getData());
				remove(newNode);
			} else {
				BSTNode<T> newNode = (BSTNode<T>) node.getLeft();

				if (newNode.isEmpty()) {
					newNode = (BSTNode<T>) node.getRight();
				}
				node.setData(newNode.getData());
				node.setRight(newNode.getRight());
				node.setLeft(newNode.getLeft());

				if (!node.isEmpty() && node.getRight() != null) {
					node.getRight().setParent(node);
				}
				if (!node.isEmpty() && node.getLeft() != null) {
					node.getLeft().setParent(node);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrder() {
		T[] returnArray = (T[]) new Comparable[this.size()];
		preOrder(this.getRoot(), returnArray);

		return returnArray;
	}

	private void preOrder(BSTNode<T> node, T[] returnArray) {
		if (!node.isEmpty()) {
			addElement(returnArray, node.getData());
			preOrder((BSTNode<T>) node.getLeft(), returnArray);
			preOrder((BSTNode<T>) node.getRight(), returnArray);
		}
	}

	private void addElement(T[] returnArray, T data) {
		int i = 0;
		while (returnArray[i] != null) {
			i++;
		}

		returnArray[i] = data;

	}

	@Override
	public T[] order() {
		T[] returnArray = (T[]) new Comparable[this.size()];
		order(this.getRoot(), returnArray);

		return returnArray;
	}

	private void order(BSTNode<T> node, T[] returnArray) {
		if (!node.isEmpty()) {
			order((BSTNode<T>) node.getLeft(), returnArray);
			addElement(returnArray, node.getData());
			order((BSTNode<T>) node.getRight(), returnArray);
		}
	}

	@Override
	public T[] postOrder() {
		T[] returnArray = (T[]) new Comparable[this.size()];
		postOrder(this.getRoot(), returnArray);

		return returnArray;
	}

	private void postOrder(BSTNode<T> node, T[] returnArray) {
		if (!node.isEmpty()) {
			postOrder((BSTNode<T>) node.getLeft(), returnArray);
			postOrder((BSTNode<T>) node.getRight(), returnArray);
			addElement(returnArray, node.getData());
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
