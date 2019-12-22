package packetConstruction;

import mainPackage.Convert;

public class Payload {

    private String[] data; // Changes based on message type

    public Payload(String... data) {
        this.data = data;
    }

    public Payload() {
        this.data = new String[0];
    }

    public String get() {
        String converted = "";

        for (String s : data) {
            converted += Convert.hexToLittleEndian(s);
        }
        return converted;
    }

    public int getSize() {
        return get().length() / 2;
    }
}
