package Poker;

/* AUTEUR: Samuel Pinto Da Silva
 * NOM DE LA CLASSE: 
 * DESCRIPTION: Permet de créer, définir, gérer et comparer les mains possibles pour définir le joueur vainqueur et le perdant
 */

import java.util.ArrayList;
import java.util.Arrays;

public class HandType {

	private String HTName; // nom de la main
	private boolean found; // si elle a été trouvée ou non
	private int value; // valeur de la main
	private int secondValue; // seconde valeur (permet de comparer deux paires
								// par exemple)
	private String display = ""; // affichage de la main (exemple: "paire de
									// Rois")

	private HandType[] allRules = new HandType[10]; // tableau contenant toutes
													// les mains

	public HandType() {

	}

	// --------------------------------METHODES----------------------------------------------------------------------------

	// rempli le tableau de règles
	public void fillRules(HandType rulesTab[]) {

		for (int i = 0; i < rulesTab.length; i++) {
			rulesTab[i] = new HandType(); // crée la règle
			rulesTab[i].setFound(false); // elle est fausse par défaut
			rulesTab[i].setValue(i); // lui donne la valeur équivalente à son
										// indice dans le tableau
			rulesTab[i].setDisplay(""); // crée un display par défaut

		}

		rulesTab[0].setHTName("hauteur"); // la personne ayant la carte la plus
											// haute
		rulesTab[1].setHTName("paire"); // une paire
		rulesTab[2].setHTName("doublePaire"); // deux paires de deux nombres
												// différents
		rulesTab[3].setHTName("brelan"); // 3 cartes du même numéro
		rulesTab[4].setHTName("quinte"); // Suite de 5 cartes
		rulesTab[5].setHTName("color"); // 5 cartes de la même couleur
		rulesTab[6].setHTName("fullHouse"); // un brelan et une paire
		rulesTab[7].setHTName("carre"); // 4 cartes du même numero
		rulesTab[8].setHTName("quinteFlush"); // toutes les cartes de la même
												// couleur
		rulesTab[9].setHTName("royalQuinteFlush"); // Suite d'un 10,
													// Valet,Reine, Roi, As (de
													// la même couleur)

		rulesTab[0].setDisplay("Hauteur"); //
	}

	// cherche la main présente dans un flopHand
	protected HandType handSearching(Card[] flopHand) {

		Arrays.sort(flopHand); // trie les cartes par ordre croissant
		fillRules(allRules); // rempli le tableau de règles
		int presence[] = new int[14]; // tableau de présence des cartes

		HandType wonHand; // main gagnante

		fillPresence(flopHand, presence);
		totalPresence(presence);

		numeralHands(flopHand, presence);
		sequence(flopHand);
		colorHand(flopHand);
		quinteFlush(flopHand);
		quinteFlushRoyale(flopHand);
		highterFound(flopHand);

		wonHand = handFound(allRules);

		return wonHand;
	}

	// cherche la main qui a été trouvée parmis le tableau
	protected HandType handFound(HandType[] rulesTab) {

		HandType found = null;

		// parcours le tableau de regles
		for (int i = 0; i < rulesTab.length; i++) {
			if (rulesTab[i].isFound() == true) { // si la règle a été trouvée

				found = rulesTab[i]; // found = la règle trouvée
				return found;
			}

			else // si aucune règle n'a été trouvée
				found = rulesTab[0]; // la seule règle possible est "hauteur"

		}

		return found;

	}

	// cherche une quinte flush royale
	private HandType quinteFlushRoyale(Card[] flopHand) {

		// si on a trouvé une quinte ET que le numberCard de la 1ere carte est 9
		if (allRules[8].isFound() == true && flopHand[0].getNumberCard() == 9) {

			allRules[9].setFound(true); // on définit la main "quinte flush
										// royale" comme true
			allRules[9].setDisplay("Quinte flush royale");
			allRules[8].setFound(false); // on définit la main "quinte flush"
											// comme false

			return allRules[9];

		}

		return allRules[9];

	}

	// cherche une quinte flush
	private HandType quinteFlush(Card[] flopHand) {

		// si on a trouvé une suite ET une couleur
		if (allRules[4].isFound() == true && allRules[5].isFound() == true) {

			allRules[8].setFound(true); // on définit la main "quinte flush"
										// comme true
			allRules[8].setDisplay("Quinte flush");
			allRules[5].setFound(false); // on définit la main "color" comme
											// false
			allRules[4].setFound(false); // on définit la main "quinte" comme
											// false

			return allRules[8];
		}

		// sinon on défini la quinte flush comme fausse
		allRules[8].setFound(false);
		return allRules[8];
	}

