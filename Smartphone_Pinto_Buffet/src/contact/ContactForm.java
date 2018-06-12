package contact;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
//import java.awt.Image;
import java.awt.event.ActionListener;
//import java.nio.file.Path;

import javax.swing.ImageIcon;
//import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Ressources.Button;
import Ressources.FlatTextField;
import gallery.Photo;


public class ContactForm extends JPanel {
	
	private JLabel nom = new JLabel("Nom");
	private JLabel prenom = new JLabel("Prenom");
	private JLabel phone = new JLabel("Téléphone");
	private JLabel adresse = new JLabel("Adresse");
	private JLabel mail = new JLabel("Mail");
	private String adressePhoto="image/icons/photoprofile.png";
	

	private FlatTextField tnom;
	private FlatTextField tprenom;
	private FlatTextField tphone;
	private FlatTextField tadresse;
	private FlatTextField tmail;

	private Button delete = new Button(new ImageIcon("image/icons/delete.png"), 480, 40, new Color(222, 44, 60));

	private FlowLayout flowLayout = new FlowLayout();
	private JPanel gridPanel = new JPanel(flowLayout);
	private JPanel topPanel = new JPanel(new BorderLayout());
	private JPanel formulairePanel = new JPanel(new GridLayout(5, 2, 10, 10));
	private JPanel bottomPanel = new JPanel(new GridLayout(1, 1, 10, 10));

	private Dimension dimension = new Dimension(400, 200); // dimension des panels et textfield
	private Font font22 = new Font("Times New Roman", Font.PLAIN, 22); // Changement police en Times New Roman, Gras,22px

	public Button profilePhoto;
	private ImageIcon photoccontact;
	private Photo photo;
	private Contact contact;
	private boolean editable;

	//  Constructeur du formulaire editable
	//  @param editable
	
	public ContactForm(boolean editable) 
	{
		this.editable = editable;
		this.profilePhoto = new Button(new ImageIcon(adressePhoto), 480, 300);
		paintPanel();
	}

	// Constructeur du formulaire de modification non editable
	// @param contact
	// @param editable
	 

	public ContactForm(Contact contact, boolean editable) 
	{
		this.contact = contact;
		this.editable = editable;
		this.profilePhoto = new Button(contact.getProfilePhoto(), 480, 300, this.contact.getID());
		paintPanel();
		setFieldsText();
		gridPanel.add(bottomPanel);
		bottomPanel.setPreferredSize(new Dimension(400, 60));
		bottomPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
		bottomPanel.setOpaque(false);
	}
	
	 //Méthode de dessin du panel
	 

	public ContactForm() {
		// TODO Auto-generated constructor stub
	}

	private void paintPanel() 
	{
		setLayout(new BorderLayout());
		setOpaque(false);

		tnom = new FlatTextField(editable, Color.WHITE, Color.BLACK);
		tprenom = new FlatTextField(editable, Color.WHITE, Color.BLACK);
		tphone = new FlatTextField(editable, Color.WHITE, Color.BLACK);
		tadresse = new FlatTextField(editable, Color.WHITE, Color.BLACK);
		tmail = new FlatTextField(editable, Color.WHITE, Color.BLACK);

		add(gridPanel);
		gridPanel.add(topPanel);

		flowLayout.setVgap(0);
		topPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
		topPanel.setPreferredSize(new Dimension(480, 300));
		topPanel.add(profilePhoto);

		gridPanel.add(formulairePanel);
		formulairePanel.setPreferredSize(dimension);

		gridPanel.setOpaque(true);
		topPanel.setOpaque(false);
		formulairePanel.setOpaque(false);
		gridPanel.setBackground(new Color(242,206,190)); // créer sa propre couleur

		formulairePanel.add(prenom);
		prenom.setFont(font22);
		prenom.setForeground(Color.WHITE);
		formulairePanel.add(tprenom);

		formulairePanel.add(nom);
		nom.setFont(font22);
		nom.setForeground(Color.WHITE);
		formulairePanel.add(tnom);

		formulairePanel.add(phone);
		phone.setFont(font22);
		phone.setForeground(Color.WHITE);
		formulairePanel.add(tphone);

		formulairePanel.add(adresse);
		adresse.setFont(font22);
		adresse.setForeground(Color.WHITE);
		formulairePanel.add(tadresse);

		formulairePanel.add(mail);
		mail.setFont(font22);
		mail.setForeground(Color.WHITE);
		formulairePanel.add(tmail);
	}

