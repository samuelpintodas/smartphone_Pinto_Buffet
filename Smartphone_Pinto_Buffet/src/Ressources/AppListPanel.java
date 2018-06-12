package Ressources;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AppListPanel extends AppInterface {

	// Boutons
	private ImageIcon imgAdd = new ImageIcon("image/icons/add.png");
	private JButton add = new Button(imgAdd);

	private ImageIcon imgBack = new ImageIcon("image/icons/back.png");
	private JButton back = new Button(imgBack);

	public AppListPanel(String PANELNAME, Color COLORPANEL) 
	{
		super(PANELNAME, COLORPANEL);
		panelButton1.add(add);
		panelButton2.add(back);
	}

	// Getters et Setters
	public JButton getAdd() 
	{
		return add; 
	}
 
	public JButton getBack() 
	{
		return back;
	}
}