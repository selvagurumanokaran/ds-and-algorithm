import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class DefenderArcadeTest {
    private DefenderArcade defenderArcade;

    public DefenderArcadeTest() {
        defenderArcade = new DefenderArcade();
    }

    @Test
    public void testArcadesWithNoOverlap() {
        String[] times = {"900 910", "940 1200", "1300 1400", "1450 1500"};
        int arcades = defenderArcade.countArcades(Arrays.asList(times));
        Assert.assertEquals(arcades, 1);

        String[] timesWithDiffOrder = {"940 1200", "900 910", "1300 1400", "1450 1500"};
        arcades = defenderArcade.countArcades(Arrays.asList(timesWithDiffOrder));
        Assert.assertEquals(arcades, 1);
    }

    @Test
    public void testArcadesWithTwoOverlapInFirstBatch() {
        String[] times = {"900 910", "940 1200", "950 1120", "1100 1130", "1300 1400", "1350 1420"};
        int arcades = defenderArcade.countArcades(Arrays.asList(times));
        Assert.assertEquals(arcades, 3);
    }

    @Test
    public void testArcadesWithOneOverlap() {
        String[] times = {"700 900", "830 1130", "1100 1200"};
        int arcades = defenderArcade.countArcades(Arrays.asList(times));
        Assert.assertEquals(arcades, 2);
    }

    @Test
    public void testArcadesWithTwoOverlap() {
        String[] times = {"700 900", "830 1130", "1100 1200", "830 1000"};
        int arcades = defenderArcade.countArcades(Arrays.asList(times));
        Assert.assertEquals(arcades, 3);
    }

    @Test
    public void testArcadesWithSameStartTime() {
        String[] times = {"700 900", "700 1130", "1100 1200", "830 1000"};
        int arcades = defenderArcade.countArcades(Arrays.asList(times));
        Assert.assertEquals(arcades, 3);
    }

    @Test
    public void testArcadesWithSameStartEndTime() {
        String[] times = {"700 900", "700 900", "1100 1200"};
        int arcades = defenderArcade.countArcades(Arrays.asList(times));
        Assert.assertEquals(arcades, 2);
    }
}
