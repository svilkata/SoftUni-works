package telephony;

import java.util.List;

public class Smartphone implements Browsable, Callable {
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String browse() {
        StringBuilder sb = new StringBuilder();

        for (String url : this.urls) {
            if (isValidURL(url)) {
                sb.append("Browsing... ").append(url).append("!").append(System.lineSeparator());
            } else {
                sb.append("Invalid URL!").append(System.lineSeparator());
            }
        }

        return sb.toString().trim();
    }

    private boolean isValidURL(String url) {
        for (char symbol : url.toCharArray()) {
            if (Character.isDigit(symbol)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String call() {
        StringBuilder sb = new StringBuilder();

        for (String number : this.numbers) {
            if (isValidNumber(number)) {
                sb.append("Calling... ").append(number).append(System.lineSeparator());
            }else {
                sb.append("Invalid number!").append(System.lineSeparator());
            }
        }


        return sb.toString().trim();
    }

    private boolean isValidNumber(String number) {
        for (char symbol : number.toCharArray()) {
            if (!Character.isDigit(symbol)) {
                return false;
            }
        }

        return true;
    }
}
