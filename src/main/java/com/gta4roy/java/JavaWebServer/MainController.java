package com.gta4roy.java.JavaWebServer;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/health")
    public static String getHealth(){
        JSONObject object = new JSONObject();
        object.put("UP","true");
        return object.toString();
    }
}
