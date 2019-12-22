package lightPackage;

import mainPackage.Convert;
import packetConstruction.Packet;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Light {

    private String address;
    public static final String OFF = "0000", ON = "FFFF";
    public static final String NO_APPLY = "00", APPLY = "01", APPLY_ONLY = "02";

    public Light(String address) {
        this.address = address;
    }

    public void sendPacket(Packet packet) throws IOException {
        byte[] message = Convert.hexToByteArray(packet.get());
        InetAddress iAddress = InetAddress.getByName(address);
        DatagramPacket dataPack = new DatagramPacket(message, message.length, iAddress, 56700);
        DatagramSocket socket = new DatagramSocket();

        socket.send(dataPack);
        socket.close();
    }

    public void setColor(LIFXColor color, int duration) throws IOException, InterruptedException {
        Packet setColor = new Packet(102, "00", color.h, color.s, color.b, color.k,
                Convert.setDigits(Integer.toHexString(duration),8));

        sendPacket(setColor);

        Thread.sleep(duration);
    }

    public void setColorZones(int startIndex, int endIndex, LIFXColor color, int duration, String apply)
            throws IOException, InterruptedException {
        Packet setColor = new Packet(501, Convert.setDigits(Integer.toHexString(startIndex / 4), 2),
                Convert.setDigits(Integer.toHexString(endIndex / 4), 2), color.h, color.s, color.b, color.k,
                Convert.setDigits(Integer.toHexString(duration),8), apply);

        sendPacket(setColor);

        Thread.sleep(duration);
    }

    public void setPower(String level, int duration) throws IOException, InterruptedException {
        Packet setPower = new Packet(117, level, Convert.setDigits(Integer.toHexString(duration),8));

        sendPacket(setPower);

        Thread.sleep(duration);
    }
}
