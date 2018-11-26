

import java.util.Scanner;
import java.util.HashMap;

public class sanakirja {
	public static String[] fin = { "kissa", "koira", "hevonen", "auto", "vene" };
	public static String[] eng = { "cat", "dog", "horse", "car", "boat" };
	public static HashMap <String, String> sanakirja = new HashMap<String, String>();

	public static void main(String[] args) {
		
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
					System.out.print("Minkä sanan käännöksen haluat tietää? (tyhjä sanalla alotukseen) \n");
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
					String fin = lukija.nextLine();
					if (fin.length() > 0) {
						
					} else {
						break;
					}
					System.out.println("Sana käännettynä? (tyhjä sanalla alotukseen): ");
					String eng = lukija.nextLine();
					sanakirja.put(fin, eng);
				}
				
				System.out.println("Sanakirjan sisältö: " + sanakirja + "\n");
				
				
			} else if (nro == 3) {
				System.out.println("Kiitos näkemiin!");
				System.exit(0);
				
				
			}
		}
	}

}
