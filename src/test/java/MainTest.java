import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    @Timeout(22)
    @Disabled
    void timeMainTest() throws Exception {
        Main.main(null);
    }
}