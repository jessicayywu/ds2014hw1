import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class PolynomialCalculator extends JApplet implements ActionListener {
	/** Create Components
	========================================================= */
	
	/** Menu Bar
	--------------------------------- */
	JMenuItem jmiViewHelp = new JMenuItem("View Help", 'H');
	JMenuItem jmiAbout = new JMenuItem("About", 'A');
	
	/** Polynomial A
	--------------------------------- */
	private JLabel jlPolyA = new JLabel("多項式(A=)");
	private JTextField jtfPolyA = new JTextField();
	
	/** Polynomial B
	--------------------------------- */
	private JLabel jlPolyB = new JLabel("多項式(B=)");
	private JTextField jtfPolyB = new JTextField();	
	
	/** Buttons
	--------------------------------- */
	private JButton jbtAdd = new JButton("+");
	private JButton jbtSubtract = new JButton("-");
	private JButton jbtClear = new JButton("Clear");
	
	/** Result
	--------------------------------- */
	private JLabel jlResult = new JLabel("結果");
	private JTextField jtfResult = new JTextField();
	
	/** Show Steps
	--------------------------------- */
	private JLabel jlSteps = new JLabel("處理過程");	
	private JRadioButton jrbSteps = new JRadioButton("Show Steps");
	private JTextArea jtaSteps = new JTextArea();
	
	/////////////////////////////////////////////////////////////
	
	/** Initialize the Applet
	========================================================= */
	public void init() {

		/** Menu Bar
		--------------------------------- */
		JMenuBar jmb = new JMenuBar();
		setJMenuBar(jmb);
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
		jmb.add(helpMenu);

		helpMenu.add(jmiViewHelp);
		helpMenu.add(jmiAbout);
		
		/** Polynomial A
		--------------------------------- */
		JPanel p1 = new JPanel(new BorderLayout());
		p1.add(jlPolyA, BorderLayout.WEST);
		p1.add(jtfPolyA, BorderLayout.CENTER);
		
		/** Polynomial B
		--------------------------------- */
		JPanel p2 = new JPanel(new BorderLayout());
		p2.add(jlPolyB, BorderLayout.WEST);
		p2.add(jtfPolyB, BorderLayout.CENTER);
		
		/** Buttons
		--------------------------------- */
		JPanel p3 = new JPanel(new GridLayout(1, 4, 5, 5));
		p3.add(jbtClear);
		p3.add(jbtAdd);
		p3.add(jbtSubtract);
		p3.add(jrbSteps);
		
		/** Result
		--------------------------------- */
		JPanel p4 = new JPanel(new BorderLayout());
		p4.add(jlResult, BorderLayout.WEST);
		p4.add(jtfResult, BorderLayout.CENTER);
		
		/** Show Steps
		--------------------------------- */
		JPanel p5 = new JPanel(new BorderLayout());
		jtaSteps.setBackground(Color.WHITE);
		jtaSteps.setLineWrap(true);
		jtaSteps.setWrapStyleWord(false);
		
		final JScrollPane jspSteps = new JScrollPane(jtaSteps);
		jspSteps.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		p5.add(jlSteps, BorderLayout.NORTH);
		p5.add(jspSteps, BorderLayout.CENTER);
		
		/** Panels for Poly. A, B, Buttons, and Result
		--------------------------------- */
		JPanel p6 = new JPanel(new GridLayout(4, 1, 5, 5));
		p6.add(p1);
		p6.add(p2);
		p6.add(p3);
		p6.add(p4);
		
		/** Set the Frame
		--------------------------------- */
		setLayout(new BorderLayout());
		setSize(400, 350);
		add(p6, BorderLayout.NORTH);
		add(p5, BorderLayout.CENTER);
	} // End of the init method
	
	/** Register the Action Listeners When Start
	========================================================= */
	public void start() {
		
		/** Menu Bar
		--------------------------------- */
		
		/** About */
		jmiAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String about = "資料結構暑假程式作業1\n" +
							   "多項式相加/減\n\n"+
							   "U10216020 吳彥儀";
				JOptionPane.showMessageDialog(null, about, "About", JOptionPane.PLAIN_MESSAGE);
			} // End of the actionPerformed method
		}); // End of the inner class
		
		/** View Help */
		jmiViewHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String help = "【輸入說明】\n" +
							  "請輸入僅含有數字0~9、x或X、括弧、^、空格的多項式\n\n" +
							  "【輸入範例】\n" +
							  "1. 3x^2 - 5x + 9\n" +
							  "2. 4 x ^ (-2) - 2 x\n" +
							  "3. 9X^3 -4 +5X";
				JOptionPane.showMessageDialog(null, help, "About", JOptionPane.PLAIN_MESSAGE);
			} // End of the actionPerformed method
		}); // End of the inner class

		/** Buttons
		--------------------------------- */
		jbtClear.addActionListener(this);
		jbtAdd.addActionListener(this);
		jbtSubtract.addActionListener(this);
	} // End of the start method
	
	/** Deregister the Action Listeners When Stop
	========================================================= */
	public void stop() {
		jmiAbout.addActionListener(null);
		jmiViewHelp.addActionListener(null);
		jbtClear.addActionListener(null);
		jbtAdd.addActionListener(null);
		jbtSubtract.addActionListener(null);
	} // End of the stop method
	
	/////////////////////////////////////////////////////////////
	
	/** Insert the Numbers to Build a Polynomial
	========================================================= */
	public void insertPoly(String text, final Polynomial poly) {	
		text = text.replaceAll(" ", ""); // Remove the spaces
		text = text.replaceAll("-", "+-"); // Replace the - with +-
		text = text.replaceAll("\\(", "").replaceAll("\\)", ""); // Remove the parentheses
		text = text.replaceAll("\\^\\+", "").replaceAll("\\^", ""); // Replace the ^+ with ^
		
		if (text.charAt(0)=='+')
			text = text.substring(1); // Remove the + before the first number
		String[] tokens1 = text.split("\\+");
		
		for (int i = 0; i < tokens1.length; i++) {
			String[] tokens2 = tokens1[i].split("[xX]");
			try {
				if (tokens2.length == 2)
					poly.insert(Integer.parseInt(tokens2[0]), Integer.parseInt(tokens2[1]));
				else {
					if (tokens1[i].contains("x") || tokens1[i].contains("X"))
						poly.insert(Integer.parseInt(tokens2[0]), 1); // When the exponent is 1
					else
						poly.insert(Integer.parseInt(tokens2[0]), 0); // When the exponent is 0
				}
			} // End of try
			catch (NumberFormatException nfe) { // Show Error Message when invalid input occurs
				JOptionPane.showMessageDialog(null, "輸入格式錯誤。", "錯誤", JOptionPane.ERROR_MESSAGE);
			} // End of catch
		} // End of the for loop
		
	} // End of the insertPoly method
	
	/** Actions for the Buttons
	========================================================= */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbtClear) {
			jtfPolyA.setText("");
			jtfPolyB.setText("");
			jtfResult.setText("");
			jtaSteps.setText("");
	    } // End of the events of the jbtClear
		
		if (e.getSource() == jbtAdd) {	
			Polynomial polyA = new Polynomial("A");
			insertPoly(jtfPolyA.getText(), polyA);
			
			Polynomial polyB = new Polynomial("B");
			insertPoly(jtfPolyB.getText(), polyB);
			
			Polynomial result = polyA.add(polyB);
			if (jrbSteps.isSelected()) showSteps(result);
			jtfResult.setText(result.print());
	    } // End of the events of the jbtAdd
		
		if (e.getSource() == jbtSubtract) {
			Polynomial polyA = new Polynomial("A");
			insertPoly(jtfPolyA.getText(), polyA);
			
			Polynomial polyB = new Polynomial("B");
			insertPoly(jtfPolyB.getText(), polyB);
			
			Polynomial result = polyA.subtract(polyB);
			if (jrbSteps.isSelected()) showSteps(result);
			jtfResult.setText(result.print());
	    } // End of the events of the jbtSubtract
	} // End of the actionPerformed method
	
	/** Show Each Step
	========================================================= */
	public void showSteps(final Polynomial result) {
		final Timer timer = new Timer(1000, null);
		
		timer.addActionListener(new ActionListener() {
			String[] tokens= result.getTempResult().split("\n");
			int i = 0;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(i < tokens.length) {
					jtaSteps.setText(jtaSteps.getText() + tokens[i++] + "\n");
				}
				else
					timer.stop();
			} // End of the actionPerformed method
		}); // End of the inner class
	
		timer.start();
	} // End of the showSteps method
}
