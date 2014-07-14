import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class PolynomialCalculator extends JApplet implements ActionListener {
	
	JMenuItem jmiViewHelp = new JMenuItem("View Help", 'H');
	JMenuItem jmiAbout = new JMenuItem("About", 'A');
	
	private JLabel jlPolyA = new JLabel("多項式(A=)");
	private JLabel jlPolyB = new JLabel("多項式(B=)");
	private JLabel jlResult = new JLabel("結果");
	private JLabel jlSteps = new JLabel("處理過程");
	
	private JButton jbtAdd = new JButton("+");
	private JButton jbtSubtract = new JButton("-");
	private JButton jbtClear = new JButton("Clear");
	
	private JRadioButton jrbSteps = new JRadioButton("Show Steps");
	
	private JTextField jtfPolyA = new JTextField();
	private JTextField jtfPolyB = new JTextField();	
	private JTextField jtfResult = new JTextField();	

	private JTextArea jtaSteps = new JTextArea();
	
	public void init() {
		JMenuBar jmb = new JMenuBar();
		setJMenuBar(jmb);
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
		jmb.add(helpMenu);
		
		helpMenu.add(jmiViewHelp);
		helpMenu.add(jmiAbout);
		
		JPanel p1 = new JPanel(new BorderLayout());
		p1.add(jlPolyA, BorderLayout.WEST);
		p1.add(jtfPolyA, BorderLayout.CENTER);
		
		JPanel p2 = new JPanel(new BorderLayout());
		p2.add(jlPolyB, BorderLayout.WEST);
		p2.add(jtfPolyB, BorderLayout.CENTER);
		
		JPanel p3 = new JPanel(new GridLayout(1, 4, 5, 5));
		p3.add(jbtClear);
		p3.add(jbtAdd);
		p3.add(jbtSubtract);
		p3.add(jrbSteps);
		
		JPanel p4 = new JPanel(new BorderLayout());
		p4.add(jlResult, BorderLayout.WEST);
		p4.add(jtfResult, BorderLayout.CENTER);
		
		JPanel p5 = new JPanel(new BorderLayout());
		jtaSteps.setBackground(Color.WHITE);
		jtaSteps.setLineWrap(true);
		jtaSteps.setWrapStyleWord(false);
		
		final JScrollPane jspSteps = new JScrollPane(jtaSteps);
		jspSteps.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		p5.add(jlSteps, BorderLayout.NORTH);
		p5.add(jspSteps, BorderLayout.CENTER);
				
		JPanel p6 = new JPanel(new GridLayout(4, 1, 5, 5));
		p6.add(p1);
		p6.add(p2);
		p6.add(p3);
		p6.add(p4);
		
		setLayout(new BorderLayout());
		setSize(400, 350);
		add(p6, BorderLayout.NORTH);
		add(p5, BorderLayout.CENTER);
	}
	
	public void start() {
		/** About */
		
		jmiAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String about = "";
				JOptionPane.showMessageDialog(null, about, "About", JOptionPane.PLAIN_MESSAGE);
			} // End of the actionPerformed method
		}); // End of the inner class
		
		/** About */
		
		jmiViewHelp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String help = "";
				JOptionPane.showMessageDialog(null, help, "About", JOptionPane.PLAIN_MESSAGE);
			} // End of the actionPerformed method
		}); // End of the inner class
		
		jbtClear.addActionListener(this);
		jbtAdd.addActionListener(this);
		jbtSubtract.addActionListener(this);
	}
	
	public void stop() {
		jmiAbout.addActionListener(null);
		jmiViewHelp.addActionListener(null);
		jbtClear.addActionListener(null);
		jbtAdd.addActionListener(null);
		jbtSubtract.addActionListener(null);
	}
	
	public void insertPoly(String text, Polynomial poly) {
		text = text.replaceAll(" ", "");
		text = text.replaceAll("-", "+-");
		text = text.replaceAll("\\(", "").replaceAll("\\)", "");
		text = text.replaceAll("\\^\\+", "").replaceAll("\\^", "");
		if (text.charAt(0)=='+')
			text = text.substring(1);
		String[] tokens1 = text.split("\\+");
		
		for (int i = 0; i < tokens1.length; i++) {
			String[] tokens2 = tokens1[i].split("[xX]");
			if (tokens2.length == 2)
				poly.insert(Integer.parseInt(tokens2[0]), Integer.parseInt(tokens2[1]));
			else {
				if (tokens1[i].contains("x") || tokens1[i].contains("X"))
					poly.insert(Integer.parseInt(tokens2[0]), 1);
				else
					poly.insert(Integer.parseInt(tokens2[0]), 0);
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbtClear) {
			jtfPolyA.setText("");
			jtfPolyB.setText("");
			jtfResult.setText("");
			jtaSteps.setText("");
	    }
		if (e.getSource() == jbtAdd) {
			Polynomial polyA = new Polynomial("A");
			insertPoly(jtfPolyA.getText(), polyA);
			
			Polynomial polyB = new Polynomial("B");
			insertPoly(jtfPolyB.getText(), polyB);
			
			Polynomial result = polyA.add(polyB);
			jtfResult.setText(result.print());
	    }
		if (e.getSource() == jbtSubtract) {
			Polynomial polyA = new Polynomial("A");
			insertPoly(jtfPolyA.getText(), polyA);
			
			Polynomial polyB = new Polynomial("B");
			insertPoly(jtfPolyB.getText(), polyB);
			
			Polynomial result = polyA.subtract(polyB);
			jtfResult.setText(result.print());
	    }
	}

}
