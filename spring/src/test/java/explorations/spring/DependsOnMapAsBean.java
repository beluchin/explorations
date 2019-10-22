package explorations.spring;

import javax.inject.Named;
import java.util.Map;

@Named
class DependsOnMapAsBean {
    public final Map<Foo, Bar> m;

    DependsOnMapAsBean(Map<Foo, Bar> m) {
        this.m = m;
    }
}
