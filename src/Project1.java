/**********************************************************
 * CS241 - Project 1: Binary Search Tree
 * Author:	KANLUE ZHANG
 * Date:	04/21/2014
 *
 * File II: Project1.java 
 * (Tests unbalanced Binary Search Tree.)
 *
 *********************************************************/

import java.io.*;
import java.util.*;


public class Project1 {

	public static void main(String[] args) throws IOException {

		BST<Integer> bst = new BST<Integer>(); 

		InputStreamReader instr = new InputStreamReader(System.in);
		BufferedReader stdin = new BufferedReader(instr);
		StringTokenizer stok;
		int num = 0;
		String value;
		String cmd = null;

		System.out.println();

		System.out.println("Please enter the initial sequence of values: (separated by a space) ");
		value = stdin.readLine();
		stok = new StringTokenizer(value);
		while (stok.hasMoreTokens()) {
			num = Integer.parseInt(stok.nextToken());
			bst.insert(num);
		}

		System.out.print("Pre-order:\t");
		bst.preOrderTraversal();
		System.out.println();
		System.out.print("In-order:\t");
		bst.inOrderTraversal();
		System.out.println();
		System.out.print("Post-order:\t");
		bst.postOrderTraversal();
		System.out.println();

		/*********************************************************
		 * Please enter the initial sequence of values: 
		 * I: 51 29 68 90 36 40 22 59 44 99 77 60 27 83 15 75 3
		 * II: 12 20 6 13 16 24 5 3 11 20
		 *********************************************************/

		// Interacting with user
		boolean exitFlg = false;
		System.out.println();

		do {
			System.out.print("Command? ");
			
			value = null;
			value = stdin.readLine();
			stok = new StringTokenizer(value);
			num = 0;
			cmd = null;			
			if (stok.hasMoreTokens()){
				cmd = stok.nextToken();
				if(stok.hasMoreTokens()) num = Integer.parseInt(stok.nextToken());
			}
			
			if (cmd.equals("E") || cmd.equals("e")) {
				exitFlg = true;
				System.out.println("Thank you for using! Bye.");
				break;
			}

			if (cmd.equals("H") || cmd.equals("h")) { // help menu
				System.out.println();
				System.out.println("I	Insert a value");
				System.out.println("D  	Delete a value");
				System.out.println("P	Find predecessor");
				System.out.println("S	Find successor");
				System.out.println("E	Exit the program");
				System.out.println("H	Display this message");
				System.out.println();
			} else if (cmd.equals("I") || cmd.equals("i")) {
				bst.insert(num);

				System.out.print("Pre-order:\t");
				bst.preOrderTraversal();
				System.out.println();
				System.out.print("In-order:\t");
				bst.inOrderTraversal();
				System.out.println();
				System.out.print("Post-order:\t");
				bst.postOrderTraversal();
				System.out.println("\n");

			} else if (cmd.equals("D") || cmd.equals("d")) {
				bst.delete(num);

				System.out.print("Pre-order:\t");
				bst.preOrderTraversal();
				System.out.println();
				System.out.print("In-order:\t");
				bst.inOrderTraversal();
				System.out.println();
				System.out.print("Post-order:\t");
				bst.postOrderTraversal();
				System.out.println("\n");
				
			} else if (cmd.equals("P") || cmd.equals("p")) {
				System.out.println("The Predecessor of  " + num + " is " + bst.predecessor(num) + "\n");

			} else if (cmd.equals("S") || cmd.equals("s")) {
				System.out.println("The Successor of " + num + " is "	+ bst.successor(num) + "\n");

			} else {
				System.out.println("Please enter a valid command. ('H'- help)");

			}

		} while (!exitFlg); // Loop until receiving 'E - exit' input from the user.

	
		/* -------------testing----------------------
		 * Integer[] a = {18,2,55,73,16,9,11,20};
		 * 
		 * BST<Integer> bst = new BST<Integer>(); 
		 * for(Integer n : a) bst.insert(n);
		 * 
		 * //testing Tree-Traversing 
		 * bst.preOrderTraversal();
		 * System.out.println(); 
		 * bst.inOrderTraversal(); 
		 * System.out.println();
		 * bst.postOrderTraversal(); 
		 * System.out.println();
		 * 
		 * //testing countLeaves 
		 * System.out.println("leavesCount = " + bst.countLeaves()); 
		 * //testing width 
		 * System.out.println("size = " + bst.size());
		 * 
		 * //testing minValue 
		 * System.out.println("min = " + bst.minValue());
		 * //testing width 
		 * System.out.println("max = " + bst.maxValue());
		 */


	}
	
	

	public static class BST<T extends Comparable<T>> {

