package ca.bazlur;

public class MyClassLoader extends ClassLoader {
  public Class<?> defineClass(String name, byte[] bytes) {
    return super.defineClass(name, bytes, 0, bytes.length);
  }
}
