package bg.softuni.state.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class SessionController {
    private static final String LANG_SESSION_ATTRIBUTE = "lang";
    private static final String DEFAULT_LANG = "en";

    @GetMapping("/session")
    public String session(HttpSession session, Model model) {
        //сесия кода
        var sessionLang = session.getAttribute(LANG_SESSION_ATTRIBUTE);
        sessionLang = sessionLang != null ? sessionLang : DEFAULT_LANG;


        model.addAttribute("language", sessionLang); //към HTML кода

        return "session.html";
    }

    @PostMapping("/session")
    public String session(
            HttpSession session,
            @RequestParam("language") String language
    ) {
        //сесия кода - атрибута LANG_SESSION_ATTRIBUTE реално не е видим в браузъра, но в режим debug в IntelliJ можем да го видим, че се празаписва реално
        session.setAttribute(LANG_SESSION_ATTRIBUTE, language);

        return "redirect:/session";
    }
}
