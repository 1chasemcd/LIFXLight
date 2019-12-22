package packetConstruction;

import mainPackage.Convert;

public class Packet {

    private FrameHeader frameHeader;
    private FrameAddress frameAddress;
    private ProtocolHeader protocolHeader;
    private Payload payload;

    public Packet(int type, String... data) {
        frameAddress = new FrameAddress();

        String typeString = Convert.setDigits(Integer.toHexString(type), 4);

        protocolHeader = new ProtocolHeader(typeString);
        payload = new Payload(data);
        frameHeader = new FrameHeader(payload.getSize());
    }

    public String get() {
        return frameHeader.get() + frameAddress.get() + protocolHeader.get() + payload.get();
    }
}
