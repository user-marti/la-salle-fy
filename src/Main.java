import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Controller control = new Controller();
        String opcio = "";

        // Dades inicials per provar (com demana l'entrega)
        Song s1 = new Song("1", "Imagine", "Lennon", 180, Mood.HAPPY, "Pop");
        s1.addNote(new Note(440.0, 500, WaveType.SINE)); // Afegim una nota
        control.afegirCanço(s1);

        while (!opcio.equals("Q")) {
            System.out.println("\nLasallefy");
            System.out.println("1. Llistar cançons");
            System.out.println("2. Reproduir canço");
            System.out.println("Q. Sortir");
            System.out.print("Tria: ");
            opcio = sc.nextLine().toUpperCase();

            switch (opcio) {
                case "1":
                    control.llistarCançons();
                    break;
                case "2":
                    System.out.print("ID de la canço: ");
                    String id = sc.nextLine();
                    control.reproduirCanço(id);
                    break;
            }
        }
    }
}