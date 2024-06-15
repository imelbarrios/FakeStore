package com.store.fake.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class Autorizations {

    private static GeneralResponse generalResponse;

    private Autorizations(){}

    public static ResponseEntity<GeneralResponse> getRespondeDenied(String message){
        return new ResponseEntity<>(new GeneralResponse(CommonConstants.HTTP_CODE_UNAUTHORIZED,message,null,  LocalDateTime.now()), HttpStatus.UNAUTHORIZED);
    }

    public static ResponseEntity<GeneralResponse> getResponseServerError(String message){
        return new ResponseEntity<>(new GeneralResponse(CommonConstants.HTTP_CODE_ERROR_INTERNAL_SERVICE, message,null, LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<GeneralResponse> getResponseSuccess(Object obj,String message){
        return new ResponseEntity<>(new GeneralResponse(CommonConstants.HTTP_CODE_SUCCESS, message,obj, LocalDateTime.now()), HttpStatus.OK);
    }

    public static ResponseEntity<GeneralResponse> getResponsenNoContent(Object obj,String message){
        return new ResponseEntity<>(new GeneralResponse(CommonConstants.HTTP_CODE_NO_CONTENT, message,obj, LocalDateTime.now()), HttpStatus.NO_CONTENT);
    }

    public static ResponseEntity<GeneralResponse> getResponsenBadRequest(Object obj,String message){
        return new ResponseEntity<>(new GeneralResponse(CommonConstants.HTTP_CODE_BAD_REQUEST, message,obj, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    public static GeneralResponse getGeneralResponse() {
        return generalResponse;
    }

    public static void setGeneralResponse(GeneralResponse generalResponse) {
        Autorizations.generalResponse = generalResponse;
    }
}
