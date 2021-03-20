package com.kviewer.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class Welcome extends JDialog {
	private final JPanel contentPanel = new JPanel();
	public JButton okButton;

	/**
	 * Create the dialog.
	 */
	public Welcome() {
		setSize(550, 400);
		setTitle("How to use KViewer");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(new Color(255, 165, 0));
		contentPanel.setBackground(new Color(33, 33, 33));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbl_title = new JLabel("How to use KViewer", SwingConstants.CENTER);
			lbl_title.setForeground(UIManager.getColor("Button.disabledToolBarBorderBackground"));
			lbl_title.setBounds(12, 14, 519, 31);
			contentPanel.add(lbl_title);
		}
		{
			JLabel image = new JLabel("");
			image.setBounds(147, 57, 258, 171);
			ImageIcon icon = new ImageIcon(Welcome.class.getResource("/com/kviewer/icons/keys.png"));
			Image scaled = icon.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
			image.setIcon(new ImageIcon(scaled));
			contentPanel.add(image);
		}
		{
			JLabel instr1 = new JLabel("Go to the previos image");
			instr1.setForeground(UIManager.getColor("Button.disabledToolBarBorderBackground"));
			instr1.setBounds(79, 257, 176, 15);
			contentPanel.add(instr1);
		}
		{
			JLabel instr2 = new JLabel("Go to the next image");
			instr2.setForeground(UIManager.getColor("Button.disabledToolBarBorderBackground"));
			instr2.setBounds(344, 257, 154, 15);
			contentPanel.add(instr2);
		}
		{
			JLabel instr3 = new JLabel("Hide buttons");
			instr3.setForeground(UIManager.getColor("Button.disabledToolBarBorderBackground"));
			instr3.setBounds(92, 311, 101, 15);
			contentPanel.add(instr3);
		}
		{
			JLabel instr4 = new JLabel("Show buttons");
			instr4.setForeground(UIManager.getColor("Button.disabledToolBarBorderBackground"));
			instr4.setBounds(333, 311, 165, 15);
			contentPanel.add(instr4);
		}
		{
			JLabel str1_par1 = new JLabel("Left:");
			str1_par1.setForeground(new Color(255, 165, 0));
			str1_par1.setBounds(43, 257, 40, 15);
			contentPanel.add(str1_par1);
		}
		
		JLabel lblNewLabel = new JLabel("Down:");
		lblNewLabel.setForeground(new Color(255, 105, 180));
		lblNewLabel.setBounds(44, 311, 70, 15);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Right:");
		lblNewLabel_1.setForeground(new Color(255, 165, 0));
		lblNewLabel_1.setBounds(303, 257, 70, 15);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Up:");
		lblNewLabel_2.setForeground(new Color(255, 105, 180));
		lblNewLabel_2.setBounds(303, 311, 70, 15);
		contentPanel.add(lblNewLabel_2);
		{
			
		}
		{
			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			buttonPane.setBackground(new Color(33, 33, 33));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Let me try!");
				buttonPane.add(okButton);
				//getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
