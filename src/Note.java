public class Note {
    private double frecuencia; // en herzios
    private int tiempo;        // en milisegundos
    private WaveType wt;       // timbre

    // Constructor principal
    public Note(double frecuencia, int tiempo, WaveType wt) {
        this.frecuencia = frecuencia;
        this.tiempo = tiempo;
        this.wt = wt;
    }

    public Note(int durationMs) {
        this(0, durationMs, WaveType.SILENCE);
    }

    public double getFrecuencia() { return frecuencia; }
    public int getTiempo() { return tiempo; }
    public WaveType getWt() { return wt; }

    public boolean isSilence() {
        return this.wt == WaveType.SILENCE;
    }
}