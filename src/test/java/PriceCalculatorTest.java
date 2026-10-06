import com.bookingqa.utils.PriceCalculator;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class PriceCalculatorTest {

    SoftAssertions softAssertions = new SoftAssertions();

    @Test public void testRegularPrice() {
        softAssertions.assertThat(new PriceCalculator().calculateTotal(100, 5)).isEqualTo(500);
        softAssertions.assertAll();
    }

    @Test public void testSevenNights() {
        softAssertions.assertThat(new PriceCalculator().calculateTotal(100, 7)).isEqualTo(700);
        softAssertions.assertAll();
    }

    @Test public void testEightNights() {
        softAssertions.assertThat(new PriceCalculator().calculateTotal(100, 8)).isEqualTo(720);
        softAssertions.assertAll();
    }

    @Test public void testZeroNights() {
        softAssertions.assertThatThrownBy(() -> {
            new PriceCalculator().calculateTotal(100, 0);
        }).isInstanceOf(IllegalArgumentException.class);
        softAssertions.assertAll();
    }
}
