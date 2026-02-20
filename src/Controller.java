import java.util.ArrayList;

public class LasallefyController {
    private ArrayList<Song> biblioteca;
    private ArrayList<Playlist> llistes;

    public LasallefyController() {
        this.biblioteca = new ArrayList<>();
        this.llistes = new ArrayList<>();
    }

    public void afegirCanço(Song s) {
        biblioteca.add(s);
    }

    public void llistarCançons() {
        for (Song s : biblioteca) {
            // Imprimim l'objecte (Java cridarà al seu toString)
            System.out.println(s);
        }
    }

    // Busca canço segons ID
    public Song buscarCanço(String id) {
        for (Song s : biblioteca) {
            if (s.getId().equals(id)) return s;
        }
        return null; // Si no la troba
    }

    public void reproduirCanço(String id) {
        Song s = buscarCanço(id);

        // Validem si existeix per si es pot reorduir
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