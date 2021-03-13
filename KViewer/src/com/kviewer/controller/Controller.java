package com.kviewer.controller;

import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import com.kviewer.view.Window;

public class Controller {
	/**
	 * Configuration resources
	 */
	private String fConfig = getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "conf.txt";
	ArrayList<String> configs = new ArrayList<String>();
	
	/**
	 * Attributes
	 */
	private int index = -1;
	private Window win;
	private String current;
	private File root;
	private String[] supportedImageExtensions = { ".jpeg", ".jpg", ".png", ".gif" };
	private ArrayList<String> images = new ArrayList<String>();

	/**
	 * Constructor
	 * 
	 * @param view
	 * @param PATH
	 */
	public Controller(Window view, String PATH) {
		win = view;
		current = PATH;
		root = new File(PATH);

		win.setVisible(true);

		/**
		 * This KeyListener set the functions on each key
		 */
		KeyListener kl = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case 38:// UP
					win.buttons.setVisible(true);
					win.menuPanel.setVisible(true);
					setImage(root.getAbsolutePath() + "/" + images.get(index));
					break;
				case 40:// DOWN
					win.buttons.setVisible(false);
					win.menuPanel.setVisible(false);
					setImage(root.getAbsolutePath() + "/" + images.get(index));
					break;
				case 39: // RIGHT
					moveToRight();
					break;
				case 37:// LEFT
					moveToLeft();
					break;
				}
			}
		};

		/**
		 * This event change the size of image when the window is resized
		 */
		win.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				setImage(root.getAbsolutePath() + "/" + images.get(index));
			}
		});

		win.itemAbout.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				goToURL("https://github.com/KevinA410/KViewer");
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		/**
		 * This event change the current image to the next left
		 */
		win.left.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveToLeft();
			}
		});

		/**
		 * This event change the current image to the next right
		 */
		win.right.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveToRight();
			}
		});

		/**
		 * These events control the keys
		 */
		win.setFocusable(true);
		win.addKeyListener(kl);
		win.left.addKeyListener(kl);
		win.right.addKeyListener(kl);
	}

	/**
	 * Initialize the application
	 */
	public void run() {
		File config = new File(fConfig);
		Scanner sc = null;
		
		// If doesn't exist a file configuration file is create and set the default
		if (!config.exists()) {
			try {
				FileWriter fw = new FileWriter(config);

				fw.write("isFirstTime: 1\n");
				fw.write("hidePanelsByDefault: 0\n");
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Get the configuration for the file and save in the "configs" ArrayList
		try {
			sc = new Scanner(config);

			while (sc.hasNextLine())
				configs.add(sc.nextLine());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// If is the first time
		if (Integer.parseInt(configs.get(0).split(": ")[1]) == 1) {
			System.out.println("Is the first time");
		}

		// Set the hiddenPanel setting
		if (Integer.parseInt(configs.get(1).split(": ")[1]) == 1) {
			win.menuPanel.setVisible(false);
			win.buttons.setVisible(false);
			win.checkPanelVisible.setSelected(true);
		}

		File f = new File(current);

		// If the file doesn't exist, close the application
		if (!f.exists()) {
			JOptionPane.showMessageDialog(null, "We couldn't find " + f.getAbsolutePath(), "Error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		// If is a file get its parent directory
		if (!f.isDirectory()) {
			root = f.getParentFile();
		}

		// Check which of the file contents are images
		for (String str : root.list()) {
			if (isSupported(str))
				images.add(str);
		}

		// If no file is an image there's nothing to show
		if (images.size() == 0) {
			JOptionPane.showMessageDialog(null, "We couldn't find any image in your directory ", "Error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		// The index begins in the image opened
		index = images.indexOf(f.getName());

		// If the application was open with a directory the index begin in zero
		if (index < 0)
			index = 0;

		// Set image on the label
		setImage(root.getAbsolutePath() + "/" + images.get(index));
		
		//Save the configuration in the config file
		saveAndExit();
	}
	
	/**
	 * Save the application setting in the configuration file and close the application
	 */
	private void saveAndExit() {
		try {
			win.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					File config = new File(fConfig);
					try {
						FileWriter fw = new FileWriter(config);

						fw.write("isFirstTime: 0\n");

						if (win.checkPanelVisible.isSelected())
							fw.write("hidePanelsByDefault: 1\n");
						else
							fw.write("hidePanelsByDefault: 0\n");

						fw.close();
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					System.exit(0);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Change the image viewed for the next right
	 */
	private void moveToRight() {
		// move the index to right & set the image
		if (index == images.size() - 1)
			index = 0;
		else
			index++;

		setImage(root.getAbsolutePath() + "/" + images.get(index));
	}

	/**
	 * Change the image viewed for the next left
	 */
	private void moveToLeft() {
		// Move the index to left & set the image
		if (index == 0)
			index = images.size() - 1;
		else
			index--;

		setImage(root.getAbsolutePath() + "/" + images.get(index));
	}

	/**
	 * Check if the file has a supported file format image
	 * 
	 * @param fileName
	 * @return
	 */
	private boolean isSupported(String fileName) {
		// If the file doesn't has an extension is not a file
		if (fileName.lastIndexOf('.') < 0)
			return false;

		boolean isSupported = false;
		// Gets the file format extension
		String fileExtension = fileName.substring(fileName.lastIndexOf('.'), fileName.length());

		for (String ext : supportedImageExtensions) {
			if (fileExtension.equals(ext)) {
				isSupported = true;
				break;
			}
		}
		return isSupported;
	}

	/**
	 * Puts the image in the view
	 * @param path
	 */
	private void setImage(String path) {
		if (index < 0)
			return;

		// Create an icon, scale the image and set on the label
		ImageIcon icon = new ImageIcon(path);
		Image scaled = icon.getImage().getScaledInstance(win.canva.getWidth(), win.canva.getHeight(),
				Image.SCALE_SMOOTH);

		win.canva.setText("");
		win.canva.setIcon(new ImageIcon(scaled));

		// Set the image title on the window title
		win.setTitle(Window.title + ": " + path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf(".")));
	}

	/**
	 * Open the project GitHub repository in the browser
	 * @param URL
	 */
	private void goToURL(String URL) {
		if (!Desktop.isDesktopSupported()) {
			JOptionPane.showMessageDialog(null, "Sorry, your system doesn't support Desktop class Java", "Warning",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		Desktop desk = Desktop.getDesktop();

		if (!desk.isSupported(Desktop.Action.BROWSE)) {
			JOptionPane.showMessageDialog(null, "Sorry, we couldn't find any default browser", "Warning",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			URI uri = new URI(URL);
			desk.browse(uri);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Coming soon
	 */
	private void isFirstExecution() {
		//ToDo
	}

	/**
	 * Main method, run the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// If there's no an argument, close de application
		if (args.length == 0) {
			JOptionPane.showMessageDialog(null, "You have to specify a path", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		// Run the application
		Window win = new Window();
		Controller con = new Controller(win, args[0]);
		con.run();
	}
}
