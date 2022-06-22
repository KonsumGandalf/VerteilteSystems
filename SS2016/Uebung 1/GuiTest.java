import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GuiTest extends JFrame implements ActionListener {
	
	private JTextField eingabe;
	private JTextArea ausgabe;
	private JButton ok, beenden;
	
	public GuiTest() {
		super("Mein GUI-Test");
		
		ok = new JButton("Senden");
		beenden = new JButton("Programm beenden");
		eingabe = new JTextField();
		ausgabe = new JTextArea(20, 60);
		
		JPanel panelUnten = new JPanel( new GridLayout(1, 2) );
		panelUnten.add(beenden);
		panelUnten.add(ok);
		
		ok.addActionListener(this);
		beenden.addActionListener(this);
		
		add(eingabe, BorderLayout.CENTER);
		add(ausgabe, BorderLayout.NORTH);
		add(panelUnten, BorderLayout.SOUTH);
		
		setVisible(true);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new GuiTest();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if( e.getSource() == beenden )
			System.exit(NORMAL);
		else {
			String text = eingabe.getText();
			ausgabe.setText( ausgabe.getText() + text + "\n");
			eingabe.setText("");
		}
	}

}
