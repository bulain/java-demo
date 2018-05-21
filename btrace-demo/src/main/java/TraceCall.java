import static com.sun.btrace.BTraceUtils.println;

import com.sun.btrace.BTraceUtils.Strings;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;
import com.sun.btrace.annotations.Self;
import com.sun.btrace.annotations.TargetInstance;
import com.sun.btrace.annotations.TargetMethodOrField;

@BTrace
public class TraceCall {
    
    @OnMethod(clazz = "com.bulain.btrace.WorkThread", method = "/.*/", location = @Location(value = Kind.CALL, clazz = "com.bulain.btrace.WorkTask", method = "runInternal"))
    public static void n(@Self Object self, @ProbeClassName String pcn, @ProbeMethodName String pmn,
            @TargetInstance Object instance, @TargetMethodOrField String method) {
        println(Strings.strcat("Probe: ", Strings.strcat(pcn, Strings.strcat("#", pmn))));
        println(Strings.strcat("Target: ", Strings.strcat(Strings.str(instance), Strings.strcat("#", method))));
        println(Strings.strcat("Self: ", Strings.str(self)));
    }

}
