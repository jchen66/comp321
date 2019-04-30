import java.util.Stack;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class GuessTheDataStructure {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Stack<Integer> stack= new Stack<Integer>();
		Queue<Integer> queue= new LinkedList<Integer>();
		PriorityQueue<Integer> priorityQ= new PriorityQueue<Integer>(Collections.reverseOrder());
		
		String cmdStr;
		while ((cmdStr = br.readLine()) != null) {
			//clear Data Structures
			stack.clear();
			queue.clear();
			priorityQ.clear();
			
			//define as true until proven that it is not that data structure
			boolean isStack=true;
			boolean isQueue=true;
			boolean isPriorityQueue=true;
			
			//determine how many lines is needed to parse for this test case 
			int n= Integer.parseInt(cmdStr);
			for(int i=1; i<=n; i++) {
				cmdStr = br.readLine();
				String[] cmds=cmdStr.split(" ");
				int num= Integer.parseInt(cmds[1]);
				
				// if cmd[0]=1 --> insert in all the
				if(cmds[0].equals("1")) {
					stack.add(num);
					queue.add(num);
					priorityQ.add(num);
				}
				// if cmd[0]=2 --> pop
				// check and decide if its a queue, stack or priority queue
				else if(cmds[0].equals("2")){
					//if we already know that it is not of that data structure--> no need to simulate it anymore
					//if the data structures are of size 0 --> can't pop... i.e its not the correct data structure
					//if the pop//poll number is not the same as whats given --> then its false
					if(isStack & (stack.size()==0 || stack.pop()!=num)) {
						isStack=false;
					}
					if(isQueue && (queue.size()==0 || queue.poll()!=num)) {
						isQueue=false;
					}
					if(isPriorityQueue && (priorityQ.size()==0 || priorityQ.poll()!=num)) {
						isPriorityQueue=false;
					}
				}
				
				
			}
			
			//print out the data structure possibilities for this test case
			//if its all true--> impossible as stack != queue --> means that there were no command "2" taken
			if(isStack && !isQueue && !isPriorityQueue) {
				System.out.println("stack");
			}
			else if(!isStack && isQueue && !isPriorityQueue) {
				System.out.println("queue");
			}
			else if(!isStack && !isQueue && isPriorityQueue) {
				System.out.println("priority queue");
			}
			else if((isStack && isQueue) || (isStack && isPriorityQueue) || (isQueue && isPriorityQueue) ) {
				System.out.println("not sure");
			}
			else {
				System.out.println("impossible");
			}
			
		}
		
	}
}
