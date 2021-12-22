package com.example.mym.server;

public class URLs {
    public static final String BASE_URL = "http://10.0.2.2:3000/";
    public static final String CREATE_NEW_POST = BASE_URL +"user/post/new";
    public static final String GET_ALL_USERS = BASE_URL + "user/friends";
    public static final String SIGN_IN = BASE_URL + "auth/signin";
    public static final String GET_ALL_POSTS = BASE_URL +"post/";
    public static final String VALIDATE_LIKE_WITH_USER = BASE_URL + "user/post/validateLikeWithUser";
    public static final String CHANGE_FRIEND_RELATION = BASE_URL + "user/changeFriendRelation";
    public static final String ADD_NEW_COMMENT = BASE_URL +"user/post/comment";
    public static final String CREATE_NEW_USER = BASE_URL +"auth/signup";
    public static final String GET_USER_POSTS = BASE_URL + "user/post/posts";
    public static final String DELETE_POST = BASE_URL + "user/post/delete";
}
