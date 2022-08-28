package aq.explorations.typetoken;

import com.google.common.reflect.TypeToken;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

final class TypeArgResolutionTest {
    /**
     * @see <a href="https://github.com/google/guava/wiki/ReflectionExplained#resolvetype">https://github.com/google/guava/wiki/ReflectionExplained#resolvetype</a>
     */
    @Test
    void _1_getting_to_the_generic_parameter() {
        val tt = new TypeToken<WithTypeArg<Integer>>() {};

        assertThat(tt.resolveType(WithTypeArg.class.getTypeParameters()[0]).getRawType())
                .isEqualTo(Integer.class);
    }

    @Test
    void _2_getting_to_the_generic_parameter_generically() {
        val tt = new TypeToken<WithTypeArg<Integer>>() {};

        assertThat(tt.resolveType(tt.getRawType().getTypeParameters()[0]).getRawType())
                .isEqualTo(Integer.class);
    }

    @Test
    void _3_nested() {
        val tt = new TypeToken<WithTypeArg<WithTypeArg<Integer>>>() {};

        val outerTypeParameter = tt.resolveType(tt.getRawType().getTypeParameters()[0]);
        val innerTypeParameter = outerTypeParameter.resolveType(outerTypeParameter.getRawType().getTypeParameters()[0]);

        assertThat(innerTypeParameter.getRawType()).isEqualTo(Integer.class);
    }

    @Test
    void _4_constructor_arg() {
        val tt = new TypeToken<WithTypeArg<Integer>>() {};
        val constructorArg1 = tt.getRawType()

                // only returns public constructors. Care needs to be taken
                // to deal with non-public constructors.
                .getConstructors()[0]

                .getParameters()[0]

                // also works for constructors with no type parameters.
                // see #4 below
                .getParameterizedType();

        assertThat(tt.resolveType(constructorArg1).getRawType())
                .isEqualTo(Integer.class);
    }

    @Test
    void _5_constructor_arg_for_local_inner() {
        final class LocalInnerWithTypeArg<T> {
            final T t;

            public LocalInnerWithTypeArg(T t) {this.t = t;}
        }

        val tt = new TypeToken<LocalInnerWithTypeArg<Integer>>() {};
        val constructorArg0 = tt.getRawType()

                // only returns public constructors. Care needs to be taken
                // to deal with non-public constructors.
                .getConstructors()[0]

                .getParameters()[0]

                // also works for constructors with no type parameters.
                // see #4 below
                .getParameterizedType();
        val constructorArg1 = tt.getRawType()

                // only returns public constructors. Care needs to be taken
                // to deal with non-public constructors.
                .getConstructors()[0]

                .getParameters()[1]

                // also works for constructors with no type parameters.
                // see #4 below
                .getParameterizedType();

        assertThat(tt.resolveType(constructorArg0).getRawType())
                .isEqualTo(TypeArgResolutionTest.class);
        assertThat(tt.resolveType(constructorArg1).getRawType())
                .isNotEqualTo(Integer.class);
    }

    @Test
    void constructor_arg_without_type_parameter() {
        val tt = new TypeToken<WithNoTypeArg>() {};
        val constructorArg1 = tt.getRawType()

                // only returns public constructors. Care needs to be taken
                // to deal with non-public constructors.
                .getConstructors()[0]

                .getParameters()[0]
                .getParameterizedType();

        assertThat(tt.resolveType(constructorArg1).getRawType())
                .isEqualTo(Integer.class);
    }

    @SuppressWarnings("unused")
    final static class WithTypeArg<T> {
        final T t;

        public WithTypeArg(T t) {this.t = t;}
    }

    final static class WithNoTypeArg {
        @SuppressWarnings("unused")
        public WithNoTypeArg(Integer x) {}
    }
}
