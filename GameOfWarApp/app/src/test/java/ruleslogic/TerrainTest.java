package ruleslogic;

import org.junit.Test;

import static org.junit.Assert.*;

public class TerrainTest {

    @Test
    public void equalsTest() {
        Terrain a = new Mountain(100);
        Terrain b = new Mountain(100);
        assertTrue(a.equals(b));

        Terrain c = new MountainPass(342);
        Terrain d = new MountainPass(342);
        assertTrue(c.equals(d));

        Terrain e = new Fortress(71);
        Terrain f = new Mountain(71);
        assertTrue(e.equals(f));
    }
}