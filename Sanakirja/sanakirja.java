
import java.util.Scanner;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

public class sanakirja {
	
	static void serializeToXML(HashMap<String, String> sanavarasto) throws Exception  {
		FileOutputStream fos = new FileOutputStream("Sanasto.xml");
		XMLEncoder encoder = new XMLEncoder(fos);
		encoder.writeObject(sanavarasto);
		encoder.close();
		fos.close();
	}

	static HashMap<String, String> deserializeFromXML() throws Exception {
		FileInputStream fis = new FileInputStream("Sanasto.xml");
		XMLDecoder decoder = new XMLDecoder(fis);
		HashMap<String, String> decodedSanasto = (HashMap<String, String>) decoder.readObject();
		decoder.close();
		fis.close();
		return decodedSanasto;
	}

public static void main(String[] args) throws Exception {
	
	String[] fin = { "kissa", "koira", "hevonen", "auto", "vene" };
	String[] eng = { "cat", "dog", "horse", "car", "boat" };
	HashMap <String, String> sanakirja = new HashMap<String, String>();

	

	
		
		for (int i = 0; i < fin.length; i++) {
			sanakirja.put(fin[i], eng[i]);
		}
		
		Scanner lukija = new Scanner(System.in);
		
		while (true) {
			System.out.println("Valitse toiminto.");
			System.out.println("1. Sanakirja");
			System.out.println("2. Lisää sanoja");
			System.out.println("3. Lopeta");
			
			int nro = lukija.nextInt();
			
			
			if (nro == 1) {
				System.out.println("Sanakirja: " + sanakirja);
				String luku = lukija.nextLine();
				while (true) {
					
					System.out.println();
					System.out.print("Minkä sanan käännöksen haluat tietää? (tyhjä sanalla alotukseen) ");
					String sana = lukija.nextLine();
					
					if (sanakirja.containsKey(sana)) {
						System.out.println("Sanan \"" + sana + "\" käännös on \"" + sanakirja.get(sana) + "\"");
						continue;
					} else if (sanakirja.containsKey(sana) == false && !(sana.isEmpty())){
						System.out.println("Valittua sanaa ei löydy");
						continue;
					} else if (sana.isEmpty());{
						break;
					}
					
				}
			} else if (nro == 2) {
				System.out.println("Sanakirja: " + sanakirja + "\n");
				String luku = lukija.nextLine();
				while (true) {
					lukija.reset();
					System.out.println("Sana alkukielellä? (tyhjä sanalla alotukseen): ");
					String suomi = lukija.nextLine();
					if (suomi.length() > 0) {
						
					} else {
						break;
					}
					System.out.println("Sana käännettynä? (tyhjä sanalla alotukseen): ");
					String enkku = lukija.nextLine();
					sanakirja.put(suomi, enkku);
				}
				
				System.out.println("Sanakirjan sisältö: " + sanakirja + "\n");
				
				
			} else if (nro == 3) {
				serializeToXML(sanakirja);
				System.out.println("Kiitos näkemiin! \n");
				System.out.print("Sanakirjan sanat: " + sanakirja);
				System.exit(0);
				
				
				
				
			}
		}
	}

}
