package ru.ifmo.junitdemo;

import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.Timeout;

/**
 * Unit test for simple App.
 * See: http://habrahabr.ru/post/120101/
 */
public class DemoTest extends Assert {

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();
    @Rule
    public final Timeout timeout = new Timeout(1000);
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void testTempFile() throws IOException {
        File f = folder.newFile();
        System.out.println(f.getAbsolutePath());
        f.delete();
    }

    @Test
    public void testExpectedException() {
        thrown.expect(NullPointerException.class);
        Integer s = null;
        s.toString();
    }
    
    
    @Test(expected = NullPointerException.class)
    public void testToHexStringWrong() {
        Integer s = null;
        s.toString();
    }

    @Ignore
    @Test(timeout = 1000)
    public void infinity() {
        while (true);
    }
}
