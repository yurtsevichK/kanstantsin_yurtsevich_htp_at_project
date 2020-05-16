package other;

import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Formatter;

public class BookingUtils {

    public static String generateDateXpath(int quantityOfDays) {
        Formatter form = new Formatter();
        LocalDate today = LocalDate.now();
        String date = "//*[@id='frm']//descendant::td[@data-date='%s']";
        return form.format(date, today.plus(quantityOfDays, ChronoUnit.DAYS)).toString();
    }

    public static int getPriceForNight(WebElement element, int interval) {
        String cost = element.getText();
        return Integer.parseInt(cost.replaceAll("[^\\d.]", "")) / interval;
    }

}
