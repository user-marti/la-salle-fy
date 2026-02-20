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

    // Mètodes
    public void addSongId(String songId) {
        this.songIds.add(songId);
    }

    public void removeSongId(String songId) {
        this.songIds.remove(songId);
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<String> getSongIds() { return songIds; }

    @Override
    public String toString() {
        return "Playlist: " + name + " (" + songIds.size() + " cançons) - Mood: " + mood;
    }
}