import java.util.HashMap;
import java.util.Scanner;

public class addingwords {
	public static void main(String[] args) {
		String u="unknown";
		//use 2 hashmaps because java doesn't have dicitonnaries
		//if iterate through hashmap and search by value instead of key --> might take long if hashmap is big
		HashMap<String,Integer> search_by_key_d= new HashMap<String, Integer>();
		HashMap<Integer,String> search_by_val_d= new HashMap< Integer, String>();
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		//parse through the def/calculations
		while(sc.hasNextLine()) {
			// get line and retrieve the words
			String cmd= sc.nextLine();
			String[] commands=cmd.split(" ");
			String cmd_type= commands[0];
			
			switch(cmd_type) {
			case "def":
				// [1]: var   [2]: value
				String key=commands[1];
				int val= Integer.parseInt(commands[2]);
				//if already contains key --> overwrite
				//update the second dictionnary too!
//				search_by_key_d.put(key,val);
//				search_by_val_d.put(val,key);
				
				// doesn't need to replace! it does it automatically
				if(search_by_key_d.containsKey(key) == false) {
					search_by_key_d.put(key,val);
					search_by_val_d.put(val,key);
				}
				else {
					//remove previous value
					int prevval=search_by_key_d.get(key);
					search_by_key_d.remove(key,prevval);
					search_by_val_d.remove(prevval,key);
					//System.out.println(""+key+": "+val);
					//System.out.println("redefined");
					search_by_key_d.put(key,val);
					search_by_val_d.put(val,key);
				}
				//System.out.println(""+key+":"+val);
				
				break;
				
			case "calc":
				// [1]: var1     [2]: operation symbol     [3]: var2
				//.get(key) returns null if no value for key
				int ans=0;
				boolean isPrevAdd=true;
				boolean isUnknown=false;
				for(int i=1;i<commands.length;i++) {
					//System.out.println(""+i+": "+commands[i]);
					if(commands[i].equals("=")) {
						break;
					}
					else if(commands[i].equals("+")) {
						//System.out.println(commands[i]+"has +");
						isPrevAdd=true;
					}
					else if(commands[i].equals("-")) {
						//System.out.println(commands[i]+"has +");
						isPrevAdd=false;
					}
					else {
						//get value
						//System.out.println("looking for:"+commands[i]);
						if(search_by_key_d.get(commands[i])!=null) {
							int tmp_val=search_by_key_d.get(commands[i]);
							if(isPrevAdd) {
								ans+=tmp_val;
							}
							else {
								//is substraction
								ans-=tmp_val;
							}
						}
						//if value doesn't exist --> print false
						else {
							isUnknown=true;
							break;
						}
					}
				}
				if (!isUnknown) {
					//System.out.println(ans);
					if(search_by_val_d.containsKey(ans)) {
						System.out.println(cmd.replaceAll("calc ", "") +" "+search_by_val_d.get(ans));
					}
					else {
						System.out.println(cmd.replaceAll("calc ", "") +" "+u);
					}
					
				}
				else {
					System.out.println(cmd.replaceAll("calc ", "") +" "+u);
				}
				
				//assuming the operands are either + or - ==> otherwise add if statements
				
				break;
				
			case "clear":
				//reset everything from hashmap/dictionnaries
				search_by_key_d.clear();
				search_by_val_d.clear();
				break;
				
			default: 
				break;
			}
		}
		
	}
}
