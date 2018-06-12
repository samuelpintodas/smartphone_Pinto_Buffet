/* AUTEUR: Samuel Pinto Da Silva
 * NOM DE LA CLASSE: Gallery
 * DESCRIPTION: affiche toutes les vignettes d'images sous forme de JButtons disposés en grille.
 * 				Les listeners sont définis dans GalleryApp. On ajoute ici un JScrollPane afin de parcourir 
 * 				la grille d'images
 */

package gallery;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

public class Gallery extends JPanel {

	PicturesFiles picturesFiles = new PicturesFiles(); 
	MyPicture myPicture; 
	private PictureButton[] pictureButton = new PictureButton[picturesFiles.picturesList.length]; // contient les bouttons d'images

	JPanel allPictures = new JPanel();
	GridLayout galleryLayout = new GridLayout(20,3);
	
	private int idContact; 
	
	JScrollPane galleryScroll;
	
	
	
	// constructeur du panel Gallery
	public Gallery() {
		
		setLayout(galleryLayout);

		this.setSize(480, 755);

		setVisible(true);

		// ajoute un bouton pour chaque image
		for (int i = 0; i < picturesFiles.picturesFiles.length; i++) {

			myPicture = new MyPicture(i);

			getPictureButton()[i] = new PictureButton(myPicture);
			add(getPictureButton()[i]);

		}
		
		// cree le scroll 
		galleryScroll = new JScrollPane(this);
		galleryScroll.setLayout(new ScrollPaneLayout());
		
		Dimension dim = new Dimension (460, 755);
		galleryScroll.setPreferredSize(dim);
		
		JScrollBar verticalScrollBar = galleryScroll.getVerticalScrollBar();
		
	    galleryScroll.setVerticalScrollBar(verticalScrollBar);
	    
		galleryScroll.repaint();
		galleryScroll.revalidate(); 
		
	}

	//----------------GETTERS & SETTERS --------------------------

	//----------------GETTERS-----------------
	public PictureButton[] getPictureButton() {
		return pictureButton;
	}

	
	public int getIdContact() {
		return idContact;
	}

	//----------------SETTERS---------------
	public void setPictureButton(PictureButton[] pictureButton) {
		this.pictureButton = pictureButton;
	}


	public void setIdContact(int idContact) {
		this.idContact = idContact;
	}
}
