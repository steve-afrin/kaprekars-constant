package home;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation for performing computations related to finding Kaprekar's Constant
 * for four digit integers.
 */
public abstract class KaprekarsCalculator {
  private static final Logger LOG = LoggerFactory.getLogger(KaprekarsCalculator.class);

  protected static final int KAPREKARS_CONSTANT = 6174;
  protected static final String FOUR_DIGIT_INTEGER_FORMAT = "%04d";

  private static final String BAD_INTEGER_ERROR_MESSAGE = "The input '%s' is not a valid integer";
  private static final String INVALID_FORMAT_ERROR_MESSAGE = "A valid Kaprekar's input must not have the same digit"
    + " for all columns";
  private static final String INVALID_NUMBER_OF_DIGITS_ERROR_MESSAGE = "A valid Kaprekar's input must have four"
    + " digits, not %d";
  private static final String NULL_OR_BLANK_ERROR_MESSAGE = "A null or blank value is not a valid Kaprekar's input";

  /**
   * <p>Validate that a given input as a program argument is a valid input for finding Kaprekar's Constant.</p>
   * <ul>
   *   <li>The input must contain a valid integer.</li>
   *   <li>The integer must contain exactly four digits (leading zeros are okay.)</li>
   *   <li>The digits cannot be all the same digit - at least one digit must be different from the other
   *       digits.</li>
   * </ul>
   * @param input a String value representing a four digit integer
   * @return the {@code int} represented by the input String
   * @throws IllegalArgumentException when the input String value violates any of the rules for a good input value
   * for finding Kaprekar's Constant
   */
  public final int isValidStartingNumber(final String input) {
    if (input == null || input.isBlank()) {
      throw new IllegalArgumentException(NULL_OR_BLANK_ERROR_MESSAGE);
    }

    final var trimmedInput = input.trim();

    if (trimmedInput.length() != 4) {
      throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_DIGITS_ERROR_MESSAGE,
                                                       trimmedInput.length()));
    }

    this.validateDigitsForKaprekarsInput(trimmedInput);

    try {
      return Integer.parseInt(trimmedInput);
    } catch (NumberFormatException ex) {
      throw new IllegalArgumentException(String.format(BAD_INTEGER_ERROR_MESSAGE, input), ex);
    }
  }

  /**
   * <p>Kaprekar's Constant can be found in seven or fewer iterations of the computational algorithm for a four
   * digit integer. This is the main method to find the sequence of computed values to finding Kaprekar's Constant
   * for the given four digit integer input.</p>
   * <p>Any input value less than one thousand is assumed to have leading zeros.</p>
   *
   * @param n the {@code int} value for which Kaprekar's Constant will be computed
   * @throws IllegalArgumentException when the provided input value does not conform to a valid integer for
   * computing Kaprekar's Constant
   */
  public final void findKaprekarsConstant(int n) {
    this.isValidStartingNumber(this.padWithZeros(n));

    final var values = new ArrayList<String>();
    values.add(this.padWithZeros(n));

    while (n != KAPREKARS_CONSTANT) {
      n = this.computeNextValue(n);
      values.add(this.padWithZeros(n));
    }

    LOG.info("It took {} computations to arrive at Kaprekar's constant", values.size() - 1);
    LOG.info("The values computed were {}", values);
  }

  /**
   * The primary implementation of the core algorithm for computing Kaprekar's Constant or the values leading to
   * Kaprekar's Constant.
   *
   * @param n an {@code int} value that conforms to the proper rules for finding Kaprekar's Constant
   * @return Kaprekar's Constant or the next {@code int} value in the sequence leading to Kaprekar's Constant
   */
  abstract int computeNextValue(int n);

  protected String padWithZeros(final int n) {
    return String.format(FOUR_DIGIT_INTEGER_FORMAT, n);
  }

  private void validateDigitsForKaprekarsInput(final String input) {
    final var chars = input.chars()
                           .boxed()
                           .collect(Collectors.toUnmodifiableSet());

    if (chars.size() == 1) {
      throw new IllegalArgumentException(INVALID_FORMAT_ERROR_MESSAGE);
    }
  }
}
