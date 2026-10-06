package com.careercounsel.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ResumeService {

    // Notice we changed the parameter here to MultipartFile
    public Map<String, Object> analyze(MultipartFile file) {
        Map<String, Object> response = new HashMap<>();

        try {
            // 1. Extract text from the uploaded PDF file
            PDDocument document = PDDocument.load(file.getInputStream());
            PDFTextStripper stripper = new PDFTextStripper();
            String extractedText = stripper.getText(document);
            document.close();

            // 2. Send the extracted text to your AI (Gemini/OpenAI) here.
            // Replace the mock logic below with your actual AI calling logic using 'extractedText'

            // --- MOCK AI RESPONSE FOR TESTING ---
            response.put("score", 85);
            response.put("feedback", "Excellent resume! Consider adding more quantifiable metrics to your recent projects.");
            response.put("keywordsMatched", 14);
            // ------------------------------------

        } catch (IOException e) {
            e.printStackTrace();
            response.put("score", 0);
            response.put("feedback", "Error reading the PDF document. Please ensure it is not corrupted or password protected.");
            response.put("keywordsMatched", 0);
        }

        return response;
    }
}