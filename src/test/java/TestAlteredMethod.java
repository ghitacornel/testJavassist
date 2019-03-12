import org.junit.Assert;
import org.junit.Test;
import service.impl.ServiceImpl;

public class TestAlteredMethod {

    @Test
    public void testAlteredServiceImplementation() {

        // test service
        Assert.assertEquals("one two", new ServiceImpl().execute("one"));

    }

}
