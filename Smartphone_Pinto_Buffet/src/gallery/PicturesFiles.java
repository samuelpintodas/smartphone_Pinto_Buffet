/* AUTEUR: Samuel Pinto Da Silva
 * NOM DE LA CLASSE: PictureFiles
 * DESCRIPTION: Sert � g�rer les fichiers des images. Via un dossier d�fini (ici "./src/Images", 
 * 				la classe cr�e un tableau d'images, aussi grand que la quantit� de fichiers contenue dans le dossier, 
 * 				en r�cup�rant leur chemin syst�me.
 */

package gallery;

import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class PicturesFiles {

	File[] picturesFiles; // tableau de fichiers
	File picture; // fichier d'une image
	ImageIcon[] picturesList; // tableau des images au format ImageIcon

	protected PicturesFiles() {

		File picturesFolder = new File("./image/Images"); // dossier contenant les
														// images

		picturesFiles = picturesFolder.listFiles(); // liste les fichiers

		picturesList = new ImageIcon[picturesFiles.length]; // cr�e un tableau
															// d'imageIcon de la
															// taille du
															// picturesFiles

		for (int i = 0; i < picturesFiles.length; i++) {

			// on r�cup�re le path de chaque photo
			String pathFile = picturesFiles[i].getAbsolutePath();

			// on cr�e l'imageIcon � partir de ce path
			picturesList[i] = new ImageIcon(pathFile);

		}
	}
}
