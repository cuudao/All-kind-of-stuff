

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tietokanta {

	public static void main(String[] args) {

		ArrayList<Object[]> data = new ArrayList<Object[]>();
		
		
		try {

		
			Connection con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7269131", "sql7269131", "gW6QBJmeGB");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM kirja");
			int i = 0;

			
			while (rs.next()) {
				
				data.add( new Object[] { rs.getString(1), rs.getString(2), rs.getString(3) } ); 
				i++;
			}
		
			con.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Jokin meni pieleen");
		}
		
		

		JScrollPane scrollPane = new JScrollPane();
		
		
		DefaultTableModel model = new DefaultTableModel(); 
		model.addColumn("Kirjan nimi");
		model.addColumn("Tekijä");
		model.addColumn("Julkaisuvuosi");

		JTable table = new JTable(model);


		for (int i=0; i < data.size(); i++ ) {
			model.addRow(data.get(i));
		}
		
		JFrame panel = new JFrame(); 
		JPanel toiminnot = new JPanel(); 
		JButton lisää = new JButton("Lisää"); 
		
		
		lisää.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				JFrame lisäys = new JFrame();  
				lisäys.setSize(400, 200);
				lisäys.setTitle("Lisää Kirja"); 
				lisäys.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
				lisäys.setVisible(true); 
				lisäys.setLocationRelativeTo(null);
				JTextField tekstiKenttä = new JTextField(); 
				tekstiKenttä.setPreferredSize( new Dimension( 150, 20 ) );
				
				JTextField tekstiKenttä1 = new JTextField(); 
				tekstiKenttä1.setPreferredSize( new Dimension( 150, 20 ) );
				
				JTextField tekstiKenttä2 = new JTextField();
				tekstiKenttä2.setPreferredSize( new Dimension( 150, 20 ) );
				
				
				JButton lisääKirja = new JButton("Lisää kirjastoon"); 
				lisääKirja.setPreferredSize(new Dimension(140, 24));
				
				
				JPanel panel = new JPanel();
				JPanel panel1 = new JPanel();
				JPanel panel2 = new JPanel();
				JPanel pää = new JPanel();
				JPanel flow = new JPanel(new FlowLayout());
				
				JLabel kirjanNimi = new JLabel("Kirjan nimi:");
				kirjanNimi.setPreferredSize(new Dimension(100,24));
				JLabel tekijänNimi = new JLabel("Tekijä:");
				tekijänNimi.setPreferredSize(new Dimension(100, 24));
				JLabel julkaisuVuosi = new JLabel("Julkaisuvuosi:");
				julkaisuVuosi.setPreferredSize(new Dimension(100, 24));
				
				panel.add(kirjanNimi, BorderLayout.EAST);
				panel.add(tekstiKenttä, BorderLayout.WEST);
				panel1.add(tekijänNimi, BorderLayout.EAST);
				panel1.add(tekstiKenttä1, BorderLayout.WEST); 
				panel2.add(julkaisuVuosi, BorderLayout.EAST);
				panel2.add(tekstiKenttä2, BorderLayout.WEST);
				
				
				flow.add(lisääKirja);
				pää.add(panel, BorderLayout.NORTH);
				pää.add(panel1, BorderLayout.CENTER);
				pää.add(panel2, BorderLayout.SOUTH);
				lisäys.getContentPane().add(pää, BorderLayout.CENTER);
				lisäys.getContentPane().add(flow, BorderLayout.SOUTH);
				
				
				
				lisääKirja.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
				try {
					String nimi = tekstiKenttä.getText();
					String tekijä = tekstiKenttä1.getText();
					String vuosiTekstinä = tekstiKenttä2.getText();
					ArrayList<Object[]> data1 = new ArrayList<Object[]>(); 
					int vuosi = Integer.parseInt(vuosiTekstinä);
					
					
					Connection con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7269131", "sql7269131", "gW6QBJmeGB");

				
					Statement stmt = con.createStatement();
					
					if(nimi != null || tekijä != null || vuosiTekstinä != null){ 
					String query = " insert into kirja (kirjan_nimi, tekijä, julkaisuvuosi)"
					        + " values (?, ?, ?)";
					
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
				      preparedStmt.setString (1, nimi);
				      preparedStmt.setString (2, tekijä);
				      preparedStmt.setInt(3, vuosi);
				      preparedStmt.execute();

				      
				      JOptionPane.showMessageDialog(pää, "Lisätty tietokantaan");
				      lisäys.dispose();
				     
				      
					} else { 
						JOptionPane.showMessageDialog(pää, "Täytä kaikki kohdat!");
					}
					
		

					ResultSet rs = stmt.executeQuery("SELECT * FROM kirja");
					int i = 0;
					
					while (rs.next()) {

						data1.add( new Object[] { rs.getString(1), rs.getString(2), rs.getString(3) } ); 
						i++;
					}
					
					 DefaultTableModel model1=(DefaultTableModel)table.getModel();
			            int rc= model.getRowCount();
			            for(i = 0;i<rc;i++){
			                model1.removeRow(0);
			            }     
					
					con.close();
					
					
					for (int x=0; x < data1.size(); x++ ) { 
						model.addRow(data1.get(x));
					}
				
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Toimintoa ei voitu toetuttaa");
				}
			}
		});
			}
		});
		
		JButton poista = new JButton("Poista");
		poista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int delete = table.getSelectedRow(); 
				
				Object tieto = table.getValueAt(delete, 0);
				
				
				String muokkaa = String.valueOf(tieto);
				
				
				int modelRow = table.convertRowIndexToModel(delete);
				model.removeRow(modelRow);
				
				
				String pois = "DELETE FROM kirja WHERE kirjan_nimi = " + "'"+muokkaa+"'" + ";";
				
				
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7269131", "sql7269131", "gW6QBJmeGB");
					
					Statement stmt = con.createStatement();
					stmt.executeUpdate(pois);
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "'" + muokkaa + "'" + " terminated from database");
					
				} catch (SQLException e1) {
					
					JOptionPane.showMessageDialog(null, "Tapahtui virhe");
					e1.printStackTrace();
				}
				
			}
		});
		
		
		panel.setTitle("Kirjatietokanta");
		panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		toiminnot.add(lisää);
		toiminnot.add(poista);
		
		JMenuBar menuBar = new JMenuBar();
		panel.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu menu_1 = new JMenu("Tiedosto");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_1 = new JMenuItem("Lisää kirja");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				JFrame lisäys = new JFrame();  
				lisäys.setSize(400, 200);
				lisäys.setTitle("Lisää Kirja"); 
				lisäys.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
				lisäys.setVisible(true); 
				lisäys.setLocationRelativeTo(null);
				JTextField tekstiKenttä = new JTextField(); 
				tekstiKenttä.setPreferredSize( new Dimension( 150, 20 ) );
				
				JTextField tekstiKenttä1 = new JTextField(); 
				tekstiKenttä1.setPreferredSize( new Dimension( 150, 20 ) );
				
				JTextField tekstiKenttä2 = new JTextField();
				tekstiKenttä2.setPreferredSize( new Dimension( 150, 20 ) );
				
				
				JButton lisääKirja = new JButton("Lisää kirjastoon"); 
				lisääKirja.setPreferredSize(new Dimension(140, 24));
				
				
				JPanel panel = new JPanel();
				JPanel panel1 = new JPanel();
				JPanel panel2 = new JPanel();
				JPanel pää = new JPanel();
				JPanel flow = new JPanel(new FlowLayout());
				
				JLabel kirjanNimi = new JLabel("Kirjan nimi:");
				kirjanNimi.setPreferredSize(new Dimension(100,24));
				JLabel tekijänNimi = new JLabel("Tekijä:");
				tekijänNimi.setPreferredSize(new Dimension(100, 24));
				JLabel julkaisuVuosi = new JLabel("Julkaisuvuosi:");
				julkaisuVuosi.setPreferredSize(new Dimension(100, 24));
				
				panel.add(kirjanNimi, BorderLayout.EAST);
				panel.add(tekstiKenttä, BorderLayout.WEST);
				panel1.add(tekijänNimi, BorderLayout.EAST);
				panel1.add(tekstiKenttä1, BorderLayout.WEST); 
				panel2.add(julkaisuVuosi, BorderLayout.EAST);
				panel2.add(tekstiKenttä2, BorderLayout.WEST);
				
				
				flow.add(lisääKirja);
				pää.add(panel, BorderLayout.NORTH);
				pää.add(panel1, BorderLayout.CENTER);
				pää.add(panel2, BorderLayout.SOUTH);
				lisäys.getContentPane().add(pää, BorderLayout.CENTER);
				lisäys.getContentPane().add(flow, BorderLayout.SOUTH);
				
				
				
				lisääKirja.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
				try {
					String nimi = tekstiKenttä.getText();
					String tekijä = tekstiKenttä1.getText();
					String vuosiTekstinä = tekstiKenttä2.getText();
					ArrayList<Object[]> data1 = new ArrayList<Object[]>(); 
					int vuosi = Integer.parseInt(vuosiTekstinä);
					
					
					Connection con = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7269131", "sql7269131", "gW6QBJmeGB");

				
					Statement stmt = con.createStatement();
					
					if(nimi != null || tekijä != null || vuosiTekstinä != null){ 
					String query = " insert into kirja (kirjan_nimi, tekijä, julkaisuvuosi)"
					        + " values (?, ?, ?)";
					
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
				      preparedStmt.setString (1, nimi);
				      preparedStmt.setString (2, tekijä);
				      preparedStmt.setInt(3, vuosi);
				      preparedStmt.execute();

				      
				      JOptionPane.showMessageDialog(pää, "Lisätty tietokantaan");
				      lisäys.dispose();
				     
				      
					} else { 
						JOptionPane.showMessageDialog(pää, "Täytä kaikki kohdat!");
					}
					
		

					ResultSet rs = stmt.executeQuery("SELECT * FROM kirja");
					int i = 0;
					
					while (rs.next()) {

						data1.add( new Object[] { rs.getString(1), rs.getString(2), rs.getString(3) } ); 
						i++;
					}
					
					 DefaultTableModel model1=(DefaultTableModel)table.getModel();
			            int rc= model.getRowCount();
			            for(i = 0;i<rc;i++){
			                model1.removeRow(0);
			            }     
					
					con.close();
					
					
					for (int x=0; x < data1.size(); x++ ) { 
						model.addRow(data1.get(x));
					}
				
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Toimintoa ei voitu toetuttaa");
				}
			}
		});
			}
		});
		
		menu_1.add(menuItem_1);
		
		JMenuItem mntmSulje = new JMenuItem("Sulje");
		mntmSulje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		JMenuItem mntmPoistaKirja = new JMenuItem("Poista kirja");
		menu_1.add(mntmPoistaKirja);
		menu_1.add(mntmSulje);
		
		JMenu mnTietoja = new JMenu("Tietoja");
		menuBar.add(mnTietoja);
		
		JMenuItem mntmTietojaOhjelmasta = new JMenuItem("Tietoja Ohjelmasta");
		mntmTietojaOhjelmasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = new ImageIcon ("Saitamaok.png");
				JOptionPane.showMessageDialog(null, "\u00a9 Taas yritetään,   2018 \n"
						+ "" , "TIEDOT", JOptionPane.INFORMATION_MESSAGE, icon);
			}
		});
		
		mntmTietojaOhjelmasta.setIcon(new ImageIcon(Tietokanta.class.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
		mnTietoja.add(mntmTietojaOhjelmasta);
		
		panel.getContentPane().add(scrollPane, BorderLayout.CENTER);
		panel.getContentPane().add(toiminnot, BorderLayout.SOUTH);
		
		
		JButton päivitä = new JButton("p\u00E4ivit\u00E4");
		toiminnot.add(päivitä);
		
		scrollPane.setViewportView(table);

		panel.pack();
		panel.setLocationRelativeTo(null); 
		panel.setVisible(true);

	}

}
	