package FlexiRent;
import utilities.DateTime;

class PremiumSuite extends RentalProperty {

    private DateTime lastMaintenanceDate;
    private DateTime nextMaintenanceDate;

    PremiumSuite(String propertyId, String streetNum, String streetName, String suburb) {
        super(propertyId, streetNum, streetName, suburb);
        super.numberOfBedrooms = 3;
        super.typeOfProperty = "Premium Suite";
        super.dailyRental = 554;
        super.lateFee = 662;
        String datetime = DateTime.getCurrentTime();
        DateTime dateTime;
        int day = Integer.valueOf(datetime.substring(8,10));
        int month = Integer.valueOf(datetime.substring(5,7));
        int year = Integer.valueOf(datetime.substring(0,4));
        dateTime = new DateTime(day,month,year);
        this.lastMaintenanceDate = dateTime;
        this.nextMaintenanceDate = new DateTime(dateTime, 10);
    }

    private void setLastMaintenanceDate(String lastDate) {
        DateTime dateTime;
        int day = Integer.valueOf(lastDate.substring(8,10));
        int month = Integer.valueOf(lastDate.substring(5,7));
        int year = Integer.valueOf(lastDate.substring(0,4));
        dateTime = new DateTime(day,month,year);
        this.lastMaintenanceDate = dateTime;
        this.nextMaintenanceDate = new DateTime(dateTime, 10);
    }

    DateTime getNextMaintenanceDate() {
        return this.nextMaintenanceDate;
    }

    @Override
    public void finishMaintenance() {
        if (!super.underMaintenance) {
            System.out.println(this.getTypeOfProperty() + " " + this.getPropertyId() + " is not under maintenance, please check your input.");
        }
        else {
            this.switchOnProperty();
            String crtTime = DateTime.getCurrentTime();
            this.setLastMaintenanceDate(crtTime);
            System.out.println("Maintenance completion date (dd/mm/yyyy):" + this.lastMaintenanceDate.getFormattedDate());
            System.out.println(this.getTypeOfProperty() + " " + this.getPropertyId() + " has all maintenance completed and ready for rent.");
            super.underMaintenance = false;
        }
    }

    @Override
    void showPropertyStatus() {
        System.out.println("Property ID: " + super.getPropertyId());
        System.out.println("Address: " + super.getStreetNum() + " " + super.getStreetName() + " " + super.getSuburb());
        System.out.println("Type: " + this.typeOfProperty);
        System.out.println("Bedroom: " + this.numberOfBedrooms);
        if (super.getIsAvailable()) {
            System.out.println("Status: Available");
        }
        else {
            System.out.println("Status: Unavailable");
        }
        System.out.println("Last Maintenance Date: " + this.lastMaintenanceDate);
        System.out.println("Next Maintenance Date: " + this.nextMaintenanceDate);
    }
}