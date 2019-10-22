package explorations.spring;

import javax.inject.Named;
import java.util.Map;

@Named
class DependsOnMapNotAsBeanToo {
    public final Map<Integer, Integer> m;

    DependsOnMapNotAsBeanToo(Map<Integer, Integer> m) {
        this.m = m;
    }
}
