package SS2016.Uebung;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;
import java.util.UUID;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

@SuppressWarnings("serial")
public class ChatGui extends JFrame implements ActionListener, WindowListener, IMessageGui {

	public static final int DEFAULT_PORT = 1234;
	private JTextField eingabe;
	private JTextPane ausgabe;
	private JButton ok, beenden, anmelden;
	private JTextField hostField, portField, usernameField;
	private Style defaultStyle, regularStyle, userHeadlineStyle, adminStyle;
	private StyledDocument document;
	private String host = "localhost", username = UUID.randomUUID().toString();
	private int port = DEFAULT_PORT;
	private AnmeldeDialog dialog;
	private IMessageSender sender;
	private String lastMessageFrom = null;
	private boolean isFirstRun = true;

	public ChatGui(IMessageSender sender) {
		super("OTH-Chat-Client");
		this.sender = sender;

		ok = new JButton("Senden");
		beenden = new JButton("Programm beenden");
		eingabe = new JTextField();
		ausgabe = new JTextPane();

		ausgabe.setEditable(false);
		ausgabe.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		ausgabe.setSize(new Dimension(250, 500));
		document = ausgabe.getStyledDocument();
		defaultStyle = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

		regularStyle = document.addStyle("regular", defaultStyle);
		StyleConstants.setFontFamily(regularStyle, "SansSerif");
		StyleConstants.setFontSize(regularStyle, 12);
		StyleConstants.setLineSpacing(regularStyle, 0.05f);
		StyleConstants.setForeground(regularStyle, Color.BLACK);
		StyleConstants.setBackground(regularStyle, Color.white);
		StyleConstants.setAlignment(regularStyle, StyleConstants.ALIGN_LEFT);

		userHeadlineStyle = document.addStyle("userHeadline", defaultStyle);
		StyleConstants.setFontFamily(userHeadlineStyle, "Arial");
		StyleConstants.setFontSize(userHeadlineStyle, 9);
		StyleConstants.setLineSpacing(userHeadlineStyle, 0.2f);
		StyleConstants.setForeground(userHeadlineStyle, Color.WHITE);
		StyleConstants.setBackground(userHeadlineStyle, Color.GRAY);
		StyleConstants.setAlignment(userHeadlineStyle, StyleConstants.ALIGN_RIGHT);

		adminStyle = document.addStyle("adminStyle", userHeadlineStyle);
		StyleConstants.setFontFamily(adminStyle, "Arial");
		StyleConstants.setFontSize(adminStyle, 9);
		StyleConstants.setLineSpacing(adminStyle, 0.2f);
		StyleConstants.setForeground(adminStyle, Color.GRAY);
		StyleConstants.setBackground(adminStyle, Color.WHITE);
		StyleConstants.setAlignment(adminStyle, StyleConstants.ALIGN_RIGHT);

	    JPanel panelUnten = new JPanel( new BorderLayout() );
		panelUnten.add(eingabe, BorderLayout.NORTH);
		panelUnten.add(ok, BorderLayout.SOUTH);

		ok.addActionListener(this);
		eingabe.addActionListener(this);
		beenden.addActionListener(this);

		this.addWindowListener( new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		    	eingabe.requestFocus();
		    }
		});

		add(new JScrollPane(ausgabe), BorderLayout.CENTER);
		add(panelUnten, BorderLayout.SOUTH);

		this.setSize(new Dimension(500, 400));
		setVisible(true);
		this.addWindowListener(this);

		dialog = new AnmeldeDialog(this, "Anmeldung", true);
		dialog.setVisible(true);
		if(username != null && !"".equals(username) && !username.contains("#")){
			sender.openChatConnection(username, host, port, this);
		}
		else
		{
			System.exit(NORMAL);
		}
		this.setTitle(this.getTitle() + " - " + username);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if( e.getSource() == beenden )
			System.exit(NORMAL);
		else {
			String text = eingabe.getText();
			eingabe.setText("");
			if(isFirstRun){
				isFirstRun = false;
				//sender.openChatConnection(username, host, port, this);
			}
			
			int pos = document.getLength() + 1;
			try {
				document.insertString(pos, text + "\n", document.getStyle("regular"));
				document.setParagraphAttributes(pos, (text + "\n").length(), document.getStyle("regular"), false);
				sender.sendChatMessage(text);
				eingabe.requestFocus();
			} catch (BadLocationException ex) {
				ex.printStackTrace();
			}
			lastMessageFrom = null;
			scrollDown();
		}
	}

	@Override
	public synchronized void showNewMessage(String user, String message) {
		Style userStyle = document.getStyle(user);

		if(userStyle == null) {
			userStyle = document.addStyle(user, defaultStyle);
			StyleConstants.setForeground(userStyle, new Color(new Random().nextInt(240), new Random().nextInt(240), new Random().nextInt(240)));
			StyleConstants.setFontFamily(userStyle, "SansSerif");
			StyleConstants.setFontSize(userStyle, 12);
			StyleConstants.setLineSpacing(userStyle, 0.05f);
			StyleConstants.setAlignment(userStyle, StyleConstants.ALIGN_RIGHT);
		}

		if(!user.equals(lastMessageFrom))
			insertString(user + ":", userHeadlineStyle);
		insertString(message, userStyle);
		lastMessageFrom = user;
		scrollDown();
	}

	@Override
	public void showAdminMessage(String message) {
		insertString(message, adminStyle);
		lastMessageFrom = null;
		scrollDown();
	}


	private void insertString(String message, Style style) {
		int pos = document.getLength() + 1;
		try {
			document.insertString(pos, message + "\n", style);
		    document.setParagraphAttributes(pos, (message + "\n").length(), style, false);
		} catch (BadLocationException e) {
		}
	}

	public void showMessageDialog(String message, String title){
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void scrollDown(){
		ausgabe.setCaretPosition(ausgabe.getDocument().getLength());
	}

	class AnmeldeDialog extends JDialog {

		public AnmeldeDialog(JFrame parent, String title, boolean modal) {
			super(parent, title, modal);
			JPanel panel = new JPanel(new GridLayout(3,2));
			panel.add(new JLabel("Host:"));
			hostField = new JTextField();
			hostField.setEditable(true);
			hostField.setFocusable(true);
			hostField.setColumns(12);
			hostField.setText("im-vm-011");
			panel.add(hostField);
			panel.add(new JLabel("Port:"));
			portField = new JTextField();
			portField.setEditable(true);
			portField.setColumns(6);
			portField.setText("1234");
			panel.add(portField);
			panel.add(new JLabel("Username:"));
			usernameField = new JTextField();
			usernameField.setEditable(true);
			usernameField.setColumns(12);
			usernameField.setText("Hans" + Math.random()*7+1);
			usernameField.addActionListener( new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					host = hostField.getText();
					try {
						port = Integer.parseInt(portField.getText());
					} catch(NumberFormatException nfex) { /* ignore, will be DEFAULT_PORT*/}
					username = usernameField.getText();
					dialog.dispose();
				}
			});
			this.addWindowListener( new WindowAdapter() {
			    public void windowOpened( WindowEvent e ){
			    	usernameField.requestFocus();
			    }
			});
			panel.add(usernameField);
			add(panel, BorderLayout.CENTER);
			anmelden = new JButton("Anmelden");
			anmelden.addActionListener( new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					host = hostField.getText();
					try {
						port = Integer.parseInt(portField.getText());
					} catch(NumberFormatException nfex) { /* ignore, will be DEFAULT_PORT*/}
					username = usernameField.getText();
					dialog.dispose();
				}
			});

			add(anmelden, BorderLayout.SOUTH);
			pack();
			//setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			hostField.requestFocus();
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void windowClosing(WindowEvent e) {
		int confirm = JOptionPane.showOptionDialog(null, "Chat wirklich beenden?", "Auf Wiedersehen", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (confirm == 0) {
			sender.closeChatConnection();
			System.exit(0);
		}
    }


	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}


}
