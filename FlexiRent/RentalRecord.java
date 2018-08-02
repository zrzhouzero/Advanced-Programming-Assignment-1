package FlexiRent;
import utilities.DateTime;

public class RentalRecord {
    private String recordId;
    private DateTime rentDate;
    private DateTime estRtnDate;
    private DateTime actRtnDate;
    private double rentalFee;
    private double lateFee;

    public RentalRecord(String recordId, DateTime rentDate, DateTime estRtnDate, DateTime actRtnDate) {
        this.recordId = recordId;
        this.rentDate = rentDate;
        this.estRtnDate = estRtnDate;
        this.actRtnDate = actRtnDate;
    }

    RentalRecord(String recordId, DateTime rentDate, int rentalLength) {
        this.recordId = recordId;
        this.rentDate = rentDate;
        this.estRtnDate = new DateTime(rentDate, rentalLength);
        this.actRtnDate = null;
    }

    void setRtnDate(DateTime rtnDate) {
        this.actRtnDate = rtnDate;
    }

    String getRecordId() {
        return this.recordId;
    }

    void setRentalFee(double feeRate) {
        int days = DateTime.diffDays(this.estRtnDate, this.rentDate);
        this.rentalFee = feeRate * days;
    }

    void setLateFee(double feeRate) {
        int days = DateTime.diffDays(this.actRtnDate, this.estRtnDate);
        if (days >= 0) {
            this.lateFee = feeRate * days;
        }
        else {
            this.lateFee = 0;
        }
    }

    void printRecord() {
        System.out.println("Record ID: " + this.recordId);
        System.out.println("Rent Date: " + this.rentDate.getFormattedDate());
        System.out.println("Estimated Return Date: " + this.estRtnDate.getFormattedDate());
        if (actRtnDate == null) {
            System.out.println("Actual Return Date: Currently in use.");
        }
        else {
            System.out.println("Actual Return Date: " + this.actRtnDate.getFormattedDate());
        }
        System.out.println("Rental Fee: " + this.rentalFee);
        System.out.println("Late Fee: " + this.lateFee);
    }
}
