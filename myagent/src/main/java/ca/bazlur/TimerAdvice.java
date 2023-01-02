package ca.bazlur;

import net.bytebuddy.asm.Advice;

public class TimerAdvice {

  @Advice.OnMethodEnter
  static long invokeBeforeEachMethod(
      @Advice.Origin String method) {
    System.out.println("Entering to invoke : " + method);
    return System.currentTimeMillis();
  }

  @Advice.OnMethodExit
  static void invokeWhileExitingEachMethod(@Advice.Origin String method,
      @Advice.Enter long startTime) {
    System.out.println(
        "Method " + method + " took " + (System.currentTimeMillis() - startTime) + "ms");
  }
}
