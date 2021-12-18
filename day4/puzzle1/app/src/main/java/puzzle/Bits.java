package puzzle;

/**
 * A set of bits, or as you might otherwise think of it - a binary number.
 */
public class Bits {

    /**
     * Factory method.
     *
     * @throws NumberFormatException if the bits does not represent a valid binary number
     */
    public static Bits valueOf(String bits) {
        return new Bits(bits);
    }

    private final String bits;

    /**
     * @throws NumberFormatException if the bits is not a valid binary number
     */
    private Bits(String bits) {
        bitsToDecimal(bits);
        this.bits = bits;
    }

    /**
     * Zero-based index (index 0 is least significant digit).
     */
    public boolean isZero(int index) {
        return charAt(index) == '0';
    }

    /**
     * Zero-based index (index 0 is least significant digit).
     */
    public boolean isOne(int index) {
        return charAt(index) == '1';
    }

    public char charAt(int index) {
        return bits.charAt(index);
    }

    /**
     * Return the decimal value of the bits.
     *
     * @throws NumberFormatException if it's not a binary number
     */
    public int value() {
        return bitsToDecimal(bits);
    }

    /**
     * Return the decimal value of the bits.
     *
     * @throws NumberFormatException if it's not a binary number
     */
    private int bitsToDecimal(String theBits) {
        return Integer.parseInt(theBits, 2);
    }

    public int length() {
        return bits.length();
    }

    @Override
    public String toString() {
        return "Bits{" +
                "bits='" + bits + '\'' +
                '}';
    }
}
