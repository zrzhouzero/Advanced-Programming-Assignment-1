package FlexiRent;
import java.util.ArrayList;

public abstract class RentalProperty {
    private String propertyId;
    private String streetNum;
    private String streetName;
    private String suburb;
    private boolean isAvailable;
    protected String typeOfProperty = "New Property";
    private ArrayList<RentalRecord> rentalRecord = new ArrayList<>();
    private String currentCustomer;
    protected int numberOfBedrooms;
    protected boolean underMaintenance = false;
    protected double dailyRental;
    protected double lateFee;

    RentalProperty(String propertyId, String streetNum, String streetName, String suburb) {
        this.propertyId = propertyId;
        this.streetNum = streetNum;
        this.streetName = streetName;
        this.suburb = suburb;
        this.isAvailable = true;
        this.currentCustomer = null;
        this.numberOfBedrooms = 0;
        this.dailyRental = 0;
        this.lateFee = 0;
    }

    double getDailyRental() {
        return this.dailyRental;
    }

    double getLateFee() {
        return this.lateFee;
    }

    String getPropertyId() {
        return this.propertyId;
    }

    String getStreetNum() {
        return this.streetNum;
    }

    String getStreetName() {
        return this.streetName;
    }

    public String getSuburb() {
        return this.suburb;
    }

    void switchOnProperty() {
        this.isAvailable = true;
    }

    void switchOffProperty() {
        this.isAvailable = false;
    }

    boolean getIsAvailable()
    {
        return this.isAvailable;
    }

    String getTypeOfProperty() {
        return this.typeOfProperty;
    }

    void rentProperty(String customerId) {
        this.isAvailable = false;
        this.currentCustomer = customerId;
    }

    void printRentalRecord() {
        int end = 0;
        int i = this.rentalRecord.size();
        i -= 1;
        int num = 0;
        while (i >= end && num <= 10) {
            System.out.println("---------- " + (num + 1) + " ----------");
            this.rentalRecord.get(i).printRecord();
            i--;
            num++;
        }
        System.out.println();
    }

    void addRentalRecord(RentalRecord rr) {
        this.rentalRecord.add(rr);
    }

    String getCurrentCustomer() {
        return this.currentCustomer;
    }

    void showPropertyStatus() {
        System.out.println("Property ID: " + this.propertyId);
        System.out.println("Address: " + this.streetNum + " " + this.streetName + " " + this.suburb);
        System.out.println("Type: " + this.typeOfProperty);
        System.out.println("Bedroom: " + this.numberOfBedrooms);
        if (this.isAvailable) {
            System.out.println("Status: Available");
        }
        else {
            System.out.println("Status: Unavailable");
        }
    }

    void rtnProperty() {
        this.currentCustomer = null;
        this.isAvailable = true;
        this.showPropertyStatus();
    }

    /*
    public int searchRecords(String targetId) {
        int i;
        int numberOfRecords = this.rentalRecord.size();
        boolean ifFound = false;
        for (i = 0; i < numberOfRecords; i++) {
            if (targetId.toUpperCase().equals(this.rentalRecord.get(i).getRecordId().toUpperCase())) {
                ifFound = true;
                break;
            }
        }
        if (!ifFound) {
            System.out.println("Record does not exist!");
            i = -1;
            return i;
        }
        else {
            return i;
        }
    }
    */

    RentalRecord showLastRecord() {
        int numberOfRecords = this.rentalRecord.size();
        return this.rentalRecord.get(numberOfRecords - 1);
    }

    void propertyMaintenance() {
        boolean ifCanBeMaintained = this.isAvailable;
        if (ifCanBeMaintained) {
            this.switchOffProperty();
            System.out.println(this.getTypeOfProperty() + " " + this.getPropertyId() + " is now under maintenance.");
            this.underMaintenance = true;
        }
        else {
            System.out.println(this.getTypeOfProperty() + " " + this.getPropertyId() + " is now being rented. Maintenance failed.");
        }
    }

    public void finishMaintenance() {
        if (!this.underMaintenance) {
            System.out.println(this.getTypeOfProperty() + " " + this.getPropertyId() + " is not under maintenance, please check your input.");
        }
        else {
            this.switchOnProperty();
            System.out.println(this.getTypeOfProperty() + " " + this.getPropertyId() + " has all maintenance completed and ready for rent.");
            this.underMaintenance = false;
        }
    }

    /*
    public void popRentalRecord() {
        this.rentalRecord.remove(0);
        int i = 1;
        int t = 0;
        int end = this.rentalRecord.size();
        while (i < end) {
            RentalRecord temp = this.rentalRecord.get(i);
            this.rentalRecord.remove(i);
            this.rentalRecord.set(t, temp);
            i++;
            t++;
        }
    }
    */
}