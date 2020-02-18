public static int sum(int arr[], int i){
	if(i == arr.length){
		return 0;
	}
		return arr[i]+sum(arr, i+2);
}
public static int sum2(int arr[], int left, int right)
{
	if(left == right){
		return arr[left];
	}
	else{
		int center = (left + right)/2
		int leftsum = sum2(arr,left,center);
		int rightsum = sum2(arr,center+1,right);
		return leftsum+rightsum;
	}
}
public class sumClass{
	public static void main(String args[]){
	int arr[] = {1,2,3,4,5,};
	int mysum = sum(arr,0);
	sysout(mysum);
	int mysum2 = sum2(arr,)
	}
}


Pair<string> pair = new Pair<String>;
pair.setOne("Hello");
pair.setTwo("World");
String one = pair.getFirst();
String two = pair.getSecond();
System.out.println(one + " " + two);



public void reverse(){
	if (size<2){
		return;
	}
	else{
		Node p1 = null; 
		NOde p2 = head;
		Node p3 = head.next;
		while(p3!=null){
			p2.next= p1;
			p1 =p2;
			p2=p3
			p3=p3.next;

		}
		p2.next = p1;
		Node temp;
		temp = head;
		head = tail;
		tail = temp;
	}
}
@Override
public String toString(){
	StringBuilder SB = new StringBuilder("[ ");
	Node p = head;
	while(p != null){
		SB.append(p.value + " ");
		p = p.next;
	}
	SB.append(" ]");
	return new String(sb);
}