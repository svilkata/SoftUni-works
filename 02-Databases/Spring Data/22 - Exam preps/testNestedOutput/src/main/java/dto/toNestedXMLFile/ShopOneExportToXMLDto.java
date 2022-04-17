package dto.toNestedXMLFile;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "shop")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopOneExportToXMLDto {
    @XmlElement
    private String name;

    @XmlElement
    private String address;

    @XmlElement(name = "employee-count")
    private int employeeCount;

    @XmlElement(name = "shop-area")
    private int shopArea;

    @XmlElement(name = "town")
    private TownToXmlDto town;

    public ShopOneExportToXMLDto() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShopArea() {
        return shopArea;
    }

    public void setShopArea(int shopArea) {
        this.shopArea = shopArea;
    }

    public TownToXmlDto getTown() {
        return town;
    }

    public void setTown(TownToXmlDto town) {
        this.town = town;
    }
}
