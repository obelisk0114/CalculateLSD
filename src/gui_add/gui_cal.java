package gui_add;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.StringTokenizer;
import java.util.List;
import java.util.LinkedList;

public class gui_cal {
	final int width = 600;
	final int height = 500;
	
	private JFrame mainFrame;
	private JPanel Display;
	private JPanel Control;
	private JLabel Result;
	private JTextField input, degree_of_freedom;
	private JButton enter;
	private Font font = new Font("Time", Font.BOLD, 20);
	
	private double[] t_95 = { 6.3137515, 2.9199856, 2.3533634, 2.1318468,
			2.0150484, 1.9431803, 1.8945786, 1.859548, 1.8331129,
			1.8124611, 1.7958848, 1.7822876, 1.7709334, 1.7613101,
			1.7530504, 1.7458837, 1.7396067, 1.7340636, 1.7291328,
			1.7247182, 1.7207429, 1.7171444, 1.7138715, 1.7108821,
			1.7081408, 1.7056179, 1.7032884, 1.7011309, 1.699127, 1.6972609 };
	
	public gui_cal(String title) {
		mainFrame = new JFrame(title);
		Display = new JPanel();
		Control = new JPanel();
		Result = new JLabel();
		Result.setFont(font);
		degree_of_freedom = new JTextField("パ", 5);
		degree_of_freedom.addActionListener(new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				String numberLine = input.getText();
				try {
					stat_calculate(numberLine);
				} catch (NumberFormatException ex) {
					Result.setText("块计");
				}
			}
		});
		input = new JTextField("フだ筳计", 30);
		input.addActionListener(new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				String numberLine = input.getText();
				try {
					stat_calculate(numberLine);
				} catch (NumberFormatException ex) {
					Result.setText("块计");
				}
			}
		});
		enter = new JButton("块");
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numberLine = input.getText();
				try {
					stat_calculate(numberLine);
				} catch (NumberFormatException ex) {
					Result.setText("块计");
				}
			}
		});
	}

	public void stat_calculate (String aLine) {
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
		
		double t = 0;
		try {
			t = t_95[Integer.parseInt(degree_of_freedom.getText()) - 1];
		} catch (NumberFormatException ex) {
			t = t_95[numberSave.size() - 1];
			degree_of_freedom.setText("计ヘ-1");
		}
		double LSD = t * Math.sqrt(2 * MSE / numberSave.size());
		
		String displayLine = "<html> 羆㎝ : " + sum + "<br />" + "キА : " + avg
				+ "<br />" + "population variance : " + population_variance
				+ "<br />" + "sample variance : " + sample_variance + "<br />"
				+ "population standard deviation : "
				+ population_standard_deviation + "<br />"
				+ "sample standard deviation : " + sample_standard_deviation
				+ "<br />" + "Mean squared error : " + MSE + "<br />"
				+ "LSD : " + LSD + "</html>";
		Result.setText(displayLine);
	}
	
	public void launchFrame() {
		mainFrame.setLocation(180, 80);
		mainFrame.setSize(width, height);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.add(Display,BorderLayout.CENTER);
		Display.add(Result);
		
		Control.setLayout(new FlowLayout());
		Control.add(degree_of_freedom);
		Control.add(input);
		Control.add(enter);
		Control.setVisible(true);
		
		mainFrame.add(Control, BorderLayout.SOUTH);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		gui_cal guiWindow = new gui_cal("参璸ㄧ计    ---  by Ting-Ching Chou   ---");
		guiWindow.launchFrame();

	}

}
