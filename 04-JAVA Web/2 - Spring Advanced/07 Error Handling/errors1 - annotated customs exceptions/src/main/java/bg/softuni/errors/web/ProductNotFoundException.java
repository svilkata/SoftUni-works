package bg.softuni.errors.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product not found!")
public class ProductNotFoundException extends RuntimeException{

}
