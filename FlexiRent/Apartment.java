package FlexiRent;

class Apartment extends RentalProperty {

    Apartment(String propertyId, String streetNum, String streetName, String suburb, int numOfBedrooms) {
        super(propertyId, streetNum, streetName, suburb);
        super.numberOfBedrooms = numOfBedrooms;
        super.typeOfProperty = "Apartment";
        if (numOfBedrooms == 1) {
            super.dailyRental = 143;
        }
        else if (numOfBedrooms == 2) {
            super.dailyRental = 210;
        }
        else if (numOfBedrooms == 3) {
            super.dailyRental = 319;
        }
        super.lateFee = super.dailyRental * 1.15;
    }
}
