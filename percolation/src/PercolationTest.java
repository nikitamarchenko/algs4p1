/**
 * Created by tpuctah on 15.02.15.
 */

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;


public class PercolationTest {

    @Test
    public void bounding_test() {

        try {
            Percolation p = new Percolation(0);
        }
        catch (IllegalArgumentException ex)
        {
        }

        try {
            Percolation p = new Percolation(10);
            p.isOpen(0, 1);
        }
        catch (IndexOutOfBoundsException ex)
        {
        }

        try {
            Percolation p = new Percolation(10);
            p.isOpen(1, 0);
        }
        catch (IndexOutOfBoundsException ex)
        {
        }

        try {
            Percolation p = new Percolation(10);
            p.isOpen(11, 1);
        }
        catch (IndexOutOfBoundsException ex)
        {
        }

        try {
            Percolation p = new Percolation(10);
            p.isOpen(11, 1);
        }
        catch (IndexOutOfBoundsException ex)
        {
        }

    }

    @Test
    public void open() {
        Percolation p = new Percolation(10);

        assertEquals("j=1 i=1 must be closed", false, p.isOpen(1, 1));

        p.open(1, 1);

        assertEquals("j=1 i=1 must be open", true, p.isOpen(1, 1));

        p.open(10, 10);

        assertEquals("j=10 i=10 must be open", true, p.isOpen(10, 10));

    }

    @Test
    public void percolation() {
        Percolation p = new Percolation(2);
        assertFalse(p.percolates());
        p.open(1, 1);
        assertTrue(p.isFull(1, 1));
        p.open(2, 1);
        assertTrue(p.percolates());
    }
}
