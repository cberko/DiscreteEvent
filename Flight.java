import java.util.ArrayList;

public class Flight  {
    String FlightCode;
    ArrayList<Integer> operationTimes = new ArrayList<>();
    ATC departureATC = new ATC("");
    ATC landingATC= new ATC("");
    ACC Acc = new ACC("");
    int ACCTime = 0;
    boolean isACC;
    boolean isRun;

    int curr;

    boolean isNew = true;

    public String toString() {
        return FlightCode + " " + AdmissionTime +" "+ curr + " " + isACC + " " + isRun;
    }
    int AdmissionTime;
    public Flight(String Code){
        FlightCode = Code;

    }


}