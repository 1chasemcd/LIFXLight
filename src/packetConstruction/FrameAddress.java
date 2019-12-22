package packetConstruction;

import mainPackage.Convert;

public class FrameAddress {

    // Prepare variables. All variables should be in Hexadecimal unless otherwise specified
    private String target; // 8 bytes
    private String reserved1 = "000000000000"; // 6 bytes
    private String reserved2 = "000000"; // 6 bits binary
    private String ackRequired; // 1 bit binary
    private String resRequired; // 1 bit binary
    private String sequence; // 1 byte

    public FrameAddress(String target, String binAckRequired, String binResRequired, String sequence) {
        this.target = Convert.setDigits(target, 16);
        this.ackRequired = binAckRequired;
        this.resRequired = binResRequired;
        this.sequence = Convert.setDigits(sequence, 2);
    }

    public FrameAddress() {
        this.target = "0000000000000000";
        this.ackRequired = "0";
        this.resRequired = "0";
        this.sequence = "00";
    }

    public String get() {
        String converted = "";

        converted += Convert.hexToLittleEndian(target);
        converted += reserved1;

        converted += Convert.hexToLittleEndian(Convert.binaryToHex(
                reserved2 + ackRequired + resRequired));

        converted += sequence;

        return converted;
    }
}
