package bg.softuni.kotlin.service

import org.springframework.stereotype.Service

@Service
class GreetingServiceImpl : GreetingService {    //GreetingServiceImpl имплементира GreetingService
    override fun greeting(person: String): String {
        return "Hello, $person"
    }
}