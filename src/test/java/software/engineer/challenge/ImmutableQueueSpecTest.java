package software.engineer.challenge;

import static org.junit.Assert.*;
import static com.greghaskins.spectrum.dsl.specification.Specification.*;

import com.greghaskins.spectrum.Spectrum;
import org.junit.runner.RunWith;

import java.util.NoSuchElementException;


@RunWith(Spectrum.class)
public class ImmutableQueueSpecTest {
    {
        describe("Test of enQueue.", () -> {
            ImmutableQueue<Integer> queue = new ImmutableQueue<>();
            it("enQueue 1, 2", () -> {
                var result = queue.enQueue(1).enQueue(2);
                assertEquals(result.head(), Integer.valueOf(1));
            });
            it("enQueue Null", () -> {
                assertThrows(IllegalArgumentException.class, () -> {
                    queue.enQueue(null);
                });
            });
        });
        describe("Test of deQueue.", () -> {
            ImmutableQueue<Integer> queue = new ImmutableQueue<>();
            it("enQueue 1, 2 and deQueue", () -> {
                var result = queue.enQueue(1).enQueue(2).deQueue();
                assertEquals(result.head(), Integer.valueOf(2));
            });
            it("deQueue in the empty", () -> {
                assertThrows(NoSuchElementException.class, () -> {
                    queue.deQueue();
                });
            });
        });
        describe("Test of head.", () -> {
            ImmutableQueue<Integer> queue = new ImmutableQueue<>();
            it("enQueue 3, deQueue, enQueue 1, enQueue 4 and head", () -> {
                var result = queue.enQueue(3).deQueue().enQueue(1).enQueue(4).head();
                assertEquals(result, Integer.valueOf(1));
            });
            it("head in the empty", () -> {
                assertThrows(NoSuchElementException.class, () -> {
                    queue.head();
                });
            });
        });
        describe("Test of head.", () -> {
            ImmutableQueue<Integer> queue = new ImmutableQueue<>();
            it("enQueue 3 and isEmpty", () -> {
                var result = queue.enQueue(3);
                assertFalse(result.isEmpty());
            });
            it("isEmpty in the empty", () -> {
                assertTrue(queue.isEmpty());
            });
        });
    }

    public static void assertThrows(Class<? extends Exception> exceptionClass, Code code) {
        boolean thrown = false;
        try {
            code.run();
        } catch (Exception ex) {
            assertTrue(exceptionClass.isInstance(ex));
            thrown = true;
        }
        assertTrue(thrown);
    }

    @FunctionalInterface
    public interface Code {
        void run() throws Exception;
    }
}
