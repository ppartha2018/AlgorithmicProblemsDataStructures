package com.prasanna.practice;

import java.util.HashMap;
import java.util.Map;

/*
 * Contacts insertion and lookup in linear time with Trie data structure.
 * Hackerrank, contacts lookup. 
 * 
 */
public class ContactsTrieHashMap {

	static class TrieNode {
		boolean endOfWord;
		Map<Character, TrieNode> children = null;

		TrieNode() {
			endOfWord = false;
			children = new HashMap<Character, TrieNode>();
		}
	}

	static TrieNode root;

	static void insert(String key) {
		TrieNode curr = root;
		for (int i = 0; i < key.length(); i++) {
			char c = key.charAt(i);
			if (curr.children.get(c) == null) {
				TrieNode child = new TrieNode();
				curr.children.put(c, child);
				curr = child;
			} else {
				curr = curr.children.get(c);
			}
		}
		curr.endOfWord = true;
	}

	static boolean searchFullKey(String key) {
		char c;
		TrieNode curr = root;
		for (int i = 0; i < key.length(); i++) {
			c = key.charAt(i);
			if (curr.children.get(c) == null) {
				return false;
			}
			curr = curr.children.get(c);
		}

		return (curr.endOfWord) ? true : false;
	}

	static int findPartialKeys(String partialKey) {
		char index;
		TrieNode curr = root;
		for (int i = 0; i < partialKey.length(); i++) {
			index = partialKey.charAt(i);
			if (curr.children.get(index) == null) {
				return 0;
			}
			curr = curr.children.get(index);
		}

		// count all the subtrees that ends with endofWord

		return countEndOfWords(curr);
	}

	static int countEndOfWords(TrieNode curr) {
		int count = 0;
		if (curr.endOfWord)
			count++;

		for (Map.Entry<Character, TrieNode> entry : curr.children.entrySet()) {
			count = count + countEndOfWords(entry.getValue());
		}

		return count;
	}

	public static void main(String[] args) {
		root = new TrieNode();
		ContactsTrieHashMap.insert("hack");
		ContactsTrieHashMap.insert("hacker");
		ContactsTrieHashMap.insert("hackgod");
		ContactsTrieHashMap.insert("hackman");
		ContactsTrieHashMap.insert("hackgg");
		ContactsTrieHashMap.insert("hackzz");
		ContactsTrieHashMap.insert("hackz");
		// System.out.println(ContactsWithTrie.searchFullKey("hack"));
		// System.out.println(ContactsWithTrie.searchFullKey("prasanna"));
		// System.out.println(ContactsWithTrie.searchFullKey("hacker"));
		System.out.println(ContactsTrieHashMap.countEndOfWords(root));
	}
}
