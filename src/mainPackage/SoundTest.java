package mainPackage;

import javax.sound.sampled.*;

public class SoundTest {
    public static void main(String[] args) {

        TargetDataLine line;
        AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("Line not supported");
        }
        // Obtain and open the line.
        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);

            // Read audio
            //ByteArrayOutputStream out  = new ByteArrayOutputStream();
            int numBytesRead;
            byte[] data = new byte[line.getBufferSize() / 5];

            // Begin audio capture.
            line.start();

            long startTime = System.nanoTime();
            // Here, stopped is a global boolean set by another thread.
            while (System.nanoTime() < startTime + 60000000000.0) {
                // Read the next chunk of data from the TargetDataLine.
                numBytesRead = line.read(data, 0, data.length);
            }

        } catch (LineUnavailableException ex) {
            System.out.println("Line not availible: " + ex);
        }
    }
}
