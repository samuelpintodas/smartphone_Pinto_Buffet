/* AUTEUR: Samuel Pinto Da Silva
 * NOM DE LA CLASSE: PictureButton
 * DESCRIPTION: Crée des JButtons prenant en paramètre une image correspondante. 
 * 				On leur attribue également le path de l'image afin de le récupèrer dans le display
 * 				pour supprimer la bonne image lors du "delete".
 */

package gallery;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class PictureButton extends JButton {
	
	MyPicture mypicture; // image correspondante
	Path picturePath; // path de l'image
	
	protected PictureButton(MyPicture mypicture){
		Dimension pictureButtonDim = new Dimension(140,140);
		
		// le path = le path de l'image correspondante
		picturePath = Paths.get(mypicture.picturePathText);
		
		
		setPreferredSize(pictureButtonDim);
		setSize(pictureButtonDim);
		setMinimumSize(pictureButtonDim);
		setMaximumSize(pictureButtonDim);
		setIcon(mypicture.picture);
		
		
	}

	public Path getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(Path picturePath) {
		this.picturePath = picturePath;
	}
	
	

}
