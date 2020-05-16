package runners;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;
import tests.booking.BookingStepsTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({BookingStepsTest.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRunner {
}
