package test_system;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.List;
import java.util.LinkedList;

public class Calculate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter all numbers");
		double[] t_95 = { 6.3137515, 2.9199856, 2.3533634, 2.1318468,
				2.0150484, 1.9431803, 1.8945786, 1.859548, 1.8331129,
				1.8124611, 1.7958848, 1.7822876, 1.7709334, 1.7613101,
				1.7530504, 1.7458837, 1.7396067, 1.7340636, 1.7291328,
				1.7247182, 1.7207429, 1.7171444, 1.7138715, 1.7108821,
				1.7081408, 1.7056179, 1.7032884, 1.7011309, 1.699127, 1.6972609 };
		String aLine = keyboard.nextLine();
		StringTokenizer st = new StringTokenizer(aLine);
		List<Double> numberSave = new LinkedList<Double>();
		while (st.hasMoreTokens()) {
			numberSave.add(Double.parseDouble(st.nextToken()));
		}
		
		double sum = 0;
		for (Double element: numberSave) {
			sum += element;
		}
		double avg = sum / numberSave.size();
		
		double square_sum = 0;
		for (Double element : numberSave) {
			square_sum += Math.pow(element - avg, 2);
		}
		
		double population_variance = square_sum / numberSave.size();
		double sample_variance = square_sum / (numberSave.size() - 1);
		double population_standard_deviation = Math.sqrt(population_variance);
		double sample_standard_deviation = Math.sqrt(sample_variance);
		double MSE = population_variance / numberSave.size();
		
		double t = t_95[numberSave.size() - 1];
		double LSD = t * Math.sqrt(2 * MSE / numberSave.size());

		System.out.println("總和 : " + sum);
		System.out.println("總數 : " + numberSave.size());
		System.out.println("平均 : " + avg);
		System.out.println("population variance : " + population_variance);
		System.out.println("sample variance : " + sample_variance);
		System.out.println("population standard deviation : "
				+ population_standard_deviation);
		System.out.println("sample standard deviation : "
				+ sample_standard_deviation);
		System.out.println("Mean squared error : " + MSE);
		System.out.println("LSD : " + LSD);

		keyboard.close();
	}

}
