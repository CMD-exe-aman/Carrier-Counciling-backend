package com.careercounsel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.careercounsel.service.ResumeService;

import java.util.Map;

@RestController
@RequestMapping("/api/resume")
@CrossOrigin("*")
public class ResumeController {

    @Autowired
    private ResumeService service;

    @PostMapping("/analyze")
    public Map<String, Object> analyze(@RequestParam("file") MultipartFile file) {
        // You will need to update your ResumeService to extract text from this PDF file
        // using a library like Apache PDFBox before sending it to the AI.
        return service.analyze(file);
    }
}