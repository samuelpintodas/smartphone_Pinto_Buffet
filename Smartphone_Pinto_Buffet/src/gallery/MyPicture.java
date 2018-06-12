/* AUTEUR: Samuel Pinto Da Silva
 * NOM DE LA CLASSE: MyPicture
 * DESCRIPTION: Cette classe h�rite de ImageIcon. Elle contient pour param�tres le path de l'image et l'image en soit (en ImageIcon).
 * 				Le constructeur cr�e une MyPicture en r�cup�rant l'image dans le tableau de Files et d�fini le picturePathText gr�ce
 *				� ce tableau �galement
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
		picture = picturesFiles.picturesList[pictureNum]; // l'image = l'image situ�e � l'indice s�lectionn�
		this.picturePathText = picturesFiles.picturesFiles[pictureNum].getAbsolutePath();

		
	}
	
}
