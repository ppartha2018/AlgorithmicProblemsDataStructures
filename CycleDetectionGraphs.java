import java.util.*;import javax.print.CancelablePrintJob;

public class CycleDetectionGraphs {
	//detecting cycles in the graph
    //while performing dfs, if the same element in the stack is visited again, declare cycle is detected
    public boolean dfs_iterative_util(LinkedList<Integer>[] graph, boolean[] visited, int s){
        Stack<Integer> fringe = new Stack<Integer>();
        Set<Integer> currStack = new HashSet<Integer>();
        
        fringe.push(s);
        currStack.add(s);
        
        while(!fringe.isEmpty()){
            int curr = fringe.pop();
            if(currStack.contains(curr))
                return false;
            visited[curr] = true;
            currStack.add(curr);
            if(graph[curr] != null){
                Iterator<Integer> itr = graph[curr].iterator();
                while(itr.hasNext()){
                    int n = itr.next();
                    
                    if(!visited[n]){
                        fringe.push(n);
                    }
                }
                currStack.remove(curr);
            }
           
        }
        
        return true;
    }
    
    //if cycle present return false
    public boolean canFinishUtil(LinkedList<Integer>[] graph,int i,boolean[] recStack, boolean[] visited) {
    	if(recStack[i])
    		return false;
    	
    	visited[i] = true;
    	recStack[i] = true;
    	
    	if(graph[i] != null){
    		Iterator<Integer> itr = graph[i].iterator();
    		while(itr.hasNext()) {
    			int n = itr.next();
    			if(!canFinishUtil(graph, n, recStack, visited))
    				return false;
    		}
    	}
    	
    	recStack[i] = false;
    	return true;
    }
    
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //edge case
        if(numCourses == 1)
            return true;
        
        LinkedList<Integer>[] adjacencyList = new LinkedList[numCourses];
        //adding edges of the directed graph
        for(int i=0; i < prerequisites.length; i++){
            if(adjacencyList[prerequisites[i][0]] == null){
                LinkedList<Integer> edges = new LinkedList<Integer>();
                edges.add(prerequisites[i][1]);
                adjacencyList[prerequisites[i][0]] = edges;
            } else{
                adjacencyList[prerequisites[i][0]].add(prerequisites[i][1]);
            } 
        }
        
        
        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];
        
        //call dfs_iterative for all vertices
        for(int i=0; i < numCourses; i++){
            if(!visited[i]){
               /*if(!dfs_iterative_util(adjacencyList, visited, i)){
                    return false;
                }*/
            	if(!canFinishUtil(adjacencyList, i, recStack, visited)) {
            		return false;
            	}
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
    	CycleDetectionGraphs cg = new CycleDetectionGraphs();
    	int[][] edges = new int[3][3];
    	edges[0][0] = 0;
    	edges[0][1] = 1;
    	
    	edges[1][0] = 0;
    	edges[1][1] = 2;
    	
    	edges[2][0] = 1;
    	edges[2][1] = 2;
    	
    	System.out.println(cg.canFinish(3, edges));
	}

}
