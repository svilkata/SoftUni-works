package bg.softuni.kotlin.web

import bg.softuni.kotlin.service.GreetingService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class GreeterController(val greetingService: GreetingService) {

    @GetMapping("/greet")
    fun greet(@RequestParam(name = "person", defaultValue = "Anonymous") person: String, model : Model)
    : String {

        model.addAttribute("greeting", greetingService.greeting(person = person));  //възможност за задаване на име на параметър

        return "hello.html";
    }
}