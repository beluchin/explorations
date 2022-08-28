package aq.explorations.typetoken;

import com.google.common.reflect.TypeToken;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

final class ResolveTypeTest {
    @Test
    void called_with_arbitrary_type() {
        val tt = new TypeToken<Integer>() {};
        val differentTT = new TypeToken<String>() {};

        assertThat(tt.resolveType(differentTT.getRawType())).isEqualTo(differentTT);
    }
}
