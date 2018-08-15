package FlexiRent;
import utilities.DateTime;
import java.util.*;

public class Console {
    private ArrayList<RentalProperty> properties = new ArrayList<>();

    private static DateTime currentTimeConvert(String datetime) {
        DateTime dateTime;
        int day = Integer.valueOf(datetime.substring(8,10));
        int month = Integer.valueOf(datetime.substring(5,7));
        int year = Integer.valueOf(datetime.substring(0,4));
        dateTime = new DateTime(day,month,year);
        return dateTime;
    }

    private static DateTime inputTimeConvert(String datetime) {
        DateTime dateTime;
        int day = Integer.valueOf(datetime.substring(0,2));
        int month = Integer.valueOf(datetime.substring(3,5));
        int year = Integer.valueOf(datetime.substring(6,10));
        dateTime = new DateTime(day,month,year);
        return dateTime;
    }

    private int findProperty(String targetid) {
        int i;
        int numberOfProperties = properties.size();
        boolean ifFound = false;
        for (i = 0; i < numberOfProperties; i++) {
            if (properties.get(i).getPropertyId().toUpperCase().equals(targetid.toUpperCase())) {
                ifFound = true;
                break;
            }
        }
        if (!ifFound) {
            System.out.println("Property does not exist!");
            i = -1;
            return i;
        }
        else {
            return i;
        }
    }

    private static void showMenu() {
        System.out.println("***** FLEXIRENT SYSTEM MENU *****");
        System.out.println();
        System.out.println("Add Property:               1");
        System.out.println("Rent Property:              2");
        System.out.println("Return Property:            3");
        System.out.println("Property Maintenance:       4");
        System.out.println("Complete Maintenance:       5");
        System.out.println("Display All Properties:     6");
        System.out.println("Exit Programme:             7");
        System.out.println("Enter your choice:");
    }

