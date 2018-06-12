package Poker;
/* AUTEUR: Samuel Pinto Da Silva
 * NOM DE LA CLASSE: Card
 * DESCRIPTION: Représente la carte. Chaque carte possède un ID (propre à chaque carte), un numberCard (défini sa valeur)
 * 				un nom de carte (de de 2 à 10 + As, Valet, Reine, Roi), sa couleur (coeur, piques etc), un booléen définissant
 * 				si la carte est visible, un autre définissant si elle est piochée, une image (gCard) et un path avec le chemin
 * 				système de la carte.
 */

import javax.swing.ImageIcon;

public class Card implements Comparable<Card>
{
	
	private int idCard;	// ID de la carte. Chaque carte est unique de par son identifiant
	private int numberCard;	// numéro de la Card dans sa couleur (de 1 à 13, correspondant au 2 jusqu'à l'As).
	private String cardName; // nom de la Card (concerne surtout As, Valet, Reine, Roi)
	private String color;	// couleur de la Card (Piques, Coeurs etc...)
	private boolean piochee;	// indique si la Card a été piochée ou non
	private boolean visible;	// indique si l'on peut voir la carte
	private ImageIcon gCard;	// image correspondant à la carte
	private String cardPath;	// chemin systeme correspondant à l'image de la carte
	


	// constructeur de la carte
	protected Card() {
		
		this.piochee = false; // valeur par defaut: non piochee
		this.visible = false; // valeur par defaut: non visible (= face verso)
	}
	
	// permet de comparer les numéros de cartes entre eux
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
		
		// tout sauf le cardName est généré à partir de l'identifiant
		setColor(idCard);
		setNumberCard(idCard);
		setCardName(numberCard);
		setPiochee(false);
		setgCard(idCard);
	}


	protected void setNumberCard(int numberCard) {
		
		// On ajoute 1 pour que les numeros partent de 1
		numberCard = idCard+1;
		
		// les 52 cartes sont réparties en 4 groupes de 13 grace à un modulo 13
		numberCard = numberCard%13;
		
		// pour éviter une valeur = 0, si on tombe sur 0, numberCard = 13
		if(numberCard==0)
			numberCard= 13;
			
		
		this.numberCard = numberCard;
		
		
	}


	protected void setCardName(int numberCard) {
		
		// si on a des cartes "numériques" (pas d'As, de valet, de reine ou de roi)
		if(numberCard>0 && numberCard<10)
		{
			// cardName = numéro de la carte +1 (1 = 2, 2 = 3,..., 9 = 10)
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
		
		// les images des cartes sont nommées selon leur identifiant
		// donc, on concatène le chemin système + l'identifiant + ".jpg"
		cardPath =  idCard + ".jpg";
		
		// on crée la carte en fonction du chemin systeme créé
		gCard = new ImageIcon(getClass().getClassLoader().getResource(cardPath));
	}


	
	

}
