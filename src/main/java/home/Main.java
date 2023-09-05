package home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main entry point to this program.
 */
public class Main {
  private static final Logger LOG = LoggerFactory.getLogger(Main.class);
  private static final float ONE_MILLION = 1_000_000F;
  private static final String FINAL_REPORT_MESSAGE = "The {} calculator found Kaprekar's Constant from {} in {} milliseconds.";

  private static final KaprekarsCalculator NUMERIC_CALCULATOR = new KaprekarsNumericCalculator();
  private static final KaprekarsCalculator STRING_CALCULATOR = new KaprekarsStringCalculator();

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
    if (args.length == 0) {
      LOG.error("No program arguments provided; no work to do");
      System.exit(1);
    }

    for (final String argument : args) {
      try {
        compute(argument);
      } catch (IllegalArgumentException ex) {
        LOG.warn("Failed to process argument '{}'; {}", argument, ex.getMessage());
      }
    }
  }

  private static void compute(final String argument) {
    final var numericCalculatorStartTime = System.nanoTime();
    NUMERIC_CALCULATOR.findKaprekarsConstant(NUMERIC_CALCULATOR.isValidStartingNumber(argument));
    final var numericCalculatorEndTime = System.nanoTime();

    final var stringCalculatorStartTime = System.nanoTime();
    STRING_CALCULATOR.findKaprekarsConstant(STRING_CALCULATOR.isValidStartingNumber(argument));
    final var stringCalculatorEndTime = System.nanoTime();

    final var numericComputationTime = numericCalculatorEndTime - numericCalculatorStartTime;
    final var stringComputationTime = stringCalculatorEndTime - stringCalculatorStartTime;

    LOG.info(FINAL_REPORT_MESSAGE,
        "Numeric",
        argument,
        String.format("%1.4f", numericComputationTime / ONE_MILLION));
    LOG.info(FINAL_REPORT_MESSAGE,
        "String",
        argument,
        String.format("%1.4f", stringComputationTime / ONE_MILLION));
  }
}