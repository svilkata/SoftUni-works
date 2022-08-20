package bg.softuni.kotlin.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class BankController {

//    @CrossOrigin(origins = arrayOf("http://example.com"))
    @CrossOrigin("*") //отвсякъде има достъп
    @PostMapping("/transfer")
    fun transferMoney(@RequestParam("from") from : String,
                      @RequestParam("to") to : String,
                      model: Model
    ) : String {
        model.addAttribute("from", from);
        model.addAttribute("to", to);
        return "transfer.html"
    }
}