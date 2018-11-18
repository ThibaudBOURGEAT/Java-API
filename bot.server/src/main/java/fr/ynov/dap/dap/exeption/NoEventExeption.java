package fr.ynov.dap.dap.exeption;

public class NoEventExeption extends Exception  {

	public NoEventExeption() {
        super("Pas d'évènement disponnible pour le moment.");
    }
}
