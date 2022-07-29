package bg.softuni.errors.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//with Annotated Custom Exceptions
@Controller
public class ProductController1 {
    //Error 404
    @GetMapping("/products/{id}/details")
    public String showProductDetails(@PathVariable("id") String productId){
        //retreive product from repository
        //productRepository.findBy(productId).orElseThrow(new ProductNotFoundException());
        throw new ProductNotFoundException();
    }

    //Error 500
    @GetMapping("/products/{id}/error")
    public String boom(@PathVariable("id") String productid){
        //something wrong here
        //productRepository.findBy(productId).orElseThrow(new ProductNotFoundException());
        throw new NullPointerException();
    }
}




