package com.jkhhuse.plan.constants;

public final class ExceptionConstants {

    private ExceptionConstants() {
    }

    /**
     * the prefix of exception code
     */
    private static final String PREFIX = "MSG";
    /**
     * 异常错误码前缀长度
     */
    public static final Integer PREFIX_LENGTH = PREFIX.length();

    //-----------------------------公共模块异常代码部分-----------------------------

    /**
     * 模块内部标识, 000 -> 公共模块
     */
    private static final String PREFIX_COMMON = PREFIX + "000";
    /**
     * 未知异常, 前端显示为系统异常
     */
    public static final String ERROR_STATUS = PREFIX_COMMON + "00000";
    /**
     * 只有主账号拥有操作权限
     */
    public static final String PERMISSION_DENIED = PREFIX_COMMON + "00001";
    /**
     * 外部系统的HTTP响应结果异常或者空
     */
    public static final String HTTP_RESPONSE_ERROR = PREFIX_COMMON + "00002";
    /**
     * 越权或不合法的访问
     */
    public static final String PERMISSION_ILLEGAL = PREFIX_COMMON + "00003";
    /**
     * 无所识别前端传入的数据属性:{0}
     */
    public static final String UNKNOWN_PROPERTY = PREFIX_COMMON + "00004";
    /**
     * 不能获取到该项目组对应的{0}
     */
    public static final String NO_GROUP_INFO = PREFIX_COMMON + "00005";
    /**
     * 请求传参不符合规则
     */
    public static final String VALIDATE_PARAMS_ERROR = PREFIX_COMMON + "00006";
    /**
     * 记录不存在或无权操作
     */
    public static final String RECORD_NOT_EXISTS = PREFIX_COMMON + "00007";
    /**
     * {0}不能为null或空白字符串
     */
    public static final String NOT_BLANK = PREFIX_COMMON + "00008";
    /**
     * {0}长度不能超过{1}位
     */
    public static final String LENGTH_GREATER_N = PREFIX_COMMON + "00009";
    /**
     * {0}是一个必填项
     */
    public static final String PARAM_REQUIRED = PREFIX_COMMON + "00010";
    /**
     * {0}只能为{1}
     */
    public static final String VALUE_LIMIT = PREFIX_COMMON + "00011";
    /**
     * 用户不存在
     */
    public static final String USER_NOT_EXIST = PREFIX_COMMON + "00012";
    /**
     * 不能获取agent的连接信息
     */
    public static final String AGENT_CONNECT_FAILURE = PREFIX_COMMON + "00013";
    /**
     * 无效的agent连接URI
     */
    public static final String INVALID_URI = PREFIX_COMMON + "00014";
    /**
     * 执行API对应SQL获取结果失败
     */
    public static final String LIST_SQL_RESULT_FAILURE = PREFIX_COMMON + "00015";
    /**
     * 结果不符预期:{0}
     */
    public static final String UNEXPECTED_RESULT = PREFIX_COMMON + "00016";
    /**
     * 状态不符预期
     */
    public static final String UNEXPECTED_STATUS = PREFIX_COMMON + "00017";

}