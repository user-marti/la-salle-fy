import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private ArrayList<Song> biblioteca;
    private ArrayList<Playlist> llistes;
    private Scanner sc = new Scanner(System.in);

    public void run() {
        Controller control = new Controller();
        String opcio = "";

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
                    System.out.println("\n--- Cançons disponibles ---");
                    control.llistarCançons();
                    System.out.println("---------------------------");

                    System.out.print("Escriu l'ID de la canço a reproduir: ");
                    String id = sc.nextLine();
                    control.reproduirCanço(id);
                    break;
            }
        }
    }

    public Controller() {
        this.biblioteca = new ArrayList<>();
        this.llistes = new ArrayList<>();
    }

    public void afegirCanço(Song s) {
        biblioteca.add(s);
    }

    public void llistarCançons() {
        for (Song s : biblioteca) {
            System.out.println(s);
        }
    }

    // Busquem canço segons ID
    public Song buscarCanço(String id) {
        for (Song s : biblioteca) {
            if (s.getId().equals(id)) return s;
        }
        return null;
    }

    public void reproduirCanço(String id) {
        Song s = buscarCanço(id);

        // Mirem si existeix per si es pot reorduir
        if (s == null) {
            System.out.println("Error: No existeix la canço.");
            return;
        }

        if (!s.isPlayable()) {
            System.out.println("Error: Aquesta canço no té notes.");
            return;
        }

        SoundSynth synth = new SoundSynthSinus();

        System.out.println("Reproduint: " + s.getTitle());

        // Recorrem les notes una a una
        for (Note n : s.getNotes()) {
            if (n.isSilence()) {
                synth.playSilence(n.getTiempo());
            } else {
                synth.makeSound(n.getFrecuencia(), n.getTiempo());
            }
        }
    }
}