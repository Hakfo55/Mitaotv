package com.mountain.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 系统环境变量
 */
@Component
public class ApplicationConfig {

	@Value("${spring.profiles.active}")
	public String env;//系统环境  test 测试  product 生产
	@Value("${api.host}")
	public String apiHost;//本机访问地址

	/**
	 * 初始化管理后台一些数据
	 */
	public HashMap<String, String> map = new HashMap<String, String>(){
//		{
//			put("出售中商品", "/admin/userGoods");
//			put("寄卖单", "/ck/rk/verify/?type=2");
//			put("库存管理", "");
//			put("库存查询", "/ck/kc");
//			put("库存上架", "/ck/kc/sj");
//			put("订单", "/admin/order");
//			put("退货订单", "/admin/order/refunds");
//			put("退货仓退货", "/ck/th/userList");
//			put("七天无理由退货", "/fin/refundOrder/userRefundOrder");
//			put("条形码管理", "/ck/txm");
//			put("地址", "/goods/duUserAddress");
//			put("财务管理", "");
//			put("提现账号管理", "/finance/account");
//			put("提现", "/finance/cash");
//			put("余额明细", "/fin/balance");
//			put("保证金明细", "/fin/bond");
//			put("权限管理", "");
//			put("管理员/商户", "/page/jurisdiction/user/");
//			put("角色管理", "/page/jurisdiction/role/");
//			put("菜单管理", "/page/jurisdiction/function/");
//		}
	};
}
