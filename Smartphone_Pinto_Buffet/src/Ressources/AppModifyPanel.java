package Ressources;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AppModifyPanel extends AppInterface {

	// Boutons
	private ImageIcon imgModify = new ImageIcon("image/icons/edit.png");
	private JButton modify = new Button(imgModify);

	private ImageIcon imgBack = new ImageIcon("image/icons/back.png");
	private JButton back = new Button(imgBack);

	private ImageIcon imgSave = new ImageIcon("image/icons/save.png");
	private JButton save = new Button(imgSave);

	private ImageIcon imgsavegallery=new ImageIcon("image/icons/savegallery.png");
	private JButton savegallery = new Button(imgsavegallery);

	 // Constructeur du panel de modification
	 // @param PANELNAME
	 // @param COLORPANEL

	public AppModifyPanel(String PANELNAME, Color COLORPANEL) 
	{
		super(PANELNAME, COLORPANEL); 
		panelButton1.add(modify);
		panelButton2.add(back);
	}

	 // Méthode lors de la sauvegarde


	public void save() 
	{
		panelButton1.remove(save);
		panelButton1.add(modify);
		panelButton1.updateUI();
	}


	 // Méthode lors de l'edition

	public void edit() 
	{ 
		panelButton2.setEnabled(false);
		panelButton1.remove(modify);
		panelButton1.add(save);
		panelButton1.updateUI();
	}

	// Getters et setters
	public JButton getModify() 
	{
		return modify;
	}

	public JButton getBack() 
	{
		return back;
	}

	public JButton getSave() 
	{
		return save;
	}
	public JButton getSavegallery() 
	{
		return savegallery;
	}

}
