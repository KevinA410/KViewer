package com.kviewer.controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import com.kviewer.view.Window;

public class Controller {
	/**
	 * Attributes
	 */
	private int index;
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
				if (index == 0)
					index = images.size() - 1;
				else
					index--;

				setImage(root.getAbsolutePath() + "/" + images.get(index));
			}
		});

		/**
		 * This event change the current image to the next right
		 */
		win.right.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (index == images.size() - 1)
					index = 0;
				else
					index++;

				setImage(root.getAbsolutePath() + "/" + images.get(index));
			}
		});
	}

	/**
	 * Initialize the application
	 */
	public void run() {
		File f = new File(current);

		if (!f.exists()) {
			win.canva.setText("We couldn't find " + f.getAbsolutePath());
			return;
		}

		if (!f.isDirectory()) {
			root = f.getParentFile();
		}

		for (String str : root.list()) {
			if (isSupported(str))
				images.add(str);
		}

		index = images.indexOf(f.getName());

		if (index < 0)
			index = 0;

		setImage(root.getAbsolutePath() + "/" + images.get(index));
	}

	/**
	 * Check if the file has a supported file format image
	 * 
	 * @param fileName
	 * @return
	 */
	private boolean isSupported(String fileName) {
		boolean isSupported = false;
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
		ImageIcon icon = new ImageIcon(path);
		Image scaled = icon.getImage().getScaledInstance(win.canva.getWidth(), win.canva.getHeight(),
				Image.SCALE_SMOOTH);

		win.canva.setText("");
		win.canva.setIcon(new ImageIcon(scaled));
		win.setTitle(Window.title + ": " + path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf(".")));
	}

	/**
	 * Main method, run the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("You have to specify a path");
			return;
		}

		Window win = new Window();
		Controller con = new Controller(win, args[0]);
		con.run();
	}
}
