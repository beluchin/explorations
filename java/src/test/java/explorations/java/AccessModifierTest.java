package explorations.java;

import explorations.java.nested.Public;
import org.junit.jupiter.api.Test;

public class AccessModifierTest {
    @Test
    public void accessToPackagePrivateClass() {
        System.out.println(new Public().foo);

        // cannot reference foo members.
        // this does not compile. `foo.i` cannot be accessed outside the package
        // even though it is public.
//        System.out.println(new Public().foo.i);
    }
}
