package FamilyTree;

public class ManualPair {
    private String keyParent;
    private String valueChild;

    public ManualPair(String keyName, String valueBirthday) {
        this.keyParent = keyName;
        this.valueChild = valueBirthday;
    }

    public String getKeyParent() {
        return keyParent;
    }

    public String getValueChild() {
        return valueChild;
    }

    public void setKeyParent(String keyParent) {
        this.keyParent = keyParent;
    }

    public void setValueChild(String valueChild) {
        this.valueChild = valueChild;
    }
}