	// cherche s'il y a une suite
	private HandType sequence(Card[] flopHand) {

		int sequenceValue = 0; // total des cartes de la suite
		for (int i = 0; i<flopHand.length-1; i++) {
			// si chaque carte - la précédente != 1 --> il y a donc une suite
			if (flopHand[i].getNumberCard() - flopHand[i + 1].getNumberCard() != 1) {

				allRules[4].setFound(false); // main définie comme non-trouvée
				i = 4;
				return allRules[4];

			} else { // s'il y a une exception dans la suite
				sequenceValue += flopHand[i].getNumberCard(); // on ajoute à sequenceValue le numberCard de la carte
				allRules[4].setSecondValue(sequenceValue); // la secondValue =
				// sequenceValue
				allRules[4].setFound(true); // main définie comme trouvée
				allRules[4].setDisplay("Suite");
				return allRules[4];

			}
		}

		allRules[4].setFound(false);// main définie comme non-trouvée

		return allRules[4];
	}

	// cherche s'il y a une couleur
	private HandType colorHand(Card[] flopHand) {

		// la couleur à trouver : la couleur de la 1ere carte du flop
		String colorToFind = flopHand[0].getColor();

		// parcours le tableau de flopHand
		for (int i = 1; i < flopHand.length; i++) {
			if (flopHand[i].getColor() != colorToFind) { // si la carte en
															// question n'est
															// pas de la couleur
															// "colorToFind"
				allRules[5].setFound(false); // main définie comme non-trouvée

				return allRules[5];
			}
		}

		allRules[5].setFound(true);
		// si aucune quinte flush ni quinte flush royale n'a été trouvé
		if (allRules[9].isFound() == false && allRules[8].isFound() == false)
			allRules[5].setDisplay("Couleur (" + colorToFind + ")");

		return allRules[5];
	}

	// Rempli le tableau avec le nbre d'iteration de chaque numero
	private int[] fillPresence(Card[] flopHand, int presence[]) {

		for (int i = 0; i < flopHand.length; i++)
			presence[flopHand[i].getNumberCard()]++;

		return presence;
	}

	// total de "points" apportés par le tableau <presence>. Exemple: paire de 2
	// + paire de 3 = 2x2 + 2x3
	private int totalPresence(int[] presence) {

		for (int i = 1; i < presence.length; i++) {

			if (presence[i] > 1)
				presence[0] += i * presence[i];
		}

		return presence[0];

	}

	// calcul des methodes numeriques (paire, brelan, carré, double-paire)
	private HandType numeralHands(Card[] flopHand, int presence[]) {

		int cptHand = 0; // compte le nombre de fois qu'une carte est trouvée
		int cptPeer = 0; // compte le nombre de paires trouvées
		String toDisplay = ""; // à afficher

		int i = 0;

		for (i = 1; i < presence.length; i++) {
			if (presence[i] == 2) {
				toDisplay += "Paire de " + numberName(i) + " ";
				cptPeer += 1;
				cptHand += 2;
			}

			else if (presence[i] == 3) {
				toDisplay += "Brelan de " + numberName(i) + " ";
				cptPeer -= 1;
				cptHand += 3;
			}

			else if (presence[i] == 4) {
				toDisplay += "Carre de " + numberName(i) + " ";
				cptPeer -= 1;
				cptHand += 4;
			}
		}

		// selon le cptHand on a une main numérique différente
		// pour chaque cas:
		// - on défini la main comme trouvée
		// - on défini son display avec "toDisplay"
		// - sa second value = presence [0]
		switch (cptHand) {
		case 2:
			allRules[1].setFound(true);
			allRules[1].setDisplay(toDisplay);
			allRules[1].setSecondValue(presence[0]);
			return allRules[1];

		case 3:
			allRules[3].setFound(true);
			allRules[3].setDisplay(toDisplay);
			allRules[3].setSecondValue(presence[0]);
			return allRules[3];

		case 4: // il peut s'agir d'un carré ou d'une double paire
			if (cptPeer == 2) { // si on a deux paires: double paire
				allRules[2].setFound(true);
				allRules[2].setDisplay(toDisplay);
				allRules[2].setSecondValue(presence[0]);
				return allRules[2];
			} else { // sinon il s'agit d'un carré
				allRules[7].setFound(true);
				allRules[7].setDisplay(toDisplay);
				allRules[7].setSecondValue(presence[0]);
				return allRules[7];
			}

		case 5:
			allRules[6].setFound(true);
			allRules[6].setDisplay(toDisplay);
			allRules[6].setSecondValue(presence[0]);
			return allRules[6];
		}

		return allRules[0];
	}

