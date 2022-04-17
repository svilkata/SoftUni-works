package dto.toNestedJSONFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.toNestedJSONFile.CustomerOutputToJSONDto;
import dto.toNestedJSONFile.TownJsonDto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson = new GsonBuilder()
//                .excludeFieldsWithoutExposeAnnotation() //ако използваме expose на полетата на класа
                .setPrettyPrinting()
                .create();

        CustomerOutputToJSONDto entry1 = new CustomerOutputToJSONDto();
        entry1.setFirstName("Ivan");
        entry1.setLastName("Petkov");
        entry1.setEmail("ivan@abv.bg");
        TownJsonDto tn1 = new TownJsonDto();
        tn1.setName("Pazardjik");
        entry1.setTown(tn1);

        CustomerOutputToJSONDto entry2 = new CustomerOutputToJSONDto();
        entry2.setFirstName("Svilen");
        entry2.setLastName("Velikov");
        entry2.setEmail("svilen@abv.bg");
        TownJsonDto tn2 = new TownJsonDto();
        tn2.setName("Shumen");
        entry2.setTown(tn2);

        List<CustomerOutputToJSONDto> toJSONDtos = new ArrayList<>();
        toJSONDtos.add(entry1);
        toJSONDtos.add(entry2);

        String content = gson.toJson(toJSONDtos);

        Path path = Path.of("src/main/resources/output/outputToJSON.json");

        FileWriter fileWriter = new FileWriter(String.valueOf(path));
        fileWriter.write(content);
        fileWriter.close();
    }
}
