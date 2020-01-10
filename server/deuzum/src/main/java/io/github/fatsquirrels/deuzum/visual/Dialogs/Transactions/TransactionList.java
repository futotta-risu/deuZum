package io.github.fatsquirrels.deuzum.visual.Dialogs.Transactions;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;


import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.database.WhereAST;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

@Deprecated
public class TransactionList extends JDialog{

	
	private static final long serialVersionUID = 1L;
	private JTextField txtFiltrado;
	private JComboBox<String> comboFiltrar;
	private JButton btnFiltrar;
	private JButton btnEliminar;
	private JList<Transacion> listaTransacciones;
	
	/**
	 * Crea un objeto de TransactionList, el cual contiene un Dialogo que muestra la lista
	 * de todos las transacciones y permite hacer busquedas filtradas para poder eliminarlas.
	 * @param Connection 
	 */
	public TransactionList(Connection c) {
		setSize(500,500);
		setTitle("Lista de Transacciones");
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		comboFiltrar = new JComboBox<String>();
		comboFiltrar.setBounds(25, 33, 156, 27);
		getContentPane().add(comboFiltrar);
		comboFiltrar.addItem("Origen");
		
		
		txtFiltrado = new JTextField();
		txtFiltrado.setBounds(193, 32, 130, 26);
		getContentPane().add(txtFiltrado);
		txtFiltrado.setColumns(10);
		
		DefaultListModel<Transacion> model = new DefaultListModel<>();
		listaTransacciones = new JList<Transacion>();
		listaTransacciones.setBounds(25, 106, 337, 338);
		getContentPane().add(listaTransacciones);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setBounds(354, 32, 117, 29);
		getContentPane().add(btnFiltrar);
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String conditions ="";
				try {
					String query = GeneralSQLFunctions.getEntryFromDatabase(c, "transacion", comboFiltrar.getItemAt(comboFiltrar.getSelectedIndex()), conditions);
					ResultSet resultados = GeneralSQLFunctions.getExecQuery(c, query);
					while(resultados.next()) {
						// retrieve and print the values for the current row
						String codigo = resultados.getString("codigo");
						String source = resultados.getString("source");
						String destino = resultados.getString("destino");
						String dinero = resultados.getString("dinero");
						String fecha = resultados.getString("fecha");
						Transacion tempTransacion = new Transacion(codigo,source, destino, dinero, fecha);
						model.addElement(tempTransacion);
						
					}
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "ERROR", "No se ha podido realizar la consulta", 0);
				}
				listaTransacciones = new JList<Transacion>(model);
				listaTransacciones.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				listaTransacciones.setBounds(48, 85, 381, 287);
				listaTransacciones.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			}
		});
		
		
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(374, 250, 117, 29);
		getContentPane().add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Transacion> listaBorrar = listaTransacciones.getSelectedValuesList();
				ArrayList<String> ids = new ArrayList<String>();
				for (Transacion t : listaBorrar) {
					ids.add(t.getCodigo());
				}
				try {
					WhereAST where = new WhereAST().addValue("user_id='"+ids+"'");
					GeneralSQLFunctions.deleteEntryFromDatabase(c, "transacion", where);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "ERROR", "Ha habido un error al eliminar la transacion", 0);
				}
			}
		});
	}
}
