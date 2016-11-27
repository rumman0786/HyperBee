package net.therap.hyperbee.utils.constant;

/**
 * @author duity
 * @author rumman
 * @since 11/23/16.
 */

public interface Url {

    String ROOT_URL = "/";

    String LOGIN_URL = "/login";
    String LOGOUT_URL = "/logout";

    String SIGN_UP_URL = "/signup";

    String USER_URL = "/user";

    String USER_DASHBOARD_URL = "/user/dashboard";

    //Profile Constant
    String PROFILE_URL = "/profile";
    String CREATE_PROFILE_URL = "profile/createprofile";
    String USER_PROFILE_URL = "/user/profile";
    String VIEW_PROFILE_URL = "profile/viewprofile";

    //Stalk Therap Constant
    String SEARCH_URL = "/search";
    String PROFILE_SEARCH_URL = "profile/searchprofile";
    String STALK_PROFILE_URL = "/stalk/profile/{username}";
    String PROFILE_STALK_URL = "profile/stalkprofile";

    //Notes Constant
    String NOTE_VIEW_URL = "/user/notes";
    String NOTE_ADD_URL = "/user/note/add";
    String NOTE_SAVE_URL = "/user/note/save";
    String NOTE_VIEW_ALL = "note/notes";
    String NOTE_ADD_VIEW = "note/note_form";
    String NOTE_DELETE_URL = "/note/delete/{id}";

    String SUCCESS_VIEW = "success";

    // Notice Constants
    String NOTICE_BASE_URL= "/notice";
    String NOTICE_LIST_URL= "/list";
    String NOTICE_ADD_URL = "/add";
    String NOTICE_UPDATE_URL = "/update";
    String NOTICE_DELETE_URL = "/delete";

    // Notice Constants
    String CONFERENCE_ROOM_BASE_URL= "/conference";
    String CONFERENCE_ROOM_LIST_URL= "/list";
    String CONFERENCE_ROOM_ADD_URL = "/add";
    String CONFERENCE_ROOM_UPDATE_URL = "/update";
    String CONFERENCE_ROOM_DELETE_URL = "/delete";
}
