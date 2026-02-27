import java.util.ArrayList;
import java.util.List;

public class Song {
    private String id, title, artist, style;
    private int durationSeconds;
    private Mood mood;
    private List<Note> notes = new ArrayList<>();

    public Song(String id, String title, String artist, int durationSeconds, Mood mood, String style) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.durationSeconds = durationSeconds;
        this.mood = mood;
        this.style = style;
    }

    // Mètodes
    public void addNote(Note n) { 
        notes.add(n); 
    }

    public boolean isPlayable() { 
        return !notes.isEmpty(); 
    }

    // GETTERS
    public String getId() { return id; }
    public String getTitle() { return title; }
    public List<Note> getNotes() { return notes; }    
    public int getDurationSeconds() { return durationSeconds; }

    @Override
    public String toString() {
        String tag = isPlayable() ? "[PLAYABLE]" : "[NOT PLAYABLE]";
        return tag + " ID: " + id + " | " + title + " - " + artist + 
               " (" + durationSeconds + "s) | Mood: " + mood + " | Estil: " + style;
    }
}