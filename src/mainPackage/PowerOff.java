package mainPackage;


import lightPackage.Light;
import java.io.IOException;

public class PowerOff {

    public static void main(String[] args) throws IOException, InterruptedException {
        Light buzzbar = new Light("192.168.0.70");

        buzzbar.setPower(Light.OFF, 1000);
    }
}