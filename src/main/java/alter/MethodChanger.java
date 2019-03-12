package alter;

import javassist.*;

public class MethodChanger {

    /**
     * Run main and replace by hand the class on target
     *
     * @param argv
     * @throws Exception
     */
    public static void main(String[] argv) throws Exception {

        CtClass clas = ClassPool.getDefault().get("service.impl.ServiceImpl");

        // add timing interceptor to the class
        addLoggingAndTiming(clas, "execute");
        clas.writeFile();

    }

    private static void addLoggingAndTiming(CtClass theClass, String methodName) throws NotFoundException, CannotCompileException {

        //  get the method information (throws exception if method with
        //  given name is not declared directly by this class, returns
        //  arbitrary choice if more than one with the given name)
        CtMethod mold = theClass.getDeclaredMethod(methodName);

        //  rename old method to synthetic name, then duplicate the
        //  method with original name for use as interceptor
        String nname = methodName + "$impl";
        mold.setName(nname);
        CtMethod mnew = CtNewMethod.copy(mold, methodName, theClass, null);

        //  start the body text generation by saving the start time
        //  to a local variable, then call the timed method; the
        //  actual code generated needs to depend on whether the
        //  timed method returns a value
        String type = mold.getReturnType().getName();
        StringBuilder body = new StringBuilder();
        body.append("{\nlong start = System.currentTimeMillis();\n");
        body.append("System.out.println(\"start service\");");
        if (!"void".equals(type)) {
            body.append(type + " result = ");
        }
        body.append(nname + "($$);\n");

        //  finish body text generation with call to print the timing
        //  information, and return saved value (if not void)
        body.append("System.out.println(\"Call to method " + methodName +
                " took \" +\n (System.currentTimeMillis()-start) + " +
                "\" ms.\");\n");
        body.append("System.out.println(\"end service\");");
        if (!"void".equals(type)) {
            body.append("return result;\n");
        }
        body.append("}");

        //  replace the body of the interceptor method with generated
        //  code block and add it to class
        mnew.setBody(body.toString());
        theClass.addMethod(mnew);

        //  print the generated code block just to show what was done
        System.out.println("Interceptor method body:");
        System.out.println(body.toString());
    }

}
