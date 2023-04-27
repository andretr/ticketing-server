package com.ridebeep.bdipticketingserver.controller;

import com.ridebeep.bdipticketingserver.model.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tenants/{tenantId}/tickets")
public class TicketController {

    //TODO acknowledge/close ticket -> change Ticket Status
    //TODO reassign users to tickets
    //TODO ticket comments -> get, add, update, delete
    //TODO ticket history
    //TODO Pagination for tickets??

    @GetMapping
    public ResponseEntity<List<Ticket>> returnAllTickets(@PathVariable UUID tenantId) {
        return null;
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<Ticket> getTicket(@PathVariable UUID tenantId, @PathVariable UUID ticketId) {
        return null;
    }

    @PostMapping()
    public ResponseEntity<Ticket> addTicket(@PathVariable UUID tenantId, @Valid @RequestBody Ticket newTicket) {
        return null;
    }

    @PutMapping("/{ticketId}")
    public ResponseEntity<List<Ticket>> updateTicket(@PathVariable UUID tenantId, @PathVariable UUID ticketId, @Valid @RequestBody Ticket newTicket) {
        return null;
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<List<Ticket>> deleteTicket(@PathVariable UUID tenantId, @PathVariable UUID ticketId) {
        return null;
    }



}
