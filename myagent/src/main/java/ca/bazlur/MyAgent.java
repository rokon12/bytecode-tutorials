package ca.bazlur;

import java.lang.instrument.Instrumentation;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

public class MyAgent {

  public static void premain(String agentArgs, Instrumentation inst) {
    new AgentBuilder.Default()
        .type(ElementMatchers.any())
        .transform((builder, typeDescription, classLoader, module) -> builder
            .method(ElementMatchers.any())
            .intercept(Advice.to(TimerAdvice.class)))
        .installOn(inst);
  }
}
