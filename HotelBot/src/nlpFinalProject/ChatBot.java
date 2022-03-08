package nlpFinalProject;

import weka.classifiers.Classifier;
import java.awt.*;  
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Window;
import javax.swing.*;  
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class ChatBot {	
	private Classifier classifier;
	private static ChatBot chat;
	private JTextPane dialog;
	private StyledDocument styledDocument;
	private String openingMessage;

	//constructor
	private ChatBot(String openingMessage) {

		this.openingMessage = openingMessage;
		JFrame frame = createChatScreen();
		Window window = frame;
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - window.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - window.getHeight()) / 2);
		window.setLocation(x, y);
		addOpeningMessage();
	}

	// make sure to have only one chat screen
	public static ChatBot getInstance(String openingMessage) {
		if (chat == null) {
			chat = new ChatBot(openingMessage);
		} 
		return chat;
	}

	public void setClassifier(Classifier classifier) {
		this.classifier = classifier;
	}

	private JFrame createChatScreen() {
		// create Frame
		JFrame frame = new JFrame("ChatBot");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 800);
		frame.setResizable(false);
		//creates the hotel Icon  
		Image icon = Toolkit.getDefaultToolkit().getImage("logo.png");  
		frame.setIconImage(icon);     

		// create panels
		JPanel topPanel = new JPanel();
		JPanel buttons = new JPanel();
		JPanel bottomPanel = new JPanel();

		//Define colors
		Color  bgColor = new Color(173, 216, 230);
		Color  textColor = Color.black;
		Color  orange = new Color(255,165,0);

		//Create logo label
		JLabel logoLabel = new JLabel("W&R HOTEL");

		//Label design
		logoLabel.setForeground(textColor);
		logoLabel.setBackground(orange);
		logoLabel.setFont(new Font("Serif", Font.BOLD, 35));		
		logoLabel.setHorizontalAlignment(JLabel.CENTER);

		//Create input field
		JTextField inputField = new JTextField(50);

		//InputField Deisgn 
		inputField.setBorder(BorderFactory.createLineBorder(textColor));//add boreder to the label
		inputField.setForeground(textColor);
		inputField.setBackground(Color.white); 	
		inputField.setFont(new Font("Serif", Font.PLAIN, 18));

		//Create the input label
		JLabel inputLabel = new JLabel("Enter your text message here");

		inputLabel.setForeground(textColor);
		inputLabel.setBackground(Color.white);
		inputLabel.setFont(new Font("Serif", Font.BOLD, 24));		
		inputLabel.setHorizontalAlignment(JLabel.CENTER);

		//Create send button
		JButton send = new JButton("Send");
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent action) {
				String text = inputField.getText();
				if (!text.isEmpty()) {
					inputField.setText("");
					changeChatStyle(text, "Guest",false);
					String response = Prediction.predictTextByModel(classifier, text);
					changeChatStyle(response, "W&R",false);
				} else { 
					//Show the message for 3 seconds if the guest clicked the "send" button without writing any message 
					new java.util.Timer().schedule( 
							new java.util.TimerTask() {
								@Override
								public void run() {
									//Enable the send button & remove the message
									send.setEnabled(true);
									inputField.setText("");
								}
							}, 
							3000);
					//Disable the send button & show message
					send.setEnabled(false);
					inputField.setText("Please enter a message...");
				}
			}
		});

		//Create generate message button 
		JButton generate = new JButton("Generate");
		generate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent action) {
				String[] sentences = {"Hi, I want to reserve a room for tomorrow."
						, "I would love to a order room service to my room."
						, "Hey, can I order a massage for two ?"
						, "Hello, I heard about your spa, can I get some details ?"
						, "Hey there, I want to order a room for this weekend."
						, "We can't find the hotel. Where is it located excatly ?"
						, "We are very upset about the hotel's staff. We would like to talk with the CEO immediately"};
				int i = (int) (Math.random()*sentences.length);                                
				inputField.setText(sentences[i]);
			}
		});

		//send & generate buttons Design
		send.setBackground(orange);
		send.setForeground(textColor);
		send.setFont(new Font("Serif", Font.BOLD, 18));

		generate.setBackground(orange);
		generate.setForeground(textColor);
		generate.setFont(new Font("Serif", Font.BOLD, 18));

		topPanel.setLayout(new GridLayout(1,1));
		topPanel.add(logoLabel,BorderLayout.CENTER);
		bottomPanel.setLayout(new GridLayout(4, 1));
		bottomPanel.add(inputLabel, BorderLayout.AFTER_LINE_ENDS);
		bottomPanel.add(inputField, BorderLayout.AFTER_LINE_ENDS);
		buttons.add(send);
		buttons.add(generate);
		bottomPanel.add(buttons);

		//panels design 
		topPanel.setBackground(orange);  
		topPanel.setForeground(textColor);
		bottomPanel.setBackground(bgColor);  
		bottomPanel.setForeground(textColor);
		bottomPanel.setBorder(BorderFactory.createLineBorder(textColor));//add boreder to the label
		buttons.setBackground(bgColor);  
		buttons.setForeground(textColor);
		buttons.setBorder(BorderFactory.createLineBorder(textColor));//add boreder to the label

		//Create dialog area
		dialog = new JTextPane();		
		styledDocument = dialog.getStyledDocument();

		//Disable edit dialog area
		dialog.setEditable(false);		

		//Dialog area design
		dialog.setBackground(bgColor);
		dialog.setForeground(textColor); 
		dialog.setFont(new Font("Serif", Font.PLAIN, 16));
		dialog.setBorder(BorderFactory.createLineBorder(textColor));//add boreder to the label

		//Create scroll bar
		JScrollPane scrollPane = new JScrollPane(dialog);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBackground(bgColor);

		//Adding Components to the frame.
		frame.getContentPane().add(BorderLayout.NORTH, topPanel);
		frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
		frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
		frame.setVisible(true);
		return frame;
	}   

	// change icons and text style 
	private void changeChatStyle(String message, String user, boolean firstMessage) {

		message = message.replaceAll("\n", "\n   ");     
		Style sIcon = dialog.addStyle("style", null);
		Style sMessage = dialog.addStyle("style", null);
		if(firstMessage != false)
		{	
			StyleConstants.setFontSize(sMessage,20);
			StyleConstants.setForeground(sMessage,Color.black);
			StyleConstants.setForeground(sMessage,Color.black);
		}
		if(user=="W&R")
		{   
			ImageIcon imageIcon = new ImageIcon("rob.png"); 
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(35, 35,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			StyleConstants.setIcon(sIcon, imageIcon);
		}
		else if(user=="Guest"){

			ImageIcon imageIcon = new ImageIcon("guest.png"); 
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(35, 35,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			StyleConstants.setIcon(sIcon, imageIcon);
		}
		try {
			styledDocument.insertString(styledDocument.getLength(), "  :", sIcon);
			styledDocument.insertString(styledDocument.getLength(), message +"\n\n", sMessage);
		} catch (BadLocationException e) {
			System.out.println("[ChatBot] (updateDialog) Exception: " + e);
		}
	}

	// sends the first message in the chat
	public void addOpeningMessage() {		
		changeChatStyle(this.openingMessage,"W&R",true);
	}
}