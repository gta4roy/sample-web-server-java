package com.gta4roy.java.JavaWebServer.controller;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;

@RestController
public class MainController {

    private static final Path UPLOAD_DIR = Paths.get("uploads");

    @GetMapping("/health")
    public String getHealth(){
        JSONObject object = new JSONObject();
        object.put("UP","true");
        return object.toString();
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        Files.createDirectories(UPLOAD_DIR);

        Path target = UPLOAD_DIR.resolve(
                UUID.randomUUID() + "_" + Paths.get(file.getOriginalFilename()).getFileName()
        );

        try (InputStream in = file.getInputStream()) {
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        }

        return ResponseEntity.ok(Map.of(
                "fileName", target.getFileName().toString(),
                "size", file.getSize(),
                "path", target.toAbsolutePath().toString()
        ));
    }
}