		/*****************************************************
		 * 
		 * the BSTNode class
		 * 
		 ******************************************************/

		@SuppressWarnings("hiding")
		private class BSTNode<T> {
			private T key;
			private BSTNode<T> left, right;

			public BSTNode(T key, BSTNode<T> l, BSTNode<T> r) {
				left = l;
				right = r;
				this.key = key;
			}

			public BSTNode(T key) {
				this(key, null, null);
			}

			public String toString() {
				return key.toString();
			}
		} // end of BSTNode

		private BSTNode<T> root;

		private Comparator<T> comparator;

		public BST() {
			root = null;
			comparator = null;
		}

		public BST(Comparator<T> comp) {
			root = null;
			comparator = comp;
		}

		private int compare(T x, T y) {
			if (comparator == null)
				return x.compareTo(y);
			else
				return comparator.compare(x, y);
		}

		/*****************************************************
		 * 
		 * INSERT
		 * 
		 ******************************************************/
		public void insert(T key) {
			root = insert(root, key);
		}

		private BSTNode<T> insert(BSTNode<T> p, T toInsert) {
			if (p == null)
				return new BSTNode<T>(toInsert);

			// no duplicate allowed
			if (compare(toInsert, p.key) == 0)
				return p;

			if (compare(toInsert, p.key) < 0)
				p.left = insert(p.left, toInsert);
			else
				p.right = insert(p.right, toInsert);

			return p;
		}

		/*****************************************************
		 * 
		 * SEARCH
		 * 
		 ******************************************************/
		public boolean search(T toSearch) {
			return search(root, toSearch);
		}

		private boolean search(BSTNode<T> p, T toSearch) {
			if (p == null)
				return false;
			else if (compare(toSearch, p.key) == 0)
				return true;
			else if (compare(toSearch, p.key) < 0)
				return search(p.left, toSearch);
			else
				return search(p.right, toSearch);
		}

		/*****************************************************
		 * 
		 * DELETE
		 * 
		 ******************************************************/

		public void delete(T toDelete) {
			root = delete(root, toDelete);
		}

		private BSTNode<T> delete(BSTNode<T> p, T toDelete) {

			if (p == null) {
				System.out.println(toDelete + " doesn't exist! \n");
				return null;
			}
			else if (compare(toDelete, p.key) < 0)
				p.left = delete(p.left, toDelete);
			else if (compare(toDelete, p.key) > 0)
				p.right = delete(p.right, toDelete);
			else {
				if (p.left == null)
					return p.right;
				else if (p.right == null)
					return p.left;
				else {
					// get key from the rightmost BSTNode in the left subtree
					p.key = retrievekey(p.left);
					// delete the rightmost BSTNode in the left subtree
					p.left = delete(p.left, p.key);
				}
			}
			return p;
		}

		private T retrievekey(BSTNode<T> p) {
			while (p.right != null)
				p = p.right;

			return p.key;
		}

		/*************************************************
		 * 
		 * TRAVERSAL
		 * 
		 **************************************************/

		/**
		 * Prints the BSTNode keys in the "PREORDER" order. 
		 * @param r - root note of the subtree
		 */

		public void preOrderTraversal() {
			preOrderHelper(root);
		}

		private void preOrderHelper(BSTNode<T> r) {
			if (r != null) {
				System.out.print(r + " ");
				preOrderHelper(r.left);
				preOrderHelper(r.right);
			}
		}

		/**
		 * Prints the BSTNode keys in the "INORDER/SORTED" order. 
		 */

		public void inOrderTraversal() {
			inOrderHelper(root);
		}

		private void inOrderHelper(BSTNode<T> r) {
			if (r != null) {
				inOrderHelper(r.left);
				System.out.print(r + " ");
				inOrderHelper(r.right);
			}
		}

		/**
		 * Prints the BSTNode keys in the "POSTORDER" order. 
		 */

		public void postOrderTraversal() {
			postOrderHelper(root);
		}

		private void postOrderHelper(BSTNode<T> r) {
			if (r != null) {
				postOrderHelper(r.left);
				postOrderHelper(r.right);
				System.out.print(r + " ");
			}
		}

		/*************************************************
		 * 
		 * COPY
		 * 
		 **************************************************/

		public BST<T> copy() {
			BST<T> twin = null;

			if (comparator == null)
				twin = new BST<T>();
			else
				twin = new BST<T>(comparator);

			twin.root = copyHelper(root);
			return twin;
		}
		private BSTNode<T> copyHelper(BSTNode<T> p) {
			if (p == null)
				return null;
			else
				return new BSTNode<T>(p.key, copyHelper(p.left),
						copyHelper(p.right));
		}

