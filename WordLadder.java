package com.prasanna.practice;
import java.util.*;

/*
 * Leet Code - Word Ladder
 * Implementation with Trie data structure for pre-processing and Graph BFS.
 * 
 */
public class WordLadder {
	
	 public int ladderLength(String beginWord, String endWord, List<String> wordList) {
	        //construct the graphs and do bfs to find the shortest path to the goal
	        
	        //edge cases
	        if(wordList == null)
	            return 0;
	        if(wordList.size() == 0)
	            return 0;
	        
	        boolean found = false;
	        Map<Integer, Set<Character>> indexChars = new HashMap<Integer, Set<Character>>();
	        
	        //graph - adjacency list representation
	        //Map<String, List<String>> graph = new HashMap<String, List<String>>();
	        Map<String, Integer> dist = new HashMap<String, Integer>();
	        Map<String, Boolean> graph = new HashMap<String, Boolean>();
	        
	        graph.put(beginWord, false);
	        
	        for(String word: wordList){
	            //graph.put(word, new LinkedList<String>());
	            dist.put(word, Integer.MAX_VALUE);
	            graph.put(word, false);
	            if(endWord.equals(word))
	                found = true;
	            for(int i=0; i < word.length(); i++){
	                if(indexChars.get(i) == null){
	                	Set<Character> chars = new HashSet<Character>();
	                    chars.add(word.charAt(i));
	                    indexChars.put(i, chars);
	                } else{
	                	Set<Character> chars = indexChars.get(i);
	                    chars.add(word.charAt(i));
	                    indexChars.put(i, chars);
	                }
	            }
	        }
	        
	        //endWord not found
	        if(found == false)
	            return 0;
	        //for bfs
	        Queue<String> queue = new LinkedList<String>();
	        queue.add(beginWord);
	        dist.put(beginWord, 0);
	        //construct the graph
	        while(queue.size() > 0){
	            String word = queue.remove();
	            graph.put(word, true);
	            //generating the neighbors
	            for(int i = 0; i < word.length(); i++){
	                char curr = word.charAt(i);
	                Iterator<Character> itr = indexChars.get(i).iterator();
	                while(itr.hasNext()){
	                    char currChange = itr.next();
	                    char[] org = word.toCharArray();
	                    org[i] = currChange;
	                    String ss = new String(org);
	                    if(graph.containsKey(ss)){
	                        //graph.get(word).add(ss);
	                        //Relaxing step - bfs and shorted path
	                        if(dist.get(ss) > dist.get(word)+1)
	                            dist.put(ss, dist.get(word)+1);
	                        if(!graph.get(ss))
	                            queue.add(ss);
	                    }
	                }
	            }
	        }
	        
	        return dist.get(endWord) == Integer.MAX_VALUE ? 0 : dist.get(endWord)+1;
	    }
	
	public static void main(String[] args) {
		WordLadder wl = new WordLadder();
		//List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
		List<String> wordList = Arrays.asList("a","b","c");
		System.out.println(wl.ladderLength("a", "c", wordList));
	}

}