	//	Méthode d'écriture dans les textfields

	private void setFieldsText() 
	{
		tprenom.setText(contact.getPrenom());
		tnom.setText(contact.getNom());
		tphone.setText(contact.getTelephone());
		tadresse.setText(contact.getAdresse());
		tmail.setText(contact.getMail());
	}

	//	 Méthode du reset des textfields


	public void resetTextField()
	{
		tnom.setText(null);
		tprenom.setText(null);
		tphone.setText(null);
		tadresse.setText(null);
		tmail.setText(null);
	}
	
	//	  Méthode de modificatio du mode editable des textfields
	 

	public void changeEditable() 
	{
		editable = !editable;
		tnom.changeEditable();
		tprenom.changeEditable();
		tphone.changeEditable();
		tadresse.changeEditable();
		tmail.changeEditable();

		if (editable) 
		{
			bottomPanel.add(delete);
			updateUI();
		}

		if (editable == false) 
		{
			bottomPanel.removeAll();
			updateUI();
		}
	}

	public Contact getModifiedContact() 
	{
		contact.setPrenom(tprenom.getText());
		contact.setNom(tnom.getText());
		contact.setTelephone(tphone.getText());
		contact.setAdresse(tadresse.getText());
		contact.setMail(tmail.getText());
		
		if (photo != null)
			contact.setPhoto(photo);

		return contact;
	}
	
	//	  Méthode qui retourne le nouveau contact
	//	  @param id
	//	  @return nouveau contact
	 

	public Contact getNewContact(int id) {
	if (tphone.getText().matches("[0-9]*" ) == true){
		if (photo != null)
			return new Contact(id, tprenom.getText(), tnom.getText(), tphone.getText(), tadresse.getText(),
					tmail.getText(), photo);
		
		return new Contact(id, tprenom.getText(), tnom.getText(), tphone.getText(), tadresse.getText(),
				tmail.getText());
	}
	return new Contact(id, tprenom.getText(), tnom.getText(), "", tadresse.getText(),
			tmail.getText());
	}
	
	//	  Met à jour la photo
	//	  @param photo
	 
	
	public void updatePhoto(Photo photo) 
	{
		this.photo = photo;
		topPanel.remove(profilePhoto);
		profilePhoto = new Button(photo.getProfileimage(),480,300, this.contact.getID());
		topPanel.add(profilePhoto);
		topPanel.repaint();
		topPanel.revalidate();
		updateUI();
	}
	
	//	  Listener sur le clique d'ajout d'une photo
	//	  @param actionListener
	 

	public void AddPhotoClick(ActionListener actionListener) 
	{
		profilePhoto.addActionListener(actionListener);
	}

	//	  Listener sur le cliquer de suppression d'un contact
	//	  @param actionListener


	public void AddDeleteClick(ActionListener actionListener) 
	{
		delete.addActionListener(actionListener);
	}
	
	// controle les données du formulaire
	public boolean validation() {
		boolean validated;
		Validation validate = new Validation();
		validated = validate.isNotEmpty(tprenom);
		if (!validated)
			return validated;
		validated = validate.isNotEmpty(tphone);
		if (!validated)
			return validated;
		validated = validate.phoneValide(tphone);
		if (!validated)
			return validated;
		return validated;
	}

	public void setPhoto(String cheminImage) 
	{


		System.out.println("je sios dans Cntact form " + cheminImage);
		profilePhoto = new Button(new ImageIcon(cheminImage),480,300, this.contact.getID());
		profilePhoto.setIcon(new ImageIcon(cheminImage));

	}

	public void setimage(ImageIcon imageIcon) {
		// TODO Auto-generated method stub
		this.photoccontact=imageIcon;
		profilePhoto.setIcon(imageIcon);
		
	}
}