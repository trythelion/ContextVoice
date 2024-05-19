package ca.tlcp.apis.read2redapi.utils;

import io.github.amithkoujalgi.ollama4j.core.OllamaAPI;
import io.github.amithkoujalgi.ollama4j.core.exceptions.OllamaBaseException;
import io.github.amithkoujalgi.ollama4j.core.models.OllamaResult;
import io.github.amithkoujalgi.ollama4j.core.utils.OptionsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AI {
    private static final OllamaAPI API = new OllamaAPI("http://localhost:11434/");

    public static OllamaResult runAI(List<File> files) throws OllamaBaseException, IOException, InterruptedException {
        String prompt = "Do your job with the following passage from these files. You are restricted to making the modifications only to the word \"read\".\n";
        return API.generateWithImageFiles("read2red", prompt, files, new OptionsBuilder().build());
    }

    public static OllamaResult runAI(String input) throws OllamaBaseException, IOException, InterruptedException {
        String prompt = "Do your job with the following passage. You are restricted to making the modifications only to the word \"read\".\n" +  input;
        return API.generate("read2red", prompt , new OptionsBuilder().build());
    }

}
