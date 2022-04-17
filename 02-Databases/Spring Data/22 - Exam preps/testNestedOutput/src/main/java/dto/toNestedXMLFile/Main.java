package dto.toNestedXMLFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, JAXBException {
        ShopOneExportToXMLDto entry1 = new ShopOneExportToXMLDto();
        entry1.setName("Bai Kirovata krychma");
        entry1.setAddress("u nas si");
        entry1.setEmployeeCount(8);
        entry1.setShopArea(200);
        TownToXmlDto tn1 = new TownToXmlDto();
        tn1.setName("Sofia");
        entry1.setTown(tn1);

        ShopOneExportToXMLDto entry2 = new ShopOneExportToXMLDto();
        entry2.setName("Emi\'s shop");
        entry2.setAddress("Prava polqna 18");
        entry2.setEmployeeCount(14);
        entry2.setShopArea(150);
        TownToXmlDto tn2 = new TownToXmlDto();
        tn2.setName("Plovdiv");
        entry2.setTown(tn2);

        List<ShopOneExportToXMLDto> outputList = new ArrayList<>();
        outputList.add(entry1);
        outputList.add(entry2);

        ShopsRootExportToXMLDto allShops = new ShopsRootExportToXMLDto(outputList);

        JAXBContext context = JAXBContext.newInstance(ShopsRootExportToXMLDto.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Path path = Path.of("src/main/resources/output/outputToXML.xml");
        FileWriter fileWriter = new FileWriter(path.toString());

        marshaller.marshal(allShops, fileWriter);

        fileWriter.close();
    }
}
