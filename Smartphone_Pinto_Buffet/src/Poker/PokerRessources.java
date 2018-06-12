package Poker;
/* AUTEUR: Samuel Pinto Da Silva
 * NOM DE LA CLASSE: PokerRessources
 * DESCRIPTION: Classe contenant les constantes et les méthodes permettant de jouer: tailles des tableaux de cartes,
 * 				méthodes de pioche, remplissage de deck, retournement de cartes, affichage de carte, etc.
 * 				Contient également des méthodes de test basées sur des system.out.prinltln.
 * 				
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class PokerRessources {

	// -------------------TAILLES DES TABLEAUX DE CARTES----------------------------------------
	protected final int HANDSIZE = 2;
	protected final int FLOPSIZE = 3;
	protected final int FLOPHANDSIZE = HANDSIZE + FLOPSIZE;
	protected final int DECKSIZE = 52;
	
	// ------------------- GAME BACKGROUND ----------------------------------------
	ImageIcon CARPET = new ImageIcon(getClass().getClassLoader().getResource("tapis.jpg"));
	
	// ------------------- FONTS ----------------------------------------
	protected final Font BIGPOKERFONT = new Font("Times New Roman", Font.PLAIN, 25);
	protected final Font SMALLPOKERFONT = new Font("Times New Roman", Font.BOLD, 20);
	
	// ------------------- BORDERS ----------------------------------------
	protected final Border POKERBORDER = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.YELLOW);
	protected final Border WARNINGBORDER = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED);
	protected final Border LOOSERBORDER = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.RED);
	protected final Border WINNERBORDER = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GREEN);
	protected final Border TIEDBORDER = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE);
	protected final Border BLACKBORDER = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
	
	// -------------------GAME METHODS----------------------------------------------
	
	// Permet de retourner une carte
	protected void retourner(Card[] cardTab) {

		// Parcourt le tableau de cartes
		for (int i = 0; i < cardTab.length; i++) {

			// la met face recto si elle est face verso
			if (cardTab[i].isVisible() == false) {
				cardTab[i].setVisible(true);

			// la met face verso si elle est face recto
			} else if (cardTab[i].isVisible() == true) {
				cardTab[i].setVisible(false);

			}
		}
	}
	
	// Permet de piocher une carte dans le deck de 52 cartes
	protected Card[] piocher(Card[] deck, Card[] pioche) {

		// ID de la carte piochée
		int piocheID;

		// Parcours le tableau de cartes dans lequel on va insérer nos pioches (flop ou hand)
		for (int i = 0; i < pioche.length; i++) {

			// l'ID de la carte est aléatoire et limité au nombre de cartes du deck-1
			piocheID = (int) (Math.random() * 52);

			// tant que l'on est tombé sur une carte piochée, on réincrémente l'ID de la pioche
			while (deck[piocheID].isPiochee() == true) {
				piocheID = (int) (Math.random() * 52);
			}

			// la carte sur laquelle on est positionné = la carte située à l'ID correspondant à l'ID dans le deck
			pioche[i] = deck[piocheID];
			deck[piocheID].setPiochee(true); // On défini la carte sélectionnée dans le deck comme piochée
			pioche[i].setVisible(true); // On rend la carte visible 
		}

		return pioche;
	}
	
	// Rempli le deck de cartes
	protected void fillDeck(Card[] deckFull) {

		for (int i = 0; i < deckFull.length; i++) {

			// instancie chaque nouvelle carte et l'ajoute au deck 
			deckFull[i] = new Card();
			deckFull[i].setIdCard(i);

		}

	}
	
	// crée un nouveau flop et le dispose face cachée 
	protected void disposeFlop (Card[] deck, Card[] flop){
		
		piocher(deck, flop);
		retourner(flop);
		
	}
	
	// Créé un tableau flopHand avec les tableaux flop et hand
	protected Card[] createHand(Card[] flop, Card[] hand) {
		
		Card[] flopHand = new Card[FLOPHANDSIZE];

		// parcourt le flop et l'ajoute au flopHand
		for (int i = 0; i < flop.length; i++)
			flopHand[i] = flop[i];
		
		// parcourt la main et l'ajoute au flopHand à la suite du flop
		for (int i = 0; i < hand.length; i++)
			flopHand[i + flop.length] = hand[i];

		return flopHand;
	}

	// rafraichi le panel de mise 
	protected void refreshBetPanel(PlayerPanel pPanel, int bet, int money){
		
		pPanel.informations.remove(pPanel.betLabel);
		pPanel.setBetLabel(bet, money);
		pPanel.informations.add(pPanel.betLabel, BorderLayout.CENTER);
	}
	
	// rafraichi le panel de cartes face recto du joueur en question
	protected void refreshRectoPanel(PlayerPanel pPanel, JPanel rectoCardPanel, Joueur joueur, Card[]deck, Card[]flop){
		
		pPanel.remove(rectoCardPanel);
		joueur.newPioche(flop, deck);
		pPanel.rectoCards = joueur.getRectoCardsPanel();
		pPanel.add(rectoCardPanel, BorderLayout.EAST);
		
	}


	
	/*
	 * Lien : https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-
	 * imageicon Auteur: trolologuy
	 * Date : 20.08.2013
	 * Utilité: permet de redimensionner une image 
	 */
	protected ImageIcon resizeCardImage(ImageIcon imageIconCard, int width, int height) {

		Image imageCard = imageIconCard.getImage(); // transform it
		Image newimg = imageCard.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); 
		
		imageIconCard = new ImageIcon(newimg); // transform it back

		return imageIconCard;
	}

	// permet d'ajouter les images d'un tableau de cartes à un tableau de JLabel, selon des dimensions insérées
	protected void displayImageCard(Card[] cardTab, JLabel[] cardLabelTab, int width, int height) {

		ImageIcon imageIconCard = new ImageIcon();
		

		for (int i = 0; i < cardTab.length; i++) {

			if (cardTab[i].isVisible() == true)
				imageIconCard = cardTab[i].getgCard();
			else
				imageIconCard = new ImageIcon(getClass().getClassLoader().getResource("back.jpg"));

			imageIconCard = resizeCardImage(imageIconCard, width, height);

			cardLabelTab[i] = new JLabel(imageIconCard);

		}

	}

	
	// -------------------TEST/SYSOUT METHODS-------------------------------------------------------
	protected void displayCard(Card[] deck, int idCard) {

		System.out.println("\t" + deck[idCard].getCardName() + " de " + deck[idCard].getColor() + "("
				+ deck[idCard].getIdCard() + ")");
	}

	protected void displayCard(Card card) {

		System.out.println("\t" + card.getCardName() + " de " + card.getColor() + "(" + card.getIdCard() + ")");
	}

	protected void displayCardTab(Card[] cardTab) {

		for (int i = 0; i < cardTab.length; i++) {

			displayCard(cardTab, i);

		}
	}

	protected void displayReturnedCardTab(Card[] cardTab) {
		for (int i = 0; i < cardTab.length; i++) {

			if(cardTab[i].isVisible() == true)
			displayCard(cardTab, i);
			
			else
				System.out.println("\t???????????");

		}
	}

	protected void displayDeck(Card[] deck) {

		for (int i = 0; i < deck.length; i++) {
			// On l'affiche à condition qu'elle ne soit pas piochée
			if (deck[i].isPiochee() == false) {

				displayCard(deck, i);
			}
		}
	}
	
	protected void displayInfoCard(Card[] cardTab){
		for (int i = 0; i < cardTab.length; i++) {
			
		System.out.println("-------------------------------------");
		System.out.println("ID Card : " + cardTab[i].getIdCard());
		System.out.println("\tNumberCard :" + cardTab[i].getNumberCard());
		System.out.println("\t" + cardTab[i].getCardName() + " de " + cardTab[i].getColor());
		}
	}
	
	protected void displayScore(Joueur player){
		
		System.out.println();
		System.out.println(player.getPlayerName() + ":");
		System.out.println("Main : " + player.getHandFound().getHTName());
		System.out.println("Points" + player.getPoints());
		System.out.println("Hand value " + player.getValueHand());
		System.out.println("Hight card " + player.getHight().getCardName() + "/" + player.getHight().getColor());
	}

	
	protected void displayRules(HandType[] rulesTab) {

		for (int i = 0; i < rulesTab.length; i++) {
			System.out.print("\nIndex [" + i + "]");
			System.out.print("Nom : " + rulesTab[i].getHTName() + "\n");
			System.out.print("Found : " + rulesTab[i].isFound() + "\n");
			System.out.print("Valeur: " + rulesTab[i].getValue() + "\n");
			System.out.print("Seconde valeur : " + rulesTab[i].getSecondValue() + "\n" );
		}
	}
	

}
