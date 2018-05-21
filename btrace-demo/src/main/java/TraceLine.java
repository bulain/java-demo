import com.sun.btrace.BTraceUtils.Strings;
import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class TraceLine {

    @OnMethod(clazz = "com.bulain.btrace.WorkTask", location = @Location(value = Kind.LINE, line = -1))
    public static void online(@ProbeClassName String pcn, @ProbeMethodName String pmn, int line) {
        println(Strings.strcat("Probe: ", Strings.strcat(pcn, Strings.strcat("#", pmn))));
        println(Strings.strcat("Line: ", Strings.str(line)));
    }

}
