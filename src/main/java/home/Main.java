package home;

/**
 * The main entry point to this program.
 */
public class Main {

  /**
   * The main entry point to this program. One program argument is required - a four digit integer that
   * conforms to the rules of a valid input value for finding Kaprekar's Constant.
   * <ul>
   *   <li>The input must contain a valid integer.</li>
   *   <li>The integer must contain exactly four digits (leading zeros are okay.)</li>
   *   <li>The digits cannot be all the same digit - at least one digit must be different from the other
   *       digits.</li>
   * </ul>
   * @throws IllegalArgumentException when the input String value violates any of the rules for a good input value
   * for finding Kaprekar's Constant
   */
  public static void main(final String[] args) {
    final var calculator = new KaprekarsCalculator();
    final var n = Integer.parseInt(args[0]);
    calculator.findKaprekarsConstant(n);
  }
}