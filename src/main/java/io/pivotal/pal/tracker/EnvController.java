package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class EnvController {
    private final String portNum, LIMIT, INDEX, ADDR;

    public EnvController(@Value("${PORT:NOT SET}") String PORT,@Value("${MEMORY_LIMIT:NOT SET}") String MEMORY_LIMIT,
                         @Value("${CF_INSTANCE_INDEX:NOT SET}")String CF_INSTANCE_INDEX,
                         @Value("${CF_INSTANCE_ADDR:NOT SET}")String CF_INSTANCE_ADDR) {

        portNum = PORT;
        LIMIT = MEMORY_LIMIT;
        INDEX = CF_INSTANCE_INDEX;
        ADDR = CF_INSTANCE_ADDR;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        Map<String, String> result = new HashMap<>();
        result.put("PORT", portNum);
        result.put("MEMORY_LIMIT", LIMIT);
        result.put("CF_INSTANCE_INDEX", INDEX);
        result.put("CF_INSTANCE_ADDR", ADDR);

        return result;
    }
}
