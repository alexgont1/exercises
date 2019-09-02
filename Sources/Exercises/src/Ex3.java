public class Ex3 {

	// generate array with random numbers (1-1000)
	public static int[] generator(int s) {
		int data[] = new int[s];
		for (int i = 0; i < s; i++) {
			data[i] = (int) (Math.random() * 1000 + 1);
		}
		return data;
	}

	// find the smallest number, its index and print them
	public static void smallest(int data[]) {

		int min = data[0];
		int ind = 0;
		for (int i = 1; i < data.length; i++) {
			if (data[i] < min) {
				min = data[i];
				ind = i;
			}
		}

		System.out.println("The smallest number is " + min + ", at position " + ind);
	}

	public static void main(String[] args) {

		// choose array size and generate it
		int x[] = generator(500);
		smallest(x);
	}

}
