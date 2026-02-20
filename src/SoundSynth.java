public abstract class SoundSynth {
    // Mètode
    public abstract void makeSound(double frequency, int durationMs);

    public void playSilence(int durationMs) {
        try {
            Thread.sleep(durationMs);
        } catch (InterruptedException e) {
            System.err.println("Error en el silenci");
        }
    }
}