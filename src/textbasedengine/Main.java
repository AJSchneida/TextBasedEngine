package textbasedengine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

import textbasedengine.commands.Interpreter;
import textbasedengine.entities.Location;
import textbasedengine.entities.Player;
import textbasedengine.gui.WindowHandler;



public class Main {
	public static JTextArea output;
	static JTextField input;
	static JScrollPane sp;
	
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setTitle("TextBasedEngine");
		window.setSize(700, 500);
		window.addWindowListener(new WindowHandler());
		
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		
		// Output
		output = new JTextArea();
		output.setBorder(BorderFactory.createLineBorder(Color.black));
		output.setEditable(false);
		output.setWrapStyleWord(true);
		output.setLineWrap(true);
		((DefaultCaret)output.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		// Input
		input = new JTextField();
		input.setPreferredSize(new Dimension(680, 20));
		input.setMaximumSize(new Dimension(1920, 20));
		input.setBorder(BorderFactory.createLineBorder(Color.black));
		
		input.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Interpreter.run(input.getText());
				input.setText("");
			}
		});
		
		// ScrollPanel
		sp = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setPreferredSize(new Dimension(660, 430));
		
		pane.add(sp);
		pane.add(Box.createVerticalGlue());
		pane.add(input);
		pane.add(Box.createVerticalGlue());
		window.add(pane);
		window.pack();
		window.setVisible(true);
		
		Location.Load();
		Player.init();
	}
}
