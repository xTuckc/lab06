package selectcontract;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

class ContractModel {

    private ArrayList<Contract> theContracts;
    private int contractCounter;
    private ArrayList<Contract> theContractsAll;
    private SortedSet<String> originCityList;
    
    public ContractModel() {
        contractCounter = 0;
        theContracts = new ArrayList<Contract>();
        originCityList = new TreeSet<>();
        String filename = "C:\\Users\\tuckw\\OneDrive\\ICS_WINTER_2020\\ICS125\\SelectContract\\src\\selectcontract\\Contracts.txt";
        
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {

                String[] tokens = line.split(",", Contract.NUMBER_OF_CONTRACT_ATTRIBUTES);

                String contractID = tokens[Contract.INDEX_OF_CONTRACT_ID];
                String originCity = tokens[Contract.INDEX_OF_ORIGIN_CITY];
                String destCity = tokens[Contract.INDEX_OF_DEST_CITY];
                String orderItem = tokens[Contract.INDEX_OF_ORDER_ITEM];

                Contract dataContract = new Contract(contractID,
                        originCity,
                        destCity,
                        orderItem);
                theContracts.add(dataContract);
                originCityList.add(dataContract.getOriginCity());
            }
            fileReader.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        originCityList.add("All");
        theContractsAll = theContracts;
    }
    
    public String[] getOriginCityList() {
        String[] a;
        a = originCityList.toArray(new String[originCityList.size()]);
        return a;
    }
    
    public void updateContractList(String city){
        theContracts = new ArrayList<>(theContractsAll);
        if (city != "All") {
            theContracts.removeIf(s -> !s.contains(city));
        }
        contractCounter = 0;
    }
    
    boolean foundContracts() {
        return theContracts.size() >= 1;
    }

    public Contract getTheContract() {
        return theContracts.get(contractCounter);
    }

    public int getContractCount() {
        return theContracts.size();
    }

    public int getCurrentContractNum() {
        return contractCounter;
    }

    public void nextContract() {
        if (contractCounter < theContracts.size()) {
            contractCounter++;
        }
    }

    public void prevContract() {
        if (contractCounter > 0) {
            contractCounter--;
        }
    }
}
