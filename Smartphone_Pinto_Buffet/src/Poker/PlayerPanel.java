package Poker;
/* AUTEUR: Samuel Pinto Da Silva
 * NOM DE LA CLASSE: PlayerPanel
 * DESCRIPTION: Ce panel contient 2 panels:
 * 				- un panel information contenant le nom du joueur, la mise et la main trouvée
 * 				- un panel contenant les deux cartes, recto ou verso
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;


import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerPanel extends JPanel {

	private PokerRessources pRessources = new PokerRessources();
	private Dimension playerPanelDim = new Dimension(480, 120);

	JPanel informations; // Panel contenant le nom du joueur et sa mise/argent restant
	JPanel rectoCards; // Panel contenant les cartes recto
	JPanel versoCards; // Panel contenant les cartes verso

	JLabel playerName; // nom du joueur
	JLabel betLabel; // label contenant la mise/argent restant
	JLabel handTypeLabel; // label affichant la main obtenue via le flopHand du joueur

	// constructeur par défaut
	protected PlayerPanel() {
		
	}
	
	// constructeur du panel du joueur
	protected PlayerPanel(Joueur joueur, boolean ia, JPanel rectoCardsPanel, JPanel versoCardsPanel) {

		
		this.setPreferredSize(playerPanelDim);
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.setBorder(pRessources.POKERBORDER);

		informations = new JPanel();
		informations.setPreferredSize(new Dimension(300, 100));
		informations.setLayout(new BorderLayout());
		informations.setOpaque(false);

		playerName = new JLabel(joueur.getPlayerName());
		playerName.setFont(pRessources.BIGPOKERFONT);
		playerName.setForeground(Color.yellow);

		betLabel = new JLabel("CHF " + joueur.getBet() + ".-/" + joueur.getMoney() + ".-");
		betLabel.setFont(pRessources.SMALLPOKERFONT);
		betLabel.setForeground(Color.yellow);
		
		handTypeLabel = new JLabel(joueur.getHandFound().getDisplay());
		handTypeLabel.setFont(pRessources.SMALLPOKERFONT);
		handTypeLabel.setForeground(Color.yellow);
		handTypeLabel.setVisible(false);

		rectoCards = joueur.getRectoCardsPanel();
		versoCards = joueur.getVersoCardsPanel();

		informations.add(playerName, BorderLayout.NORTH);
		informations.add(betLabel, BorderLayout.CENTER);
		informations.add(handTypeLabel, BorderLayout.SOUTH);
		
		
		this.add(informations, BorderLayout.WEST);
		// si le joueur n'est pas une IA : c'est nous, donc on peut voir nos cartes
		if (ia = false) {
			rectoCardsPanel.setVisible(true);
			this.add(rectoCardsPanel, BorderLayout.EAST);
	
		}
		// si le joueur est une IA: c'est notre adversaire, on ne voit pas ses cartes
		else if (ia = true){
			versoCardsPanel.setVisible(true);
			this.add(versoCardsPanel, BorderLayout.EAST);
			
		}
	}
	// --------------------------------GETTERS & SETTERS----------------------------------------------------------------------------		



	// -GETTERS---------------------------------------------------	
	public JLabel getBetLabel() {
		return betLabel;
	}

	// -SETTERS---------------------------------------------------	
	public void setBetLabel(int bet, int money) {		
		
		remove(this.getBetLabel());
		
		// concatène la mis et l'argent restant
		JLabel betLabel = new JLabel("CHF " + bet + ".-/" + money + ".-");
		betLabel.setFont(pRessources.SMALLPOKERFONT);
		betLabel.setForeground(Color.yellow);
		
		
		this.betLabel = betLabel;

	}


}
