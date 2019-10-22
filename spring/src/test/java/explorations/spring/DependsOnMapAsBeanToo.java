package explorations.spring;

import javax.inject.Named;
import java.util.Map;

@Named
class DependsOnMapAsBeanToo {
    public final Map<Foo, Bar> m;

    DependsOnMapAsBeanToo(Map<Foo, Bar> m) {
        this.m = m;
    }
}
