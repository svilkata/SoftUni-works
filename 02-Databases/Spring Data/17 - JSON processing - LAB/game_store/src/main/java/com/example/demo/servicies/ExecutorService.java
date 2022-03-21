package com.example.demo.servicies;

public interface ExecutorService {
    final String REGISTER_USER_COMMAND = "RegisterUser";
    final String LOGIN_USER_COMMAND = "LoginUser";
    final String LOGOUT_USER_COMMAND = "LogoutUser";
    final String ADD_GAME_COMMAND = "AddGame";

    String execute(String command, String data);

    String REGISTER_USER_JSON = """
{
    "email": "pesho22@mail.com",
    "password": "1234567",
    "confirmPassword": "1234567",
    "fullName": "Pesho 22334",
}
            """;

    String LOGIN_USER_JSON = """
            {
                "email": "pesho22@mail.com",
                "password": "1234567",
            }
            """;
}
