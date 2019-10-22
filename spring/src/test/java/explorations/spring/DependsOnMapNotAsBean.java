package explorations.spring;

import javax.inject.Named;
import java.util.Map;

@Named
class DependsOnMapNotAsBean {
    public final Map<Integer, Integer> m;

    DependsOnMapNotAsBean(Map<Integer, Integer> m) {
        this.m = m;
    }
}
