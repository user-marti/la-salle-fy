import java.util.ArrayList;

public class Controller {
    private ArrayList<Song> biblioteca;
    private ArrayList<Playlist> llistes;
    private MenuController menu;

    public Controller() {
        this.biblioteca = new ArrayList<>();
        this.llistes = new ArrayList<>();
        this.menu = new MenuController();
    }

    public void run() {
        String opcio = "";

        while (!opcio.equals("Q")) {
            menu.showMainMenu();
            opcio = menu.getString().toUpperCase();

            switch (opcio) {
                case "1":
                    gestionarCancons();
                    break;
                case "2":
                    gestionarPlaylists();
                    break;
                case "3":
                    menuReproduccio();
                    break;
                case "Q":
                    menu.show("Fins la pròxima!");
                    break;
                default:
                    menu.show("Opció no vàlida.");
            }
        }
    }

    // GESTIÓ DE CANÇONS
    private void gestionarCancons() {
        menu.show("\n--- Gestió de Cançons ---");
        menu.show("1. Llistar totes");
        menu.show("2. Afegir nova cançó");
        menu.show("3. Eliminar cançó");
        menu.show("B. Tornar");
        String subOpcio = menu.getString().toUpperCase();

        if (subOpcio.equals("1")) {
            llistarCancons();
        } else if (subOpcio.equals("2")) {
            crearNovaCanco();
        } else if (subOpcio.equals("3")) {
            eliminarCanco();
        }
    }

    private void llistarCancons() {
        if (biblioteca.isEmpty()) {
            menu.show("La biblioteca està buida.");
        } else {
            for (Song s : biblioteca) {
                menu.show(s.toString()); // Mostra ID, títol, artista i [PLAYABLE]
            }
        }
    }

    private void crearNovaCanco() {
        menu.show("ID:");
        String id = menu.getString();
        menu.show("Títol:");
        String titol = menu.getString();
        menu.show("Artista:");
        String artista = menu.getString();
        menu.show("Durada (segons):");
        int durada = menu.getInteger();
        menu.getString(); // Netejem entreada 

        Song nova = new Song(id, titol, artista, durada, Mood.HAPPY, "Generic");
        
        menu.show("És reproducible? (S/N)");
        if (menu.getString().equalsIgnoreCase("S")) {
            // Aqui afegim una nota d'exemple per fer-la playable
            nova.addNote(new Note(440.0, 500, WaveType.SINE));
            menu.show("Cançó creada amb notes de prova.");
        }

        biblioteca.add(nova);
        menu.show("Cançó afegida amb èxit.");
    }

    private void eliminarCanco() {
        menu.show("Escriu l'ID de la cançó a eliminar:");
        String id = menu.getString();
        Song s = buscarCanco(id);

        if (s != null) {
            biblioteca.remove(s);
            menu.show("Cançó eliminada.");
        } else {
            menu.show("Error: ID no trobat.");
        }
    }

    // GESTIÓ DE PLAYLISTS
    private void gestionarPlaylists() {
        menu.show("\n--- Gestió de Playlists ---");
        menu.show("1. Llistar Playlists");
        menu.show("2. Crear Playlist");
        menu.show("3. Afegir cançó a Playlist");
        String subOpcio = menu.getString().toUpperCase();

        if (subOpcio.equals("1")) {
            for (Playlist p : llistes) menu.show(p.toString());
        } else if (subOpcio.equals("2")) {
            crearPlaylist();
        } else if (subOpcio.equals("3")) {
            afegirCancoAPlaylist();
        }
    }

    private void crearPlaylist() {
        menu.show("ID de la Playlist:");
        String id = menu.getString();
        menu.show("Nom:");
        String nom = menu.getString();
        
        Playlist novaP = new Playlist(id, nom, "Sense descripció", Mood.RELAX);
        llistes.add(novaP);
        menu.show("Playlist creada.");
    }

    private void afegirCancoAPlaylist() {
        menu.show("ID de la Playlist:");
        String idPl = menu.getString();
        Playlist p = buscarPlaylist(idPl);

        if (p != null) {
            llistarCancons();
            menu.show("ID de la cançó a afegir:");
            String idS = menu.getString();
            if (buscarCanco(idS) != null) {
                p.addSongId(idS);
                menu.show("Afegida correctament.");
            } else {
                menu.show("La cançó no existeix.");
            }
        } else {
            menu.show("La llista no existeix.");
        }
    }

    // REPRODUCCIÓ
    private void menuReproduccio() {
        menu.show("\n--- Cançons Reproduïbles ---");
        boolean hiHaPlayables = false;
        for (Song s : biblioteca) {
            if (s.isPlayable()) {
                menu.show(s.toString());
                hiHaPlayables = true;
            }
        }

        if (!hiHaPlayables) {
            menu.show("No hi ha cançons per reproduir.");
            return;
        }

        menu.show("Escriu el ID per sonar: ");
        String id = menu.getString();
        reproduirCanco(id);
    }

    public void reproduirCanco(String id) {
        Song s = buscarCanco(id);

        if (s == null || !s.isPlayable()) {
            menu.show("Error: No es pot reproduir.");
            return;
        }

        SoundSynth synth = new SoundSynthSinus(); //
        menu.show("Reproduint: " + s.getTitle());

        for (Note n : s.getNotes()) {
            if (n.isSilence()) {
                synth.playSilence(n.getTiempo());
            } else {
                synth.makeSound(n.getFrecuencia(), n.getTiempo());
            }
        }
    }

    // METODES SUB
    private Song buscarCanco(String id) {
        for (Song s : biblioteca) {
            if (s.getId().equals(id)) return s;
        }
        return null;
    }

    private Playlist buscarPlaylist(String id) {
        for (Playlist p : llistes) {
            if (p.getId().equals(id)) return p;
        }
        return null;
    }
}