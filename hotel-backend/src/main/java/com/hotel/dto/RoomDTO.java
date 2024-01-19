package com.hotel.dto;

import com.hotel.entity.room.RoomType;
import jakarta.validation.constraints.NotNull;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

public record RoomDTO (Long id,
                       @NotNull RoomType roomType,
                       @NotNull String name, @NotNull String description,
                       @NotNull String size,
                       @NotNull double price,
                       @NotNull String imageUrl,
                       ByteArrayResource image){
}
