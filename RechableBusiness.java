package com.prasanna.practice;
import java.util.*;

class Business{
	String name;
	Map<Business, Integer> reachableBusinesses = new HashMap<Business, Integer>();
	int distance = 0;
	
	Business(String n){
		this.name = n;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<Business, Integer> getReachableBusinesses() {
		return reachableBusinesses;
	}
	public void setReachableBusinesses(Map<Business, Integer> reachableBusinesses) {
		this.reachableBusinesses = reachableBusinesses;
	}
	
	
}
public class RechableBusiness {
	
	public void dfsUtil(Business b, int distance, List<String> result, HashMap<String, Boolean> visited) {
		visited.put(b.getName(), true);
		
		Map<Business, Integer> edges = b.getReachableBusinesses();
		if(edges != null) {
			for(Business b1: edges.keySet()) {
				int d = edges.get(b1);
				if(d < distance) {
					result.add(b1.getName());
					if(visited.get(b1.getName()) == null || !visited.get(b1.getName()))
						dfsUtil(b1, distance - d, result, visited);
				}
				else if(d == distance) {
					result.add(b1.getName());
				}
			}
		}
	}
	
	public List<String> getRecheableBusiness(int distance, Business startingBusiness){
		HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
		List<String> result = new ArrayList<String>();
		visited.put(startingBusiness.getName(), true);
		dfsUtil(startingBusiness, distance, result, visited);
		return result;
	}
	/*
	 *               A
	 *     		   4/ \4
	 * 	  		   /   \	
	 * 			  B    C
	 *          2/
	 *          /
	 *         D 
	 */
	public static void main(String[] args) {
		RechableBusiness rb = new RechableBusiness();
		Business startingBusiness = new Business("A");
		Business B = new Business("B");
		B.reachableBusinesses.put(new Business("D"), 5);
		startingBusiness.reachableBusinesses.put(B, 2);
		startingBusiness.reachableBusinesses.put(new Business("C"),4);
		
		System.out.println(rb.getRecheableBusiness(5, startingBusiness));
	}
}
