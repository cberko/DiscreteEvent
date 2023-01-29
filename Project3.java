import java.io.*;
import java.util.*;
public class Project3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
        ArrayList<ACC> allACC = new ArrayList<>();
        String line = reader.readLine();
        String[] parts = line.split(" ");
        int totalACC = Integer.parseInt(parts[0]);
        Hashtable<Integer,ATC>[] Airports = new Hashtable[totalACC];
        HashMap<String, Integer> ACCNameToIndex = new HashMap<>();
        for(int i = 0; i < totalACC; i++){
            Airports[i] = new Hashtable<>();
        }
        int totalFlight = Integer.parseInt(parts[1]);
        for (int i = 0; i < totalACC; i++) {
            line = reader.readLine();
            String[] accParts = line.split(" ");
            ACC tempACC = new ACC(accParts[0]);
            ACCNameToIndex.put(accParts[0], i);
            tempACC.queue = new PriorityQueue<>(new OperationComparator());
            allACC.add(tempACC);
            for (int j = 1; j < accParts.length; j++) {
                ATC tempATC = new ATC(accParts[j]);
                int index = (HashTable.Indexing(accParts[j]));
                while (Airports[i].containsKey(index)) {
                    index++;
                    index = index % 1000;
                }
                tempATC.ATCName = HashTable.atcName(accParts[j], index);
                allACC.get(i).ATCs.add(tempATC);


                Airports[i].put(index,tempATC);


            }
        }
        for (int i = 0; i < totalFlight; i++) {
            line = reader.readLine();
            String[] flightParts = line.split(" ");
            Flight tempFlight = new Flight(flightParts[1]);
            tempFlight.AdmissionTime = Integer.parseInt(flightParts[0]);
            for (ACC acc : allACC) {
                if (acc.ACCName.equals((flightParts[2]))) {
                    tempFlight.Acc = acc;
                }
            }
            int index = (HashTable.Indexing(flightParts[3]));
            while(!Airports[ACCNameToIndex.get(flightParts[2])].get(index).ATCName.substring(0,3).equals(flightParts[3])){
                index++;
                index = index % 1000;
            }
            tempFlight.departureATC = Airports[ACCNameToIndex.get(flightParts[2])].get(index);
            index = (HashTable.Indexing(flightParts[4]));
            while(!Airports[ACCNameToIndex.get(flightParts[2])].get(index).ATCName.substring(0,3).equals(flightParts[4])){
                index++;
                index = index % 1000;
            }
            tempFlight.landingATC = Airports[ACCNameToIndex.get(flightParts[2])].get(index);
            for (int j = 0; j < flightParts.length - 5; j++) {
                tempFlight.operationTimes.add(Integer.parseInt(flightParts[j + 5]));
                if (j == flightParts.length - 6) {
                    for (ACC acc : allACC) {
                        if (acc.ACCName.equals(tempFlight.Acc.ACCName)) {
                            acc.flights.add(tempFlight);
                        }
                    }
                }
            }
        }
        for(int j=0;j<totalACC;j++){
            for(Flight flight:allACC.get(j).flights){
                allACC.get(j).queue.add(flight);
            }
            String output = allACC.get(j).ACCName + " " + (allACC.get(j).DiscreteEvent());
            for(int i=0;i<1000;i++){
                if(!Airports[j].containsKey(i))
                    continue;
                output += " " + Airports[j].get(i).ATCName;
            }
            writer.write(output + '\n');
           
        }
        reader.close();
        writer.close();
    }
}