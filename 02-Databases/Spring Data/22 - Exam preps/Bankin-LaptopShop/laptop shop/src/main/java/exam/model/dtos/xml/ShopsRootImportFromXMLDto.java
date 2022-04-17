package exam.model.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "shops")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopsRootImportFromXMLDto {
    @XmlElement(name = "shop")
    private List<ShopOneImportFromXMLDto> shops;

    public ShopsRootImportFromXMLDto() {
    }

    public List<ShopOneImportFromXMLDto> getShops() {
        return shops;
    }
}
