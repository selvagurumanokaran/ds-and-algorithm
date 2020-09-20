import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class RoyalRumbleTest {

    private RoyalRumble royalRumble;

    public RoyalRumbleTest() {
        royalRumble = new RoyalRumble();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFirstName() {
        String[] names = {"David III", "Louis IX", "Louis Invalid VIII", "David II"};
        royalRumble.getSortedList(Arrays.asList(names));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFirstName1() {
        String[] names1 = {"IAM David III", "Louis IX", "Louis Invalid VIII", "David II"};
        royalRumble.getSortedList(Arrays.asList(names1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidOrdinalLetters() {
        String[] names = {"David III", "Louis C", "Louis Invalid VIII", "David II"};
        royalRumble.getSortedList(Arrays.asList(names));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidOrdinalLetters1() {
        String[] names1 = {"David III", "Louis C", "Louis Invalid VIII", "David LI"};
        royalRumble.getSortedList(Arrays.asList(names1));
    }

    @Test
    public void testSortingMultipleFirstNames() {
        List<String> sortedList = royalRumble.getSortedList(Arrays.asList("David III", "Louis IX", "Louis VIII", "David II", "Louis X", "Louis I", "Salman X"));
        List<String> mockSortedList = Arrays.asList("David II", "David III", "Louis I", "Louis VIII", "Louis IX", "Louis X", "Salman X");
        IntStream.range(0, sortedList.size())
                .forEachOrdered(i -> Assert.assertEquals(sortedList.get(i), mockSortedList.get(i)));

    }

    @Test
    public void testSortingSingleFirstName() {
        List<String> sortedList = royalRumble.getSortedList(Arrays.asList("David III", "David XL", "David XXIV", "David II", "David L"));
        List<String> mockSortedList = Arrays.asList("David II", "David III", "David XXIV", "David XL", "David L");
        IntStream.range(0, sortedList.size())
                .forEachOrdered(i -> Assert.assertEquals(sortedList.get(i), mockSortedList.get(i)));
    }

    @Test
    public void testSortingWithDuplicateFirstName() {
        List<String> sortedList = royalRumble.getSortedList(Arrays.asList("David III", "David XL", "David III", "David II", "David L"));
        List<String> mockSortedList = Arrays.asList("David II", "David III", "David III", "David XL", "David L");
        IntStream.range(0, sortedList.size())
                .forEachOrdered(i -> Assert.assertEquals(sortedList.get(i), mockSortedList.get(i)));
    }

    @Test
    public void testSingleInput() {
        List<String> sortedList = royalRumble.getSortedList(Collections.singletonList("David III"));
        Assert.assertEquals("David III", sortedList.get(0));
    }

    @Test
    public void testNoInput() {
        List<String> sortedList = royalRumble.getSortedList(Collections.emptyList());
        Assert.assertEquals(0, sortedList.size());
    }
}
