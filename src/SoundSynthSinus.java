import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class SoundSynthSinus extends SoundSynth {
    @Override
    public void makeSound(double frequency, int durationMs) {
        System.out.println("~~~ SONANT SINUS: " + frequency + "Hz durant " + durationMs + "ms ~~~");

        try {
            AudioFormat af = new AudioFormat(44100f, 8, 1, true, false);
            SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
            sdl.open(af);
            sdl.start();

            int bytesToPlay = (int)(durationMs * 44100f / 1000f);
            byte[] buffer = new byte[1];

            for (int i = 0; i < bytesToPlay; i++) {
                double angle = i / (44100f / frequency) * 2.0 * Math.PI;
                buffer[0] = (byte)(Math.sin(angle) * 127.0);
                sdl.write(buffer, 0, 1);
            }

            sdl.drain();
            sdl.stop();
            sdl.close();

        } catch (Exception e) {
            System.err.println("Error accedint a la targeta de so.");
        }
    }
}