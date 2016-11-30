package net.therap.hyperbee.utils.constant;

/**
 * @author duity
 * @author rumman
 * @author rayed
 * @since 11/23/16.
 */

public interface Url {

    //User constants
    String ROOT_URL = "/";
    String LOGIN_URL = "/login";
    String LOGIN_VIEW = "user/login";
    String LOGOUT_URL = "/logout";
    String SIGN_UP_URL = "/signUp";
    String SIGN_UP_VIEW = "user/signUp";

    String USER_DASHBOARD_VIEW = "dashboard";
    String USER_DASHBOARD_URL = "/user/dashboard";

    //Profile Constant
    String PROFILE_URL = "/profile";
    String PROFILE_EDIT_URL = "/edit";
    String CREATE_PROFILE_URL = "profile/createprofile";
    String USER_PROFILE_URL = "/user";
    String VIEW_PROFILE_URL = "profile/viewprofile";

    //Image Constants
    String PROFILE_IMAGE_URL = "/image/{imagePath}";
    String COVER_IMAGE_URL = "/cover/{coverImage}";

    //Stalk Therap Constant
    String SEARCH_URL = "/search";
    String PROFILE_SEARCH_URL = "profile/searchprofile";
    String STALK_PROFILE_URL = "/stalk/{username}";
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
    String NOTICE_BASE_URL = "/notice";
    String NOTICE_LIST_URL = "/list";
    String NOTICE_ADD_URL = "/add";
    String NOTICE_UPDATE_URL = "/update";
    String NOTICE_DELETE_URL = "/delete";
    // Notice Constants
    String CONFERENCE_ROOM_BASE_URL = "/conference";

    String CONFERENCE_ROOM_LIST_URL = "/list";
    String CONFERENCE_ROOM_ADD_URL = "/add";
    String CONFERENCE_ROOM_UPDATE_URL = "/update";
    String CONFERENCE_ROOM_DELETE_URL = "/delete";

    //Resource Constants
    String RESOURCE_STYLE = "/css/";
    String RESOURCE_SCRIPT = "/js/";
    String RESOURCE_FONT = "/fonts/";
    String RESOURCE_IMAGES = "/images/";

    //Common Constants
    String ACCESS_DENIED_URL = "/denied";

    //Hive Constants
    String HIVE_CREATE_URL = "/create";
    String HIVE_VIEW_URL = "/show/{id}";
    String HIVE_ADD_USER_URL = "/insertuser/{hiveId}";
    String HIVE_REMOVE_USER_URL = "/removeuser/{hiveId}";
    String HIVE_ADD_POST_URL = "/post/{hiveId}";

    //Buzz Constants
    String BUZZ_BASE_URL = "/buzz";
    String BUZZ_VIEW_URL = "/buzzList";
    String BUZZ_CREATE_URL = "/sendBuzz";
    String BUZZ_FLAG_URL = "/flagBuzz";
    String BUZZ_DEACTIVATE_URL = "/deactivateBuzz";
    String BUZZ_PIN_URL = "/pinBuzz";
    String BUZZ_HISTORY_URL = "/buzzHistory";
}
