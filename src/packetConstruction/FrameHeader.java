package packetConstruction;

import mainPackage.Convert;

public class FrameHeader {

    // Prepare variables. All variables should be binary EXCEPT size.
    private String size; // 2 bytes. Counts number of bytes in message
    private String origin = "00"; // 2 bits
    private String tagged; // 1 bit
    private String addressable = "1"; // 1 bit
    private String protocol = "010000000000"; //12 bits
    private String source; // 4 bytes. Hexadecimal

    public FrameHeader(int payloadSize, String tagged, String source) {
        this.size = Convert.setDigits(Integer.toHexString(8 + 16 + 12 + payloadSize), 4);

        this.tagged = tagged;
        this.source = Convert.setDigits(source, 8);
    }

    public FrameHeader(int payloadSize) {
        this.size = Convert.setDigits(Integer.toHexString(8 + 16 + 12 + payloadSize), 4);

        this.tagged = "1";
        this.source = "00000000";
    }

    public String get() {
        String constructed = "";

        constructed += Convert.hexToLittleEndian(size);

        constructed += Convert.hexToLittleEndian(Convert.binaryToHex(
                origin + tagged + addressable + protocol));

        constructed += Convert.hexToLittleEndian(source);

        return constructed;
    }
}
