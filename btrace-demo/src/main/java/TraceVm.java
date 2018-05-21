import static com.sun.btrace.BTraceUtils.println;

import com.sun.btrace.BTraceUtils.Strings;
import com.sun.btrace.BTraceUtils.Sys;
import com.sun.btrace.BTraceUtils.Time;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnTimer;

@BTrace
public class TraceVm {
    static {
        println(Strings.strcat("vm version ", Sys.VM.vmVersion()));
        println(Strings.strcat("vm starttime ", Strings.str(Sys.VM.vmStartTime())));
    }

    @OnTimer(1000)
    public static void f() {
        println(Strings.strcat("1000 msec: ", Strings.str(Sys.VM.vmUptime())));
    }

    @OnTimer(3000)
    public static void f1() {
        println(Strings.strcat("3000 msec: ", Strings.str(Time.millis())));
    }

}
