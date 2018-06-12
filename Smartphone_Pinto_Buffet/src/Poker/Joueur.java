package Poker;

/* AUTEUR: Samuel Pinto Da Silva
 * NOM DE LA CLASSE: Joueur
 * DESCRIPTION: Permet de cr�er un objet joueur. Il poss�de une main et un flopHand (celui-ci d�finit 
 * 				par le flop et sa main). Ses points sont les points rapport�s par sa main (sa valeur
 * 				directe). "valueHand" est d�fini par le total des cartes lors des mains num�riques ou
 * 				les suites. Les autres param�tres sont d�taill�s en commentaire par la suite.
 * 				
 */

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Joueur {

	private String playerName; // nom du joueur
	private Card hand[]; // main du joeur
	private Card flopHand[]; // main du joueur + flop
	private int points; // points du joueur
	private int valueHand; // valeur de la main en soit (en cas de paire, de
							// suite ou de quinte)
	private Card hight; // carte du joueur la plus haute
	private HandType handFound; // main trouv�e (brelan, quinte etc.)
	private int bet; // somme que le joueur souhaite miser
	private int money = 3000; // somme d'argent avec laquelle chaque joueur
								// d�marre
	private JPanel rectoCardsPanel; // panel de cartes recto
	private JPanel versoCardsPanel; // panel de cartes verso
	private boolean Winner; // d�fini le joueur perdant ou gagnant

	// instance de la classe PokerRessources
	private PokerRessources pRessources = new PokerRessources();

	// constructeur du joueur
	public Joueur(String playerName) {

		setPlayerName(playerName);

	}
	// --------------------------------METHODES----------------------------------------------------------------------------

	// calcul de la nouvelle pioche � chaque tour
	protected Card[] newPioche(Card[] flop, Card[] deck) {


		HandType myHandType = new HandType(); // cree une nouvelle instance de
												// handType

		this.hand = new Card[pRessources.HANDSIZE]; // cree un nouveau tableau
													// de cartes
		pRessources.piocher(deck, this.hand); // pioche de nouvelles cartes

		this.flopHand = new Card[pRessources.FLOPHANDSIZE]; // cree un nouveau
															// flopHand
		setRectoCardsPanel(this.rectoCardsPanel); // cree un nouveau
													// rectoCardsPanel
		setVersoCardsPanel(this.versoCardsPanel); // cree un nouveau
													// versoCardsPanel
		setFlopHand(pRessources.createHand(flop, this.hand)); // ajoute au flopHand
														// les cartes du nouveau
														// flop et la nouvelle
														// main du joueur
		setHight(hand); // d�fini la carte la plus forte de la main du joueur
		setHandFound(myHandType.handSearching(this.flopHand)); // d�fini la main
																// trouv�e grace
																// au flopHand
		setPoints(handFound); // d�fini le nbre de points
		setValueHand(handFound); // d�fini la valeur de la main

		return flopHand;
	}

	// ajoute les images � un panel de cartes
	private JPanel playerCardsPanel(JPanel playerCardsPanel) {

		playerCardsPanel = new JPanel(); // cr�e un nouveau JPanel
		JLabel playerHandCard[] = new JLabel[2]; // cr�e un nouveau tableau de
													// jlabels
		for (int i = 0; i < playerHandCard.length; i++) {
			playerHandCard[i] = new JLabel(); // instancie les deux jlabels
		}

		pRessources.displayImageCard(this.hand, playerHandCard, 72, 100); // ajoute
																			// les
																			// images
																			// uax
																			// jlabels

		playerCardsPanel.setPreferredSize(new Dimension(160, 120)); // d�fini
																	// les
																	// dimensions
		playerCardsPanel.setOpaque(false); // d�fini le panel comme transparent
		// ajoute les jlabels au jpanel
		playerCardsPanel.add(playerHandCard[0]);
		playerCardsPanel.add(playerHandCard[1]);

		return playerCardsPanel;
	}

	// --------------------------------GETTERS &
	// SETTERS----------------------------------------------------------------------------

	// -GETTERS---------------------------------------------------

	public Card[] getHand() {
		return hand;
	}

	public Card[] getFlopHand() {
		return flopHand;
	}

	public int getPoints() {
		return points;
	}

	public int getValueHand() {
		return valueHand;
	}

	public Card getHight() {
		return hight;
	}

	public HandType getHandFound() {
		return handFound;
	}

	public int getBet() {
		return bet;
	}

	public int getMoney() {
		return money;
	}

	public String getPlayerName() {
		return playerName;
	}

	public JPanel getRectoCardsPanel() {
		return rectoCardsPanel;
	}

	public JPanel getVersoCardsPanel() {
		return versoCardsPanel;
	}

	public boolean isWinner() {
		return Winner;
	}

	// -SETTERS---------------------------------------------------

	public void setHand(Card[] hand) {
		this.hand = hand;
	}

	public void setFlopHand(Card[] flopHand) {
		this.flopHand = flopHand;
	}

	public void setPoints(HandType handFound) {

		this.points = handFound.getValue();
	}

	public void setValueHand(HandType handFound) {

		this.valueHand = handFound.getSecondValue();
	}

	// d�fini la carte la plus forte
	public void setHight(Card[] hand) {

		Card hight = null;

		// compare les deux cartes et d�fini la plus forte
		if (hand[0].getNumberCard() > hand[1].getNumberCard())
			hight = hand[0];
		else if (hand[0].getNumberCard() < hand[1].getNumberCard())
			hight = hand[1];
		else
			hight = hand[1];

		this.hight = hight;
		;
	}

	public void setHandFound(HandType handFound) {

		if (handFound.getValue() == 0)
			handFound.setDisplay(this.hight.getCardName() + " de " + this.hight.getColor());

		this.handFound = handFound;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	// cree un nouveau rectoCardsPanel
	public void setRectoCardsPanel(JPanel rectoCardsPanel) {

		this.rectoCardsPanel = playerCardsPanel(rectoCardsPanel);
	}

	// cree un nouveau versoCardsPanel
	public void setVersoCardsPanel(JPanel versoCardsPanel) {

		// retourne la main pour cr�e un panel de cartes verso
		pRessources.retourner(this.hand);

		this.versoCardsPanel = playerCardsPanel(versoCardsPanel);
	}

	public void setWinner(boolean winner) {
		Winner = winner;
	}

}
