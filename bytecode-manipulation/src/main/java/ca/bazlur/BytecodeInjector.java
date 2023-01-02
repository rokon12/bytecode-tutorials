package ca.bazlur;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class BytecodeInjector {

  public static void main(String[] args) throws IOException {

    try (var resource = BytecodeInjector.class.getResourceAsStream("Greetings.class")) {
      final var classBytes = resource.readAllBytes();

      // Create a ClassPool and import the original class
      ClassPool classPool = ClassPool.getDefault();
      CtClass ctClass = classPool.makeClass(new java.io.ByteArrayInputStream(classBytes));

      // Create a new method and add it to the class
      CtMethod newMethod = CtMethod.make("""
            public void printHelloWorld() {
              System.out.println("Hello, world!");
            }
          """, ctClass);
      ctClass.addMethod(newMethod);

      // Write the modified class back to a byte array
      byte[] modifiedClassBytes = ctClass.toBytecode();

      // Load the modified class bytes into the JVM
      MyClassLoader classLoader = new MyClassLoader();
      Class<?> modifiedClass = classLoader.defineClass("ca.bazlur.Greetings", modifiedClassBytes);

      // Invoke the new method on an instance of the modified class
      Object obj = modifiedClass.newInstance();
      Method method = modifiedClass.getMethod("printHelloWorld");
      method.invoke(obj);
    } catch (CannotCompileException | InvocationTargetException | InstantiationException |
             IllegalAccessException | NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }
}
