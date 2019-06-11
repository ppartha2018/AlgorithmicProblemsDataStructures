package com.prasanna.practice;

/*
 * 
 * Implentation of sorted ciruclar linked list.
 */
class SCLL{
	SCLL left = null;
	SCLL right = null;
	int content;
	
	SCLL(int c){
		this.content = c;
	}
	
}

public class SortedCircularLL {
	
	SCLL root = null;
	
	public void insertElement(SCLL node, int c) {
		if(node.right == root) {
			//last element
			node.right = new SCLL(c);
			node.right.left = node;
			node.right.right = root;
		}else if(c < node.right.content) {
			SCLL newNode = new SCLL(c);
			newNode.left = node;
			SCLL temp = node.right;
			newNode.right = temp;
			node.right = newNode;
			
		}else { //c >= node.right.content
			insertElement(node.right, c);
		}
	}
	
	public void insertElement(int c) {
		if(root == null) {
			root = new SCLL(c);
			root.left = root;
			root.right = root;
		}else if(c < root.content) {
			SCLL newNode = new SCLL(c);
			SCLL temp = root.left;
			newNode.left = temp;
			newNode.right = root;
			root = newNode;
		}else {
			insertElement(root, c);
		}
	}
	
	public void traveseList() {
		SCLL currNode = root;
		do {
			System.out.print(currNode.content + " -> ");
			currNode = root.right;
		} while(currNode != root);
		System.out.print(currNode.content);
	}
	
	public static void main(String[] args) {
		SortedCircularLL sc = new SortedCircularLL();
		sc.insertElement(5);
		sc.insertElement(34);
		sc.insertElement(2);
		sc.insertElement(98);
		sc.insertElement(76);
		sc.insertElement(9);
		
		sc.traveseList();
	}

}
