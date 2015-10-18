
public class Loopy {
	public static void main(String[] args) {
		int x = 1;
		System.out.println("Before the loop");
		
		int sum = 0;
		while (x <= 100) {
			sum = sum + x;
			x = x +1;
		}
		
		System.out.println("The value of sum is: " + sum);
		
		System.out.println("This is after the loop");
	}

}