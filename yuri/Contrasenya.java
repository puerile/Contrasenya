package yuri;

public class Contrasenya
{
	/**
	 * @author Yuri
	 */

	public static void main(String[] args)
	{
		PasswordGenerator pwGen = new PasswordGenerator();

		// Passwort generieren lassen und
		// generiertes Passwort ausgeben
		System.out.println("Password: " + pwGen.createPW(pwGen.getLength()));
	}
}
