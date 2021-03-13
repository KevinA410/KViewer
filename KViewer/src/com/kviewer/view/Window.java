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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class Window extends JFrame {
	public static String title = "KViewer";
	/**
	 * Panels
	 */
	private JPanel mainPanel = (JPanel) getContentPane();
	public JPanel menuPanel;
	public JPanel buttons;
	/**
	 * Components
	 */
	public JLabel canva;
	public JButton left;
	public JButton right;
	/**
	 * Menu
	 */
	private JMenuBar menuBar;
	private JMenu menuConfig;
	public JCheckBox checkPanelVisible;
	public JMenuItem itemAbout;
	/**
	 * Spaces
	 */
	private JLabel label;

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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("KViewer");
		/**
		 * Panels settings
		 */
		mainPanel.setLayout(new BorderLayout(0, 0));
		mainPanel.setBackground(new Color(33, 33, 33));
		
		menuPanel = new JPanel();
		menuPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 2));
		menuPanel.setBackground(null);
		mainPanel.add(menuPanel, BorderLayout.NORTH);
		
		buttons = new JPanel();
		buttons.setBackground(null);
		buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 3));
		mainPanel.add(buttons, BorderLayout.SOUTH);
		/**
		 * Components settings
		 */
		menuBar = new JMenuBar();
		menuBar.setBackground(null);
		menuBar.setBorder(null);
		menuPanel.add(menuBar);
		
		menuConfig = new JMenu("");
		menuConfig.setIcon(new ImageIcon(Window.class.getResource("/com/kviewer/icons/settings.png")));
		menuConfig.setBackground(null);
		menuConfig.setBorder(null);
		menuBar.add(menuConfig);
		{
			checkPanelVisible = new JCheckBox("Hide panel by default");
			menuConfig.add(checkPanelVisible);
			
			itemAbout = new JMenuItem("About KViewer");
			menuConfig.add(itemAbout);
		}
		
		
		
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
		/**
		 * Look and feel
		 */
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
			System.out.println("Unsupported look and feel GUI");
		}
	}
}
