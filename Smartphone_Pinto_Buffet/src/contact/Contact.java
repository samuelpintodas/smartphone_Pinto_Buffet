package contact;

import java.awt.Image;
import java.io.Serializable;

import javax.swing.ImageIcon;

import Ressources.Button;
import gallery.Photo;


public class Contact implements Serializable{
	
	private int ID;
	private String nom;
	private String prenom;
	private String telephone;
	private String adresse;
	private String mail;
	private Photo photo;
	private ImageIcon profilePhoto ;
	private ImageIcon miniature ;
	private String url;
	
	

	
	/**
	 Constructeur de contact 
	 * @param ID
	 * @param prenom
	 * @param nom
	 * @param telephone
	 * @param adresse
	 * @param mail
	 */
	public Contact(int ID, String prenom, String nom, String telephone, String adresse, String mail)
	{	
	
		this.prenom = prenom;
		this.nom = nom;
		this.telephone = telephone;
		this.adresse = adresse;
		this.mail = mail;
		profilePhoto = new ImageIcon ("image/icons/photoprofile.png");
		miniature =  new ImageIcon("image/icons/contactprofile.png");
	
	}

	/**
	 * Constructeur de contact avec photo
	 * @param ID
	 * @param prenom
	 * @param nom
	 * @param telephone
	 * @param adresse
	 * @param mail
	 * @param photo
	 */
	public Contact(int ID, String prenom, String nom, String telephone, String adresse, String mail, Photo photo)
	{	
		this.ID = ID;
		this.prenom = prenom;
		this.nom = nom;
		this.telephone = telephone;
		this.adresse = adresse;
		this.mail = mail;
		this.url=photo.getUrl();


		setPhoto(photo);
	}

	// getter et setter
	
	public int getID()  
	{
		return ID;
	}
	/**
	 * 
	 * @param photo
	 */
	public void setPhoto(Photo photo)
	{
		this.photo = photo;
		

		
		ImageIcon	ii = new ImageIcon (photo.getUrl());
		profilePhoto.setImage(ii.getImage());
		miniature.setImage(ii.getImage());
		

	}
	
	public ImageIcon getProfilePhoto() 
	{
		return profilePhoto;
	}
	public void  setProfilePhoto(ImageIcon profilePhoto)
	{
		this.profilePhoto=profilePhoto;
	}

	public ImageIcon getMiniature() 
	{
		return miniature;
	}

	public String getNom() 
	{
		return nom;
	}

	public void setNom(String nom) 
	{
		this.nom = nom;
	}

	public String getPrenom() 
	{
		return prenom;
	}

	public void setPrenom(String prenom) 
	{
		this.prenom = prenom;
	}

	public String getTelephone() 
	{
		return telephone;
	}

	public void setTelephone(String telephone) 
	{
		this.telephone = telephone;
	}

	public String getAdresse() 
	{
		return adresse;
	}

	public void setAdresse(String adresse) 
	{
		this.adresse = adresse;
	}

	public String getMail() 
	{
		return mail;
	}

	public void setMail(String mail) 
	{
		this.mail = mail;
	}

	public void setID(int ID) {
	
		
		this.ID = ID;
		
		
	}
	
		
	
}