import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Testing {
    private List<Integer> list = new ArrayList<>();

    @Test
    public void test() {
        list.add(1);
        assertEquals(1, list.size());
    }

    @Test
    public void tes2t() {
        list.add(1);
        list.add(2);
        assertEquals(2, list.size());
    }
}
