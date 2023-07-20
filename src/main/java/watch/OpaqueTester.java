package watch;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.Expect;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Mode;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.Signal;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;


//在idea中配置 org.openjdk.jcstress.Main 类进行运行

@JCStressTest(Mode.Termination)
@Outcome(id = "STALE", expect = Expect.FORBIDDEN)
@Outcome(id = "TERMINATED", expect = Expect.ACCEPTABLE)
public class OpaqueTester {
    private int x = 0;
    private static final VarHandle X;

    static {
        try {
            X = MethodHandles.lookup().findVarHandle(OpaqueTester.class, "x", int.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new Error(e);
        }
    }

    @Actor
    public void actor() {
        while ((int) X.getOpaque(this) == 0) {
            //do nothing
        }
    }
    @Signal
    public void signal() {
        X.setOpaque(this, 1);
    }
}
