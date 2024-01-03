package com.hotel.dto;

import java.util.Date;

public record ReservationDTO(Long personId, Long roomId, Date start, Date end) {}