		/*****************************************************
		 * 
		 * SUCCESSOR 
		 * 
		 * (The successor is the node that appears right 
		 * AFTER the given key in an in-order traversal.)
		 * 
		 * @param n -> root of the subtree
		 * @param x -> key entered
		 * @param p -> successor of the x
		 * 
		 ******************************************************/
		public T successor(T key) {
			BSTNode<T> n = succ(root, key, null);
			if (n != null) {
				return n.key;
			}
			return null;
		}

		private BSTNode<T> succ(BSTNode<T> n, T x, BSTNode<T> p) {
			if (n == null)
				return null;
			if (compare(x, n.key) < 0) {
				return succ(n.left, x, n);
			} else if (compare(x, n.key) > 0) {
				return succ(n.right, x, p);
			}
			if (n.right != null)
				return min(n.right);

			return p;
		}

		private BSTNode<T> min(BSTNode<T> t) {
			if (t.left == null) {
				return t;
			} else {
				return min(t.left);
			}
		}

		/*****************************************************
		 * 
		 * PREDECESSOR 
		 * 
		 * (The predecessor is the node that appears right 
		 * BEFORE the given key in an in-order traversal.)
		 * 
		 * @param n -> root of the subtree
		 * @param x -> key entered
		 * @param p -> predecessor of the x
		 * 
		 ******************************************************/

		public T predecessor(T key) {
			BSTNode<T> n = predec(root, key, null);
			if (n != null) {
				return n.key;
			}
			return null;
		}

		private BSTNode<T> predec(BSTNode<T> n, T x, BSTNode<T> p) {
			if (n == null)
				return null;
			if (compare(x, n.key) > 0) {
				return predec(n.right, x, n);
			} else if (compare(x, n.key) < 0) {
				return predec(n.left, x, p);
			}
			if (n.left != null)
				return max(n.left);

			return p;
		}

		private BSTNode<T> max(BSTNode<T> t) {
			if (t.right == null) {
				return t;
			} else {
				return max(t.right);
			}
		}

		/*************************************************
		 * 
		 * MISC
		 * 
		 **************************************************/

		/**
		 * Returns the number of BSTNodes in the tree.
		 */
		public int size() {
			return (size(root));
		}

		private int size(BSTNode<T> BSTNode) {
			if (BSTNode == null)
				return (0);
			else {
				return (size(BSTNode.left) + 1 + size(BSTNode.right));
			}
		}

		/**
		 * Returns the max root-to-leaf depth of the tree. 
		 */
		public int maxDepth() {
			return (maxDepth(root));
		}

		private int maxDepth(BSTNode<T> BSTNode) {
			if (BSTNode == null) {
				return (0);
			} else {
				int lDepth = maxDepth(BSTNode.left);
				int rDepth = maxDepth(BSTNode.right);
				// use the larger + 1
				return (Math.max(lDepth, rDepth) + 1);
			}
		}

		/**
		 * Returns the height leaf-to-root height of the tree. 
		 */
		public int height() {
			return height(root);
		}

		private int height(BSTNode<T> p) {
			if (p == null)
				return -1;
			else
				return 1 + Math.max(height(p.left), height(p.right));
		}

		public int countLeaves() {
			return countLeaves(root);
		}

		/**
		 * Returns the number of leaf BSTNotes in the tree.
		 */
		private int countLeaves(BSTNode<T> p) {
			if (p == null)
				return 0;
			else if (p.left == null && p.right == null)
				return 1;
			else
				return countLeaves(p.left) + countLeaves(p.right);
		}

		/**
		 * Returns the min key in a non-empty binary search tree. 
		 */
		public T minValue() {
			return (minValue(root));
		}
		private T minValue(BSTNode<T> BSTNode) {
			BSTNode<T> current = BSTNode;
			while (current.left != null) {
				current = current.left;
			}
			return (current.key);
		}

		/**
		 * Returns the max key in a non-empty binary search tree. 
		 */
		public T maxValue() {
			return (maxValue(root));
		}
		private T maxValue(BSTNode<T> BSTNode) {
			BSTNode<T> current = BSTNode;
			while (current.right != null) {
				current = current.right;
			}
			return (current.key);
		}

	}// end of BST


	/*

		 *****************************************************
		 * 
		 * the BSTNode class (with Key & Value)
		 * 
		 ******************************************************

		private class BSTNode<K, V> {
			private K key;
			private V value;
			private BSTNode<K, V> left, right;

			public BSTNode(K key, V value, BSTNode<K, V> l, BSTNode<K, V> r) {
				left = l;
				right = r;
				this.key = key;
				this.value = value;
			}

			public BSTNode(K key, V value) {
				this(key, value, null, null);
			}
			
			
			// **** methods ****
			... ...


	 	} // end of BSTNode


	*/	
	
	
	

}
