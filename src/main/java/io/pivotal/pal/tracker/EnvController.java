package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {


    @Value("${PORT:NOT SET}")
    String port;
    @Value("${MEMORY.LIMIT:NOT SET}")
    String memoryLimit;
    @Value("${CF.INSTANCE.INDEX:NOT SET}")
    String cfInstanceIndex;

    public EnvController() {
    }

    @Value("${CF.INSTANCE.ADDR:NOT SET}")
    String cfInstanceAddr;

    public EnvController(String port, String memoryLimit, String cfInstanceIndex, String cfInstanceAddr) {
        this.port = port;
        this.memoryLimit = memoryLimit;
        this.cfInstanceIndex = cfInstanceIndex;
        this.cfInstanceAddr = cfInstanceAddr;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {

        Map<String, String> e = new HashMap<>();
        e.put("PORT",port);
        e.put("MEMORY_LIMIT",memoryLimit);
        e.put("CF_INSTANCE_INDEX",cfInstanceIndex);
        e.put("CF_INSTANCE_ADDR",cfInstanceAddr);



        return e;
    }
}
