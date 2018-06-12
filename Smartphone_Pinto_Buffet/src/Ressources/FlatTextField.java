package Ressources;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class FlatTextField extends JTextField {

	private Color background;
	private Color foreground;
	private Font font15 = new Font("Montserrat", Font.BOLD, 15);
	private boolean editable;
	// constructeur de la class
	public FlatTextField(boolean editable, Color background, Color foreground) 
	{
		this.editable = editable;
		this.background = background;
		this.foreground = foreground;
		setBorder(null);
		setHorizontalAlignment(JTextField.CENTER);
		setFont(font15);
		setBackground(this.background);
		setForeground(this.foreground);
		setEditable(editable);
		addFocusListener(new FieldListener());
		addMouseListener(new TextFieldListener());
		
	}
	// change pour pemettre la modification 
	public void changeEditable() 
	{
		this.editable = !editable;
		setEditable(editable);
	}
	
	// permet le changement du texte au click
	class TextFieldListener extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			Color test = new Color(222, 44, 60);
			if (isEditable() && getBackground().equals(test)) 
			{
				setText(null);
				setBackground(background);
				setForeground(foreground);
			}
		}
		
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			Color test = new Color(222, 44, 60);
			if (isEditable() && getBackground().equals(test)) 
			{
				setText(null);
				setBackground(background);
				setForeground(foreground);
			}
		}
	}
	// permet le changement d'arriere plan au click
	class FieldListener implements FocusListener 
	{
		public void focusGained(FocusEvent arg0) 
		{
			Color test = new Color(222, 44, 60);
			if (isEditable() && getBackground().equals(test)) 
			{
				setText(null);
				setBackground(background);
				setForeground(foreground);
			}
		}

		public void focusLost(FocusEvent arg0) 
		{
		}
	}
}