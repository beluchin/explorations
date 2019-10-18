import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
public class StubConstructorTest {
    class Foo {
        Foo(int i) {throw__();}
        @Override
        public boolean equals(final Object obj) {throw__();return false; }
    }

    @Test
    public void constructorMayBeStubbed() throws Exception {
        final Foo foo = mock(Foo.class);
        whenNew(Foo.class).withArguments(42).thenReturn(foo);
        assertThat(new Foo(42)).isSameAs(foo);
    }

    private static void throw__() {
        throw new UnsupportedOperationException();
    }
}
