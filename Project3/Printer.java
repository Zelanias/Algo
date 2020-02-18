import java.util.*;
import java.io.*;
public class Printer{
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(new File("input.txt"));
		ArrayList<PrintJob> ls = new ArrayList<> ();
		while(sc.hasNext()){
			String line = sc.nextLine();
			String[] ob = line.split(Character.toString((char)9));
			if(ob[3].equals("I")){
				PrintJob temp = new PrintJob(ob[0],Integer.parseInt(ob[1]),Integer.parseInt(ob[2]));
				ls.add(temp);
			}else{
				OutsidePrintJob temp = new OutsidePrintJob(ob[0],Integer.parseInt(ob[1]),Integer.parseInt(ob[2]));
				ls.add(temp);
			}
			
		}
		int size = ls.size();
		BinaryHeap<PrintJob> heap = new BinaryHeap<PrintJob>(size);
		while(!ls.isEmpty()){
			heap.insert(ls.get(0));
			ls.remove(0);
		}
		while(!heap.isEmpty()){
			PrintJob temp = heap.deleteMin();
			System.out.println(temp);
		}
	}	
}