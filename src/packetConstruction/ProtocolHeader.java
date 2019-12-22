package packetConstruction;

import mainPackage.Convert;

public class ProtocolHeader {

    // Prepare variables. All are in hexadecimal
    private String reserved1 = "0000000000000000"; // 8 bytes
    private String type; // 2 bytes
    private String reserved2 = "0000"; // 2 bytes

    public ProtocolHeader(String type) {
        this.type = Convert.setDigits(type, 4);
    }

    public String get() {
        String converted = "";

        converted += reserved1;

        converted += Convert.hexToLittleEndian(type);

        converted += reserved2;

        return converted;
    }
}