    public static void main(String args[]) {
        Console admin = new Console();
        Scanner sc = new Scanner(System.in);
        boolean ifEnd = false;
        while (!ifEnd) {
            int choice;
            showMenu();
            choice = Integer.parseInt(sc.nextLine());

            if (choice == 1) {
                String propertyId;
                String streetNum;
                String streetName;
                String suburb;
                int propertyTypeNum = 0;
                int numberOfBedrooms = 0;
                while (propertyTypeNum != 1 && propertyTypeNum != 2) {
                    System.out.println("Choose the type of the property:");
                    System.out.println("1. Apartment");
                    System.out.println("2. Premium Suite");
                    propertyTypeNum = Integer.parseInt(sc.nextLine());
                    if (propertyTypeNum != 1 && propertyTypeNum != 2) {
                        System.out.println("Please input 1 or 2");
                    }
                }
                System.out.println("Input street number:");
                streetNum = sc.nextLine();
                System.out.println("Input street name:");
                streetName = sc.nextLine();
                System.out.println("Input suburb:");
                suburb = sc.nextLine();
                if (propertyTypeNum == 1) {
                    while (numberOfBedrooms <= 0 || numberOfBedrooms >= 4) {
                        System.out.println("Input the number of bedrooms (1-3):");
                        numberOfBedrooms = Integer.parseInt(sc.nextLine());
                        if (numberOfBedrooms <= 0 || numberOfBedrooms >= 4) {
                            System.err.println("Input a valid number of bedrooms");
                        }
                    }
                }
                else {
                    numberOfBedrooms = 3;
                }
                if (propertyTypeNum == 1) {
                    propertyId = "A_" + streetNum + streetName.toUpperCase().substring(0,2) + suburb.toUpperCase().substring(0,2);
                    admin.properties.add(new Apartment(propertyId, streetNum, streetName, suburb, numberOfBedrooms));
                    System.out.println("Apartment added.");
                }
                else {
                    propertyId = "S_" + streetNum + streetName.toUpperCase().substring(0,2) + suburb.toUpperCase().substring(0,2);
                    admin.properties.add(new PremiumSuite(propertyId, streetNum, streetName, suburb));
                    System.out.println("Premium Suite added.");
                }
            }

            else if (choice == 2) {
                System.out.println("Currently available property ID: ");
                int manuLength = admin.properties.size();
                for (int i = 0; i < manuLength; i++) {
                    if (admin.properties.get(i).getIsAvailable()) {
                        System.out.println(admin.properties.get(i).getPropertyId());
                    }
                }
                String epid;
                System.out.println("Enter property ID:");
                epid = sc.nextLine();
                int targetIndex = admin.findProperty(epid);
                if (targetIndex == -1) {
                    System.out.println("Please enter a valid property ID!");
                }
                else if (targetIndex >= 0) {
                    if (!admin.properties.get(targetIndex).getIsAvailable()) {
                        System.out.println(admin.properties.get(targetIndex).getTypeOfProperty() + " " + admin.properties.get(targetIndex).getPropertyId() + " could not be rented currently");
                    }
                    else {
                        String recordId;
                        String customerId;
                        String parseRentDate;
                        int rentalLength;
                        System.out.println("Enter customer ID:");
                        customerId = sc.nextLine();
                        System.out.println("Rent date (dd/mm/yyyy):");
                        parseRentDate = sc.nextLine();
                        System.out.println("How many days?:");
                        rentalLength = Integer.parseInt(sc.nextLine());
                        int actDay = inputTimeConvert(parseRentDate).getWeekDay();
                        if (admin.properties.get(targetIndex) instanceof Apartment) {
                            if (actDay >= 1 && actDay <= 5) {
                                if (rentalLength >= 2 && rentalLength <= 28) {
                                    System.out.println("Successfully rented!");
                                    admin.properties.get(targetIndex).rentProperty(customerId);
                                    System.out.println(admin.properties.get(targetIndex).getTypeOfProperty() + " " + admin.properties.get(targetIndex).getPropertyId() + " is now rented by customer " + customerId);
                                    DateTime rentDate = inputTimeConvert(parseRentDate);
                                    recordId = admin.properties.get(targetIndex).getPropertyId() + customerId + rentDate.getEightDigitDate();
                                    admin.properties.get(targetIndex).addRentalRecord(new RentalRecord(recordId, rentDate, rentalLength));
                                }
                                else if (rentalLength <= 1) {
                                    System.out.println("You need to rent for more days! Reservation failed.");
                                }
                                else {
                                    System.out.println("You need to rent for less days! Reservation failed.");
                                }
                            }
                            else if (actDay >= 6 && actDay <= 7) {
                                if (rentalLength >= 3 && rentalLength <= 28) {
                                    System.out.println("Successfully rented!");
                                    admin.properties.get(targetIndex).rentProperty(customerId);
                                    System.out.println(admin.properties.get(targetIndex).getTypeOfProperty() + " " + admin.properties.get(targetIndex).getPropertyId() + " is now rented by customer " + customerId);
                                    DateTime rentDate = inputTimeConvert(parseRentDate);
                                    recordId = admin.properties.get(targetIndex).getPropertyId() + customerId + rentDate.getEightDigitDate();
                                    admin.properties.get(targetIndex).addRentalRecord(new RentalRecord(recordId, rentDate, rentalLength));
                                }
                                else if (rentalLength <= 2) {
                                    System.out.println("You need to rent for more days! Reservation failed.");
                                }
                                else {
                                    System.out.println("You need to rent for less days! Reservation failed.");
                                }
                            }
                        }
                        else if (admin.properties.get(targetIndex) instanceof PremiumSuite) {
                            if (rentalLength >= 1) {
                                DateTime nt = ((PremiumSuite) admin.properties.get(targetIndex)).getNextMaintenanceDate();
                                if (DateTime.diffDays(nt, inputTimeConvert(parseRentDate)) > rentalLength) {
                                    System.out.println("Successfully rented!");
                                    admin.properties.get(targetIndex).rentProperty(customerId);
                                    System.out.println(admin.properties.get(targetIndex).getTypeOfProperty() + " " + admin.properties.get(targetIndex).getPropertyId() + " is now rented by customer " + customerId);
                                    DateTime rentDate = inputTimeConvert(parseRentDate);
                                    recordId = admin.properties.get(targetIndex).getPropertyId() + customerId + rentDate.getEightDigitDate();
                                    admin.properties.get(targetIndex).addRentalRecord(new RentalRecord(recordId, rentDate, rentalLength));
                                }
                                else {
                                    System.out.println(nt.getFormattedDate() + " is the maintenance day.");
                                    System.out.println("You need to input less than " + (DateTime.diffDays(nt, inputTimeConvert(parseRentDate)) - 1) + " days. Reservation failed.");
                                }
                            }
                            else {
                                System.out.println("You need to rent for more days! Reservation failed.");
                            }
                        }
                    }
                }
            }

            else if (choice == 3) {
                String targetId;
                System.out.println("Enter property ID:");
                targetId = sc.nextLine();
                int i = admin.findProperty(targetId);
                if (i < 0) {
                    System.out.println("Input a valid property ID!");
                }
                else if (admin.properties.get(i).getIsAvailable()) {
                    System.out.println("This property is not being rented!");
                }
                else if (!admin.properties.get(i).getIsAvailable()) {
                    String rtDate;
                    rtDate = DateTime.getCurrentTime();
                    System.out.println("Return Date: " + rtDate);
                    System.out.println(admin.properties.get(i).getTypeOfProperty() + " " + admin.properties.get(i).getPropertyId() + " is returned by customer " + admin.properties.get(i).getCurrentCustomer());
                    admin.properties.get(i).rtnProperty();
                    DateTime rtnDate = currentTimeConvert(rtDate);
                    admin.properties.get(i).showLastRecord().setRtnDate(rtnDate);
                    double feeRate = admin.properties.get(i).getDailyRental();
                    double lateFeeRate = admin.properties.get(i).getLateFee();
                    admin.properties.get(i).showLastRecord().setRentalFee(feeRate);
                    admin.properties.get(i).showLastRecord().setLateFee(lateFeeRate);
                    System.out.println("RENTAL RECORD");
                    admin.properties.get(i).showLastRecord().printRecord();
                }
            }

            else if (choice == 4) {
                String mtId;
                System.out.println("Enter property ID:");
                mtId = sc.nextLine();
                int index = admin.findProperty(mtId);
                if (index >= 0) {
                    admin.properties.get(index).propertyMaintenance();
                }
            }

            else if (choice == 5) {
                String fmtId;
                System.out.println("Enter property ID:");
                fmtId = sc.nextLine();
                int index = admin.findProperty(fmtId);
                if (index >= 0) {
                    admin.properties.get(index).finishMaintenance();
                }
            }

            else if (choice == 6) {
                int idx;
                int numberOfProperties = admin.properties.size();
                for (idx = 0; idx < numberOfProperties; idx++) {
                    admin.properties.get(idx).showPropertyStatus();
                    System.out.println("-------------------------");
                    System.out.println("Recent 10 records for " + admin.properties.get(idx).getPropertyId());
                    admin.properties.get(idx).printRentalRecord();
                    System.out.println("-------------------------");
                }
            }

            else if (choice == 7) {
                System.out.println("System Terminated!");
                ifEnd = true;
            }

            else if (choice == 8) {
                System.out.println("Input a date (dd/mm/yyyy):");
                String newDate = sc.nextLine();
                DateTime dateTime = inputTimeConvert(newDate);
                System.out.println(dateTime.getWeekDay());
            }
        }
        System.out.println("Thank you for using FlexiRent!");
        System.exit(0);
    }
}
