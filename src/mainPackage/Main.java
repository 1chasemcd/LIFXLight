package mainPackage;

import java.io.*;
import lightPackage.LIFXColor;
import lightPackage.Light;
import javax.sound.sampled.*;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Light buzzbar = new Light("192.168.0.70");

        buzzbar.setPower(Light.ON, 1000);
        TargetDataLine line;
        AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        double max = 0;
        double min = 0;

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
                buzzbar.setColor(LIFXColor.rgbk(0, 0, 0, 3500), 0);
                int volume = Convert.rootMeanSquare(data);

                if (volume > max) {
                    max = volume;
                } else if (max > min + 15){
                    max -= 0.2;
                }

                if (volume < min) {
                    min = volume;
                } else if (min < max - 15){
                    min += 0.2;
                }

                int colorValue = 0;

                if (max - min != 0) {
                    colorValue = (int) ((volume - min) / (max - min) * 255.0);
                }

                buzzbar.setColor(LIFXColor.rgbk(0, 0, colorValue, 3500), 5);
            }

        } catch (LineUnavailableException ex) {
            System.out.println("Line not availible: " + ex);
        }

        buzzbar.setPower(Light.OFF, 1000);
    }
}