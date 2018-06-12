/* AUTEUR: Samuel Pinto Da Silva
 * NOM DE LA CLASSE: PictureDisplay
 * DESCRIPTION: Recupère l'image du button clické dans la gallerie et l'affiche en grand dans un nouveau panel situé 
 * 				dans le cardLayout. Deux JButtons sont positionnés et permettent de retourner à la gallerie ou de 
 * 				supprimer l'image. Dans le second cas cela a pour effet d'également retourner à la gallerie et
 * 				de supprimer le JButton correspondant. Les listeners sont défini dans GalleryApp.
 */

package gallery;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.nio.file.Path;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Ressources.Button;

public class PictureDisplay extends JPanel {
	
	private JLabel labelPhoto; // où s'affiche l'image
	
	protected JButton back; // permet de revenir à la galerie
	protected JButton delete; // permet de supprimer une image

	private JPanel buttons; // contient les boutons delete et back

	ImageIcon picture; // image à afficher
	protected String picturePathText; // chemin systeme de l'image
	
	
	// constructeur 
	protected PictureDisplay(Icon icon, Path picturePath)	{

		
		picturePathText = picturePath.toString(); // chemin systeme de l'image
		
		delete = new JButton("Delete"); // permet de supprimer l'image
		back = new JButton("Mes images"); // permet le retour à la gallerie
		
		delete = new Button(new ImageIcon(getClass().getClassLoader().getResource("delet.png")),Color.BLACK,new Color(181,164,154)); // permet de supprimer l'image rajout de l'image par raphy
		back = new Button(new ImageIcon("image/icons/Bak.png"),Color.BLACK,new Color(181,164,154)); // permet le retour à la gallerie 
		
		
		buttons = new JPanel(); 
	
		buttons.setLayout(new BorderLayout());

		
		labelPhoto = new JLabel();
		labelPhoto.setLayout(new BorderLayout());
		picture = new ImageIcon();
		picture = (ImageIcon)(icon); 
		
		labelPhoto.setVisible(true);
		labelPhoto.setSize(480, 600);
		Dimension dim2 = new Dimension (480, 600);
		labelPhoto.setMaximumSize(dim2);
		labelPhoto.setPreferredSize(dim2);
		
		try{
		picture = labelPictureSize(labelPhoto, picture);
		labelPhoto.setIcon(picture);
		}catch(Exception e){ 
			// si l'image est nulle
			System.out.println("ERREUR: picture = null");
		}   

		
		buttons.setOpaque(false);
		buttons.setSize(480, 20);
		buttons.add(delete, BorderLayout.EAST);
		buttons.add(back, BorderLayout.WEST);
	


		
		this.setBackground(Color.BLACK);
		
		//add label to panel
		labelPhoto.setHorizontalAlignment(0);
		this.add(labelPhoto);
		
		//add icon to label 
		this.setLayout(new BorderLayout());

		this.add(labelPhoto, BorderLayout.CENTER);
		this.add(buttons, BorderLayout.NORTH);
		this.setSize(480, 755);
		this.setVisible(true);
		
		
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(picturePathText.endsWith(".png")){
			ImageIcon pngBackground = new ImageIcon ("./src/png_BackGround.jpg");
			Image monImage = pngBackground.getImage();
			g.drawImage(monImage, 0, 0, this);
		}

	}
	
	
	public PictureDisplay() {
		
	}



	// adapte l'image à la taille 
	protected ImageIcon labelPictureSize (JLabel label, ImageIcon icon){
		
			
			double width = icon.getIconWidth();
			double height = icon.getIconHeight();
			
			int labelWidth = label.getWidth();
			int labelHeight = label.getHeight();
			
			if(height > width && height > labelHeight){
				width = labelHeight/height * icon.getIconWidth();
				height = labelHeight;		
			}
			else if(width > height && width > labelWidth){
				height = labelWidth/width * icon.getIconHeight();
				width = labelWidth;
			}
			else if (width == height){
				if(width > labelWidth){
				height = labelWidth;
				width = labelWidth;
				}
			}
					

			label.setSize((int)width, (int)height);
			
			/*
			 * Lien : https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-
			 * imageicon Auteur: trolologuy
			 * Date : 20.08.2013
			 * Utilité: permet de redimensionner une image 
			 */
		
			Image imageCard = icon.getImage(); // transform it
			Image newimg = imageCard.getScaledInstance(label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH); // scale
																									// it																		// way
			icon = new ImageIcon(newimg); // transform it back
			
			return icon;
	}
	
	public JLabel getLabelPhoto() {
		return labelPhoto;
	}


	public void setLabelPhoto(JLabel labelPhoto) {
		this.labelPhoto = labelPhoto;
	}




}
