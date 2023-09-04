package home;

import java.util.Comparator;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation that converts the integer to a String value and uses the individual characters of that String to
 * get the individual digits of each four digit integer, reorders them via a {@link java.util.stream.Stream}, and
 * then generates the minuend and subtrahend from the ordering of a {@link Comparator}.
 */
public class KaprekarsStringCalculator extends KaprekarsCalculator {
  private static final Logger LOG = LoggerFactory.getLogger(KaprekarsStringCalculator.class);

  @Override
  int computeNextValue(final int n) {
    final var minuend = this.computeOrderedDigits(n, Comparator.reverseOrder());
    final var subtrahend = this.computeOrderedDigits(n, Comparator.naturalOrder());
    final var difference = minuend - subtrahend;
    LOG.debug("Value {}: {} - {} = {}",
        super.padWithZeros(n),
        super.padWithZeros(minuend),
        super.padWithZeros(subtrahend),
        super.padWithZeros(difference));
    return difference;
  }

  private int computeOrderedDigits(final int n, final Comparator<String> comparator) {
    return Integer.parseInt(
        this.padWithZeros(n).chars()
            .mapToObj(i -> String.valueOf((char) i))
            .sorted(comparator)
            .collect(Collectors.joining())
    );
  }
}
