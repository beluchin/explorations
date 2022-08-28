package aq.explorations.vavr;

import io.vavr.control.Either;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

final class EitherTest {
    @Test
    void equality() {
        val lhs = Either.<Integer, String>left(42);
        val rhs = Either.<Integer, Integer>left(42);
        //noinspection AssertBetweenInconvertibleTypes
        assertThat(lhs).isEqualTo(rhs);
    }
}
