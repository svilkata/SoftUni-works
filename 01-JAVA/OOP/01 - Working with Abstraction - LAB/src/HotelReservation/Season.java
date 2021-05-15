package HotelReservation;

public enum Season {
    Spring(2), Summer(4), Autumn(1), Winter(3);

    private final int seasonIndex;

    Season(int index) {
        this.seasonIndex = index;
    }

    public int getSeasonIndex() {
        return this.seasonIndex;
    }

}

