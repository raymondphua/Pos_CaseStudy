package Properties;

/**
 * Created by Raymond Phua on 19-9-2016.
 */
public enum Name {
    MILK, COLA, ICETEA, LAPTOP;

    public String toString() {
        switch(this) {
            case MILK: return "Milk";
            case COLA: return "Cola";
            case ICETEA: return "IceTea";
            case LAPTOP: return "Intel Laptop";
            default: return "Undefined";
        }
    }
}
