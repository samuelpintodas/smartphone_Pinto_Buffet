package gallery;

import java.awt.Image;
//import java.awt.Graphics2D;
//import java.awt.RenderingHints;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import java.io.Serializable;
//import java.nio.file.Path;

//import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import Ressources.Images;
import contact.ContactForm;

public class Photo implements Serializable {

	private int ID;
	private transient Images image;
	private ImageIcon profileimage;
	private ImageIcon profileminiature;
	private String nom;
	private String url;


	/**
	 * Constructeur photo
	 * @param ID
	 * @param nom
	 * @param thisPath
	 */
	public Photo(String nom, String thisPath) 
	{
		this.nom = nom;
		thisPath = url;
//		this.width = image.getWidth();
		this.image=new Images(new ImageIcon(thisPath));
//		this.height = image.getHeight();
		this.profileimage = new ImageIcon(new ImageIcon(thisPath).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
		this.profileminiature = new ImageIcon(new ImageIcon(thisPath).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
		
		
	}
	/**
	 * Croped la photo
	 * @param desiredWidth
	 * @param desiredHeight
	 * @return
	 */
	

	
	
public Photo() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * Retourne l'extension de la photo
	 * @param file
	 * @return
	 */

	public int getID() {
		return ID;
	}


	public String getNom() 
	{
		return nom;
	}

	public String getUrl() 
	{
		return url;
	}

	public void setUrl(String url) 
	{
		System.out.println(url + "je suis dans photo");
		this.url = url;
		setProfileimage(new ImageIcon(url));
	}
	
	public ImageIcon getProfileimage() {
		return profileimage;
	}
	protected void setProfileimage(ImageIcon profileimage) {
		this.profileimage = profileimage;
	}
	public ImageIcon getProfileminiature() {
		return profileminiature;
	}
	protected void setProfileminiature(ImageIcon profileminiature) {
		this.profileminiature = profileminiature;
	}
	
}

