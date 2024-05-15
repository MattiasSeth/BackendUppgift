package com.example.backenduppgift.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomEventDTO {

    public String type;

    @JsonProperty("TimeStamp")
    public Date timeStamp;

    @JsonProperty("RoomNo")
    public int roomNo;

    @JsonProperty("CleaningByUser")
    public String cleaningByUser;
}
