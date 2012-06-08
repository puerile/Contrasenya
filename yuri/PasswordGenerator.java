package yuri;

import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator
{
	/**
	 * @author Yuri
	 */
	// Startpunkt in der ASCII-Tabelle
	private int start = 33;
	// Endpunkt in der ASCII-Tabelle
	private int end = 126;
	// Anzahl der ASCII-Zeichen
	private int range = end - start;
	// this is just for fun
	private String[] answers = {"What are you doing?",
			"You think you're being funny, don't you?",
			"Here's news for you: You aren't.", "Stop trolling me!",
			"I'm immune to trolling, you know.",
			"Actually, you're trolling yourself more than you're trolling me.",
			"You probably tell yourself this is fun.",
			"To be honest, that is kind of sad.",
			"You're not getting tired of it, are you?",
			"My grandma is a better troll than you are.",
			"In fact, everyone is.", "You know what? I'll just ignore you.",
			"..."};
	
	public int getLength()
	{
		// Tastatur-Input
		Scanner tastatur = new Scanner(System.in);
		//Zaehler-Variable
		int i = 0;
		// Laenge des Passworts
		int length;

		System.out.print("Length: ");

		// solange nach Laenge fragen, bis diese groesser 0
		do
		{
			// Tastatureingabe auslesen und in eine Variable fuellen
			length = tastatur.nextInt();

			// Ausgabe bei nicht passender Eingabe
			if (length < 1)
			{
				System.out.println(answers[i]);

				if (i < answers.length - 1)
				{
					System.out.print("Try again: ");
					i++;
				}
			}
		}
		while (length < 1);
		
		return length;
	}

	public String createPW(int length)
	{
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
		char[] charset = new char[range];

		// Array befuellen
		// dabei Zahl zu char konvertieren
		for (int i = 0; i < range; i++)
		{
			charset[i] = (char) (i + start);
		}

		// Anfang Passwortgenerator
		// wenn Passwort nur eine Stelle haben soll
		if (length == 1)
		{
			// zufaellige Stelle im Array auswaehlen
			password = "" + charset[rand.nextInt(range)];

			// Kriterien sind immer erfuellt,
			// also findet keine Ueberpruefung statt
			pwComplete = true;
		}

		// wenn Passwort zwei oder drei Stellen haben soll
		else
		{
			// solange das Passwort noch nicht fertig ist
			// [d.h. alle Stellen befuellt & alle Kriterien erfuellt]
			do
			{
				// am Anfang der Schleife nochmal alles auf 0/leer setzen,
				// damit nichts doppelt befuellt wird
				password = "";
				uppercase = 0;
				lowercase = 0;
				num = 0;
				specialChar = 0;

				for (int i = 0; i < length; i++)
				{
					// erstmal eine zufaellige Nummer bestimmen
					int number = rand.nextInt(range);

					// solange Zeichen ein Akzent ist, neu ziehen
					// [Akzente in Passwoertern sind Krebs]
					while (charset[number] == '´' || charset[number] == '`')
					{
						number = rand.nextInt(range);
					}

					// wenn es sich um einen Kleinbuchstaben handelt,
					// entsprechende Variable auf 1 setzen
					// [Verwendung von boolean waere ebenfalls moeglich]
					if ((number + start) > 96 && (number + start) < 122)
					{
						lowercase = 1;
					}

					// gleiches wie oben mit Grossbuchstaben
					else if ((number + start) > 64 && (number + start) < 91)
					{
						uppercase = 1;
					}

					// gleiches wie oben mit Ziffern
					else if ((number + start) > 47 && (number + start) < 58)
					{
						num = 1;
					}

					// wenn nichts davon zutrifft, ist es ein Sonderzeichen
					else
					{
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
				if (lowercase + uppercase + num + specialChar == length
						|| lowercase + uppercase + num + specialChar == 4)
				{
					pwComplete = true;
				}
			}
			while (!pwComplete);
		}

		return password;
	}
}