	// défini la main "hauteur"
	protected void highterFound(Card[] flopHand) {

		// si aucune des mains n'est trouvée
		for (int i = 1; i < allRules.length; i++) {
			if (allRules[i].isFound() == false) {
				allRules[0].setFound(true); // hauteur est définie comme true
			} else {
				allRules[0].setFound(false); // sinon elle est définie comme
												// fausse
				i = 10; // on sort de la boucle
			}
		}

	}

	// défini le nom des cartes
	protected String numberName(int cardNumber) {
		String cardName = "";

		if (cardNumber > 0 && cardNumber < 10) {
			cardName = String.valueOf(cardNumber + 1);
		}

		switch (cardNumber) {
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
			System.out.println("Classe: HandType; Methode : numberName ; Carte inexistante");
			break;
		}

		return cardName;
	}

	// Test s'il y a un vainqueur, et s'il y en a un, lui transfère l'argent
	// misé par l'autre joueur
	protected void winnerIs(Joueur joueur1, Joueur joueur2) {

		// si le joueur1 a plus de points que le joueur 2
		if (joueur1.getPoints() > joueur2.getPoints()) {
			joueur1.setWinner(true); // joueur1 défini comme gagnant
			joueur2.setWinner(false); // joueur2 défini comme non-gagnant -->
										// perdnant
			winMoney(joueur1, joueur2); // transfert l'argent de joueur1 à
										// joueur 2
		}
		// sinon : la même chose mais inversée
		else if (joueur1.getPoints() < joueur2.getPoints()) {
			joueur1.setWinner(false);
			joueur2.setWinner(true);
			winMoney(joueur2, joueur1);
		}

		// si les deux joueurs ont le même nombre de points (donc la même main)
		else if (joueur1.getPoints() == joueur2.getPoints()) {

			// si la valeur de la main du joueur1 vaut plus que celle du
			// joueur2,
			if (joueur1.getValueHand() > joueur2.getValueHand()) {
				// on procède de la même façon que précédemment
				joueur1.setWinner(true);
				joueur2.setWinner(false);
				winMoney(joueur1, joueur2);
			}
			// idem, mais inversé
			else if (joueur1.getValueHand() < joueur2.getValueHand()) {
				joueur1.setWinner(false);
				joueur2.setWinner(true);
				winMoney(joueur2, joueur1);
			}

			// si de nouveau, la main a la même valeur: soit car il y a deux
			// mains de valeurs équivalentes
			// soit parce qu'aucun joueur n'a de main spécifique sauf la hauteur
			else if (joueur1.getValueHand() == joueur2.getValueHand()) {
				// si la carte la plus forte de la main du joueur1 est plus
				// forte que celle du joueur 2
				if (joueur1.getHight().getNumberCard() > joueur2.getHight().getNumberCard()) {
					// on procède de la même façon que précédemment
					joueur1.setWinner(true);
					joueur2.setWinner(false);
					winMoney(joueur1, joueur2);
				} else if (joueur1.getHight().getNumberCard() < joueur2.getHight().getNumberCard()) {
					joueur1.setWinner(false);
					joueur2.setWinner(true);
					winMoney(joueur2, joueur1);
				}
				// sinon cela signifie qu'il y a une égalité
				else {
					joueur1.setWinner(false);
					joueur2.setWinner(false);
				}
			}
		}
	}

	// gère le transfert d'argent du perdannt au gagnant
	protected void winMoney(Joueur winner, Joueur looser) {

		// on soustrait sa mise au joueur perdant et on l'additionne à l'argent
		// restant du vainqueur
		winner.setMoney(winner.getMoney() + looser.getBet());
		looser.setMoney(looser.getMoney() - looser.getBet());
	}

	// --------------------------------GETTERS &
	// SETTERS----------------------------------------------------------------------------

	// -GETTERS---------------------------------------------------

	public String getHTName() {
		return HTName;
	}

	public boolean isFound() {
		return found;
	}

	public int getValue() {
		return value;
	}

	public String getDisplay() {
		return display;
	}

	public int getSecondValue() {
		return secondValue;
	}

	// -SETTERS---------------------------------------------------

	public void setHTName(String hTName) {
		HTName = hTName;
	}

	public void setFound(boolean found) {
		this.found = found;
	}

	public void setValue(int value) {
		this.value = value;
	}

	protected void setDisplay(String display) {
		this.display = display;
	}

	public void setSecondValue(int secondValue) {
		this.secondValue = secondValue;
	}

}
