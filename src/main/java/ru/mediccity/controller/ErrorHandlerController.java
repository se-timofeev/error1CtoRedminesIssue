package ru.mediccity.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ErrorHandlerController {


    @PostMapping(value="getInfo")
    public ResponseEntity getInfo(@RequestBody String s) throws JsonProcessingException {

        Map<String,String> map = new HashMap<String,String>();

        ObjectMapper mapper = new ObjectMapper();

        map = mapper.readValue(s, HashMap.class);

        Map<Object, Object> response = new HashMap<>();
        response.put("needSendReport", true);
        response.put("userMessage", "Мы уже знаем об ошибке и принимаем меры для её исправления");
        response.put("dumpType", 1);

        return ResponseEntity.ok(response);
    }

    @PostMapping(value="pushReport")
    public ResponseEntity pushReport(@RequestParam("report") MultipartFile file,
                                     RedirectAttributes redirectAttributes)  {


        return ResponseEntity.ok().build();
    }

}
