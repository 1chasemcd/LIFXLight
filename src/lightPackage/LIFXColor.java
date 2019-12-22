package lightPackage;

import mainPackage.Convert;
import java.awt.Color;

public class LIFXColor {

    public String h;
    public String s;
    public String b;
    public String k;

    private LIFXColor(String h, String s, String b, String k) {
        this.h = h;
        this.s = s;
        this.b = b;
        this.k = k;
    }

    public static LIFXColor rgbk(int r, int g, int b, int k) {

        float[] hsb = Color.RGBtoHSB(r, g, b, null);
        LIFXColor c = hsbk((int) (hsb[0] * 360), (int) (hsb[1] * 100), (int) (hsb[2] * 100), k);

        return c;
    }

    public static LIFXColor hsbk(int h, int s, int b, int k) {
        LIFXColor c = new LIFXColor(Convert.setDigits(Convert.hueToHex(h), 4),
                Convert.setDigits(Convert.percentToHex(s), 4),
                Convert.setDigits(Convert.percentToHex(b), 4),
                Convert.setDigits(Integer.toHexString(k), 4));

        return c;
    }
}
