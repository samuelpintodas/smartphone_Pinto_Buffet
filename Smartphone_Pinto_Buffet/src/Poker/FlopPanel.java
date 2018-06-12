package Poker;
/* AUTEUR: Samuel Pinto Da Silva
 * NOM DE LA CLASSE: FlopPanel
 * DESCRIPTION: Panel contenant :
 * 				- un JButton permettant de passer d'une phase de jeu � l'autre
 * 				- un panel contenant les images des cartes du flop
 * 				- un jTextField permettant d'ins�rer la mise du joueur 1
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FlopPanel  extends JPanel{
	
	// dimension and ressources
	PokerRessources pRessources = new PokerRessources();
	Dimension flopPanelDim = new Dimension(480, 500);
	
	// GUI components
	JButton phaseButton = new JButton("Phase de mise"); // permet de passer � la phase suivante
	JTextField betField; // permet d'ins�rer la mise voulue
	JPanel phasePanel;	// panel o� l'on ins�re le phaseButton
	JPanel versoCardsPanel;	// l� o� se situent les cartes faces verso
	JPanel rectoCardsPanel; // l� o� se situent les cartes faces recto
	JPanel betPanel; // panel o� l'on ins�re le betField
	JLabel flopLabel []; // // panel o� l'on ins�re le versoCardsPanel et le rectoCardsPanel
	
	
	// constructeur du flopPanel
	protected FlopPanel(Card [] flop, Joueur joueur1, Joueur joueur2){
	
		// Creation et parametrage du button phase
		phaseButton.setFont(pRessources.BIGPOKERFONT);
		phaseButton.setBorder(pRessources.POKERBORDER);
		phaseButton.setForeground(Color.yellow);
		phaseButton.setBackground(Color.green);
		phaseButton.setOpaque(false);
	
		betField = new JTextField();
		
		// Creation du phasePanel qui accueil le phaseButton
		phasePanel = new JPanel();
		phasePanel.setLayout(new BorderLayout());
		phasePanel.add(phaseButton);
		phasePanel.setOpaque(false);
		

		
		// Creation du betPanel, qui accueil bet (JTextfield
		betPanel = new JPanel();
		betPanel.setLayout(new BorderLayout());
		betPanel.add(betField);


		
		setPreferredSize(flopPanelDim);
		setLayout(new BorderLayout());
		this.setBorder(pRessources.POKERBORDER);
		this.setOpaque(false);
		
		// Ajout des images au tableau de labels de cartes
		flopLabel  = new JLabel[3];
		
		versoCardsPanel = setCardsPanel(versoCardsPanel, flopLabel, flop);

		pRessources.retourner(flop);
		
		rectoCardsPanel = setCardsPanel(rectoCardsPanel, flopLabel, flop);
		
		
		
		
		// Ajout des 3 panels au panel central
		this.add(phasePanel, BorderLayout.NORTH);
		this.add(rectoCardsPanel, BorderLayout.CENTER);
		this.add(versoCardsPanel, BorderLayout.CENTER);
		this.add(betPanel, BorderLayout.SOUTH);
		
	}
	
	// cr�e un cardPanel en fonction du flop, et s'il est retourn� ou non
	protected JPanel setCardsPanel(JPanel cardsPanel, JLabel cards[], Card[] flop){
		
		
		cardsPanel = new JPanel();
		cardsPanel.setLayout(new BorderLayout());
		cardsPanel.setOpaque(false);
		cardsPanel.setVisible(true);
		
		pRessources.displayImageCard(flop, cards, 140, 200);
		cardsPanel.add(cards[0], BorderLayout.WEST);
		cardsPanel.add(cards[1], BorderLayout.CENTER);
		cardsPanel.add(cards[2], BorderLayout.EAST);
		
		
		return cardsPanel;
	}
	
	
	// permet de cr�er un nouveau flopPanel
	protected void newFlop(Card[] flop){		
		
		// retire les deux cardsPanels
		this.versoCardsPanel.removeAll();
		this.rectoCardsPanel.removeAll();

		// recr�e les deux cardsPanels � partir du nouveau flop
		versoCardsPanel = setCardsPanel(versoCardsPanel, flopLabel, flop);
		pRessources.retourner(flop);
		rectoCardsPanel = setCardsPanel(rectoCardsPanel, flopLabel, flop);
		
		// Les ajoute � nouveau
		this.add(rectoCardsPanel, BorderLayout.CENTER);
		this.add(versoCardsPanel, BorderLayout.CENTER);
		
	}
	

}
