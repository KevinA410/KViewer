package com.kviewer.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class Window extends JFrame{
	/**
	 * Attributes
	 */
	public JLabel image;

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
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("KViewer");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		image = new JLabel("The image will load here...", SwingConstants.CENTER);
		getContentPane().add(image, BorderLayout.CENTER);
	}

}
