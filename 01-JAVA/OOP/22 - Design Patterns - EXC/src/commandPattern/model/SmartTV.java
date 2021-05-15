package commandPattern.model;

public class SmartTV implements Television {
    private int volume;

    public SmartTV() {
        this.volume = 0;
    }


    @Override
    public void turnOn() {
        System.out.println("On");
    }

    @Override
    public void turnOff() {
        System.out.println("Off");
    }

    @Override
    public void volumeUp() {
        this.volume++;
        System.out.println("Volume: " + this.volume);
    }
}
