package frame;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.Calendar;
import javax.swing.*;

import Ressources.Button;
import Ressources.Images;
import Ressources.Kernel32;
import contact.ContactApp;
import gallery.GalleryApp;
import Poker.PokerApp;

public class FrameBases extends JFrame {
	
	private JLabel batteryPourcent;
	private JPanel statusPanel = new JPanel();
	private JPanel homePanel = new JPanel();
	private String [] HistoriquePanel=new String[20];
	private int Contactphoto;
	
	
	
	//définission du fond d'écran
	private Images accueilPanel = new Images(new ImageIcon("image/fonds/wallpaper.jpeg"));
	
	//définission des bouton pour accéder aux applications
	private Button gallery =new Button(new ImageIcon("image/icons/gallery.png"),new Color(242,206,190),new Color(181,164,154)); 
	private Button contact =new Button(new ImageIcon("image/icons/contact.png"),new Color(242,206,190),new Color(181,164,154));
	private Button poker = new Button(new ImageIcon("image/icons/carte.png"),new Color(242,206,190),new Color(181,164,154));

	/** The batterypourcent status. Uses the JAN library (included in the project)*/
	Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();
	

	//définission de la fenetre en image smartphone 
	private Images phonePanel = new Images(new ImageIcon("image/fonds/smartphone.png"));
	
	
	//création de l'horloge automatique
	private JLabel heure = new JLabel();
	final private DateFormat DATEFORMAT = new SimpleDateFormat("HH:mm");
	private Timer timer = new Timer(0, new CurrentTime());
	

	// création des boutons du panel fixe
	private Button home = new Button(new ImageIcon("image/icons/home.png"));
	private Button retour =new Button(new ImageIcon("image/icons/retour.png"));
	private Button menu =new Button(new ImageIcon("image/icons/menu.png"));
	private Button power=new Button(new ImageIcon("image/icons/power.png"));
	private ImageIcon batteryStates= new ImageIcon(getbatteryState());
	
	// importation des applications
	private ContactApp contactApp = new ContactApp(this);
	private GalleryApp galleryApp = new GalleryApp(this);
	private PokerApp pokerApp = new PokerApp(this);
	
	
	//définissions d'un cardLAyout
	protected CardLayout cardLayout = new CardLayout();
	private JPanel contentPanel = new JPanel(cardLayout);
	
	//définision d0un flowLAyout pour placer les applications
	private JPanel appsPanel = new JPanel(new FlowLayout());
	
	// constructeur de la FRame principale
	public FrameBases() {
		// démarre l'historisation du changement de card
		HistoriquePanel[0]="AccueilPanel"; 
		//crée le pourcentage de battery automatique
		Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus); 
		batteryPourcent = new JLabel(getbatterypourcentPercent());
		batteryPourcent.setForeground(Color.WHITE);
		//démarre l'horloge
		timer.start();
		

		//définis l'emplacement de notre fenetre image 
		setSize(480, 860);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		
		setContentPane(phonePanel);
		phonePanel.setOpaque(false);
		phonePanel.setLayout(new BorderLayout());
		
		phonePanel.add(contentPanel, BorderLayout.CENTER);
		
		
		//définis la barre du haut de notre smartphone
		phonePanel.add(statusPanel, BorderLayout.NORTH);
		statusPanel.add(heure, BorderLayout.WEST);
		heure.setForeground(Color.WHITE);
		heure.setPreferredSize(new Dimension(31, 45));
		statusPanel.setOpaque(false);
		statusPanel.add(power);
		power.addActionListener(new CloseListener());
		statusPanel.add(batteryPourcent);
		power.setPreferredSize(new Dimension(350, 50));
		statusPanel.setPreferredSize(new Dimension(480, 45));
				
		// ajout de nos différentes card(application)
		contentPanel.add(accueilPanel, "AccueilPanel");
		contentPanel.add(contactApp, "ContactApp");
		contentPanel.add(galleryApp,"GalleryApp");
		contentPanel.add(pokerApp,"PokerApp");
		


		accueilPanel.setLayout(new BorderLayout());
		accueilPanel.add(appsPanel, BorderLayout.WEST);
		appsPanel.setOpaque(false);

		appsPanel.add(contact);
		contact.addActionListener(new ClickContact());
		
		appsPanel.add(gallery);
		gallery.addActionListener(new ClickGallery());
		
		appsPanel.add(poker);
		poker.addActionListener(new ClickPoker());
		
		//definission de la barre du bas de notre smartphone
		phonePanel.add(homePanel, BorderLayout.SOUTH);
		homePanel.setOpaque(false);
		homePanel.setPreferredSize(new Dimension(480, 60));
		homePanel.add(retour,BorderLayout.WEST);
		homePanel.add(home,BorderLayout.CENTER);
		homePanel.add(menu,BorderLayout.EAST);
		home.setPreferredSize(new Dimension(300, 50));
		home.addActionListener(new HomeClick());
		retour.addActionListener(new backClik());
	}
	
	// creation des metode pour naviguer dans l'historique de changement de card (application du smartphone)
	protected void Historise(String panelactif) {
		for(int a=0;a<HistoriquePanel.length;a++) {
			if(HistoriquePanel[a]==null) {
				HistoriquePanel[a]=panelactif;
				return;
			}
		}
	}
	protected String getHistorique() {
		for(int a=HistoriquePanel.length-1;a>0;a--) {
			if(HistoriquePanel[a]!=null) {
				HistoriquePanel[a]=null;
				return HistoriquePanel[a-1];
			}
		}
		return null;
	}
	// creation de la metode pour changer de card
	public void changeCard(String card){
		Historise(card);
		cardLayout.show(contentPanel, card);
	}
	
	public void changeCard(String card, int idContact){
		Historise(card);
		gallery.setIdContact(idContact);
		cardLayout.show(contentPanel, card);
	}
	//getter et setter
	private String getbatterypourcentPercent()
	{
		return batteryStatus.getBatteryLifePercent() ;
	}
	
	private String getbatteryState()
	{
		return batteryStatus.getBatterystate() ;
	}
	
	public void setContentPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
	}
	
	public CardLayout getCardLayout() 
	{
		return cardLayout;
	}
	
	public JPanel getContentPanel() {
		return contentPanel;
	}
	public GalleryApp getGalleryApp() 
	{
		return galleryApp;
	}
	
	public int isContactphoto() {
		
		return Contactphoto;
	}
	public void setContactphoto(int thisID) {
		Contactphoto = thisID;
	}
	
	//creation de la classe pour le temps réel
	class CurrentTime implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			Calendar now = Calendar.getInstance();
			heure.setText(DATEFORMAT.format(now.getTime()));
			
		}
	}
	// création de l'action permettant de ferme le smartphone/fenetre
	class CloseListener implements ActionListener 
	{
		@Override
	    public void actionPerformed(ActionEvent e) 
	    {
			contactApp.serializeObject();
			dispose();
	        System.exit(0);
	    }
	}
	//creation de l'action du bouton accueil
	class HomeClick implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			changeCard("AccueilPanel");
		}
	}
	//creation de l'action du bouton contact
	class ClickContact implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			changeCard("ContactApp");
		}
	}
	//creation de l'action du bouton gallery
	class ClickGallery implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			changeCard("GalleryApp");
		}
	}
	//creation de l'action du bouton poker
	class ClickPoker implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			changeCard("PokerApp");
		}
	}
	//creation de l'action du bouton retour
	class backClik implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {
			cardLayout.show(contentPanel, getHistorique());//on affiche la dernière couche
		}
	};
	


	
	
}
	