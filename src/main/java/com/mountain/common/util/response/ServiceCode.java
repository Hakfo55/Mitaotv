package com.mountain.common.util.response;
/**
 *
 * @Title: ServiceCode.java
 * @Package com.slarts.common.util.response
 * @Description: TODO 业务返回参数
 * @author porridge
 * @date 2016年10月27日 下午10:08:14
 * @version V1.0
 */

public class ServiceCode {
    public static final int OK = 21020000;
    public static final String OK_DEFAULT_MSG = "操作成功";

    public static final int DELETE_SUCCESS = 21020001;
    public static final String DELETE_SUCCESS_MSG = "删除成功";

    public static final int SESSION_TIMEOUT = 21020002;
    public static final String SESSION_TIMEOUT_MSG = "登录超时";

    public static final int PATCH_SUCCESS = 21020003;
    public static final String UPDATE_DEFAULT_MSG = "修改成功";

    public static final int DELETE_FAILURE = 41020002;
    public static final String DELETE_FAILURE_MSG = "删除失败";

    public static final int UNAUTHORIZED = 41020003;
    public static final String UNAUTHORIZED_DEFAULT_MSG = "未授权";

    public static final int MISSING_PARAM_CODE = 41020004;
    public static final String MISSING_PARAM_CODE_DEFAULT_MSG = "请确认信息填写格式是否正确，*为必填选项。";

    public static final int PAGE_NOT_FOUND = 41020005;
    public static final String PAGE_NOT_FOUND_DEFAULT_MSG = "找不到请求的页面";

    public static final int RESOURCE_NOT_FOUND = 41020006;
    public static final String RESOURCE_NOT_FOUND_DEFAULT_MSG = "找不到请求的资源";

    public static final int WX_ERROR = 41020007;
    public static final String WX_ERROR_DEFAULT_MSG =  " 微信服务错误，具体错误代码请参考： https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1445241432&token=&lang=zh_CN";

    public static final int WX_NOT_FIND_ERROR = 41020008;
    public static final String WX_NOT_FIND_ERROR_DEFAULT_MSG =  "未找到该微信配置";

    public static final int SERVER_ERROR = 51020000;
    public static final String SERVER_ERROR_DEFAULT_MSG = "系统捉住一只BUG，请重试。";

    //无效参数
    public static final int INVILD_PARAM_CODE = 41020007;
    public static final String INVILD_PARAM_DEFAULT_MSG = "无效参数";

    public static final int ADD_PRODUCT_REPEAT_CODE = 41020008;
    public static final String ADD_PRODUCT_REPEAT_DEFAULT_MSG = "商品ID已录入，是否考虑进入编辑模式？";

    public static final int SHOPPING_CART_NOT_EXIST = 41020009;
    public static final String SHOPPING_CART_NOT_EXIST_DEFAULT_MSG = "购物车商品已存在！";

    public static final int PRODUCT_INSUFFICIENT = 41020010;
    public static final String PRODUCT_INSUFFICIENT_DEFAULT_MSG = "商品库存不足";

    public static final int PRODUCT_RULE_NOT_EXIST = 41020011;
    public static final String PRODUCT_RULE_NOT_EXIST_DEFAULT_MSG = "商品不存在！";

    public static final int SHOPPING_IS_NULL = 41020012;
    public static final String SHOPPING_IS_NULL_DEFAULT_MSG = "用户购物车为空。无法生成订单";

    public static final int EXPRESS_FIND_ERROR = 41020013;
    public static final String EXPRESS_FIND_ERROR_DEFAULT_MSG = "快递接口查询错误";

    public static final int ORDER_PAY_ERROR = 41020014;
    public static final String ORDER_PAY_ERROR_DEFAULT_MSG = "订单支付状态错误，不可支付";

    public static final int ORDER_CANCEL_ERROR = 41020015;
    public static final String ORDER_CANCEL_ERROR_DEFAULT_MSG = "订单取消错误，该订单已支付";

    public static final int INVITE_STATUS_ERROR = 41020016;
    public static final String INVITE_STATUS_ERROR_DEFAULT_MSG = "活动暂时没有开始";

    public static final int CHANCE_EXHAUSRED_CODE = 41030001;
    public static final String CHANCE_EXHAUSRED_CODE_MSG = "等级已达到上限";

    public static final int KEFU_OFFLINE_CODE = 41020017;
    public static final String KEFU_OFFLINE_CODE_DEFAULT_MSG = "当前没有客服在线";

    public static final int SPENDING_ERROR = 41020018;
    public static final String SPENDING_ERROR_DEFAULT_MSG = "付款失败";

    public static final int PUT_ERROR = 41020020;
    public static final String TOPIC_RECORD_PUT_ERROR_DEFAULT_MSG = "微信用户提交的题目不是7道";

    public static final int REPEAT_ERROR = 41020020;
    public static final String REPEAT_ERROR_DEFAULT_MSG = "您已经出题了且未超过24小时噢！";

}