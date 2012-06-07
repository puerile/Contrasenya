import java.util.Random;
import java.util.Scanner;

public class CreatePassword {

	/**
	 * @author Sarah Linda
	 */
	// Anzahl der ASCII-Zeichen
	private static int ASCII = 89;
	// Startpunkt in der ASCII-Tabelle (bis Stelle 122)
	private static int DIF = 33;

	private static String createPW(int length) {
		// Ueberpruefungsvariablen fuer Passwortkriterien
		int uppercase = 0;
		int lowercase = 0;
		int num = 0;
		int specialChar = 0;

		String password = "";
		boolean pwComplete = false;
		Random rand = new Random();

		// Array fuer die Zeichen
		// [als Stellen in der ASCII-Tabelle minus DIF]
		int[] charset = new int[ASCII];

		// Array befuellen
		for (int i = 0; i < ASCII; i++) {
			charset[i] = i + DIF;
		}

		// Anfang Passwortgenerator
		// wenn Passwort nur eine Stelle haben soll
		if (length == 1) {
			// zufaellige Stelle im ASCII-Array auswaehlen
			// und zu char konvertieren, um das Zeichen zu erhalten
			password = "" + (char) charset[rand.nextInt(89)];

			// Kriterien sind immer erfuellt,
			// also findet keine Ueberpruefung statt
			pwComplete = true;
		}

		// wenn Passwort zwei oder drei Stellen haben soll
		else if (length < 4) {
			// solange das Passwort noch nicht fertig ist
			// [d.h. alle Stellen befuellt & alle Kriterien erfuellt]
			do {
				// am Anfang der Schleife nochmal alles auf 0/leer setzen,
				// damit nichts doppelt befuellt wird
				password = "";
				uppercase = 0;
				lowercase = 0;
				num = 0;
				specialChar = 0;

				for (int i = 0; i < length; i++) {
					// erstmal eine zufaellige Nummer bestimmen
					int number = rand.nextInt(89);

					// solange Zeichen ein Akzent ist, neu ziehen
					// [Akzente in Passwoertern sind Krebs]
					while ((number + DIF) == 96) {
						number = rand.nextInt(89);
					}

					// wenn es sich um einen Kleinbuchstaben handelt,
					// entsprechende Variable auf 1 setzen
					// [Verwendung von boolean waere ebenfalls moeglich]
					if ((number + DIF) > 96 && (number + DIF) < 122) {
						lowercase = 1;
					}

					// gleiches wie oben mit Grossbuchstaben
					else if ((number + DIF) > 64 && (number + DIF) < 91) {
						uppercase = 1;
					}

					// gleiches wie oben mit Ziffern
					else if ((number + DIF) > 47 && (number + DIF) < 58) {
						num = 1;
					}

					// wenn nichts davon zutrifft, ist es ein Sonderzeichen
					else {
						specialChar = 1;
					}

					// ans bisherige Passwort anhaengen
					// [am Ende wegen der Akzentpruefung]
					password = password + (char) charset[number];
				}

				// wenn mindestens so viele Kriterien zutreffen,
				// wie das Passwort lang ist,
				// sind die Kriterien erfuellt
				// und die Schleife wird verlassen
				if (lowercase + uppercase + num + specialChar < length) {
					pwComplete = true;
				}
			} while (!pwComplete);
		}

		// wenn das Passwort vier zeichen oder mehr haben soll
		else {
			// solange noch nicht alle Kriterien erfuellt sind
			do {
				// alles zuruecksetzen [wie oben]
				password = "";
				uppercase = 0;
				lowercase = 0;
				num = 0;
				specialChar = 0;

				for (int i = 0; i < length; i++) {
					int number = rand.nextInt(89);

					while ((number + DIF) == 96) {
						number = rand.nextInt(89);
					}

					if ((number + DIF) > 96 && (number + DIF) < 122) {
						lowercase = 1;
					}

					else if ((number + DIF) > 64 && (number + DIF) < 91) {
						uppercase = 1;
					}

					else if ((number + DIF) > 47 && (number + DIF) < 58) {
						num = 1;
					}

					else {
						specialChar = 1;
					}

					password = password + (char) charset[number];
				}

				// wenn von jedem eins vorhanden
				// [d.h. jede Pruefungsvariable == 1]
				// beende Schleife [Summe ergibt 4]
				if (lowercase + uppercase + num + specialChar == 4) {
					pwComplete = true;
				}
			} while (!pwComplete);
		}

		return password;
	}

	public static void main(String[] args) {
		int length;
		// Tastatur-Input
		Scanner tastatur = new Scanner(System.in);
		// Tastatureingabe auslesen und in eine Variable fuellen

		// solange nach laenge fragen, bis diese groesser 0
		do {
			System.out.print("Laenge eingeben: ");

			length = tastatur.nextInt();

			// Ausgabe bei nicht passender Eingabe
			if (length < 1) {
				System.out.println("Dumm?");
			}
		} while (length < 1);

		// generiertes Passwort ausgeben
		System.out.println("Passwort: " + createPW(length));
	}
}
