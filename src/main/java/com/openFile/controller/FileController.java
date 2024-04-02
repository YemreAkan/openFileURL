package com.openFile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/")
public class FileController {

    @GetMapping("api/file/path/{name}")
    public ResponseEntity<Resource> showFileById(@PathVariable String name) throws IOException {


        String fileType = "";
        Path path = Paths.get("//Users//yemreakan//issuetracking_files//" + name);
        String fileExtension = name.split("\\.")[1];
        if (fileExtension.equalsIgnoreCase("pdf"))
            fileType = "application/pdf";
        else if (fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("jpeg"))
            fileType = "image/jpeg";
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "inline;filename=" + name);
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return ResponseEntity.ok()
                .headers(headers).location(path.toUri())
                .contentLength(resource.getByteArray().length)
                .contentType(MediaType.parseMediaType(fileType))
                .body(resource);
    }

    //***FROM DB***//
    /*@GetMapping("/api/file/path/{id}")
    public ResponseEntity<Resource> showFileById(@PathVariable Long id) throws IOException {


        String fileType = "";
        FileEntity fileEntity = fileService.findById(id);
        Path path = Paths.get(fileEntity.getPath());
        String fileExtension = fileEntity.getFileName().split("\\.")[1];
        if(fileExtension.equalsIgnoreCase("pdf"))
            fileType = "application/pdf";
        else if(fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("jpeg"))
            fileType = "image/jpeg";
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "inline;filename=" +fileEntity.getFileName());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return ResponseEntity.ok()
                .headers(headers).location(path.toUri())
                .contentLength(resource.getByteArray().length)
                .contentType(MediaType.parseMediaType(fileType))
                .body(resource);
    }*/
  }
