package io.github.fatsquirrels.deuzum;

import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.visual.Dialogs.general.generalCreateDialog;

public class tempMain {
public static void main(String[] args) {
		
		new generalCreateDialog(GeneralSQLFunctions.connectToDatabase("jdbc:mysql://localhost:3306/deuzumdb", "root", ""),
				generalCreateDialog.tableName.usuario, 0);
	}
}