package explorations.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ScanningTest {
    private ApplicationContext context = new AnnotationConfigApplicationContext(
            ScanningTest.class.getPackage().getName());

    @Test
    public void mapNotAsBean() {
        context.getBean(DependsOnMapNotAsBean.class);
    }

    @Test
    public void mapNotAsBeanIsNotSingleton() {
        assertThat(context.getBean(DependsOnMapNotAsBean.class).m)
                .isNotSameAs(context.getBean(DependsOnMapNotAsBeanToo.class).m);
    }

    @Test
    public void mapAsBean() {
        context.getBean(DependsOnMapAsBean.class);
    }

    @Test
    public void mapAsBeanComesFromConfig() {
        assertThat(context.getBean(DependsOnMapAsBean.class).m)
                .isSameAs(context.getBean(DependsOnMapAsBeanToo.class).m)
                .isSameAs(SpringConfiguration.map);
    }
}
