package bg.softuni.errors.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

//Controller-Based Error Handling
@Controller
public class ProductController2 {
    @GetMapping("/products/{id}/details")
    public String showProductDetails(@PathVariable("id") Long productId){
        //retreive product from repository
        //productRepository.findBy(productId).orElseThrow(new ProductNotFoundException());
        throw new ObjectNotFoundException(productId);
    }

    //В ProductController2   е   дефиниран @ExceptionHandler, и затова работи
    @ExceptionHandler({ObjectNotFoundException.class})
    public ModelAndView handleDbExceptions(ObjectNotFoundException e) {    //Parent Exception
        ModelAndView modelAndView = new ModelAndView("product-not-found");
        modelAndView.addObject("productId", e.getProductId());
        //@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product not found!") - когато използваме Controller-based error handling този ред не важи
        //затова задаваме тук http статуса на отговора
        modelAndView.setStatus(HttpStatus.NOT_FOUND);

        return modelAndView;
    }
}







