package bg.softuni.kotlin.service

interface GreetingService {
    fun greeting(person: String = "Anonymous") : String  //функция greeting, която връща String. Parameter person, Който е от тип String и има дефаълтна стойност
}