package bg.softuni.errors.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//Global exception handling
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler()
    public ModelAndView handleDbExceptions(ObjectNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("object-not-found");
        modelAndView.addObject("objectId", e.getObjectId());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
//        modelAndView.addObject("stack", {...} /* Formatted Stack Trace */);

        return modelAndView;
    }
}


