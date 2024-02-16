package ru.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Value;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.model.redmine.Issue;
import ru.model.redmine.IssueDto;
import ru.request.Redmine;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

@RestController
public class ErrorHandlerController {

    @Autowired
    private Environment env;
    private Redmine redmine;

    public ErrorHandlerController(Environment env, Redmine redmine) {
        this.env = env;
        this.redmine = redmine;
    }

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
        Map<Object, LinkedHashMap<Object, Object>> reportMap = getReportMap(file);
        Issue issue = prepearIssue(reportMap);
        redmine.addIssue(IssueDto.fromIssue(issue));

    }

    private Issue prepearIssue(Map<Object, LinkedHashMap<Object, Object>> reportMap) throws JsonProcessingException {

        Issue issue = new Issue();
        issue.setProject(env.getProperty("project"));
        issue.setPriority("5");
        issue.setTracker("Ошибка");
        LinkedHashMap<Object, Object> sessionInfo = reportMap.get("sessionInfo");
        issue.setUser((String) sessionInfo.get("userName"));
        issue.setSubject(getSubject(reportMap));
        issue.setDescription(getDescription(reportMap));
        issue.setParentIssueId("131");
        return issue;
    }

    private String getSubject(Map<Object, LinkedHashMap<Object, Object>> reportMap) {
        String subject = new String();
        LinkedHashMap<Object, Object> errorInfo = reportMap.get("errorInfo");
        LinkedHashMap<Object, Object> applicationErrorInfo = (LinkedHashMap<Object, Object>) errorInfo.get("applicationErrorInfo");
        List<Object> errors = (List<Object>) applicationErrorInfo.get("errors");
        List<Object> error1 = (List<Object>) errors.get(0);

        String err = error1.get(0).toString();

        if (err.isEmpty()) {
            List<Object> error2 = (List<Object>) errors.get(1);
            err = error2.get(0).toString();
        }

        return err;
    }

    private String getDescription(Map<Object, LinkedHashMap<Object, Object>> reportMap) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String description = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(reportMap);

        return description;
    }

    private Map<Object, LinkedHashMap<Object, Object>> getReportMap(MultipartFile file) throws IOException {
        Map<Object, LinkedHashMap<Object, Object>> map = new HashMap<>();
        ZipInputStream zin = new ZipInputStream(file.getInputStream());
        ZipEntry zipEntry = zin.getNextEntry();

        while (zipEntry != null) {
            String filename = zipEntry.getName();
            if (filename.endsWith(".json")) {
                ObjectMapper mapper = new ObjectMapper();
                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((zin)));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                String entryData = stringBuilder.toString();

                map = mapper.readValue(entryData, HashMap.class);

            }
            zipEntry = zin.getNextEntry();
        }
        zin.close();
        return map;
    }

}


