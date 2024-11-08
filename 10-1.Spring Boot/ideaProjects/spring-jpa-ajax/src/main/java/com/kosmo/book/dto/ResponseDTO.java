package com.kosmo.book.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseDTO {
    private String status; // success, fail
    private String message;


}
