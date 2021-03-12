package com.kviewer.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class Window extends JFrame {
	/**
	 * Attributes
	 */
	private JPanel mainPanel = (JPanel) getContentPane();
	private JPanel buttons;

	public static String title = "KViewer";
	public JLabel canva;
	public JButton left;
	public JButton right;
	private JLabel label;

	/**
	 * Create the application.
	 */
	public Window() {
		getContentPane().setBackground(new Color(33, 33, 33));
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
		buttons.setBackground(new Color(33,33,33));
		mainPanel.setLayout(new BorderLayout(0, 0));
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		mainPanel.add(buttons, BorderLayout.SOUTH);

		canva = new JLabel("The image will load here...", SwingConstants.CENTER);
		mainPanel.add(canva);

		left = new JButton("");
		left.setBackground(null);
		left.setBorder(null);
		left.setRolloverEnabled(true);

		left.setIcon(new ImageIcon(Window.class.getResource("/com/kviewer/icons/left.png")));
		buttons.add(left);
		
		label = new JLabel("          ");
		buttons.add(label);

		right = new JButton("");
		right.setBackground(null);
		right.setBorder(null);
		right.setRolloverEnabled(true);
		right.setIcon(new ImageIcon(Window.class.getResource("/com/kviewer/icons/right.png")));
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
