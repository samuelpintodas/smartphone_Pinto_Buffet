package contact;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Ressources.AppAddPanel;
import Ressources.AppListPanel;
import Ressources.AppModifyPanel;
import Ressources.Button;
import frame.FrameBases;
import gallery.Gallery;
import gallery.Photo;
import gallery.PictureButton;

public class ContactApp extends JPanel {

	private FrameBases FrameBase;
	private ArrayList<Contact> contacts = new ArrayList<Contact>();

	private CardLayout cardLayoutContact = new CardLayout();
	private JPanel contentPanelContact = new JPanel(cardLayoutContact);
	private gallery.Gallery contactGallery = new Gallery();
	private ContactList contactList;
	private ContactModify contactModify;
	private ContactAdd contactAdd;
	private ImageIcon Photo;


	public ContactApp(FrameBases FrameBase) {
		this.FrameBase = FrameBase;

		deSerializeObject();
		contactList = new ContactList();
		setLayout(new BorderLayout());
		add(contentPanelContact);
		contentPanelContact.add(contactList, "ListeContact");
		contentPanelContact.add(contactGallery, "ContactGallery");

		for (int i = 0; i < contactGallery.getPictureButton().length; i++) {

			contactGallery.getPictureButton()[i].addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					
					String url = (((PictureButton) e.getSource()).getPicturePath()).toString();
					
					for (int j = 0; j < contacts.size(); j++) {
						
						if(contacts.get(j).getID() == contactGallery.getIdContact()){
							
							ImageIcon pictureContact = new ImageIcon (url);
							contacts.get(j).setProfilePhoto(pictureContact);
							cardLayoutContact.show(contentPanelContact, "ListeContact");
						}
					}
				}
			});
		}

		cardLayoutContact.show(contentPanelContact, "ListeContact");
	}

	// Sauvegarde des contacts

	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<Contact> contacts) {

		setId(contacts);

		this.contacts = contacts;
	}

	public void serializeObject() {
		try {
			FileOutputStream fichier = new FileOutputStream("serials/contacts.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			oos.writeObject(contacts);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Chargement des contacts

	@SuppressWarnings("unchecked")
	public void deSerializeObject() {
		try {
			FileInputStream fichier = new FileInputStream("serials/contacts.ser");
			ObjectInputStream ois = new ObjectInputStream(fichier);
			contacts = (ArrayList<Contact>) ois.readObject();
			ois.close();
		} catch (IOException e) {
			contacts = new ArrayList<Contact>();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
	}

	// @param contact

	private void modifyContact(Contact contact) {
		contacts.set(contacts.indexOf(contact), contact);
		contactList.update();
	}

	// @param contact

	private void removeContact(Contact contact) {
		contacts.remove(contact);
		contactList.update();
	}

	// @param contact

	private void addContact(Contact contact) {
		contact.setID(contacts.size());
		contacts.add(contact);
		contactList.update();
	}

	// @return l'id du prochain contact après ajout

	private int getNextId() {
		if (contacts.size() == 0)
			return 0;
		return contacts.get(contacts.size() - 1).getID() + 1;
	}

	// Liste des contacts

	class ContactList extends AppListPanel {
		private JPanel panelListe = new JPanel();
		private JScrollPane scrollPane = new JScrollPane(panelListe);

		// Constructeur de la liste des contacts

		public ContactList() {
			super("contact", new Color(181, 164, 154));

			// BoxLayout permet d'afficher uniquement le scrollpane en cas de
			// besoin
			panelListe.setLayout(new BoxLayout(panelListe, BoxLayout.Y_AXIS));
			panelListe.setBackground(new Color(242, 206, 190));

			afficheContacts();

			super.getAdd().addActionListener(new AddClick());
			super.getBack().addActionListener(new BackClick());

			// Suppression des bordures automatiques du scrollpane
			scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
			add(scrollPane, BorderLayout.CENTER);
		}

		// Affiche les contacts avec action listener associés

		public void afficheContacts() {
			ArrayList<Contact> contactsSorted = (ArrayList<Contact>) contacts.clone();
			contactsSorted = sort(contactsSorted);
			Button buttonTemp;
			for (int i = 0; i < contacts.size(); i++) {
				if (i % 2 == 0)
					buttonTemp = new Button(contactsSorted.get(i).getPrenom() + " " + contactsSorted.get(i).getNom(),
							contactsSorted.get(i).getMiniature(), new Color(238, 166, 167), new Color(181, 164, 154),
							16);
				else
					buttonTemp = new Button(contactsSorted.get(i).getPrenom() + " " + contactsSorted.get(i).getNom(),
							contactsSorted.get(i).getMiniature(), new Color(246, 228, 208), new Color(181, 164, 154),
							16);
				panelListe.add(buttonTemp);
				buttonTemp.addActionListener(new ContactClick(contactsSorted.get(i)));
				contactModify = new ContactModify(contactsSorted.get(i));
				contentPanelContact.add(contactModify, "" + contactsSorted.get(i).getID());
			}
		}

		// Trie les contacts par ordre alphabétique
		// @param contacts
		// @return contacts trié

		public ArrayList<Contact> sort(ArrayList<Contact> contacts) {

			Collections.sort(contacts, new Comparator<Contact>() {
				@Override
				public int compare(Contact c1, Contact c2) {
					return c1.getPrenom().toLowerCase().compareTo(c2.getPrenom().toLowerCase());
				}
			});
			return contacts;
		}

		// Mise à jour des contacts

		public void update() {
			panelListe.removeAll();
			afficheContacts();
			updateUI();
		}

		// Listener après le click sur un contact

		class ContactClick implements ActionListener {
			Contact contact;

			public ContactClick(Contact contact) {
				this.contact = contact;
			}

			public void actionPerformed(ActionEvent e) {
				cardLayoutContact.show(contentPanelContact, "" + contact.getID());
			}
		}

		// Listener après le click sur ajouter un contact

		class AddClick implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (contactAdd != null)
					contentPanelContact.remove(contactAdd);

				contactAdd = new ContactAdd();
				contentPanelContact.add(contactAdd, "AddContact");
				cardLayoutContact.show(contentPanelContact, "AddContact");
			}
		}

		// Listener après le click sur retour

		class BackClick implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				FrameBase.getCardLayout().show(FrameBase.getContentPanel(), "MainPanel");
			}
		}
	}

	// Panel de modification d'un contact

	class ContactModify extends AppModifyPanel {
		// private ContactPhoto contactPhoto;
		private ContactForm ContactFormulaire;
		private Contact contact;
		private boolean editFlag = false;

		// Constructeur du panel de modification d'un contact
		// @param contact

		public ContactModify(Contact contact) {
			super("MODIFIER UN CONTACT", new Color(181, 164, 154));
			this.contact = contact;
			this.ContactFormulaire = new ContactForm(contact, false);
			add(ContactFormulaire);
			super.getBack().addActionListener(new CancelClick());
			super.getModify().addActionListener(new EditClick());
			super.getSave().addActionListener(new SaveClick());
			ContactFormulaire.AddDeleteClick(new DeleteClick());
			ContactFormulaire.AddPhotoClick(new PhotoClick());
		}

		// Met à jour la photo
		// @param photo

		public void updatePhoto(Photo photo) {
			ContactFormulaire.updatePhoto(photo);
		}

		// Listener cliquer sur annuler modification

		class CancelClick implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				contactList.update();
				cardLayoutContact.show(contentPanelContact, "ListeContact");
			}
		}

		// Listener cliquer sur modifier un contact

		class EditClick implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				ContactFormulaire.changeEditable();
				editFlag = true;
				edit();
			}
		}

		// Listener cliquer sur sauvegarder

		class SaveClick implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// if (ContactFormulaire.validation()) {
				ContactFormulaire.changeEditable();
				modifyContact(ContactFormulaire.getModifiedContact());
				editFlag = false;
				requestFocusInWindow();
				save();
				contactList.update();
				// }
			}
		}

		// Listener cliquer sur supprimer un contact

		class DeleteClick implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeContact(contact);
				contactList.update();
				cardLayoutContact.show(contentPanelContact, "ListeContact");

			}
		}

		// Listener cliquer sur la photo

		class PhotoClick implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (editFlag) {

					System.out.println("TEST: " + contact.getID());

					cardLayoutContact.show(contentPanelContact, "ContactGallery");
					contactGallery.setIdContact(contact.getID());

				}
			}
		}
	}

	// Panel d'ajout d'un contact

	class ContactAdd extends AppAddPanel {
		private ContactForm ContactFormulaire;
		private Contact contact;
		private ImageIcon photo;

		// Constructeur d'ajout d'un contact

		public ContactAdd() {
			super("AJOUTER UN CONTACT", new Color(181, 164, 154));

			this.ContactFormulaire = new ContactForm(true);
			add(ContactFormulaire);
			ContactFormulaire.AddPhotoClick(new PhotoClick());
			super.getCancel().addActionListener(new CancelClick());
			super.getSave().addActionListener(new SaveClick());

		}

		/**
		 * Mise à jour de la photo
		 * 
		 * @param photo
		 */

		public void updatePhoto(Photo photo) {
			ContactFormulaire.updatePhoto(photo);
			ContactFormulaire.AddPhotoClick(new PhotoClick());
		}

		// Listener sauvegarde d'un contact

		class SaveClick implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				// if (ContactFormulaire.validation()) {
				int id = getNextId();
				addContact(ContactFormulaire.getNewContact(id));
				ContactFormulaire.resetTextField();
				contactList.update();
				cardLayoutContact.show(contentPanelContact, "ListeContact");
				return;
				// }
			}
		}

		// Listener annuler l'ajout

		class CancelClick implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayoutContact.show(contentPanelContact, "ListeContact");

			}
		}

		// Listener clique sur la photo d'ajout

		class PhotoClick implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
	

				cardLayoutContact.show(contentPanelContact, "ContactGallery");
				contactGallery.setIdContact(contact.getID());

				FrameBase.changeCard("GalleryApp");

			}
		}
	}

	protected ImageIcon getPhoto() {
		return Photo;
	}

	public void setPhoto(ImageIcon photo) {
		Photo = photo;
	}

	protected void setId(ArrayList<Contact> contacts) {

		for (int i = 0; i < contacts.size(); i++) {

			contacts.get(i).setID(i);
			System.out.println("id contact " + i);
		}

	}

}