package ca.tlcp.apis.read2redapi.controller;

import ca.tlcp.apis.read2redapi.api.APIKey;
import ca.tlcp.apis.read2redapi.api.APIKeyRepository;
import ca.tlcp.apis.read2redapi.utils.AI;
import io.github.amithkoujalgi.ollama4j.core.exceptions.OllamaBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.KeyGenerator;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/api/v1")
public class APIController {

    private static final Logger logger = Logger.getLogger(APIController.class.getName());
    @Autowired
    private APIKeyRepository apiKeyRepository;

    @GetMapping(path = "/f")
    public String generate(@RequestParam String APIKey, @RequestParam List<MultipartFile> files) throws OllamaBaseException, IOException, InterruptedException {
        List<File> convertedFiles = files.stream().map(file -> {
            File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
            try {
                file.transferTo(convertedFile);
            } catch (Exception e) {
                logger.log(Level.FINEST, e.getMessage());
            }
            return convertedFile;
        }).toList();
        return AI.runAI(convertedFiles).getResponse();
    }

    @GetMapping(path = "/t")
    public String generate(@RequestParam String APIKey, @RequestParam String passage) throws OllamaBaseException, IOException, InterruptedException {
        return AI.runAI(passage).getResponse();
    }

    @ResponseBody
    @GetMapping(path = "/generate")
    public String generateKey(@RequestParam long PID) throws NoSuchAlgorithmException {
        String key = Base64.getEncoder().encodeToString(KeyGenerator.getInstance("HmacSHA256").generateKey().getEncoded()).replaceAll("=", "5").replaceAll("0","1");
        apiKeyRepository.save(new APIKey(PID, key));
        return PID*5555555555L + key + PID*5555555555L;
    }
}
