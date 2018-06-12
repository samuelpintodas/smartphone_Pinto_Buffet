package Ressources;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;


public class Button extends JButton {

	private Color color;
	private Color hover;
	private int idContact;

	// constructeur du bouton
	public Button() 
	{
		
		setBorderPainted(false);
		setFocusable(false);
		setBackground(new Color(0, 0, 0, 0));
		setContentAreaFilled(false);
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(0, 0, 0, 0));
	}
	// constructeur du bouton
	public Button(ImageIcon image) 
	{
		super(image);

		setBorderPainted(false);
		setFocusable(false);
		setBackground(new Color(0, 0, 0, 0)); 
		setContentAreaFilled(false);
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(0, 0, 0, 0));
	}
	// constructeur du bouton
	public Button(ImageIcon image, int width, int height, int idContact)
	{
		super(image);
		
		
		this.setIdContact(idContact);
		setBorderPainted(false);
		setFocusable(false);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
	}
	

	public Button(ImageIcon image, int width, int height)
	{
		super(image);
		
		setBorderPainted(false);
		setFocusable(false);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
	}
	
	// constructeur du bouton
	public Button(String text, ImageIcon image, int width, int height, int taillePolice) 
	{
		super(image);
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(0, 0, 0, 0));
		
		ImageIcon imgCalque = new ImageIcon("image/fonds/mcalque.png");
		Images calque = new Images(imgCalque);
		calque.setPreferredSize(new Dimension(143, 143));
		calque.setOpaque(false);
		calque.setLayout(new BorderLayout());
		add(calque, BorderLayout.CENTER);
		
		JLabel label = new JLabel(text);
		calque.add(label, BorderLayout.CENTER);
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(new Font("Times New Roman", Font.PLAIN, taillePolice));
		

		setBorderPainted(false);
		setFocusable(false);
		setBackground(new Color(0, 0, 0, 0));
		setPreferredSize(new Dimension(width, height));
	}
	// constructeur du bouton
	public Button(ImageIcon image, int width, int height, Color color)
	{
		super(image);
		setBorderPainted(false);
		setFocusable(false);
		setBackground(color);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
	}
	// constructeur du bouton
	public Button(ImageIcon image, Color color, Color hover) 
	{
		super(image);
		this.color = color;
		this.hover = hover;
		setBorderPainted(false);
		setFocusable(false);
		setBackground(color);

		addMouseListener(new MouseHover());
	}
	// constructeur du bouton
	public Button(String texte, Color color, int taillePolice) 
	{
		super(texte);
		this.color = color;
		setBorderPainted(false);
		setFocusable(false);
		setBackground(color);
		setForeground(Color.WHITE);
		setFont(new Font("Montserrat", Font.BOLD, taillePolice));

		addMouseListener(new MouseHover());
	}
	// constructeur du bouton
	public Button(String texte, ImageIcon image, Color color, Color hover, int taillePolice) 
	{
		super(texte);
		this.color = color;
		this.hover = hover;

		setLayout(new BorderLayout());
		add(new JLabel(image), BorderLayout.WEST);
		setBorderPainted(false);
		setFocusable(false);
		setBackground(color);
		setFont(new Font("Montserrat", Font.BOLD, taillePolice));
		setMaximumSize(new Dimension(480, 70));
		setMinimumSize(new Dimension(462, 70));
		setPreferredSize(new Dimension(462, 70));
		addMouseListener(new MouseHover());
		setAlignmentX(CENTER_ALIGNMENT);
	}
	

	// definit l'action on passant dessus le bouton
	class MouseHover extends MouseAdapter 
	{
		@Override
		public void mouseEntered(MouseEvent e) 
		{
			super.mouseEntered(e);
			setBackground(hover);
			setForeground(Color.WHITE);
		}
		@Override
		public void mouseExited(MouseEvent e) 
		{
			super.mouseExited(e);
			setBackground(color);
			setForeground(Color.BLACK);
		}
	}
	// GETTERS AND SETTERS
	public int getIdContact() {
		return idContact;
	}
	public void setIdContact(int idContact) {
		this.idContact = idContact;
	}
	
}