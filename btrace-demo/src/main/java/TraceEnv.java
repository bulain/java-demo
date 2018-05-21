import static com.sun.btrace.BTraceUtils.println;

import com.sun.btrace.BTraceUtils.Sys;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnTimer;

@BTrace
public class TraceEnv {

    @OnTimer(4000)
    public static void onGetProperty() {
        println("Properties: ");
        println(Sys.Env.properties());
    }

}
