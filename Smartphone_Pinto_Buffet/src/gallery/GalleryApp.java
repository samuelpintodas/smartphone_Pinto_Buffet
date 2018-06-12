/* AUTEUR: Samuel Pinto Da Silva
 * NOM DE LA CLASSE: GalleryApp
 * DESCRIPTION: Cette classe contient le cardLayout qui contient donc Gallery et PictureDisplay.
 * 				Dans celle-ci nous définissons le listener des Jbuttons de la gallerie, mais également
 * 				des JButtons "delete" et "back" situés dans le display.
 */

package gallery;


import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import contact.Contact;
import contact.ContactApp;
import contact.ContactForm;
import frame.FrameBases;
import gallery.Photo;


public class GalleryApp extends JPanel {

	private CardLayout cardLayout = new CardLayout();


	protected Gallery Gallery; // panel Gallery
	protected PictureDisplay display; // panel display
	protected int contactID = -1; // ajout raphy pour liaison contact
	protected JPanel GalleryAppPanel; // panel acueillant le cardLayout
	protected FrameBases frambases;
	protected Photo p;

	
	// constructeur du panel de l'application Gallery
	public GalleryApp(FrameBases frambases) {
		GalleryAppPanel = new JPanel();
		GalleryAppPanel.setLayout(cardLayout);
		
		contactID=frambases.isContactphoto();
		Gallery = new Gallery(); 
		display = new PictureDisplay();
		
		

		// on ajoute la Gallery au cardLayout
		GalleryAppPanel.add(Gallery.galleryScroll, "Gallery");

		for (int i = 0; i < Gallery.getPictureButton().length; i++) {
			
			// on ajoute un listener à chaque bouton
			Gallery.getPictureButton()[i].addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {		

					Icon icon = (((PictureButton) e.getSource()).getIcon());
					Path thisPath = (((PictureButton) e.getSource()).picturePath); // récupère le path de chaque image
					PictureDisplay display = new PictureDisplay(icon, thisPath); // crée un display de l'image
					setDisplay(display); // on défini le display
					GalleryAppPanel.add(display, "display"); // on ajoute le display au cardLayout
					cardLayout.show(GalleryAppPanel, "display"); // on affiche le panel display
					
					// au panel display on ajoute un listener au bouton back
					display.back.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
						
							cardLayout.removeLayoutComponent(display);// on retire ce panel
							cardLayout.show(GalleryAppPanel, "Gallery");// on raffiche la Gallery

						}

					});
					
					// on ajoute un listener au bouton delete
					display.delete.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent f) {
							// TODO Auto-generated method stub
							
						try {
							// supprime l'image selon le Path récupéré
							Files.deleteIfExists(thisPath);
							for (int i = 0; i < Gallery.getPictureButton().length; i++) {
								
								// on supprime le bouton ayant le même path
								if(Gallery.getPictureButton()[i].picturePath == thisPath){
									Gallery.remove(Gallery.getPictureButton()[i]);
								}
							}
							// on raffraichi la Gallery
							Gallery.galleryScroll.revalidate();
							Gallery.galleryScroll.repaint();
							
							// on affiche la Gallery
							cardLayout.show(GalleryAppPanel, "Gallery");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("Suppression d'image impossible!");
						}
						}

					});
				
				}
			});
		}

		// on ajoute le panel du cardLayout
		GalleryAppPanel.setSize(480, 755);
		this.add(GalleryAppPanel);


	}

	protected int getContactID() {
		return contactID;
	}

	protected void setcontactID(int contactphoto) {
		this.contactID = contactID;
	}

	public PictureDisplay getDisplay() {
		return display;
	}

	public void setDisplay(PictureDisplay display) {

		this.display = display;
	}
	public Photo getPhoto(){
		return p;
	}

}



