package home;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation that uses mathematical operations to get the individual digits of each four digit integer, reorders
 * them via an {@link java.util.stream.IntStream}, and then figures out the minuend and subtrahend based simply on
 * the natural ordering of the individual digits in the integer.
 */
public class KaprekarsNumericCalculator extends KaprekarsCalculator {
  private static final Logger LOG = LoggerFactory.getLogger(KaprekarsNumericCalculator.class);

  @Override
  int computeNextValue(final int n) {
    final var digits = this.getOrderedDigits(n);
    final var minuend = digits[3] * 1000 + digits[2] * 100 + digits[1] * 10 + digits[0];
    final var subtrahend = digits[0] * 1000 + digits[1] * 100 + digits[2] * 10 + digits[3];
    final var difference = minuend - subtrahend;
    LOG.debug("Value {}: {} - {} = {}",
        this.padWithZeros(n),
        this.padWithZeros(minuend),
        this.padWithZeros(subtrahend),
        this.padWithZeros(difference));
    return difference;
  }

  private int[] getDigitsArray(int n) {
    final var digits = new int[4];
    int factor = 1000;
    int index = 0;

    do {
      final int digit = n / factor;
      n = n - (digit * factor);
      factor /= 10;
      digits[index++] = digit;
      if (factor == 1) {
        digits[index] = n;
      }
    } while (factor > 1);

    return digits;
  }

  private int[] getOrderedDigits(final int n) {
    final var digits = this.getDigitsArray(n);
    return Arrays.stream(digits)
        .sorted()
        .toArray();
  }
}
