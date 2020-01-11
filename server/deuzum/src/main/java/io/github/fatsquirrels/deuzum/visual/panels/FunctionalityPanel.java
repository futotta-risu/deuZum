package io.github.fatsquirrels.deuzum.visual.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import io.github.fatsquirrels.deuzum.IA.ClusterElement;
import io.github.fatsquirrels.deuzum.IA.ClusterGroup;
import io.github.fatsquirrels.deuzum.IA.Clustering;
import io.github.fatsquirrels.deuzum.database.GeneralSQLFunctions;
import io.github.fatsquirrels.deuzum.net.Server;
import io.github.fatsquirrels.deuzum.res.Strings;
import io.github.fatsquirrels.deuzum.utils.math.APair;
import io.github.fatsquirrels.deuzum.visual.components.buttons.FlatButton;
import io.github.fatsquirrels.deuzum.visual.panels.util.PairPanel;
import io.github.fatsquirrels.deuzum.visual.statistics.GraphFunctions;
import io.github.fatsquirrels.deuzum.visual.style.layout.VerticalFlowLayout;

public class FunctionalityPanel extends JPanel {
	
	private static final long serialVersionUID = 4541688928942198726L;

	
	// CLUSTERING
	private JTextField txtFileName;
	private JComboBox<Clustering.ClusteringAlgorithm> clusteringAlgorithm;
	private JPanel panel_Clustering_Data;
	
	private JTextField txtKMCNCluster, txtDBSANMinPoint;
	private JSlider sldrMSCKernel, sldrRadius;
	
	private JLabel lblKernel, lblRadius;
	
	public FunctionalityPanel() {
		JTabbedPane mainPanel = new JTabbedPane();
		JPanel panel_Funct_Visual = new JPanel();
		mainPanel.addTab("Visualizacion de datos", null, panel_Funct_Visual, null);
		
		//		BOTONES FUNCIONALIDADES

		panel_Funct_Visual.add(new FlatButton("Visualizar Grafico de Transaciones", e->GraphFunctions.createGraphTransacciones()));
		panel_Funct_Visual.add(new FlatButton("Visualizar Grafico de cantidades transferidas por usuario",e->GraphFunctions.createGraphUsuario()));
		panel_Funct_Visual.add(new FlatButton("Visualizar Grafico de Permisos", e-> GraphFunctions.createGraphPermisos()));
		panel_Funct_Visual.add(new FlatButton("Visualizar Grafico de Usuarios registrados en el tiempo", e->GraphFunctions.createGraphUsuariosXTiempo()));
		panel_Funct_Visual.add(new FlatButton("Visualizar Grafico de Aportaciones en Proyecto",e -> GraphFunctions.createGraphAportaciones()));
		
		JPanel panel_Funct_IA = new JPanel(new BorderLayout());
		mainPanel.addTab("IA", panel_Funct_IA);
		

		JPanel panel_Cluster_Sector = new JPanel(new VerticalFlowLayout(10,10,10));
		
		JLabel lblClusterInfo = new JLabel(Strings.IA_cluster_info, SwingConstants.CENTER);
		
		clusteringAlgorithm = new JComboBox<Clustering.ClusteringAlgorithm>();
		clusteringAlgorithm.setModel(new DefaultComboBoxModel<Clustering.ClusteringAlgorithm>(Clustering.ClusteringAlgorithm.values()));
		clusteringAlgorithm.addActionListener(e -> createClusterDataPanel());
		
		panel_Clustering_Data = new JPanel(new VerticalFlowLayout(10,10,10));
		
		
		txtFileName = new JTextField(20);
		
		FlatButton btnClusterizarDb = new FlatButton("Clusterizar DB");
		btnClusterizarDb.addActionListener(e->clusterData());
		
		
		createClusterDataPanel();
		panel_Cluster_Sector.add(lblClusterInfo);
		panel_Cluster_Sector.add(new PairPanel("",clusteringAlgorithm));
		panel_Cluster_Sector.add(panel_Clustering_Data);
		panel_Cluster_Sector.add(new PairPanel("Nombre de la clusterizacion", txtFileName));
		panel_Cluster_Sector.add(btnClusterizarDb);
		panel_Funct_IA.add(panel_Cluster_Sector, BorderLayout.NORTH);
		JPanel panel_Funct_Seguridad = new JPanel(new FlowLayout());
		
		JButton btnEncriptar = new JButton("Encriptar");
		JButton btnDesencriptar = new JButton("Desencriptar");
		
		panel_Funct_Seguridad.add(btnEncriptar);
		panel_Funct_Seguridad.add(btnDesencriptar);
		
		mainPanel.addTab("Seguridad", panel_Funct_Seguridad);
		
		setLayout(new BorderLayout());
		add(mainPanel,BorderLayout.CENTER);
	}
	
