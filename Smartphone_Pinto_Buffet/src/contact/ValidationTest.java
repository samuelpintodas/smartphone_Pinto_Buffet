package contact;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import Ressources.FlatTextField;

public class ValidationTest {

	Validation validate = new Validation();
	FlatTextField textField = new FlatTextField(true, Color.RED, Color.RED);
	Color red = new Color(222, 44, 40);
	
	@Test
	public void test() {
		textField.setText(null);
		assertFalse(validate.isNotEmpty(textField));
		
		textField.setText("Raphy");
		assertTrue(validate.isNotEmpty(textField));
		
		textField.setText("Raphy");
		assertTrue(validate.isNotEmpty(textField));
		
		textField.setBackground(red);
		assertFalse(validate.isNotEmpty(textField));
		
		textField.setText("118");
		assertTrue(validate.phoneValide(textField));
		
		textField.setText("+41 078 527 17 23");
		assertTrue(validate.phoneValide(textField));
		
		textField.setText("0041 078 484 17 23");
		assertTrue(validate.phoneValide(textField));
		
		textField.setText("00410784841723");
		assertTrue(validate.phoneValide(textField));
		
		textField.setText("0800333333");
		assertTrue(validate.phoneValide(textField));
		
		textField.setText("0800 333 333");
		assertTrue(validate.phoneValide(textField));
	
		textField.setText("784841723");
		assertTrue(validate.phoneValide(textField));
		
		textField.setText("78 484 17 23");
		assertTrue(validate.phoneValide(textField));
		
		textField.setText("+410784841775");
		assertTrue(validate.phoneValide(textField));
		
		textField.setText("+41784841723");
		assertTrue(validate.phoneValide(textField));
		
		textField.setText("+41 78 582 17 23");
		assertTrue(validate.phoneValide(textField));
		
		textField.setText("0041 78 484 17 23");
		assertTrue(validate.phoneValide(textField));
		
		textField.setText("0041784849723");
		assertTrue(validate.phoneValide(textField));
		
		textField.setText("0789351723");
		assertTrue(validate.phoneValide(textField));
		
		textField.setText("078 518 47 23");
		assertTrue(validate.phoneValide(textField));
		
		textField.setText("07848417");
		assertFalse(validate.phoneValide(textField));
		
		textField.setText("+41 078 484 1");
		assertFalse(validate.phoneValide(textField));
		
		textField.setText("0041 078 484 1");
		assertFalse(validate.phoneValide(textField));
		
		textField.setText("004107848417");
		assertFalse(validate.phoneValide(textField));
		
		textField.setText("08003333");
		assertFalse(validate.phoneValide(textField));
		
		textField.setText("0800 333 3");
		assertFalse(validate.phoneValide(textField));
		
		textField.setText("078 484 17 2");
		assertFalse(validate.phoneValide(textField));
		
		textField.setText("+410784841");
		assertFalse(validate.phoneValide(textField));
		
		
	}

}
