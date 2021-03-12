package com.kviewer.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class Window extends JFrame{
	/**
	 * Attributes
	 */
	private JPanel mainPanel = (JPanel) getContentPane();
	private JPanel buttons;
	
	public static String title = "KViewer";
	public JLabel canva;
	public JButton left;
	public JButton right;
	
	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setSize(450, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("KViewer");
		
		buttons = new JPanel();
		mainPanel.setLayout(new BorderLayout(0, 0));
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		mainPanel.add(buttons, BorderLayout.SOUTH);
		
		canva = new JLabel("The image will load here...", SwingConstants.CENTER);
		mainPanel.add(canva);
		
		left = new JButton("<");
		buttons.add(left);
		
		right = new JButton(">");
		buttons.add(right);
		
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
			System.out.println("Unsupported look and feel GUI");
		}
	}
}
