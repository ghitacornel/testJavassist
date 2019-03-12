package generate;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import service.Service;

public class ServiceImplementationFactory {

    /**
     * @return a {@link Service} implementation build on the fly
     */
    public static Service createServiceImplementation() {

        // create the implementation class
        // for simplicity create the class each time but observe effects later
        CtClass newClass;
        try {
            newClass = createNewClass();
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }

        // create an instance of the implementation class
        Object object;
        try {
            object = newClass.toClass().newInstance();
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }

        // return result
        return (Service) object;
    }

    /**
     * @return a {@link CtClass} representing a valid {@link Service} implementation
     * @throws Exception don't care
     */
    private static CtClass createNewClass() throws Exception {

        // get the class pool
        ClassPool classPool = ClassPool.getDefault();

        // create a new class, make sure name is unique to application
        CtClass myNewClass = classPool.makeClass("my.generated.class.GeneratedServiceImplementation");

        // get interface class
        CtClass interfata = classPool.getCtClass(Service.class.getName());

        // specify interface for this new class
        myNewClass.addInterface(interfata);

        // build the implementation of the interface method as a plain text method
        CtMethod method = CtMethod.make("public String execute(String argument){ return this +\" + \" + argument; }", myNewClass);

        // add method to class
        myNewClass.addMethod(method);

        // return the newly created class
        return myNewClass;

    }

}
