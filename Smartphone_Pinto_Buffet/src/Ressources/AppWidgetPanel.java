package Ressources;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AppWidgetPanel extends AppInterface {

	// Boutons
	private ImageIcon imgBack = new ImageIcon("image/icons/Back.png");
	private JButton back = new Button(imgBack);
	private JButton empty = new Button();


	 // Constructeur des panels widgets
	 // @param PANELNAME
	 // @param COLORPANEL

	public AppWidgetPanel(String PANELNAME, Color COLORPANEL) 
	{
		super(PANELNAME, COLORPANEL);
		panelButton2.add(back);
		panelButton1.add(empty);
	}

	// Getters et setters
	public JButton getBack() 
	{
		return back;
	}

	public void setBack(JButton back) 
	{
		this.back = back;
	}
}