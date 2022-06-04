package bg.softuni.state.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CookieController {
    private static final String LANG_COOKIE_NAME = "lang";

    @GetMapping("/cookies")
    public String cookies(Model model,
                          @CookieValue(
                                  name = LANG_COOKIE_NAME,
                                  defaultValue = "en"
                          ) String language) {    //We take the cookie value with @CookieValue(), and we set the default value to "en"
        model.addAttribute("lang", language);

        return "cookies.html";
    }

    @PostMapping("/cookies")
    public String cookies(
            HttpServletResponse response,
            @RequestParam("language") String language
            ) {

        Cookie cookie = new Cookie(LANG_COOKIE_NAME, language);  //how we set a cookie - new Cookie(name, value)
        response.addCookie(cookie);

        return "redirect:/cookies";
    }
}
