import static com.sun.btrace.BTraceUtils.print;
import static com.sun.btrace.BTraceUtils.printArray;
import static com.sun.btrace.BTraceUtils.println;

import com.sun.btrace.BTraceUtils.Strings;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;
import com.sun.btrace.annotations.Return;
import com.sun.btrace.annotations.Self;

@BTrace
public class TraceEntry {

    @OnMethod(clazz = "com.bulain.btrace.WorkTask", method = "/.*/", location = @Location(value = Kind.ENTRY))
    public static void n(@Self Object self, @ProbeClassName String pcn, @ProbeMethodName String pmn) {
        println(Strings.strcat("Probe: ", Strings.strcat(pcn, Strings.strcat("#", pmn))));
        println(Strings.strcat("Self: ", Strings.str(self)));
    }

    @OnMethod(clazz = "com.bulain.btrace.WorkTask", method = "runInternal", location = @Location(value = Kind.ENTRY))
    public static void nstr(@Self Object self, @ProbeClassName String pcn, @ProbeMethodName String pmn, String str) {
        println(Strings.strcat("Probe: ", Strings.strcat(pcn, Strings.strcat("#", pmn))));
        println(Strings.strcat("Self: ", Strings.str(self)));
        println(Strings.strcat("Param: ", str));
    }

    @OnMethod(clazz = "com.bulain.btrace.WorkTask", method = "runReturn", location = @Location(value = Kind.RETURN))
    public static void nreturn(@Self Object self, @ProbeClassName String pcn, @ProbeMethodName String pmn,
            @Return String[] strs) {
        println(Strings.strcat("Probe: ", Strings.strcat(pcn, Strings.strcat("#", pmn))));
        println(Strings.strcat("Self: ", Strings.str(self)));
        print("Return: ");
        printArray(strs);
    }

}
