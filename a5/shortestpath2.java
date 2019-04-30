import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Vector;

public class shortestpath2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//since it doesn't say how many test cases the scanner has
		while(sc.hasNextLine()) {
			
			int n =sc.nextInt();   	//#nodes in the graph
			int m=sc.nextInt();		//#edges in the graph
			int q=sc.nextInt();		//#queries to parse through
			int s= sc.nextInt();	// index of the starting node
			//set up matrix for nodes/edges
//			int[][] graph= new int[n][n];
//			int[][] edges= new int[m][5];
			Node[] nodeDistances=new Node[n]; //stores the shortest time from s to each node
			if(n==0) {
				break;
			}
			
			//init nodes array
			for(int i=0;i<n;i++) {
				nodeDistances[i]=new Node(i);
			}
			
			//parse through edges to set up graph
			for(int i=0;i<m;i++) {
				int u=sc.nextInt();
				int v=sc.nextInt();
				int t0=sc.nextInt();
				int p=sc.nextInt();
				int d=sc.nextInt();
				
				int[] tuple= {u,v,t0,p,d};
				Edge e=new Edge(nodeDistances[v],t0,p,d);
				nodeDistances[u].neighbors.add(e);
			}
			//construct djikstraTable
			
			//init Time=0
			//we start at node s
			Node start=nodeDistances[s];
			start.bestTime=0;
			
			Comparator<Node> comp_node = new Comparator<Node>() {
	            @Override
	            public int compare(Node n1, Node n2) {
	                return Integer.compare(n1.bestTime, n2.bestTime);
	            }
	        };
	        
	        PriorityQueue<Node> graph_q= new PriorityQueue<>(comp_node);
	        graph_q.add(start);
	        
	        while (!graph_q.isEmpty()) {
	        	
	        	//poll -->  return null if empty + remove operations
	        	//pop --> throw + remove operations
	        	Node u=graph_q.poll();
	        	
	        	//check for neighbor nodes reachable
	        	for(Edge e: u.neighbors) {
	        		int currDist= e.t0+e.d;
	        		//
	        		if(u.bestTime >e.t0) {
	        			//if e.p is 0 --> edge can only be used at t0 and never again
	        			// therefore --> any nodes that are not s should be set the infinity
	        			if(e.p==0) {
	        				currDist=Integer.MAX_VALUE;
	        			}
	        			//find next possible time to traverse
	        			else {
	        				int tmp= ((u.bestTime-e.t0)-1)/e.p; 
	        				int steps=1+tmp;
	        				
	        				currDist=e.t0+(steps*e.p)+e.d;
	        			}
	        			
	        		}
	        		
	        		Node v=e.v;
	        		//update the best time for each node
	        		if(currDist<v.bestTime) {
	        			graph_q.remove(v);
	        			v.bestTime=currDist;
	        			v.prev=u;
	        			graph_q.add(v);
	        		}
	        	}
	        }
	        //System.out.println("finished finding optimized path!");
			
			
			//set up query
	        for(int i=0;i<q;i++) {
	        	int query=sc.nextInt();
	        	
	        	int dist=nodeDistances[query].bestTime;
	        	
	        	if(dist!=Integer.MAX_VALUE) {
	        		System.out.println(dist);
	        	}
	        	else {
	        		System.out.println("impossible");
	        	}
	        }
	        System.out.println("");
		}
		//end
	}
	
	
}

class Node {
	int index;
    Vector<Edge> neighbors;
    int bestTime;
    Node prev;

    Node(int index) {
        this.index = index;
        this.neighbors = new Vector<>();
        this.bestTime = Integer.MAX_VALUE;
        this.prev= null;
    }
}
class Edge {
    Node v;
    int t0;
    int p;
    int d;
    
    Edge(Node v,int t0,int p,int d) {
        this.v=v;
        this.t0= t0;
        this.p= p;
        this.d= d;
    }
}

