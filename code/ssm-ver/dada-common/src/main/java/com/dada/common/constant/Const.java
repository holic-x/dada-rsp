package com.dada.common.constant;

/**
 * @author KangMiao
 * @version 1.0
 * @ClassName：Const
 * @Description: 常量类
 * @Date 2016年7月10日 下午9:04:39
 */
public class Const {

    public static String ADMINNAME="";
    /**
     * session中存放验证码的变量名
     */
    public static final String SESSION_SECURITY_CODE = "sessionSecCode";
    /**
     * session中存放当前用户对象变量名
     */
    public static final String SESSION_USER = "sessionUser";
    /**
     * session中存放当前用户登录时间变量名
     */
    public static final String SESSION_LOGIN_TIME = "user_login_time";

    /**
     * session中存放接口登录用户生成的token变量名
     */
    public static final String SESSION_USER_TOKEN = "token";
    /**
     * 当前用户的菜单变量名
     */
    public static final String SESSION_menuList = "menuList";
    /**
     * 当前用户名变量名
     */
    public static final String SESSION_USERNAME = "USERNAME";
    /**
     * 租户对应的数据源对象变量名
     */
    public static final String USER_DATASOURCE = "USER_DATASOURCE";
    /**
     * 租户的机器人参数缓存对象变量名
     */
    public static final String USER_ROBOT_PARAMS_CACHE = "USER_ROBOT_PARAMS_CACHE";
    /**
     * vue前端登录地址
     */
    public static final String LOGIN_PAGE = "/index.html#/login";
    /**
     * 不拦截静态资源访问路径（正则）
     */
    public static final String NO_INTERCEPTOR_PATH = ".*/((static)|(resources)|(uploadFiles)).*";
    /**
     * 每页显示最大数100条
     */
    public static final Integer MAX_PAGE_SIZE = 100;
    /**
     * 是否开启多数据源配置项，在uicsr.properties中配置，默认值true，开启，为多数据源
     */
    public static String IS_OPEN_MULTI_DATASOURCE = "true";
    /**
     * 是否开启登录验证码验证，在uicsr.properties中配置，默认值true，开启
     */
    public static String IS_OPEN_CODE = "true";
    /**
     * 系统显示名称，在uicsr.properties中配置
     */
    public static String SYSTEM_NAME = "智能机器人管理平台";
    /**
     * 系统是否做集群部署，默认为单服务部署：true为集群部署，否则为单台服务部署
     */
    public static boolean clusterDeploymentFlag = true;
    /**
     * 项目部署的真实路径地址
     */
    public static String webRealPath;
    /**
     * 系统使用数据库类型别名，默认为oracle数据库
     */
    public static String jdbcDialect = "";

    /**
     * 表名前缀
     */
    public static final String TABLE_PREFX = "OBS_DATA_";
    /**
     * 分批导入，每次导入条数
     */
    public static final Integer BATCHNUM = 100;

    /**
     * 全局反问库id
     */
    public static final String UICSR_FAQ_TYPE = "-21";

    /**
     * 外呼问题在机器人侧
     */
    public static final String UICSR_OUTCALL_TYPE = "-22";

    public static String DB_USER = "";

    public static String getIS_OPEN_MULTI_DATASOURCE() {
        return IS_OPEN_MULTI_DATASOURCE;
    }

    public static void setIS_OPEN_MULTI_DATASOURCE(String iS_OPEN_MULTI_DATASOURCE) {
        IS_OPEN_MULTI_DATASOURCE = iS_OPEN_MULTI_DATASOURCE;
    }

    public static String getIS_OPEN_CODE() {
        return IS_OPEN_CODE;
    }

    public static void setIS_OPEN_CODE(String iS_OPEN_CODE) {
        IS_OPEN_CODE = iS_OPEN_CODE;
    }

    public static String getSYSTEM_NAME() {
        return SYSTEM_NAME;
    }

    public static void setSYSTEM_NAME(String sYSTEM_NAME) {
        SYSTEM_NAME = sYSTEM_NAME;
    }

    public static String getWebRealPath() {
        return webRealPath;
    }

    public static void setWebRealPath(String webRealPath) {
        Const.webRealPath = webRealPath;
    }

    public static String getADMINNAME() {
        return ADMINNAME;
    }

    public static void setADMINNAME(String ADMINNAME) {
        Const.ADMINNAME = ADMINNAME;
    }

    public static String getDbUser() {
        return DB_USER;
    }

    public static void setDbUser(String dbUser) {
        DB_USER = dbUser;
    }
}
