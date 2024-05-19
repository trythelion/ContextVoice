package ca.tlcp.apis.read2redapi.controller;

import ca.tlcp.apis.read2redapi.api.APIKey;
import ca.tlcp.apis.read2redapi.api.APIKeyRepository;
import ca.tlcp.apis.read2redapi.project.Project;
import ca.tlcp.apis.read2redapi.project.ProjectRepository;
import ca.tlcp.apis.read2redapi.user.User;
import ca.tlcp.apis.read2redapi.user.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.crypto.KeyGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Controller
public class ManagerController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping(path = "/signup")
    public ModelAndView signup() {
       return new ModelAndView("signup");
    }
    @GetMapping(path = "/signin")
    public ModelAndView signin() {
        return new ModelAndView("login");
    }
    @PostMapping(path = "/register")
    public String signup(@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String FullName) {
        System.out.println(username + " added");
        User user = new User();
        user.setUsername(username);
        user.setPassword(Base64.getEncoder().encodeToString(password.getBytes()));
        user.setEmail(email);
        user.setFullName(FullName);
        // save user to database
        userRepository.save(user);
        return "redirect:/signin";
    }

    @PostMapping(path = "/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        User user = userRepository.getUserByEmail(email).get();
        if (userRepository.existsById(user.getUUID()) && user.getPassword().equals(Base64.getEncoder().encodeToString(password.getBytes()))) {
            return "redirect:/home.html?UUID=" + user.getUUID();
        }
        return "redirect:/signin";
    }
    @PostMapping(path = "/newproject")
    public String login(@RequestParam String projectname, @RequestParam long UUID) {
        Project project = new Project();
        project.setName(projectname);
        project.setUUID(UUID);
        project.setPID(UUID*55555);
        // save project to database
        projectRepository.save(project);
        return "redirect:/api/v1/generate?PID=" + UUID;
    }
}
