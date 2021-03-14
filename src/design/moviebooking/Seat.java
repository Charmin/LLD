package design.moviebooking;

import design.moviebooking.enums.SeatStatus;

//this is element of a grid
public class Seat {
    private String id;
    private int rowNo;
    private int seatNo;

    private SeatStatus seatStatus;

    public String getId() {
        return id;
    }

    public Seat(String id, int rowNo, int seatNo) {
        this.id = id;
        this.rowNo = rowNo;
        this.seatNo = seatNo;
        this.seatStatus = SeatStatus.AVAILABLE;
    }

    public Seat(String id, int rowNo, int seatNo, SeatStatus status) {
        this.id = id;
        this.rowNo = rowNo;
        this.seatNo = seatNo;
        this.seatStatus = status;
    }

    //Ideally this en
    public void changeStatus(SeatStatus status) {
        this.seatStatus = status;
    }
}
