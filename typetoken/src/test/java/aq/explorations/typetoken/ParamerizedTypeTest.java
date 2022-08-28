package aq.explorations.typetoken;

import com.google.common.reflect.TypeToken;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParamerizedTypeTest {
    @Test
    public void getRawType() {
        assertThat(new TypeToken<List<String>>() {}.getRawType())
                .isSameAs(List.class);
    }
}
