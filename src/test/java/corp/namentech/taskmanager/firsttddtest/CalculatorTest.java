package corp.namentech.taskmanager.firsttddtest;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;


public class CalculatorTest {

    @Test
    void testDivideTwoPositiveNumbers(){

        Calculator calculator = new Calculator();
        double result = calculator.divide(6, 2);

        assertThat(result).isEqualTo(3);
    }

    @Test
    void testDivideByZero(){

        Calculator calculator = new Calculator();
        assertThatExceptionOfType(ArithmeticException.class)
                .isThrownBy(() -> calculator.divide(3, 0));
    }

    @Test
    void testDivideNegativeNumbers(){

        Calculator calculator = new Calculator();
        double result = calculator.divide(-6, -2);
        assertThat(result).isEqualTo(3);
    }
}