	public void createClusterDataPanel() {
		panel_Clustering_Data.removeAll();
		switch((Clustering.ClusteringAlgorithm)clusteringAlgorithm.getSelectedItem()) {
		case KMC:
			txtKMCNCluster =  new JTextField(15);
			panel_Clustering_Data.add(new PairPanel("Numero de Clusters",txtKMCNCluster));
			break;
		case MSC:
			lblKernel = new JLabel("Radio Kerner: 1");
			sldrMSCKernel = new JSlider(1,100,1);
			sldrMSCKernel.addChangeListener(e -> {
				lblKernel.setText("Radio Kernel: " + sldrMSCKernel.getValue());
			});
			panel_Clustering_Data.add(new PairPanel(lblKernel,sldrMSCKernel));
			break;
		case DBSCAN:
			lblRadius = new JLabel("Radio Kerner");
			sldrRadius = new JSlider(1,100,1);
			sldrRadius.addChangeListener(e->{
				lblRadius.setText("Radio: " + sldrRadius.getValue());
			});
			panel_Clustering_Data.add(new PairPanel(lblRadius,sldrRadius));
			txtDBSANMinPoint =  new JTextField(15);
			
			panel_Clustering_Data.add(new PairPanel("Puntos Minimos",txtDBSANMinPoint));
			break;
		default:
			break;
		}
		revalidate();
		repaint();
	}
	
	public void clusterData() {
		Connection connection = Server.createConnection();
		ClusterGroup clusterGroup = new ClusterGroup();
		
		String sqlDinero = "SELECT id_usuario, SUM(dinero), COUNT(numero_cuenta) FROM cuenta GROUP BY id_usuario";
		
		try {
			// Conseguir la informacion de la base de datos
			ArrayList<String[]> dataDinero = GeneralSQLFunctions.getCustomQuery(connection, sqlDinero,3);
			
			for(int i = 0; i < dataDinero.size(); i++) 
				clusterGroup.addElement(new ClusterElement(dataDinero.get(i)[0], 
						Double.valueOf(dataDinero.get(i)[1]),Double.valueOf(dataDinero.get(i)[2])));
			
			// Preparar los datos
			APair<ArrayList<ClusterElement>,double[][]> processeData =clusterGroup.prepareData();
			ArrayList<ClusterElement> orden = processeData.getIndex();

			int[] result = null;
			try {
				switch((Clustering.ClusteringAlgorithm)clusteringAlgorithm.getSelectedItem()) {
				case KMC:
					int clusterN = Integer.valueOf(txtKMCNCluster.getText());
					result = Clustering.KMC(processeData.getValue(),clusterN);
					
					break;
				case MSC:
					double kernelSize = (double)sldrMSCKernel.getValue();
					result = Clustering.MSC(processeData.getValue(),kernelSize);
					
					break;
				case DBSCAN:
					int minPoint = Integer.valueOf(txtDBSANMinPoint.getText());
					double raiud = (double)sldrRadius.getValue();
					result = Clustering.DBSCAN(processeData.getValue(),raiud, minPoint);
					break;
				default:
					break;
				}
			}catch(Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage());
				return;
			}
			// Clusterizar
			 
			for(int i = 0; i < result.length; i++) 
				clusterGroup.setCategory(orden.get(i), result[i]);
			
			
			saveClusterData(clusterGroup);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveClusterData(ClusterGroup clusterG) throws IOException {
		ObjectOutputStream o = new ObjectOutputStream(
				new FileOutputStream(
						new File(String.format("data/clusters/%s.bin", txtFileName.getText())
								)
						)
				);
		
		o.writeObject(clusterG);
		o.close();
	}
}
