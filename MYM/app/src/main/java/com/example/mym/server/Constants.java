package com.example.mym.server;

public class Constants {
    // TODO: 12/14/2021 put all server endpoints
    public static final String BASE_URL = "http://10.0.2.2:3000/";
    public static final String CREATE_NEW_POST = BASE_URL +"post/";
    public static final String GET_ALL_USERS = BASE_URL + "user/friends";
    public static final String SIGN_IN = BASE_URL + "auth/signin";
    public static final String GET_ALL_POSTS = BASE_URL +"post/";
    public static final String VALIDATE_LIKE_WITH_USER = BASE_URL +"user/post/validateLikeWithUser";

    public static final String ADD_NEW_COMMENT = BASE_URL +"user/post/comment";
    public static final String CREATE_NEW_USER = BASE_URL +"auth/signup";
}
