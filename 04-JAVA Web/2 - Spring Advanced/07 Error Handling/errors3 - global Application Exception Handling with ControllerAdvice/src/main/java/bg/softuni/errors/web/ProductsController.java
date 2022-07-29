package bg.softuni.errors.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductsController {
    @GetMapping("/products/{id}/details")
    public String showProductDetails(@PathVariable("id") Long objectId){
        throw new ObjectNotFoundException(objectId);
    }
}
