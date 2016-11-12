/**
 * @author Smriti, Nick
 * Class for the GUI of the calculator
 */
package Calc;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.awt.*;
import java.awt.event.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class calculator extends JFrame implements ActionListener{
	// text field for user to enter the input for what he wants to calculate
	JTextField in = new JTextField(20);
	// text field that shows the calculated output
	JTextField out = new JTextField(20);

	private JFrame mainFrame = new JFrame("Calculator");
	private JLabel displayLabel = new JLabel("Hello World");
	GridBagLayout grid = new GridBagLayout();
	private JTextField text = new JTextField();
	private JButton b9 = new JButton("9");
	private JButton b8 = new JButton("8");
	private JButton b7 = new JButton("7");
	private JButton b6 = new JButton("6");
	private JButton b5 = new JButton("5");
	private JButton b4 = new JButton("4");
	private JButton b3 = new JButton("3");
	private JButton b2 = new JButton("2");
	private JButton b1 = new JButton("1");
	private JButton b0 = new JButton("0");

	private JButton bClear = new JButton("C");
	private JButton bPosNeg = new JButton("-/+");
	private JButton bsqrt = new JButton("sqrt");
	private JButton bDivide = new JButton("/");
	private JButton bMultiply = new JButton("*");
	private JButton bSubtract = new JButton("-");
	private JButton bAdd = new JButton("+");
	private JButton bDecimal = new JButton(".");
	private JButton bEqual = new JButton("=");

	private JButton pow = new JButton("^");
	private JButton backspace = new JButton("CE");
	private JButton pi = new JButton("PI");
	private JButton sound = new JButton("ON");

	private String[] number = new String[40];
	private String numbers = "";
	private int count = 0;
	private boolean music = true;

	public calculator() {
		mainFrame.setSize(600, 600);
		mainFrame.setLayout(grid);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setFocusable(true);

		textField(in, grid, GridBagConstraints.REMAINDER, 1, 1, 1, 0);
		in.addActionListener(this);
		textField(out, grid, GridBagConstraints.REMAINDER, 1, 1, 1, 1);

		createButton(b7, grid, 1, 1, 1, 1, 2);
		createButton(b8, grid, 1, 1, 1, 2, 2);
		createButton(b9, grid, 1, 1, 1, 3, 2);
		createButton(bAdd, grid, 1, 1, 1, 4, 2);
		createButton(bClear, grid, 1, 1, 1, 5, 2);
		createButton(backspace, grid, 1, 1, 1, 6, 2);

		createButton(b4, grid, 1, 1, 1, 1, 3);
		createButton(b5, grid, 1, 1, 1, 2, 3);
		createButton(b6, grid, 1, 1, 1, 3, 3);
		createButton(bSubtract, grid, 1, 1, 1, 4, 3);
		createButton(pow, grid, 1, 1, 1, 5, 3);
		createButton(pi, grid, 1, 1, 1, 6, 3);

		createButton(b1, grid, 1, 1, 1, 1, 4);
		createButton(b2, grid, 1, 1, 1, 2, 4);
		createButton(b3, grid, 1, 1, 1, 3, 4);
		createButton(bMultiply, grid, 1, 1, 1, 4, 4);
		createButton(bPosNeg, grid, 1, 1, 1, 5, 4);
		createButton(bsqrt, grid, 1, 1, 1, 6, 4);

		createButton(sound, grid, 1, 1, 1, 1, 5);
		createButton(b0, grid, 1, 1, 1, 2, 5);
		createButton(bDecimal, grid, 1, 1, 1, 3, 5);
		createButton(bDivide, grid, 1, 1, 1, 4, 5);
		createButton(bEqual, grid, GridBagConstraints.REMAINDER, 1, 1, 5, 5);
		
		bsqrt.setToolTipText("Square Root");
		bPosNeg.setToolTipText("Positive Negative Toggle");
		pi.setToolTipText("PI");
		backspace.setToolTipText("Backspace");
		sound.setToolTipText("Sound toggle");
		mainFrame.setVisible(true);
}

	/**
	 * This method designs the JTextField
	 * 
	 * @param text
	 * @param gd
	 * @param gridwidth
	 * @param wX
	 * @param wY
	 * @param gridX
	 * @param gridY
	 */
	void textField(JTextField text, GridBagLayout gd, int gridwidth, int wX, int wY, int gridX, int gridY) {
		GridBagConstraints c = new GridBagConstraints();

		// text.setBorder(new EmptyBorder(0, 5, 0, 25) );
		text.setFont(new Font("monospaced", Font.BOLD, 26));
		// makes user input text from right side
		text.setHorizontalAlignment(SwingConstants.RIGHT);
		text.setBorder(new LineBorder(Color.GRAY));

		c.gridwidth = gridwidth;
		c.weightx = wX;
		c.weighty = wY;
		c.gridx = gridX;
		c.gridy = gridY;
		c.fill = GridBagConstraints.BOTH;
		gd.setConstraints(text, c);
		mainFrame.add(text);
	}

	/**
	 * This method designs the Jbuttons
	 * 
	 * @param button
	 * @param gd
	 * @param gridWidth
	 * @param wX
	 * @param wY
	 * @param gridX
	 * @param gridY
	 */
	public void createButton(JButton button, GridBagLayout gd, int gridWidth, int wX, int wY, int gridX, int gridY) {
		// creating an object for GridBagConstraints
		// GridBagLayout gb = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		// setting button foreground and background color and font
		button.setFont(new Font("Symbol", Font.BOLD, 24));
		button.setForeground(Color.BLUE);
		button.setBackground(Color.CYAN);
		button.setOpaque(true);
		

		// setting button width, height, fill etc.
		c.gridwidth = gridWidth;
		c.weightx = wX;
		c.weighty = wY;
		// specify column
		c.gridx = gridX;
		// specify row
		c.gridy = gridY;
		c.fill = GridBagConstraints.BOTH;
		// sets constrainsts of the button
		gd.setConstraints(button, c);
		// action listener which listens to specific actions when user performs
		// certain operation
		button.addActionListener(this);
		// adds button on the mainFrame panel.
		mainFrame.add(button);
	}
	
	/**
	 * method to play audio files when a button is pressed
	 * @param audioName
	 */

	public void playAudio(String audioName) {
		try {
			AudioInputStream aIStream = AudioSystem.getAudioInputStream(new File(audioName));
			Clip clip = AudioSystem.getClip();
			clip.open(aIStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

	/**
	 * This method implements actions that will be performed when user clicks
	 * specific buttons
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			in.setText(in.getText() + 1);
			number[count] = "1";
			count++;
			if (music)
				playAudio("1.wav");
		}
		if (e.getSource() == b2) {
			in.setText(in.getText() + 2);
			number[count] = "2";
			count++;
			if (music)
				playAudio("2.wav");
		}
		if (e.getSource() == b3) {
			in.setText(in.getText() + 3);
			number[count] = "3";
			count++;
			if (music)
				playAudio("3.wav");
		}
		if (e.getSource() == b4) {
			in.setText(in.getText() + 4);
			number[count] = "4";
			count++;
			if (music)
				playAudio("4.wav");
		}
		if (e.getSource() == b5) {
			in.setText(in.getText() + 5);
			number[count] = "5";
			count++;
			if (music)
				playAudio("5.wav");
		}
		if (e.getSource() == b6) {
			in.setText(in.getText() + 6);
			number[count] = "6";
			count++;
			if (music)
				playAudio("6.wav");
		}
		if (e.getSource() == b7) {
			in.setText(in.getText() + 7);
			number[count] = "7";
			count++;
			if (music)
				playAudio("7.wav");
		}
		if (e.getSource() == b8) {
			in.setText(in.getText() + 8);
			number[count] = "8";
			count++;
			if (music)
				playAudio("8.wav");
		}
		if (e.getSource() == b9) {
			in.setText(in.getText() + 9);
			number[count] = "9";
			count++;
			if (music)
				playAudio("9.wav");
		}
		if (e.getSource() == b0) {
			in.setText(in.getText() + 0);
			number[count] = "0";
			count++;
			if (music)
				playAudio("10.wav");
		}

		if (e.getSource() == bDecimal) {
			in.setText(in.getText() + ".");
			number[count] = ".";
			count++;
			if (music)
				playAudio("d1.wav");
		}
		if (e.getSource() == bClear) {
			for (int i = 0; i <= count; i++) {
				number[count] = null;
			}
			numbers="";
			out.setText("");
			count = 0;
			in.setText("");
			if (music)
				playAudio("d2.wav");
		}
		if (e.getSource() == backspace) {
			if (in.getText().length() > 0) {
				in.setText(in.getText().substring(0, (in.getText().length() - 1)));
				number[count] = "";
				if (count != 0) {
					count--;
				}
			}
			if (music)
				playAudio("d8.wav");
		}

		if (e.getSource() == bPosNeg) {
			String s = in.getText();
			if (s.charAt(0) != '-') {
				in.setText("-" + in.getText());
				number[count] = "n";
				count++;
			} else if (s.charAt(0) == '-') {
				in.setText(in.getText().replaceAll("-", ""));
				number[count] = "";
				count++;
			}
			if (music)
				playAudio("d3.wav");
		}

		if (e.getSource() == bsqrt) {
			in.setText("sqrt" + "(" + in.getText() + ")");
			number[count] = "^";
			number[count + 1] = ".";
			number[count + 2] = "5";
			count = count + 3;
			if (music)
				playAudio("d4.wav");
		}
		if (e.getSource() == bDivide) {
			in.setText(in.getText() + "/");
			number[count] = "/";
			count++;
			if (music)
				playAudio("d5.wav");
		}
		if (e.getSource() == bMultiply) {
			in.setText(in.getText() + "*");
			number[count] = "*";
			count++;
			if (music)
				playAudio("d9.wav");
		}
		if (e.getSource() == bSubtract) {
			in.setText(in.getText() + "-");
			number[count] = "-";
			count++;
			if (music)
				playAudio("d7.wav");
		}
		if (e.getSource() == bAdd) {
			in.setText(in.getText() + "+");
			number[count] = "+";
			count++;
			if (music)
				playAudio("d8.wav");
		}

		if (e.getSource() == pow) {
			in.setText(in.getText() + "^");
			number[count] = "^";
			count++;
			if (music)
				playAudio("d8.wav");
		}

		if (e.getSource() == pi) {
			in.setText(in.getText() + "PI");
			number[count] = "3";
			number[count + 1] = ".";
			number[count + 2] = "1";
			number[count + 3] = "4";
			count += 4;
			if (music)
				playAudio("d8.wav");
		}

		if (e.getSource() == sound) {
			if (music) {
				music = false;
				sound.setText("OFF");
				sound.setForeground(Color.RED);
			} else {
				music = true;
				sound.setText("ON");
				sound.setForeground(Color.BLUE);
			}
		}

		if (e.getSource() == bEqual) {
			ExpressionTree eT = new ExpressionTree();
			for (int i = 0; i < count; i++) {
				numbers = numbers + number[i];
			}
			System.out.println(numbers);
			eT.makeTree(numbers);
			out.setText(eT.evaluate() + "");
			out.setText(eT.evaluate() + "");
			if (music)
				playAudio("d6.wav");

		}

	}

}
