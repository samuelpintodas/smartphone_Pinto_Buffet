package Ressources;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class AppInterface extends JPanel {
	
	// Nom de l'application Couleur de l'application
	private final String PANELNAME;
	private final Color COLORPANEL;
	
	// Panel principal du titre gérant la couleur de l'applicaiton
	// Panel du texte gérant le nom de l'application
	// Panels contenant les boutons interactifs
	private JPanel panelTitre = new JPanel(new GridBagLayout()); //GridBagLayout permet de centre le contenu du panel titre
	private JPanel panelTexte = new JPanel(new BorderLayout());
	protected JPanel panelButton1 = new JPanel(new GridBagLayout());
	protected JPanel panelButton2 = new JPanel(new GridBagLayout());
	
	
	// Titre du panel
	private JLabel titre = new JLabel();
	
	//Fonts
	private Font font20 = new Font("Times New Roman", Font.PLAIN, 20);
	

	//	  Constructeur de l'entête principal
	//	  @param PANELNAME
	//	  @param COLORPANEL

	public AppInterface(String PANELNAME, Color COLORPANEL) 
	{
		this.PANELNAME = PANELNAME;
		this.COLORPANEL = COLORPANEL;
		
		//Enlever la bordure en haut
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		
		//Ajout du panel titre
		add(panelTitre, BorderLayout.NORTH);
		panelTitre.setBackground(COLORPANEL);
		panelTitre.setPreferredSize(new Dimension(480, 50));
		panelTitre.add(panelTexte); //Ajout panel texte dans panel titre
		
		//Paramètres panel texte
		panelTexte.setOpaque(false); //Panel transparent
		panelTexte.setPreferredSize(new Dimension(480, 32)); //Taille
		panelTexte.add(titre, BorderLayout.CENTER); //Ajout du titre
		
		panelTexte.add(panelButton1, BorderLayout.EAST); //Ajout du panel pour le bouton1
		panelButton1.setOpaque(false);
		panelButton1.setPreferredSize(new Dimension(64, 32)); //Dimensions du panel du bouton ==> permet de ne pas avoir un bouton collé à la fenêtre
		
		panelTexte.add(panelButton2, BorderLayout.WEST); //Ajout du du panel pour le bouton2
		panelButton2.setOpaque(false);
		panelButton2.setPreferredSize(new Dimension(64, 32)); //Dimensions du panel du bouton ==> permet de ne pas avoir un bouton collé à la fenêtre
		
		//Paramètres du titre
		titre.setText(PANELNAME.toUpperCase());
		titre.setForeground(Color.BLACK);
		titre.setFont(font20); //Changement police en Tièmes new Roman, 20px
		titre.setHorizontalAlignment(JLabel.CENTER); //Centrer texte horizontalement
	}

	//Getters & Setters
	public JPanel getPanelTitre() 
	{
		return panelTitre;
	}

	public void setPanelTitre(JPanel panelTitre) 
	{
		this.panelTitre = panelTitre;
	}
}