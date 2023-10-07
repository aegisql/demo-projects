package com.aegisql.search_engine.rest;

import com.aegisql.search_engine.ui.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "*")
public class FileUploadRestController {

    @Value("${file.storage.location}")
    private String storageLocation;

    @PostMapping(value="/processFile", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ApiResponse> processFile(@RequestParam("file") MultipartFile file,
                                                   @RequestParam(value = "replace", defaultValue = "false") boolean replace) {
        if (file.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse("Please select a file.", HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }

        File storageDir = new File(storageLocation);
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        File destinationFile = new File(storageDir, file.getOriginalFilename());
        boolean fileExists = destinationFile.exists();
        if (fileExists && !replace) {
            return new ResponseEntity<>(new ApiResponse("File already exists.", HttpStatus.CONFLICT), headers, HttpStatus.CONFLICT);
        }

        try {
            file.transferTo(destinationFile);
            return new ResponseEntity<>(new ApiResponse(fileExists?"File uploaded successfully.":"File replaced successfully.", HttpStatus.OK), headers,  HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ApiResponse("Failed to upload the file.", HttpStatus.INTERNAL_SERVER_ERROR), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
