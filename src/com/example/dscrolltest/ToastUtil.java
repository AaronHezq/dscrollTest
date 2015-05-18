package com.example.dscrolltest;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast弹出消息工具类
 * 
 * @author Aaron He
 * 
 */
public class ToastUtil {

	public static Toast toast = null;
	public static Toast toastl = null;

	/**
	 * 长时间显示消息
	 * 
	 * @param context
	 *            上下文对象
	 * @param message
	 *            消息内容
	 */
	public static void showLongToast(Context context, String message) {
		if (context == null) {
			return;
		}
		if (toastl == null) {
			toastl = Toast.makeText(context, message, Toast.LENGTH_LONG);
		} else {
			toastl.setText(message);
		}
		toastl.show();
	}

	
	/**
	 * 短时间显示消息
	 * 
	 * @param context
	 *            上下文对象
	 * @param message
	 *            消息内容
	 */
	public static void showShortToast(Context context, String message) {
		if (context == null) {
			return;
		}
		if (toast == null) {
			toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		} else {
			toast.setText(message);
		}
		toast.show();
	}

	/**
	 * 网络错误提示
	 * @param context 上下文对象
	 * @param code 状态码
	 * @param defaultMsg 默认信息
	 */
	public static void showNetMsg(Context context, int code, String defaultMsg) {
		if (context == null) {
			return;
		}
		String message = defaultMsg;
		switch (code) {
		case 0:
			message = "未连接到服务器，请检查网络是否连接";
			break;
		case 100:
			message = "继续";
			break;
		case 101:
			message = "切换协议";
			break;
		case 200:
			message = "确定";
			break;
		case 201:
			message = "已创建";
			break;
		case 202:
			message = "已接受";
			break;
		case 203:
			message = "非权威性信息";
			break;
		case 204:
			message = "无内容";
			break;
		case 205:
			message = "重置内容";
			break;
		case 206:
			message = "部分内容";
			break;
		case 302:
			message = "对象已移动";
			break;
		case 304:
			message = "未修改";
			break;
		case 307:
			message = "临时重定向";
			break;
		case 400:
			message = "错误的请求";
			break;
		case 401:
			message = "访问被拒绝";
			break;
		case 403:
			message = "禁止访问";
			break;
		case 404:
			message = "未找到请求的地址";
			break;
		case 405:
			message = "方法不被允许";
			break;
		case 406:
			message = "客户端浏览器不接受所请求页面的 MIME 类型";
			break;
		case 407:
			message = "要求进行代理身份验证";
			break;
		case 408:
			message = "服务器等候请求时发生超时";
			break;
		case 409:
			message = "服务器在完成请求时发生冲突";
			break;
		case 410:
			message = "请求的资源已删除";
			break;
		case 411:
			message = "服务器不接受不含有效内容长度标头字段的请求";
			break;
		case 412:
			message = "前提条件失败";
			break;
		case 413:
			message = "请求实体过大";
			break;
		case 414:
			message = "请求URI太长";
			break;
		case 415:
			message = "不支持的媒体类型";
			break;
		case 416:
			message = "请求的范围不符合要求";
			break;
		case 417:
			message = "执行失败";
			break;
		case 423:
			message = "锁定的错误";
			break;
		case 500:
			message = "服务器错误";
			break;
		case 501:
			message = "指定了未实现的配置";
			break;
		case 502:
			message = "错误网关";
			break;
		case 503:
			message = "服务不可用";
			break;
		case 504:
			message = "网关超时";
			break;
		case 505:
			message = "HTTP版本不受支持";
			break;
		}
		if (toast == null) {
			toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		} else {
			toast.setText(message);
		}
		toast.show();
	}
}
