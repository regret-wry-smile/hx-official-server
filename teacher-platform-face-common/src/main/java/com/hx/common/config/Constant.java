package com.hx.common.config;

public class Constant {
    //演示系统账户
    public static String DEMO_ACCOUNT = "test";
    //自动去除表前缀
    public static String AUTO_REOMVE_PRE = "true";
    //停止计划任务
    public static String STATUS_RUNNING_STOP = "stop";
    //开启计划任务
    public static String STATUS_RUNNING_START = "start";
    //通知公告阅读状态-未读
    public static String OA_NOTIFY_READ_NO = "0";
    //通知公告阅读状态-已读
    public static int OA_NOTIFY_READ_YES = 1;
    //部门根节点id
    public static Long DEPT_ROOT_ID = 0l;
    //缓存方式
    public static String CACHE_TYPE_REDIS ="redis";

    public static String LOG_ERROR = "error";
    //教室端
    public static String LOGIN_TYPE_CLASSROOM = "classroom";
    //直播端
    public static String LOGIN_TYPE_DIRECTBROADCAST= "directBroadcast";
    //超级管理员
    public static String ROLE_SIGN_ADMIN = "admin";
    //机构管理员
    public static String ROLE_SIGN_ORG_ADMIN = "orgAdmin";
    //助教角色
    public static String ROLE_SIGN_TEAC_ASSISTANT= "teacAssistant";
    //老师角色
    public static String ROLE_SIGN_TEAC= "teac";
    //数据字典 -- 版本管理
    public static String DICT_TYPE_VERSION_MANAGE= "version_manage";
    //所有权限
    public static final String PERMISSION_ALL= "all";
    //校区权限
    public static final String PERMISSION_SCHOOL= "school";

    /**
     * 保存文件所在路径的key，eg.FILE_MD5:1243jkalsjflkwaejklgjawe
     */
    public static final String FILE_MD5_KEY = "FILE_MD5:";
    /**
     * 保存上传文件的状态
     */
    public static final String FILE_UPLOAD_STATUS = "FILE_UPLOAD_STATUS";
    /**
     * 访问本地文件的前缀地址
     */
    public static final String REQUEST_FILE_PREFIX_LOCAL = "/files";
    /**
     * 访问文件服务器的前缀地址
     */
    public static final String REQUEST_FILE_PREFIX_FILE_SERVER = "/file-server/";

}
