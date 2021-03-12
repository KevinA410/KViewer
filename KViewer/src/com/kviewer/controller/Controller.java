package com.kviewer.controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import com.kviewer.view.Window;

public class Controller {
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
					setImage(root.getAbsolutePath() + "/" + images.get(index));
					break;
				case 40:// DOWN
					win.buttons.setVisible(false);
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
	 * 
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
