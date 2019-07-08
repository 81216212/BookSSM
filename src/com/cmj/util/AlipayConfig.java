package com.cmj.util;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016100200645736";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC6YhXLeZi7TnqZwU88HbC5wDU143bHn4eWrXACOGmdXKJDg5OPnuTfcCCC0goivzWvKlOD86LnhXP+2t9CUOLY6Id8ORaYAbBlOlWEE/AKkABjd8czDmqoJ9VTzLFMToLBctPrfxhe3XuVp9LpBt9iiC+ANwpF6WmsAIUYm8b7i/sfcGKtvWemXXoL1eeXk/a1ThV1H1Vwj5UAf3lTlNdTSBFZkyq+Q5NAoBZyFsOzIOVY6w8m/n+5NOoEag3UXKHQKLe3cfvvc4dt8HCSk1WCQ0Ae/oogNs1hnyooZz2CWeKW/Xkgrdr54gzCrCDZV0TUWe32bBaCJbDtW4U/ODjTAgMBAAECggEAYOGvVo8kVuiaTeXv293LCSLiBdrw2R8Rib8po93S+Fs4dUOlplf/vC06mQW5AwrFSwIDW2l/Wj1CaE05E+2bnQhxtJ6wId9u44+I+BitA7CKoymVv/JUeogKGX8jAfkleozHn3f3IQibvtT3iu1pnHwRxOHXUzPbG+9BP+NpcUMU4Pyf1ZmleSBjyKYaUKtpoC3++PgggN++FCbGzdIR3zU5NY6Cr71LkqCEaSND7Y4/brQGyVTRJxmbtSJLsLFLAiTrePYfPbCM5iaGHuDAW4TdUHV6FJ/xqvCuRv/ItAJdFe93abVPl0YunoLvS4MGqXLd6nmBZrS3gluWnKOLMQKBgQDgStcJKn0cIZMvOAU9CjY/p56PQzo1HUr8kowsK+5komVqdFf1jxKny1iGbcP477Xm5/R0zNxErTp7iBE6+/gEVmIf8Nzx8fljbEL2H1SdMddZOaRaOR9JO9i4K6ySBe6qRexWAilhL8jrd/l/95f/wfeY9h77vHxrtHmotoDphQKBgQDUu07Pu/ZAOcNjCEjSLfmV10vS6fwkAAB5UaSk4OJQO8BGp5ygy3RZEecf4h5M7dC1B4Ea4BxpGSdN1fQmGiPuBhfUvKK26FytmQP4zxWqK1Gy8YkPf34H/vslJJ4OP1SP5GX/3ai7hlTIlOMw0UHYmWn8pZYqJVbNFQc/ePa8dwKBgHpVN33/V6edRGrvsiO9yyLi6J+Y+kl4BatGvv9koqzuykPK2yLK+1DbnxgLDGVdUSh1AScniUhH92rjJwq13Wt4NiLXu5qW8P/iT49wPTVTtvIqd1LibwcBhk3FPGWUqLzkUGgE287vQjX0BPhf8d6ES+h47SM+hdo04Fo92IgFAoGBAIO38sWXiZMc8QkpwQU66FZKN63BqZiU07ZKwhpBEIBDbvuQnt/E+iaIekZ4mPBjNB5TJjKA37aHl5mPR5wsBWFFLo5PuqRsXa4zl40AlFaIeg7FlWseMug6jkDiWmdR+UcbUbjLA8YRGAAWiFHaz92QqFqCIOe5tPRI9/LqMERjAoGAUFqp1ETuQChK9N9o2UenIqUn16f22DbH65BrGfNqZEuuzTGzkIEkf+4dH7c4Fn79BFQXeBT022JatAXbp5OgGpkUuDiQJokJUpYJyeNuoLwHnwBKXhrkosqFpVTmh5SX9t+i44agiW9Sw4kkSVd5G2k0ejDuEyXI2u3peLHAV8c=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp5ShZkmbCJnpgPtpIKme6JUAdzxmpk9F5o/a42sMYmIPlIcYULCRzLeuqsat4JX/2LXzDhXABrMeVsdSba9e8IQsk1uus2828AcvypVLPDqHXuPJcLTsFkQzHDWfD1b3n1bW6JTTxBzA1mg0JtSVdjHLm5NSuiRNk0OxcYg9HV0Qhce9uHNjCYXQL7Wsk479ou1HjV1o8qWitk0zL2YhFuu0qc/LDU3V4S/xHQRdkhWHUUpMq9pDjNNWSt2okHwbysaxV3zBiIe6yUN/0qt0BSm27JnGgEYYGFduNehgzyVnNXlWDTSy2sGEhRyxMnE4Ab1Mc7SW5+9Hcvyl7bMpswIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://172.18.13.110:8080/cmj/order/returnurl";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://172.18.13.110:8080/cmj/order/returnurl";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

