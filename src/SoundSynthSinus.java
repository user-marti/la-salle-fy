public class SoundSynthSinus extends SoundSynth {
    @Override
    public void makeSound(double frequency, int durationMs) {
        System.out.println("~~~ SONANT SINUS: " + frequency + "Hz durant " + durationMs + "ms ~~~");
    }
}