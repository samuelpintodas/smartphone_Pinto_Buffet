package Poker;
/* AUTEUR: Samuel Pinto Da Silva
 * NOM DE LA CLASSE: PokerApp
 * DESCRIPTION: Panel de jeu. Contient:
 * 							- un FlopPanel
 * 							- deux playerPanel
 * 				On y ajoute un listener au phaseButton, qui permet de jouer, et effectue différentes
 * 				actions selon la phase en question
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import frame.FrameBases;

public class PokerApp extends JPanel {

	// creation de deux joueurs
	PlayerPanel player;
	PlayerPanel computer;

	// creation d'un flopPanel
	FlopPanel fPanel;
	PokerRessources pRessources = new PokerRessources();
	HandType hType = new HandType();


	// test si on est au 1er tour
	boolean firstRound = true;
	int round = 1;

	// phase de jeu
	int phase = 1;

	// affiche le tapis de jeu comme arrière-plan
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Image monImage = pRessources.CARPET.getImage();
		g.drawImage(monImage, 0, 0, this);

	}

	// constructeur
	public PokerApp(FrameBases frameBases) {

		// Crée deux joueurs
		Joueur joueur1 = new Joueur("Samuel");
		Joueur joueur2 = new Joueur("Ordinateur");

		// crée un deck et un flop
		Card[] deck = new Card[pRessources.DECKSIZE];
		Card[] flop = new Card[pRessources.FLOPSIZE];

		pRessources.fillDeck(deck); // rempli le deck
		pRessources.disposeFlop(deck, flop); // rempli le flop
		fPanel = new FlopPanel(flop, joueur1, joueur2); // cree le flopPanel
		// les deux joueurs piochent
		joueur1.newPioche(flop, deck);
		joueur2.newPioche(flop, deck);

		// les deux PlayerPanels sont instanciés
		player = new PlayerPanel(joueur1, false, joueur1.getRectoCardsPanel(), joueur1.getRectoCardsPanel());
		computer = new PlayerPanel(joueur2, true, joueur2.getRectoCardsPanel(), joueur2.getVersoCardsPanel());

		Dimension dim = new Dimension(480, 755);

		fPanel.phaseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				switch (phase) {

				// Dans cette phase on crée deux nouveaux playersPanels si nécessaires et on insère la mise dans le betField
				case 1:

					System.out.println("--------------------------------------------------------");
					System.out.println("Tour n° " + round);
					System.out.println("--------------------------------------------------------");

					// si l'on est pas au 1er tour, on doit recréer un flopPanel et deux playersPanels
					if (firstRound == false) {
						fPanel.newFlop(flop);
						remove(player);
						player = new PlayerPanel(joueur1, false, joueur1.getRectoCardsPanel(),
								joueur1.getRectoCardsPanel());
						add(player, BorderLayout.SOUTH);

						remove(computer);
						computer = new PlayerPanel(joueur2, true, joueur2.getRectoCardsPanel(),
								joueur2.getVersoCardsPanel());
						add(computer, BorderLayout.NORTH);
					}

					// teste si le betField n'est pas vide et s'il ne contient que des chiffres
					if (fPanel.betField.getText().equals("") == false
							&& fPanel.betField.getText().matches("[0-9]") == true) {
						
						// le flop est face cachée
						fPanel.rectoCardsPanel.setVisible(false);
						fPanel.versoCardsPanel.setVisible(true);
						add(fPanel.rectoCardsPanel, BorderLayout.CENTER);
						fPanel.betField.setEnabled(true);
						fPanel.phaseButton.setText("Phase de mise");

						// les cartes de l'IA aussi
						computer.rectoCards.setVisible(false);
						computer.remove(computer.rectoCards);
						computer.add(computer.versoCards, BorderLayout.EAST);
						computer.versoCards.setVisible(true);

						fPanel.versoCardsPanel.setVisible(true);

						fPanel.betField.setBorder(pRessources.BLACKBORDER);

						phase += 1;
						
					// sinon on entoure le betField en rouge tant qu'on a pas que des chiffres
					} else {

						fPanel.betField.setBorder(pRessources.WARNINGBORDER);

					}

					// on défini à partir de là le 1er round comme false
					firstRound = false;

					break;

				// dans cette phase, on récupère la mise insérée dans le betField et on la défini comme mise du joueur
				case 2:
					
					fPanel.phaseButton.setText("Phase intermediaire");
					fPanel.betField.setEnabled(false);

					// on affiche les cartes de l'adversaire 
					computer.versoCards.setVisible(false);
					computer.remove(computer.versoCards);
					computer.add(computer.rectoCards, BorderLayout.EAST);
					computer.rectoCards.setVisible(true);

					
					joueur1.setBet(Integer.parseInt(fPanel.betField.getText())); // notre mise = mise saisie dans le betField
					joueur2.setBet((int) (Math.random() * joueur2.getMoney())); // mise de l'IA = aléatoire
					System.out.println("Mise de l'ordinateur: " + joueur2.getBet());

					// on affiche les nouvelles mises
					pRessources.refreshBetPanel(player, joueur1.getBet(), joueur1.getMoney()); 
					pRessources.refreshBetPanel(computer, joueur2.getBet(), joueur2.getMoney());

					phase += 1;
					System.out.println("Mise joueur 1: " + joueur1.getBet() + "/" + joueur1.getMoney());
					System.out.println("Mise joueur 2: " + joueur2.getBet() + "/" + joueur2.getMoney());

					break;

				case 3:

					// on affiche le flopPanel (cartes face recto)
					fPanel.versoCardsPanel.setVisible(false);
					fPanel.rectoCardsPanel.setVisible(true);
					add(fPanel.rectoCardsPanel, BorderLayout.CENTER);
					fPanel.betField.setEnabled(false);
					fPanel.phaseButton.setText("Phase de résultat");

					// on affiche la main obtenue par via le flopHand de chaque joueur
					player.handTypeLabel.setVisible(true);
					computer.handTypeLabel.setVisible(true);

					// on défini le gagnant
					hType.winnerIs(joueur1, joueur2);

					// Défini une bordure spécifique selon le gagnant
					if (joueur1.isWinner() == true && joueur2.isWinner() == false) {
						player.setBorder(pRessources.WINNERBORDER);
						computer.setBorder(pRessources.LOOSERBORDER);
						System.out.println("Joueur : " + joueur1.isWinner());
						System.out.println("Ordinateur : " + joueur2.isWinner());
						pRessources.displayScore(joueur1);
						pRessources.displayScore(joueur2);
					} else if (joueur2.isWinner() == true && joueur1.isWinner() == false) {
						computer.setBorder(pRessources.WINNERBORDER);
						player.setBorder(pRessources.LOOSERBORDER);
						System.out.println("Joueur : " + joueur1.isWinner());
						System.out.println("Ordinateur : " + joueur2.isWinner());
						pRessources.displayScore(joueur1);
						pRessources.displayScore(joueur2);
					// en cas d'égalité:
					} else if (joueur1.isWinner() == false && joueur2.isWinner() == false) {
						computer.setBorder(pRessources.TIEDBORDER);
						player.setBorder(pRessources.TIEDBORDER);
						pRessources.displayScore(joueur1);
						pRessources.displayScore(joueur2);
					}

					// on affiche l'argent actuel du gagnant et du perdant
					pRessources.refreshBetPanel(player, joueur1.getBet(), joueur1.getMoney());
					pRessources.refreshBetPanel(computer, joueur2.getBet(), joueur2.getMoney());

					// si l'un des deux joueurs est à sec:
					if (joueur1.getMoney() == 0 || joueur2.getMoney() == 0) {

						if (joueur1.isWinner() == true) {
							fPanel.phaseButton.setText("Vainqueur: Humain");
						} else
							fPanel.phaseButton.setText("Vainqueur: IA");

						// on relance une partie, on remet les mises à 3000
						joueur1.setMoney(3000);
						joueur2.setMoney(3000);
					}

					// on remet les cartes dans le deck
					pRessources.fillDeck(deck);

					// on dispose un nouveau flop
					pRessources.disposeFlop(deck, flop);

					// on refresh le rectoPanel de chaque joueur
					pRessources.refreshRectoPanel(player, player.rectoCards, joueur1, deck, flop);
					pRessources.refreshRectoPanel(computer, computer.rectoCards, joueur2, deck, flop);

					// on crée une nouvelle pioche pour chaque joueur
					joueur1.newPioche(flop, deck);
					joueur2.newPioche(flop, deck);
					round += 1;

					// on repasse en phase 1
					phase = 1;
					break;
				}

			}
		});

		// on ajoute tous les composants au pokerPanel
		this.setPreferredSize(dim);
		this.setLayout(new BorderLayout());
		this.add(player, BorderLayout.SOUTH);
		this.add(computer, BorderLayout.NORTH);
		this.add(fPanel, BorderLayout.CENTER);

	}

}
