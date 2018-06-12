package contact;

import java.awt.Color;

import Ressources.FlatTextField;

public class Validation {

	/**
	 * Méthode qui check si textfield est vide
	 * @param textField
	 * @return
	 */
	public boolean isNotEmpty(FlatTextField textField)
	{
		if (textField.getText().isEmpty()) 
		{
			textField.setBackground(new Color(222, 44, 60));
			textField.setText("Champ obligatoire");
			textField.setForeground(Color.WHITE);
			return false;
		}
		if(textField.getBackground().equals(new Color(222, 44, 60)))
			return false;
		return true;
	}
	/**
	 * Méthode qui check si téléphone valide
	 * @param textField
	 * @return
	 */
	
	public boolean phoneValide(FlatTextField textField)
	{
		String phonePattern = "(\\+41)?(\\s)?(0)?\\d{2}(\\s)?\\d{3}(\\s)?\\d{2}(\\s)?\\d{2}";
		String phonePattern2 = "(0041)?(\\s)?(0)?\\d{2}(\\s)?\\d{3}(\\s)?\\d{2}(\\s)?\\d{2}";
		String phonePatternHotline = "(0800)(\\s)?\\d{3}(\\s)?\\d{3}";
		String phonePatternUrgence = "\\d{3}";
		
		if (textField.getText().matches(phonePattern) == false && textField.getText().matches(phonePattern2) == false
				&& textField.getText().matches(phonePatternHotline) == false
				&& textField.getText().matches(phonePatternUrgence) == false) 
		{
			textField.setBackground(new Color(222, 44, 60));
			textField.setText("Numéro invalide");
			textField.setForeground(Color.WHITE);
			return false;
		}
		if(textField.getBackground().equals(new Color(222, 44, 60)))
			return false;
		return true;
	}
	
}
