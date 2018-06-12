package Poker;
/* AUTEUR: Samuel Pinto Da Silva
 * NOM DE LA CLASSE: Card
 * DESCRIPTION: Repr�sente la carte. Chaque carte poss�de un ID (propre � chaque carte), un numberCard (d�fini sa valeur)
 * 				un nom de carte (de de 2 � 10 + As, Valet, Reine, Roi), sa couleur (coeur, piques etc), un bool�en d�finissant
 * 				si la carte est visible, un autre d�finissant si elle est pioch�e, une image (gCard) et un path avec le chemin
 * 				syst�me de la carte.
 */

import javax.swing.ImageIcon;

public class Card implements Comparable<Card>
{
	
	private int idCard;	// ID de la carte. Chaque carte est unique de par son identifiant
	private int numberCard;	// num�ro de la Card dans sa couleur (de 1 � 13, correspondant au 2 jusqu'� l'As).
	private String cardName; // nom de la Card (concerne surtout As, Valet, Reine, Roi)
	private String color;	// couleur de la Card (Piques, Coeurs etc...)
	private boolean piochee;	// indique si la Card a �t� pioch�e ou non
	private boolean visible;	// indique si l'on peut voir la carte
	private ImageIcon gCard;	// image correspondant � la carte
	private String cardPath;	// chemin systeme correspondant � l'image de la carte
	


	// constructeur de la carte
	protected Card() {
		
		this.piochee = false; // valeur par defaut: non piochee
		this.visible = false; // valeur par defaut: non visible (= face verso)
	}
	
	// permet de comparer les num�ros de cartes entre eux
	@Override
	public int compareTo(Card c)
	{
		return (numberCard - c.getNumberCard());
	}
	
// --------------------------------GETTERS & SETTERS----------------------------------------------------------------------------		

// -GETTERS---------------------------------------------------	
	
	protected int getIdCard() {
		
		return idCard;
	}


	protected int getNumberCard() {

		return numberCard;
	}


	protected String getCardName() {
		
		return cardName;
	}


	protected String getColor() {

		return color;
	}
	
	protected boolean isPiochee() {
		
		return piochee;
	}
	
	protected boolean isVisible() {
		return visible;
	}
	
	public ImageIcon getgCard() {
		return gCard;
	}
	
	public String getCardPath() {
		return cardPath;
	}



//	-SETTERS---------------------------------------------------	
	
	
	protected void setIdCard(int idCard) {
		
		this.idCard = idCard;
		
		// tout sauf le cardName est g�n�r� � partir de l'identifiant
		setColor(idCard);
		setNumberCard(idCard);
		setCardName(numberCard);
		setPiochee(false);
		setgCard(idCard);
	}


	protected void setNumberCard(int numberCard) {
		
		// On ajoute 1 pour que les numeros partent de 1
		numberCard = idCard+1;
		
		// les 52 cartes sont r�parties en 4 groupes de 13 grace � un modulo 13
		numberCard = numberCard%13;
		
		// pour �viter une valeur = 0, si on tombe sur 0, numberCard = 13
		if(numberCard==0)
			numberCard= 13;
			
		
		this.numberCard = numberCard;
		
		
	}


	protected void setCardName(int numberCard) {
		
		// si on a des cartes "num�riques" (pas d'As, de valet, de reine ou de roi)
		if(numberCard>0 && numberCard<10)
		{
			// cardName = num�ro de la carte +1 (1 = 2, 2 = 3,..., 9 = 10)
			cardName = String.valueOf(numberCard+1);
		}
		
		// les autres cartes ont un nom correspondant au numero de la carte
		switch (numberCard){
		case 10:
			cardName = "Valet";
			break;
		case 11:
			cardName = "Dame";
			break;
		case 12:
			cardName = "Roi";
			break;
		case 13:
			cardName = "As";
			break;
		case 0:
			System.out.println("Carte inexistante");
			break;
		}

		
	}

	
	protected void setColor(int idCard) {
		
		// chaque groupe de 13 cartes a une couleur correspondante
		
		if(idCard>=0 && idCard<13)
			color = "Piques";
		
		else if(idCard>=13 && idCard<26)
			color = "Coeurs";
		
		else if(idCard>=26 && idCard<39)
			color = "Carreaux";
		
		else if(idCard>=39 && idCard<52)
			color = "Trefles";
		
	}
	
	
	protected void setPiochee(boolean piochee) {
		
		this.piochee = piochee;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	
	public void setgCard(int idCard) {
		
		// les images des cartes sont nomm�es selon leur identifiant
		// donc, on concat�ne le chemin syst�me + l'identifiant + ".jpg"
		cardPath =  idCard + ".jpg";
		
		// on cr�e la carte en fonction du chemin systeme cr��
		gCard = new ImageIcon(getClass().getClassLoader().getResource(cardPath));
	}


	
	

}
