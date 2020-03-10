package selectcontract;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class ContractModel {

    private ArrayList<Contract> theContracts;
    private int contractCounter;

    ContractModel() {
        contractCounter = 0;
        theContracts = new ArrayList<Contract>();
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
            }
            fileReader.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
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
