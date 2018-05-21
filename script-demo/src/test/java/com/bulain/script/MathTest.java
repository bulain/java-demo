package com.bulain.script;

import static org.junit.Assert.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;

public class MathTest {
    private ScriptEngineManager manager = new ScriptEngineManager();
    private ScriptEngine engine = manager.getEngineByName("ECMAScript");

    //http://www.w3schools.com/jsref/jsref_obj_math.asp
    //    Method  Description
    //    abs(x)  Returns the absolute value of x
    //    acos(x) Returns the arccosine of x, in radians
    //    asin(x) Returns the arcsine of x, in radians
    //    atan(x) Returns the arctangent of x as a numeric value between -PI/2 and PI/2 radians
    //    atan2(y,x)  Returns the arctangent of the quotient of its arguments
    //    ceil(x) Returns x, rounded upwards to the nearest integer
    //    cos(x)  Returns the cosine of x (x is in radians)
    //    exp(x)  Returns the value of Ex
    //    floor(x)    Returns x, rounded downwards to the nearest integer
    //    log(x)  Returns the natural logarithm (base E) of x
    //    max(x,y,z,...,n)    Returns the number with the highest value
    //    min(x,y,z,...,n)    Returns the number with the lowest value
    //    pow(x,y)    Returns the value of x to the power of y
    //    random()    Returns a random number between 0 and 1
    //    round(x)    Rounds x to the nearest integer
    //    sin(x)  Returns the sine of x (x is in radians)
    //    sqrt(x) Returns the square root of x
    //    tan(x)  Returns the tangent of an angle
    @Test
    public void testAbs() throws ScriptException {
        assertEquals(3.2, engine.eval("Math.abs(-3.2)"));
        assertEquals(4d, engine.eval("Math.ceil(3.2)"));
        assertEquals(3d, engine.eval("Math.floor(3.2)"));
        assertEquals(1024d, engine.eval("Math.pow(2,10)"));
        assertEquals(3d, engine.eval("Math.round(3.2)"));
        assertEquals(2d, engine.eval("Math.sqrt(4)"));
    }

    @Test
    public void testAbs1() throws ScriptException {
        assertEquals(3.2, engine.eval("Math.abs(-3.2)"));
        assertEquals(4d, engine.eval("Math.ceil(3.2)"));
        assertEquals(3d, engine.eval("Math.floor(3.2)"));
        assertEquals(1024d, engine.eval("Math.pow(2,10)"));
        assertEquals(3d, engine.eval("Math.round(3.2)"));
        assertEquals(2d, engine.eval("Math.sqrt(4)"));
    }
}
