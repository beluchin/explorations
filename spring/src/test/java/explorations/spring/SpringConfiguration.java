package explorations.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SpringConfiguration {
    static final Map<Foo, Bar> map = new HashMap<>();
    @Bean
    Map<Foo, Bar> map() {
        return map;
    }
}
