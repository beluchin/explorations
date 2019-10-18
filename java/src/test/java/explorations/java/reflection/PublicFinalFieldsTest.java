package explorations.java.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static java.util.Arrays.stream;
import static org.assertj.core.api.Assertions.assertThat;

class PublicFinalFieldsTest {
    @Test
    void public_() {
        //noinspection unused
        class Foo {
            public int i;
        }
        assertThat(Foo.class.getFields()).hasSize(1);
    }

    @Test
    void publicFinal() {
        //noinspection WeakerAccess
        class Foo {
            public final int i;
            Foo(int i) {this.i = i;}
        }
        assertThat(stream(Foo.class.getFields())
                           .filter(f -> Modifier.isFinal(f.getModifiers())))
                .hasSize(1);
    }

    @Test
    void inherited() {
        class Base {
            public int i;
        }
        class Derived extends Base {
        }
        assertThat(Derived.class.getFields()).hasSize(1);
    }

    @Test
    void nonPublic() {
        class Foo {
            int i;
        }
        assertThat(Foo.class.getFields()).isEmpty();
    }
}
