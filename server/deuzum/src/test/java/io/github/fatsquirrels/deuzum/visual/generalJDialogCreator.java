package io.github.fatsquirrels.deuzum.visual;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.jupiter.api.Test;

import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.net.Server;
import io.github.fatsquirrels.deuzum.visual.Dialogs.general.*;;

public class generalJDialogCreator {

	@Test
	void testCreateComponentMap() {
		new generalCreateDialog(GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:3306/deuzumdb", "root", ""),
				generalCreateDialog.tableName.usuario, 0);
	}
	
}
