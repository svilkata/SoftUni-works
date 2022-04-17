package dto.toNestedXMLFile;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "shops")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopsRootExportToXMLDto {
    @XmlElement(name = "shop")
    private List<ShopOneExportToXMLDto> shops;

    public ShopsRootExportToXMLDto() {
    }

    public List<ShopOneExportToXMLDto> getShops() {
        return shops;
    }

    public ShopsRootExportToXMLDto(List<ShopOneExportToXMLDto> shops) {
        this.shops = shops;
    }
}
