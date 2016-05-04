public class example{
	public static void main(String[] args){
		double[] deezNuts = new double[] {1.2, 1.3, 1.4, 1.7,1.8,2.1};
		int sum = 0;
		for (int i =0; i < 6; i++){
			sum += deezNuts[i];
		}
		System.out.println(sum);
		double aveg = sum/6;
		System.out.println(aveg);
	}
}