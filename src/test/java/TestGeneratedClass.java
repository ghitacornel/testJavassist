import generate.ServiceImplementationFactory;
import org.junit.Assert;
import org.junit.Test;
import service.Service;

public class TestGeneratedClass {

    @Test
    public void testGeneratedOnTheFlyServiceImplementation() {

        // create implementation
        Service service = ServiceImplementationFactory.createServiceImplementation();
        Assert.assertNotNull(service);
        Assert.assertEquals("my.generated.class.GeneratedServiceImplementation", service.getClass().getCanonicalName());

        // use it as service
        Assert.assertEquals(service + " + test data", service.execute("test data"));

        // try to create again the generated class
        {
            try {
                ServiceImplementationFactory.createServiceImplementation();
            } catch (RuntimeException e) {
                Assert.assertEquals("java.lang.RuntimeException: my.generated.class.GeneratedServiceImplementation: frozen class (cannot edit)", e.getMessage());
                return;
            }
            Assert.fail("expect exception");
        }

    }

}
