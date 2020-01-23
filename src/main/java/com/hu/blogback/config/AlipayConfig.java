package com.hu.blogback.config;

/**
 * 沙箱环境
 * 使用holer来将局域网服务器来代理到公网
 */
public class AlipayConfig {

    public static String APP_ID = "2016102000726743";

    public static String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCqnpcC2SInIxN/Hh/pzIoGd5MeEuUFM8WDrmyA5dNDtZQ26WoRPQywJpqLRjt2K/tLuDId2FIsOBd52OsVKodoJc0w0WPeNUXisz+qmnqfK/nuvV12Xn4HsYDaOL+67hTxIUV5sthoqaoPZ2DHvbwpUs1Rvjknzz2LDpfu0LOjhXcb9wtci2h+99NDvyJs8fb6iZ9paWQ5EBtpnrmMsSI+TfWMnRGIVrOyQTb/HFGn3voApRweG73u413yG9ukPkFpoekNs1cWPkKV+ke56bypIk/kGJKSyu8Eo9RKhWCvCyWae5irG8pQWjGQ//RtyfZNoG75Mm/ZIOxMyslgx2/9AgMBAAECggEBAIL64kTuebh/xezVLHyoZ+FVFFyG9sfKmbtgQK2nQUr+bzzEbYe0TTRBPmziyq0KUI0tZ1PgCB0oJmWE79s83dxHAMGUTU19qhvBrYODudTNxr3PI5vGKIwxaRCddhrDwNHnz9UARf/7F7FhlDtiXbt/dF1nYas8/irtVhegofdKMjMjXrwDbMdCUR/hb7j/zkXpyBcXh0OV3gXwGQQngYf1a4MYU7csTk/yFpAUQ3Sg3tnIGT03x6CQqlOy0xP1m4WZopWNofZQzc4Y+QGHkR+xTeU8e8mU/TPBPSYfsqau3NXJd+hCLzpw6L83dtQhRfojapQX2DVJfy46NIMs50ECgYEA2IsohihN3yDqEPSckMN/6dTZUTXXqRxA61xsraMLxgqKN70oeEKQ+zlWWYrdcKFEnTGjnC1ty+FdPncTybqVjD8P7SUMensLmAwXJPgVivZe13wUpqKBR29QlO0qpjhr1dbyjZk8yZeSgAvjgpRV2RExXOTDuVWrSCxhl0z58C0CgYEAybVEVi5AJeGvDX0N9pOx+ZQv4W2s0ckgKmtzlGLT0tB7INeiHW9QQ4Pr59QQsxLNnXPqGnoR5uCGsYOvoBpKPCOJj9Yb1x29oN8Ku5fnLjdvXfmDyBX1OYdXFFx1qtCTnvnXfJ9xnbbuqgYw+dpZPfjwowDtOmhxIBhyGLHZkRECgYBDtV96c2fF+Pi3TIUYDskUSGWbKwa/7gGulju1H2IzDmChhRf+YnIK26bQYMdUzFgz/q11zA6kzVbmjT02uozASYelUwxPz+GULVOfT6MMzHRjVHzwjad2uqurpWzMhJ/TQV6eP+ft8OLx/sWostEVCncw4KcCcHMaIiZxtLQCEQKBgEofPWx59IK3K0/Gm6H35lfzl6C8X9x7j0FnWQK3SmXWzNhlxSIR7KnSyZbYfAaCK2Ln3Kg8IWX5LiX/YUNxRcRRvtRHdtWXDT8Xl2cWTBLUti0PatjVENdCH8LQs0DBAu5Rm1klcmfleFCsk8TM8yxt1WfhCEwFY0TLFupyOKQxAoGBAKXvGmS9PwFeT+ujCXJZOJ3cY4M5LmFselZySta7fr8QKffcQlVKXMCm0a9vZSNrRHX09ssoE2SgoJcc45Uege9c7xrDdQeiT9fUF97DnNP4VKrAekFQZjGr4hL9p0iB0CVzyXHoyC8mmBiEdcWUuuhaePMBduAtiuiIS/RK89rk";

    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApxQC974OVNm6oZd/U00+sOT2DUg/+VLs7z9zzHe2UfI+9mVaD0ndX7dSdE+NgiWeCiqHwVICk46H24iKjeUbwHHr+71uY7ytQqvIQWXJ3jHXiIjLiHg1uQfKlTAJwp54HdPxvR7pyr16QNQoGPB+REfLbxjXxM/v1kZHeuPq3bRjySbOwtGg5t0ApMxlEJmusKrmhrX7dF2KFtSIkdaN20Yl5aPx8z6mNs2OjMIzH/VYG6XZfcCwg0RjW1YLIA4Gflb8ZNikjj+SaCfBcM4+Cd6W+PW3mvFf9uL0jIHUpQdOEBX2Y2d8AKLyY8hZPzstZLMqeNWwzFvn4+lzSqUgJQIDAQAB";

    public static String SIGN_TYPE = "RSA2";

    public static String CHARSET = "utf-8";

    public static String FORMAT = "json";

    public static String GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";

    public static String NOTIFY_URL = "http://holer65530.wdom.net/callback";

    public static String RETURN_URL = "http://holer65530.wdom.net/return";
}
