package com.frankmoley.lil.learningspring.webservice;

import com.frankmoley.lil.learningspring.business.ReservationService;
import com.frankmoley.lil.learningspring.business.RoomReservation;
import com.frankmoley.lil.learningspring.data.Guest;
import com.frankmoley.lil.learningspring.data.Room;
import com.frankmoley.lil.learningspring.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtils;
    private final ReservationService  reservationService;

    public WebserviceController(DateUtils dateUtils, ReservationService reservationService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
    }
    @RequestMapping(path = "/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value = "date", required = false)String dateString){
        Date date = this.dateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationsForDate(date);

    }
    @RequestMapping(path = "/guests", method = RequestMethod.GET)
    public List<Guest> getAllGuests(){
        return this.reservationService.getHotelGuests();
    }
    @PostMapping("/guests")
    @ResponseStatus(HttpStatus.CREATED)
    public Guest getAllGuests(@RequestBody Guest guest){
        return this.reservationService.addGuest(guest);
    }
    @GetMapping("/rooms")
    public List<Room> getAllRooms(){
        return reservationService.getHotelRooms();
    }
}
