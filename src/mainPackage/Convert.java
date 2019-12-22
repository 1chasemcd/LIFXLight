package mainPackage;

public class Convert {
    private Convert() { }

    public static byte[] hexToByteArray(String hex) {

        byte byteArray[];

        if (isHexString(hex)) {
            int len = hex.length();
            byteArray = new byte[len / 2];

            for (int i = 0; i < len; i += 2) {
                byteArray[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                        + Character.digit(hex.charAt(i+1), 16));
            }

        } else {
            byteArray = null;
        }

        return byteArray;
    }

    public static boolean isHexString(String hexString) {
        char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'a', 'b', 'c', 'd', 'e', 'f'};

        for (int i = 0; i < hexString.length(); i++) {
            if (!contains(hexString.charAt(i), hexChars)) {
                return false;
            }
        }

        return true;
    }

    private static boolean contains(char c1, char[] cArray) {
        for (char c2 : cArray) {
            if (c1 == c2) {
                return true;
            }
        }

        return false;
    }

    public static String hexToLittleEndian(String hex) {
        String littleEnd = "";

        for (int i = hex.length() - 1; i >= 1; i-=2) {
            littleEnd = littleEnd + hex.charAt(i-1) + hex.charAt(i);
        }

        return littleEnd;
    }

    public static String binaryToHex(String binary) {
        String hex = "";
        for (int i = 0; i < binary.length() - 5; i += 4) {
            if (binary.substring(i, i+4).equals("0000")) {
                hex += "0";
            }
        }
        hex += Integer.toString(Integer.parseInt(binary, 2), 16);

        return hex;
    }

    public static String hueToHex(int hue) {
        hue = (int) ((float) hue / 360.0 * 65535);

        return Integer.toHexString(hue);
    }

    public static String percentToHex(int percent) {
        percent = (int) ((float) percent / 100.0 * 65535);

        return Integer.toHexString(percent);
    }

    public static String setDigits(String str, int dig) {

        while (str.length() < dig) {
            str = "0" + str;
        }

        return str;
    }

    public static byte rootMeanSquare(byte[] data) {
        float sumSquare = 0;

        for (byte b : data) {
            sumSquare += Math.pow(b, 2);
        }

        return (byte) (Math.sqrt(sumSquare / data.length) + 0.5);
    }
}
