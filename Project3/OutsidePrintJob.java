import java.util.*;
public class OutsidePrintJob extends PrintJob{
	double cost = 0.0;
	public OutsidePrintJob(String name, int pri, int pages){
		super(name,pri,pages);
		cost = pages*.1;
	}
	public String toString(){
		return super.toString() + ", Cost: $" + cost;
	}
}