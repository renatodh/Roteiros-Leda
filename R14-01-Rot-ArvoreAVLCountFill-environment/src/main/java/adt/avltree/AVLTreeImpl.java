package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int leftHeight = super.height((BSTNode<T>) node.getLeft());
		int rightHeight = super.height((BSTNode<T>) node.getRight());

		int balance = leftHeight - rightHeight;

		return balance;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = this.calculateBalance(node);

		if (balance > 1) {
			this.rebalanceLeft(node);
		} else if (balance < -1) {
			this.rebalanceRight(node);
		}
	}

	private void rebalanceRight(BSTNode<T> node) {
		int balanceRight = this.calculateBalance((BSTNode<T>) node.getRight());

		if (balanceRight < 0) {
			Util.leftRotation(node);
		} else if (balanceRight > 0) {
			Util.rightRotation((BSTNode<T>) node.getRight());
			Util.leftRotation(node);
		}

		if (this.getRoot().equals(node)) {
			this.root = (BSTNode<T>) node.getParent();
		}
	}

	private void rebalanceLeft(BSTNode<T> node) {
		int balanceLeft = this.calculateBalance((BSTNode<T>) node.getLeft());

		if (balanceLeft > 0) {
			Util.rightRotation(node);
		} else if (balanceLeft < 0) {
			Util.leftRotation((BSTNode<T>) node.getLeft());
			Util.rightRotation(node);
		}

		if (this.getRoot().equals(node)) {
			this.root = (BSTNode<T>) node.getParent();
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node != null) {
			this.rebalance(node);
			this.rebalanceUp((BSTNode<T>) node.getParent());
		}
	}
	
	@Override
	public void insert(T element) {
		super.insert(element);
		BSTNode<T> node = this.search(element);
		this.rebalanceUp(node);
	}

	
	@Override
	public void remove(T element) {
		if (element != null) {
			BSTNode<T> node = search(element);
			node = super.remove(node);
			this.rebalanceUp((BSTNode<T>) node.getParent());
			
		}

	}
}
