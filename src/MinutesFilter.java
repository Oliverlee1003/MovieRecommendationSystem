public class MinutesFilter implements Filter {
    private int minMin;
    private int maxMin;

    public MinutesFilter(int minMin, int maxMin) {
        this.minMin = minMin;
        this.maxMin = maxMin;
    }

    @Override
    public boolean satisfies(String id) {
        return (MovieDatabase.getMinutes(id) >= minMin && MovieDatabase.getMinutes(id) <= maxMin);
    }
}
