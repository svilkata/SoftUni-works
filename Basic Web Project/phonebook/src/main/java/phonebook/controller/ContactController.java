package phonebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import phonebook.entity.Contact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ContactController {
    private List<Contact> contacts;

    public ContactController() {
        this.contacts = new ArrayList<>();
//        this.contacts = Arrays.asList(
//                new Contact("Pesho", "+359"),
//                new Contact("Gosho", "+358"),
//                new Contact("Kircho", "+357")
//        );
    }

    @GetMapping("/") //вземи данните за всички
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        modelAndView.addObject("contacts", this.contacts);

        return modelAndView;
    }

    @PostMapping("/") //покажи всички
    public String storeContact(Contact contact) {
        this.contacts.add(contact);

        return "redirect:/"; //върни ме на началната страница
    }

    @DeleteMapping("/contacts/{name}") //това взема от URL-а
    public String deleteContact(@PathVariable String name) {
        this.contacts = this.contacts
                .stream()
                .filter(c -> !c.getName().equals(name))
                .collect(Collectors.toList());

        return "redirect:/"; //върни ме на началната страница
    }
}
