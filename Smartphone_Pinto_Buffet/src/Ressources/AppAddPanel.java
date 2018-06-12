package Ressources;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AppAddPanel extends AppInterface {

	private JButton save = new Button(new ImageIcon("image/icons/save.png"));

	private JButton cancel = new Button(new ImageIcon("image/icons/Back.png"));


	 // Constructeur des entêtes d'ajout
	 // @param PANELNAME
	 // @param COLORPANEL

	public AppAddPanel(String PANELNAME, Color COLORPANEL)
	{
		super(PANELNAME, COLORPANEL); 
		panelButton1.add(save);
		panelButton2.add(cancel);

	}

	// Getters et setters
	public JButton getSave() 
	{
		return save;
	}

	public JButton getCancel() 
	{
		return cancel;
	}

	public void setCancel(JButton cancel) 
	{
		this.cancel = cancel;
	}
}