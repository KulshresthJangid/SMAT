package com.nerdyGeek.smat.rest.controller;

import com.nerdyGeek.smat.configs.GlobalProperties;
import com.nerdyGeek.smat.enums.SMPlatform;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private GlobalProperties config;

    public WebhookController(GlobalProperties globalProperties) {
        this.config = globalProperties;
    }


    @GetMapping("/{platform}")
    public String verifyWebhook(@RequestParam("hub.mode") String mode, @RequestParam("hub.challenge") String challenge,
                                @RequestParam("hub.verify_token") String verifyToken) {
        if ("subscribe".equals(mode) && config.getVerificationToken().equals(verifyToken)) {
            return challenge; // Respond with challenge token to verify
        } else {
            return "Invalid token"; // Verification failed
        }
    }

    @PostMapping("/{platform}")
    public void verifyWebhook(@PathVariable SMPlatform platform, @RequestBody JSONObject payload) {
        System.out.println(payload.toJSONString());
        return;
    }
}
