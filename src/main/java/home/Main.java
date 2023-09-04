package home;

import java.text.NumberFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main entry point to this program.
 */
public class Main {
  private static final Logger LOG = LoggerFactory.getLogger(Main.class);
  private static final NumberFormat INTEGER_FORMATTER = NumberFormat.getIntegerInstance();

  /**
   * The main entry point to this program. One program argument is required - a four digit integer that
   * conforms to the rules of a valid input value for finding Kaprekar's Constant.
   * <ul>
   *   <li>The input must contain a valid integer.</li>
   *   <li>The integer must contain exactly four digits (leading zeros are okay.)</li>
   *   <li>The digits cannot be all the same digit - at least one digit must be different from the other
   *       digits.</li>
   * </ul>
   *
   * @param args array of String values that serve as input to the application as program arguments
   * @throws IllegalArgumentException when the input String value violates any of the rules for a good input value
   * for finding Kaprekar's Constant
   */
  public static void main(final String[] args) {
    final var calculator1 = new KaprekarsNumericCalculator();
    final var calculator2 = new KaprekarsStringCalculator();

    final var startTime1 = System.currentTimeMillis();
    calculator1.findKaprekarsConstant(calculator1.isValidStartingNumber(args[0]));
    final var endTime1 = System.currentTimeMillis();

    final var startTime2 = System.currentTimeMillis();
    calculator2.findKaprekarsConstant(calculator2.isValidStartingNumber(args[0]));
    final var endTime2 = System.currentTimeMillis();

    final var numericComputationTime = endTime1 - startTime1;
    final var stringComputationTime = endTime2 - startTime2;

    LOG.info("The Numeric calculator found Kaprekar's Constant from {} in {} milliseconds.",
        args[0],
        INTEGER_FORMATTER.format(numericComputationTime));
    LOG.info("The String calculator found Kaprekar's Constant from {} in {} milliseconds.",
        args[0],
        INTEGER_FORMATTER.format(stringComputationTime));
  }
}