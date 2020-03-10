package selectcontract;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

class ContractController {

    private ContractView theView;
    private ContractModel theModel;

    ContractController(ContractView theView, ContractModel theModel) {
        this.theView = theView;
        this.theModel = theModel;
        this.theView.addPrevListener(new PrevButtonListener());
        // this.theView.addBidListener(new BidButtonListener());
        this.theView.addNextListener(new NextButtonListener());
        this.theView.addComboBoxListener(new ComboListener());
        setUpDisplay();
    }

    private void setUpDisplay() {
            theView.disableNextButton(theModel.getCurrentContractNum() < theModel.getContractCount()-1);     
            theView.disablePrevButton(theModel.getCurrentContractNum() > 0);
                
        try {
            if (theModel.foundContracts()) {
                Contract c = theModel.getTheContract() ;
                theView.setContractID(c.getContractID());
                theView.setDestCity(c.getDestCity());
                theView.setOriginCity(c.getOriginCity());
                theView.setOrderItem(c.getOrderItem());
            } else {
                theView.setContractID("???");
                theView.setDestCity("???");
                theView.setOriginCity("???");
                theView.setOrderItem("???");
            }
        } catch (Error ex) {
            System.out.println(ex);
            theView.displayErrorMessage("Error: There was a problem setting the contract.\n" + " Contract number: " + theModel.getCurrentContractNum());
        }
        theView.updateContractViewPanel(theModel.getCurrentContractNum(), theModel.getContractCount());
    }

    class PrevButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (theModel.getCurrentContractNum() == 0) {
                return;
            }
            try {
                theModel.prevContract();
            } catch (Exception ex) {
                System.out.println(ex);
                theView.displayErrorMessage("Error: There was a problem setting a previous contract.");
            }
            setUpDisplay();
        }
    }

    class NextButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (theModel.getCurrentContractNum() == theModel.getContractCount()-1) {
                return;
            }
            try {
                theModel.nextContract();
            } catch (Exception ex) {
                System.out.println(ex);
                theView.displayErrorMessage("Error: There was a problem setting a previous contract.");
            }
            setUpDisplay();
        }
    }
    
    class ComboListener implements ActionListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            System.out.println(e.getItem().toString());
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String selectedCity = e.getItem().toString();
                System.out.println(selectedCity);
                theModel.updateContractList(selectedCity);
            setUpDisplay();
            }
        }
    }
/*
    class BidButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (theModel.getCurrentContractNum() == 0) {
                return;
            }
            try {
                theModel.bidContract();
            } catch (Exception ex) {
                System.out.println(ex);
                theView.displayErrorMessage("Error: There was a problem setting a previous contract.");
            }
            setUpDisplay();
        }
    }
*/
}
