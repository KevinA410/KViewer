package com.kviewer.controller;

import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import com.kviewer.view.Window;

public class Controller {
	private Window win;
	private String path;
	
	/**
	 * Constructor
	 * @param view
	 */
	public Controller(Window view) {
		win = view;
		
		win.setVisible(true);
		
		win.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				setImage();
			}
		});
	}
	
	public void setImage(String path) {
		this.path = path;
		ImageIcon icon = new ImageIcon(path);
		Image scaled = icon.getImage().getScaledInstance(win.image.getWidth(), win.image.getHeight(), Image.SCALE_SMOOTH);
		
		win.image.setText("");
		win.image.setIcon(new ImageIcon(scaled));
		
	}
	
	private void setImage() {
		setImage(path);
	}
	
	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("You most specify the path of the image");
			return;
		}
		
		Window win = new Window();
		Controller con = new Controller(win);
		
		con.setImage(args[0]);
	}
}
