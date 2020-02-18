import java.util.*;
public class PrintJob implements Comparable<PrintJob>{
	int pages,user_priority,priority = 0;
	String name = "";
	public PrintJob(String n, int pri, int p){
		name = n;
		pages = p;
		user_priority = pri;
		priority = pages * user_priority;
	}
	@Override
	public int compareTo(PrintJob other){
		return Integer.compare(this.priority, other.priority);
	}
	public String toString(){
		return "Name: " + name + ", User Priority: " + user_priority + ", Pages: " + pages;
	}
}