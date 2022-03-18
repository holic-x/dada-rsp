package com.dada.common.constant;

public class ConstantUtils {
    // 定义用户登录密码加密秘钥(可以使用CrytoUtil工具类默认秘钥)
    public final static String PASSWORD_SECRET_KEY = "dada";
	
	// 默认没有查找条件的标识
	public final static String DEFAULT_CHECK_CONDITION = "pass";

    // 用户状态：启用
    public final static String USER_STATE_ENABLE = "0";
    public final static String USER_STATE_ENABLE_CONTEXT = "启用";

    // 用户状态：禁用
    public final static String USER_STATE_FORBIDDEN = "1";
    public final static String USER_STATE_FORBIDDEN_CONTEXT = "禁用";

    // 删除标识(0.保存  1.删除)
    public final static String DEL_TAG_SAVE = "0";
    public final static String DEL_TAG_DELETE = "1";
    
    // 已读标识(0.未读  1.已读)
    public final static String READ_STATUS_UNREAD = "0";
    public final static String READ_STATUS_UNREAD_CONTEXT = "未读";
    public final static String READ_STATUS_READ = "1";
    public final static String READ_STATUS_READ_CONTEXT = "已读";

    // 数据字典的数据类型
    public final static String DATA_TYPE_GENDER = "department";
    
    // 数据字典数据是否为叶子结点标识
    public final static String DATA_ISLEAF_NO = "0";
    public final static String DATA_ISLEAF_NO_CONTEXT = "父节点";
    public final static String DATA_ISLEAF_YES = "1";
    public final static String DATA_ISLEAF_YES_CONTEXT = "叶子结点";
    
    // 权限/菜单是否为叶子结点标识
    public final static String MENU_ISLEAF_NO = "0";
    public final static String MENU_ISLEAF_NO_CONTEXT = "父节点";
    public final static String MENU_ISLEAF_YES = "1";
    public final static String MENU_ISLEAF_YES_CONTEXT = "叶子结点";
    
    // 数据节点数据是否启用
    public final static String DATA_STATUS_ENABLE = "0";
    public final static String DATA_STATUS_ENABLE_CONTEXT = "启用";
    public final static String DATA_STATUS_DISABLE = "1";
    public final static String DATA_STATUS_DISABLE_CONTEXT = "禁用";
    
    // 默认数据
    public final static String DEFAULT_DATA = "-1";
    public final static String DEFAULT_DATA_CONTEXT = "默认数据";

    // 默认删除标识(标识该数据禁止操作)
    public final static String DEL_TAG_FORBID = "-1";
    
    // 设置默认的登录信息(密码为加密后的‘000000’/‘670b14728ad9902aecba32e22fa4f6bd’)
    public final static String DEFAULT_ADMIN_LOGIN_ACCOUNT = "admin";
    public final static String DEFAULT_ADMIN_LOGIN_PASSWORD = "000000";
    public final static String DEFAULT_USER_LOGIN_ACCOUNT = "user";
    public final static String DEFAULT_USER_LOGIN_PASSWORD = "000000";
    public final static String DEFAULT_LOGIN_IMAGE = "";
    
    // 登录数据验证结果定义
    public final static String LOGIN_CHECK_RESULT_SUCCESS = "success";
    public final static String LOGIN_CHECK_MESSAGE_SUCCESS = "用户登录成功,正在跳转页面!";
    public final static String LOGIN_CHECK_RESULT_ERROR = "error";
    public final static String LOGIN_CHECK_MESSAGE_ERROR_CATEGORY = "当前机构信息不存在,请重新确认!";
    public final static String LOGIN_CHECK_MESSAGE_ERROR_INFO = "用户名或密码错误!";
    public final static String LOGIN_CHECK_MESSAGE_ERROR_FORBIDDEN = "当前账号被禁用,请联系管理员进行处理";

    // 登录信息保存状态
    public final static String LOGIN_SAVE_STATUS_SAVE = "save";
    public final static String LOGIN_SAVE_STATUS_UNSAVE = "unsave";
    
    // 设置菜单可见状态
    public final static String MENU_VISABLE_STATUS_ENABLE = "enable";
    public final static String MENU_VISABLE_STATUS_DISABLE = "disable";
    
    

    // 报表文件默认属性
    public final static String REPORT_FILE_PUBLIC_STATUS_PRIVATE = "0";
    public final static String REPORT_FILE_PUBLIC_STATUS_PUBLIC = "1";
    public final static String REPORT_FILE_VISABLE_STATUS_FALSE = "0";
    public final static String REPORT_FILE_VISIBLE_STATUS_TRUE = "1";
    public final static String REPORT_FILE_DEFAULT_DESCR = "当前报表尚未有详细介绍";
    
    // 报表数据源配置文件默认属性
    public final static String DATA_SOURCE_FILE_PUBLIC_STATUS_PRIVATE = "0";
    public final static String DATA_SOURCE_FILE_PUBLIC_STATUS_PUBLIC = "1";
    public final static String DATA_SOURCE_FILE_VISABLE_STATUS_FALSE = "0";
    public final static String DATA_SOURCE_FILE_VISIBLE_STATUS_TRUE = "1";
    public final static String DATA_SOURCE_FILE_DEFAULT_DESCR = "当前数据源配置文件尚未有详细介绍";
    
    // 报表数据源配置状态说明(1.首选状态  2.备选状态)
    public final static String DATA_SOURCE_PREFER_STATE_DEFAULT = "-1";
    public final static String DATA_SOURCE_PREFER_STATE_FIRST = "1";
    public final static String DATA_SOURCE_PREFER_STATE_SECOND = "2";
    
    // 报表文件管理器操作类型定义
    public final static String REPORT_OPERATOR_TYPE_ORGAN = "report_organ";
    public final static String REPORT_OPERATOR_TYPE_DADA = "report_dada";
    public final static String DATA_SOURCE_OPERATOR_TYPE_ORGAN = "datasource_organ";
    public final static String DATA_SOURCE_OPERATOR_TYPE_DADA = "datasource_dada";  

    
    // 设置报表分类关联报表发布状态
    public final static String REPORT_FILE_HIDE_STATUS_PUBLIC = "0";
    public final static String REPORT_FILE_HIDE_STATUS_PRIVATE = "1";  
    
    // operatorType为1标识添加报表分类关联关系
    public final static String LINK_OPERATOR_TYPR_SAVE = "1";
    // operatorType为2标识移除报表分类关联关系
    public final static String LINK_OPERATOR_TYPR_DELETE = "2";

    
}
