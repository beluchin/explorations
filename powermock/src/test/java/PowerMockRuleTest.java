import org.junit.Rule;
import org.junit.Test;
import org.powermock.modules.junit4.rule.PowerMockRule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.whenNew;

public class PowerMockRuleTest {

    // Useful when wanting to use other runners
    // see also: https://github.com/powermock/powermock/wiki/powermockrule
    @Rule public PowerMockRule rule = new PowerMockRule();

    class Foo {
        Foo(int i) {throw__();}
        @Override
        public boolean equals(final Object obj) {throw__();return false; }
    }

    @Test
    public void stubConstructor() throws Exception {
        final Foo foo = mock(Foo.class);
        whenNew(Foo.class).withArguments(42).thenReturn(foo);
        assertThat(new Foo(42)).isSameAs(foo);
    }

    private static void throw__() {
        throw new UnsupportedOperationException();
    }
}
