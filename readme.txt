JAVASSIST vs CGLIB vs BYTEBUDDY vs JAVA COMPILER

See :

- https://stackoverflow.com/questions/7297565/javassist-what-is-the-main-idea-and-where-real-use
- http://www.javassist.org/
- https://en.wikipedia.org/wiki/Javassist
- https://www.baeldung.com/javassist
- https://www.baeldung.com/byte-buddy

Scope :

Alter Java classes behavior at runtime
Generate Java classes at runtime

Exercise :

create a Java interface defining a single method signature
at runtime use Javassist or Java Compiler in order to generate a new Java class
make sure the newly created Java class implements the initial Java interface
use Java Reflection and create a new instance of the newly generated Java class
cast the newly generated class instance to a interface reference
test that the interface reference implements the interface method
