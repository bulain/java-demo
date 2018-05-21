import static com.sun.btrace.BTraceUtils.println;

import com.sun.btrace.BTraceUtils.Strings;
import com.sun.btrace.BTraceUtils.Threads;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;
import com.sun.btrace.annotations.Self;

@BTrace
public class TraceThread {
    
    @OnMethod(clazz = "+java.lang.Thread", method = "start", location = @Location(value = Kind.ENTRY))
    public static void n(@Self Thread self, @ProbeClassName String pcn, @ProbeMethodName String pmn) {
        println(Strings.strcat("Probe: ", Strings.strcat(pcn, Strings.strcat("#", pmn))));
        println(Strings.strcat("Self: ", Strings.str(self)));
        println(Strings.strcat("Thread: ", Threads.name(self)));

    }

}
