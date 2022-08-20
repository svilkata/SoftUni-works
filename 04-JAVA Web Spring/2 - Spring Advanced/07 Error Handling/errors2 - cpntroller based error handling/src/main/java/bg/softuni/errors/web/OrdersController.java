package bg.softuni.errors.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Controller-Based Error Handling
@Controller
public class OrdersController {
    @GetMapping("/orders/{id}/details")
    public String showProductDetails(@PathVariable("id") Long orderId){
        throw new ObjectNotFoundException(orderId);
    }

    //В OrdersController не е дефиниран @ExceptionHandler, и затова не работи
}
