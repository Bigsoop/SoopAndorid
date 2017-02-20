package com.example.sangh.soop;

/**
 * Created by sangh on 2017-02-10.
 */

public class Constant {

    public static final String ADMIN_EMAIL ="asdfasdf@naver.com";

    public static final int MENU_MAIN = 0;
    public static final int MENU_BEST = 1;
    public static final int MENU_MAIL = 2;
    public static final int MENU_SETTING = 3;
    public static final int MENU_INFO = 4;
    public static final int MENU_LOGOUT = 5;


    public static final String[] menuNameList = {"메   인", "베스트 50","문   의","설   정","앱정보","로그아웃"};
    public static final int[] menuIconList = {R.drawable.selector_ic_home,R.drawable.selector_ic_best,R.drawable.selector_ic_mail,R.drawable.selector_ic_setting,R.drawable.selector_ic_info,R.drawable.selector_ic_logout};


    public static final String DOMAIN ="http://130.211.204.198/";
    public static final String MAIN = DOMAIN+"main.php";
    public static final String BEST = DOMAIN+"best.php";
}
