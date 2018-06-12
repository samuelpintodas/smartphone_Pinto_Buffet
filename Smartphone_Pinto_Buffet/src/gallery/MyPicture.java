/* AUTEUR: Samuel Pinto Da Silva
 * NOM DE LA CLASSE: MyPicture
 * DESCRIPTION: Cette classe hérite de ImageIcon. Elle contient pour paramètres le path de l'image et l'image en soit (en ImageIcon).
 * 				Le constructeur crée une MyPicture en récupèrant l'image dans le tableau de Files et défini le picturePathText grâce
 *				à ce tableau également
 */

package gallery;

import java.awt.Image;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ImageIcon;

public class MyPicture extends ImageIcon {
	
	ImageIcon picture; // image 
	String picturePathText; // Path de l'image
	
	// constructeur
	protected MyPicture (int pictureNum){
		
		PicturesFiles picturesFiles = new PicturesFiles(); // instance de PicturesFiles
		picture = picturesFiles.picturesList[pictureNum]; // l'image = l'image située à l'indice sélectionné
		this.picturePathText = picturesFiles.picturesFiles[pictureNum].getAbsolutePath();

		
	}
	
}
