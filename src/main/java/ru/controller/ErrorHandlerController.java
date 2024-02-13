package ru.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@RestController
public class ErrorHandlerController {


    @PostMapping(value = "getInfo")
    public ResponseEntity getInfo(@RequestBody String s) throws JsonProcessingException {

        Map<String, String> map = new HashMap<String, String>();

        ObjectMapper mapper = new ObjectMapper();

        map = mapper.readValue(s, HashMap.class);

        Map<Object, Object> response = new HashMap<>();
        response.put("needSendReport", true);
        response.put("userMessage", "Мы уже знаем об ошибке и принимаем меры для её исправления");
        response.put("dumpType", 1);

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "pushReport")
    public ResponseEntity pushReport(@RequestParam("report") MultipartFile file,
                                     RedirectAttributes redirectAttributes) throws IOException {
        Long size = file.getSize();
        if (file.isEmpty() == false) {
            FileHandler(file);
        }
        return ResponseEntity.ok().build();
    }

    private void FileHandler(MultipartFile file) throws IOException {
        Map<String, String> reportMap = getReportMap(file);

    }

    private Map<String, String> getReportMap(MultipartFile file) throws IOException {
        ZipInputStream zin = new ZipInputStream(file.getInputStream());
        ZipEntry ze;
        Map<String, String> map = new HashMap<String, String>();

        while ((ze = zin.getNextEntry()) != null) {
            String name = ze.getName();
            if (name.endsWith(".json")) {
                ObjectMapper mapper = new ObjectMapper();
                map = mapper.readValue(zin, HashMap.class);
            }
        }
        zin.closeEntry();

        return map;
    }

}


