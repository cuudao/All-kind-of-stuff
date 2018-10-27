import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.InputEvent;
import javax.swing.ImageIcon;

public class tekstieditori extends JFrame {

	private JPanel contentPane;
	private JEditorPane editorPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tekstieditori frame = new tekstieditori();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public tekstieditori() {
		setTitle("Editori");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.activeCaption);
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Tiedosto");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmAvaa = new JMenuItem("Avaa");
		mntmAvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				
				JFileChooser valintaikkuna = new JFileChooser();
				valintaikkuna.showOpenDialog(null);
				valintaikkuna.setApproveButtonText("Avaa");
				valintaikkuna.setDialogTitle("Valitse tiedosto");
				
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
				String rivi = "";
				Scanner lukija = null;
				File tiedosto = new File(uusiTiedosto);
				
				try {
					
					
					lukija = new Scanner(tiedosto);
					
					while (lukija.hasNextLine()) {
						rivi += lukija.nextLine() + "\n";
						System.out.println(rivi);
						
					}
					lukija.close();
					
				} catch (FileNotFoundException p) {
					System.out.println("Error");
				}
				
				editorPane.setText(rivi);
				
			}
		});
		mntmAvaa.setIcon(new ImageIcon(tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		mntmAvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmAvaa);
		
		JMenuItem mntmTallenna = new JMenuItem("Tallenna");
		mntmTallenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser valintaikkuna = new JFileChooser();
				valintaikkuna.showSaveDialog(null);
				
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
				
				System.out.println("Kirjoitettava tiedosto: " + uusiTiedosto);
				try {
					
					PrintWriter writer = new PrintWriter(uusiTiedosto);
					String sisalto = editorPane.getText();
					
					writer.println(sisalto);
					
					writer.flush();
					writer.close();
					
				} catch (Exception e1) {
					System.out.println("Virhe tallennuksessa");
					e1.printStackTrace();
				}
			}
		});
		mntmTallenna.setIcon(new ImageIcon(tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		mntmTallenna.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmTallenna);
		
		JMenuItem mntmLopeta = new JMenuItem("Lopeta");
		mntmLopeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmLopeta);
		
		JMenuItem mntmSulje = new JMenuItem("Sulje");
		mntmSulje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		mntmSulje.setIcon(new ImageIcon(tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		mntmSulje.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		mnNewMenu.add(mntmSulje);
		
		JMenu mnMuokkaa = new JMenu("Muokkaa");
		menuBar.add(mnMuokkaa);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Etsi");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
								
				String sisalto = editorPane.getText();
				sisalto = sisalto.toLowerCase();
				
				String haettava = "auto";
				int indeks = sisalto.indexOf(haettava);
								
				editorPane.setSelectionColor(Color.YELLOW);
				
				editorPane.setSelectionStart(indeks);
				editorPane.setSelectionEnd(indeks + haettava.length());
				System.out.println("Indeksi: " + indeks);
				
			}
		});
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		mnMuokkaa.add(mntmNewMenuItem);
		
		JMenuItem mntmKorvaa = new JMenuItem("Korvaa");
		mntmKorvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String korvaa = editorPane.getText();
						
						if (!korvaa.isEmpty()) {
							editorPane.replaceSelection("Shhhh...");
						}
					}
				});
		
		mnMuokkaa.add(mntmKorvaa);
		
		JMenu mnTietoja = new JMenu("Tietoja");
		menuBar.add(mnTietoja);
		
		JMenuItem mntmTietojaOhjelmasta = new JMenuItem("Tietoja Ohjelmasta");
		mntmTietojaOhjelmasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = new ImageIcon ("Saitamaok.png");
				JOptionPane.showMessageDialog(null, "\u00a9 Yriys on kovaa Oy,   2018 \n"
						+ "" , "TIEDOT", JOptionPane.INFORMATION_MESSAGE, icon);
			}
		});
		mntmTietojaOhjelmasta.setIcon(new ImageIcon(tekstieditori.class.getResource("/com/sun/java/swing/plaf/motif/icons/Inform.gif")));
		mnTietoja.add(mntmTietojaOhjelmasta);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton uusi = new JButton("");
		uusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		JFileChooser valintaikkuna = new JFileChooser();				
		valintaikkuna.setApproveButtonText("Avaa");
		valintaikkuna.setDialogTitle("Valitse tiedosto");
		valintaikkuna.showOpenDialog(null);
		String rivi = "";
		String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();

	try {
		Scanner lukija = null;
		File tiedosto = new File(uusiTiedosto);
		lukija = new Scanner(tiedosto);
		
		while (lukija.hasNextLine()) {
		rivi += lukija.nextLine()+"\n";
		}
		
	} catch (FileNotFoundException p) {
		System.out.println("Tiedostoa ei löydy");
	}
			
	editorPane.setText(rivi);
	}
	
});
		uusi.setIcon(new ImageIcon(tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		toolBar.add(uusi);
		
		JButton leikkaa = new JButton("");
		leikkaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String korvaa = editorPane.getText();
						
						if (!korvaa.isEmpty()) {
							editorPane.replaceSelection("Shhhh...");
						}
					}
				});
		leikkaa.setIcon(new ImageIcon(tekstieditori.class.getResource("/com/sun/javafx/scene/web/skin/Cut_16x16_JFX.png")));
		toolBar.add(leikkaa);
		
		JButton save = new JButton("");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser valintaikkuna = new JFileChooser();
				
				valintaikkuna.setApproveButtonText("Tallenna");
				valintaikkuna.setDialogTitle("Tiedoston valinta");
				valintaikkuna.showSaveDialog(null);
				String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
				System.out.println("Kirjoitettava tiedosto: " + uusiTiedosto);
						
			try {
			
				PrintWriter writer = new PrintWriter(uusiTiedosto);
				String sisalto = editorPane.getText();
				System.out.println(sisalto);
				
				writer.println( sisalto );
				writer.flush();
				writer.close();
			
			} catch (Exception e1) {
				System.out.println("Virhe tallennuksessa");
				e1.printStackTrace();
			}
				
			}
		});
		
		save.setIcon(new ImageIcon(tekstieditori.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		toolBar.add(save);
		
		JProgressBar progressBar = new JProgressBar();
		contentPane.add(progressBar, BorderLayout.SOUTH);
		
		editorPane = new JEditorPane();
		contentPane.add(editorPane, BorderLayout.CENTER);
	}
	
	public JEditorPane getEditorPane() {
		return editorPane;
	}
}
