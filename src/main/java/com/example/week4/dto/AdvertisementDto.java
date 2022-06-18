package com.example.week4.dto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AdvertisementDto {

    private String title;

    private String detailedMessage;

    private Long price;

}
