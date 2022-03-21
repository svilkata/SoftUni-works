import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    private static final String jsonText = """
           [
              {
                "country": "Bulgaria",
                "city": "Sofia",
                "street": "Mladost 4"
              },
              {
                "country": "Spain",
                "city": "Barcelona",
                "street": "Las Ramblas"
              }
           ]""";

    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();


//        AddressJsonDto addressJsonDtoBulgaria = new AddressJsonDto();
//        addressJsonDtoBulgaria.setCountry("Bulgaria");
//        addressJsonDtoBulgaria.setCity("Sofia");
//        addressJsonDtoBulgaria.setStreet("Mladost 4");
//
//        AddressJsonDto addressJsonDtoSpain = new AddressJsonDto();
//        addressJsonDtoSpain.setCountry("Spain");
//        addressJsonDtoSpain.setCity("Barcelona");
//        addressJsonDtoSpain.setStreet("Las Ramblas");
//
//        List<AddressJsonDto> addressJsonDtos = new ArrayList<>();
//        addressJsonDtos.add(addressJsonDtoBulgaria);
//        addressJsonDtos.add(addressJsonDtoSpain);
//        String content = gson.toJson(addressJsonDtos);

//        AddressJsonDto addressJsonDto =
//                gson.fromJson(jsonText, AddressJsonDto.class);

        AddressJsonDto[] addressJsonDtos =
                gson.fromJson(jsonText, AddressJsonDto[].class);

        System.out.println(addressJsonDtos);
    }
}
