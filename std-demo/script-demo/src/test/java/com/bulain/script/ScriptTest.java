package com.bulain.script;

import org.junit.jupiter.api.Test;

import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.util.List;

public class ScriptTest {
    private ScriptEngineManager manager = new ScriptEngineManager();
    
    @Test
    public void testEnv() {
        List<ScriptEngineFactory> factories = manager.getEngineFactories();
        for (ScriptEngineFactory factory : factories) {
            System.out.printf("%s, %s, %s\n", factory.getEngineName(), factory.getLanguageName(),
                    factory.getEngineVersion());
            System.out.println(factory.getNames());
        }
    }
    
}
