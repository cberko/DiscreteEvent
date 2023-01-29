import java.util.Comparator;

public class OperationComparator implements Comparator<Flight> {
    public int compare(Flight o1, Flight o2){
        if(o1.AdmissionTime< o2.AdmissionTime){
            return -1;
        }
        if(o1.AdmissionTime > o2.AdmissionTime){
            return 1;
        }
        if(o1.isNew && !o2.isNew){
            return -1;
        }
        if(!o1.isNew && o2.isNew){
            return 1;
        }
        return o1.FlightCode.compareTo(o2.FlightCode);
    }
}
