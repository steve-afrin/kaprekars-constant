package home;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class KaprekarsStringCalculatorTest {

  final KaprekarsCalculator calculator = new KaprekarsStringCalculator();

  @ParameterizedTest(name = "Starting input value is {0}")
  @MethodSource("badInputValues")
  void isValidStartingNumber(final String description, final String input) {
    assertThrows(IllegalArgumentException.class, () -> this.calculator.isValidStartingNumber(input));
  }

  @Test
  @DisplayName("Validate that Kaprekar's Constant is computed for Kaprekar's Constant input value")
  void validateKaprekarsConstantIsComputedForKaprekarsConstantInput() {
    assertEquals(6174, this.calculator.computeNextValue(6174));
  }

  private static Stream<Arguments> badInputValues() {
    return Stream.of(
      Arguments.of("null", null),
      Arguments.of("empty String", ""),
      Arguments.of("blank String", "    "),
      Arguments.of("not enough digits", "123"),
      Arguments.of("all the same digit", "1111"),
      Arguments.of("too many digits", "12345"),
      Arguments.of("not a number", "text")
    );
  }
}