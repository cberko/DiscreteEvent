import java.util.*;

public class ACC {
    String ACCName = "";
    ArrayList<ATC> ATCs = new ArrayList<>();
    ArrayList<Flight> flights = new ArrayList<>();
    public int NextAvailableTime = 0;
    public ArrayList<Integer> Times= new ArrayList<>();
   public int ACCTime = 0;


    static PriorityQueue<Flight> queue= new PriorityQueue<>(new OperationComparator());

    public ACC(String Name) {
        ACCName = Name;
    }

    static HashSet<Integer> RunningonACC = new HashSet<>();

    public static void RunningStatesonACC(HashSet<Integer>ACC) {
        ACC.add(0);
        ACC.add(2);
        ACC.add(10);
        ACC.add(12);
        ACC.add(20);
    }

    static HashSet<Integer> RunningonATC = new HashSet<>();

    public static void RunningStatesonATC(HashSet<Integer>ATC) {
        ATC.add(3);
        ATC.add(5);
        ATC.add(7);
        ATC.add(9);
        ATC.add(13);
        ATC.add(15);
        ATC.add(17);
        ATC.add(19);
    }

    public static String lastThree(int i) {
        i = i % 1000;
        String hashing;
        if (i < 10) {
            hashing = "00" + i;
        } else if (i < 100) {
            hashing = "0" + i;
        } else {
            hashing = "" + i;
        }
        return hashing;
    }

    public int hashTable(String s) {
        int hash = 0;
        for (int i = 0; i < s.length(); i++) {
            int asciiVal = (int) ((Math.pow(31, i)) * (int) (s.charAt(i)));
            hash += asciiVal;
        }
        return hash;
    }




    public  int DiscreteEvent() {
        HashSet<Integer>RunningONACC = new HashSet<>();
        HashSet<Integer>RunningONATC = new HashSet<>();
        RunningStatesonACC(RunningONACC);
        RunningStatesonATC(RunningONATC);

        while (queue.isEmpty() == false) {
            Flight temp = queue.poll();
            if (temp.curr < 21) {

                if (temp.curr < 3 || (temp.curr > 9 && temp.curr < 13) || temp.curr == 20) {
                    temp.isACC = true;
                } else {
                    temp.isACC = false;
                }
                if (RunningONACC.contains(temp.curr) || RunningONATC.contains(temp.curr)) {
                    temp.isRun = true;
                } else {
                    temp.isRun = false;
                }
                if (temp.isACC) {
                    if (NextAvailableTime <= temp.AdmissionTime && temp.isRun == true) {
                        //ACCRUNNING STATE
                        if (temp.operationTimes.get(temp.curr) <= 30) {
                            //ACC Running operation whose time's is less than 30

                            temp.AdmissionTime = temp.AdmissionTime + temp.operationTimes.get(temp.curr);
                            temp.operationTimes.set(temp.curr, 0);
                            temp.curr++;
                            NextAvailableTime = temp.AdmissionTime;
                            ACC.queue.add(temp);
                            temp.isNew = true;

                        } else {
                            //ACC Running operation whose time's is greater than 30
                            temp.AdmissionTime = temp.AdmissionTime + 30;
                            NextAvailableTime = temp.AdmissionTime;
                            temp.operationTimes.set(temp.curr, temp.operationTimes.get(temp.curr) - 30);
                            temp.isNew = false;
                            ACC.queue.add(temp);
                        }

                    }   else if (temp.isRun == false) {
                        //Waiting State on ACC
                        temp.AdmissionTime = temp.AdmissionTime + temp.operationTimes.get(temp.curr);
                        temp.operationTimes.set(temp.curr, 0);
                        temp.curr++;
                        temp.isNew = true;
                        ACC.queue.add(temp);
                    } else if (temp.AdmissionTime < NextAvailableTime && temp.isRun) {
                        temp.AdmissionTime = NextAvailableTime;
                        if(temp.operationTimes.get(temp.curr) <= 30){
                            temp.AdmissionTime = temp.AdmissionTime + temp.operationTimes.get(temp.curr);
                            temp.operationTimes.set(temp.curr, 0);
                            temp.curr++;
                            temp.isNew=true;
                            NextAvailableTime = temp.AdmissionTime;
                            ACC.queue.add(temp);
                        }
                        else{
                            temp.AdmissionTime = temp.AdmissionTime + 30;
                            temp.operationTimes.set(temp.curr, temp.operationTimes.get(temp.curr) - 30);
                            NextAvailableTime = temp.AdmissionTime;
                            temp.isNew = false;
                            ACC.queue.add(temp);
                        }
                    }

                } else {
                    if ((temp.curr > 2 && temp.curr < 10)) {
                        if (temp.departureATC.ATCTime <= temp.AdmissionTime && temp.isRun == true) {
                            //ATCRUNNING STATE
                            temp.AdmissionTime = temp.AdmissionTime + temp.operationTimes.get(temp.curr);
                            temp.operationTimes.set(temp.curr, 0);
                            temp.departureATC.ATCTime = temp.AdmissionTime;
                            temp.curr++;

                            if (temp.curr == 10) {
                                temp.isNew = true;
                            }
                            queue.add(temp);
                        } else if (temp.isRun == false) {
                            //Waiting States
                            temp.AdmissionTime = temp.AdmissionTime + temp.operationTimes.get(temp.curr);
                            temp.operationTimes.set(temp.curr, 0);
                            temp.curr++;
                            temp.isNew=true;
                            queue.add(temp);
                        } else if (temp.departureATC.ATCTime > temp.AdmissionTime && temp.isRun == true) {
                            temp.AdmissionTime = temp.departureATC.ATCTime;
                            temp.AdmissionTime = temp.AdmissionTime + temp.operationTimes.get(temp.curr);
                            temp.operationTimes.set(temp.curr, 0);
                            temp.curr++;
                            temp.departureATC.ATCTime = temp.AdmissionTime;
                            temp.isNew = true;
                            queue.add(temp);
                            }

                        }
                    else if(temp.curr>12 && temp.curr<20){
                        if (temp.landingATC.ATCTime <= temp.AdmissionTime && temp.isRun == true) {
                            //ATCRUNNING STATE
                            temp.AdmissionTime = temp.AdmissionTime + temp.operationTimes.get(temp.curr);
                            temp.operationTimes.set(temp.curr, 0);
                            temp.landingATC.ATCTime = temp.AdmissionTime;
                            temp.curr++;
                            temp.isNew = true;
                            queue.add(temp);
                        } else if (temp.isRun == false) {
                            //Waiting States
                            temp.AdmissionTime = temp.AdmissionTime + temp.operationTimes.get(temp.curr);
                            temp.operationTimes.set(temp.curr, 0);
                            temp.curr++;
                            temp.isNew = true;
                            queue.add(temp);
                        } else if (temp.landingATC.ATCTime > temp.AdmissionTime && temp.isRun == true) {
                            temp.AdmissionTime = temp.landingATC.ATCTime;
                            temp.AdmissionTime = temp.AdmissionTime + temp.operationTimes.get(temp.curr);
                            temp.operationTimes.set(temp.curr, 0);
                            temp.curr++;
                            temp.landingATC.ATCTime = temp.AdmissionTime;
                            temp.isNew = true;
                            queue.add(temp);
                            }
                        }

                    }

                } else if (temp.curr == 21) {
                Times.add(temp.AdmissionTime);
            }
                }
        for (int t = 0; t < Times.size(); t++){
            if(Times.get(t)>ACCTime){
                ACCTime=Times.get(t);
            }
        }
        return ACCTime;
    }
}














    