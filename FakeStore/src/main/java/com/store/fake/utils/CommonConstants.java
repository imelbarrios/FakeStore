package com.store.fake.utils;

public final class CommonConstants {

    private CommonConstants() {
    }


    /*
    Status code
     */
    public static final int HTTP_CODE_UNAUTHORIZED = 401;
    public static final int HTTP_CODE_ERROR_INTERNAL_SERVICE = 500;
    public static final int HTTP_CODE_SUCCESS = 200;
    public static final int HTTP_CODE_NO_CONTENT = 204;
    public static final int HTTP_CODE_BAD_REQUEST = 400;

    public static final String HEADER_PREFIX = "Bearer ";
    public static final String HEADER ="Authorization";
    public static final String EXPIRED_TOKEN  = "Expired Token";

    /**
     * Messages
     */

    public  static  final String APPROVED_CREDENTIALS = "Approved credentials";


}
