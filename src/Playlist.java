import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String id;
    private String name;
    private String description;
    private Mood mood;
    private List<String> songIds;

    // Constructor
    public Playlist(String id, String name, String description, Mood mood) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.mood = mood;
        this.songIds = new ArrayList<>();
    }

    // Mètodes bàsics
    public void addSongId(String songId) {
        this.songIds.add(songId);
    }

    public void removeSongId(String songId) {
        this.songIds.remove(songId);
    }

    // CÀLCULS DE GESTIÓ
    public int getDuradaTotal(List<Song> biblioteca) {
        int total = 0;
        for (String sId : songIds) {
            Song s = buscarCanco(sId, biblioteca);
            if (s != null) {
                total += s.getDurationSeconds();
            }
        }
        return total;
    }

    // Conta quantes cançons de la llista es poden reproduir
    public int getCountPlayable(List<Song> biblioteca) {
        int count = 0;
        for (String sId : songIds) {
            Song s = buscarCanco(sId, biblioteca);
            if (s != null && s.isPlayable()) {
                count++;
            }
        }
        return count;
    }

    private Song buscarCanco(String id, List<Song> biblioteca) {
        for (Song s : biblioteca) {
            if (s.getId().equals(id)) return s;
        }
        return null;
    }

    // GETTERS
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<String> getSongIds() { return songIds; }

    @Override
    public String toString() {
        return "Playlist: " + name + " (ID: " + id + ") - Mood: " + mood + 
               "\n   Descripció: " + description + 
               "\n   Cançons afegides: " + songIds.size();
    }
}