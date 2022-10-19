package com.chixing.config;

/**
 * @author ZhangJiuJiu
 */
public class AlipayConfig {
    public static String app_id = "2021000121671591";
    /**
     * 私钥
     */
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCCLZDTm4qfHG/XGpx+9th/C1UVWRZUYxyhtibtu16pLEtJFEdgv9dq8SDWgZKLfgJyfrvNuWoZ0piK3D30mlEHxOnXLTcUuShv+4I3vSjanYf1hirJemR0wV7VFmP5Le+oVdMspvViVMyiU+7XiY4xu3aEn384WU0TslJWOsKVrqPosh4weae1zpJ6C9f6HW7AUav5q55IZlkuP/h7jqq4Wyz/EQyS7YcMik+cGPio2n4b1pnRnGHcZgCSwqV9oxX0R+m/Z7I0bJZ/9bDSTFpNB79KQwkcNgasm3dekgU7E+l1iTB1ctlG26nZQULl/p2+6KO3YKYDQdXX5NW/Bx7rAgMBAAECggEAUv8qL0AkCX3f8I0O6gQH1Dgtbwb1rafjQVcZSskDeWi8N0Ounoixl4VVIm5KOJWGiekzY96rPvt+NkplVeZtcz6guIgjbhr/JSZ9x3va6Ec3v9pivUJDeg0voTw9UUlwWysdEIMCQEdAkN5ikRkqh5q13aizPAHzmnM++6tLFJLuur8uACiE43S5n7Rl6DV2wPqcnsSvPSKTglslgXN9B5UbU2H/ZxiMBhqXlkw+cZFu66SczDLOBBI05fQvJZbF8Izwqpz/qfnGz4SeZJN7j9Ujy6qNQs4BgoFlLphbPQlve2SZlXxVM0ZuYmmt7wWlBFP1h3sf7AsFjXCKNufogQKBgQDeD1PHUGJbzuWVuGVzHZE15us3vI3PNQRPII2I5qbgjD0w3cq3UeGTFx3Gch/EUfDG3DKR4v6/TmbE9dJ4O95I5iSPKF+7thz0xiaFH/lhDbhlGWHTY6HQJo9/9V+yUlK3GfCQukC7i9RKoPcr5hKsMFd9UkQg+zFQ2s4GULXJQwKBgQCWEx6qZjPKrUKBNs41PZ+v3VyqfR7mP4iPcHfp/YtPRoXHVsPwU95NRTop4BX+4O/jeiRMQKrneaejkhl23XMrzarFo6RaPj2wFDgSykfMLuLQKaLCpwMOYMdAMl1x5umbBaZRP0CPDaTkiGC7b/IYN4Me3++5e0dCIXga6nUFOQKBgEZM++0B2eHO4og/1H5ZQfuam6UNoKeU1URnvec1fmdgfo7V6LOYzJ1QmDCXOM9iUaPjWm8tOhE+tPJuEK/X5EuX8MMRuR0oTop/oPpMYRsEyPfYzUurIzNOwed0u1MtFNq675oiHRRC9Hab9ySCti6lL02RmdgHLlm2q7r8GlEZAoGAZPEtUi32LiAFuIXVg2gUraS1/JK2IuafAuQCNCfu+P6VYvQQTOkMgGrM6XepM8u2bworugJRMerZACN370Saf98RO2iLVovA0Ft5L+JREpyCwMMQj97oJlagDqURx1gqzrOpH8LsuIvT2keE0O5GJ1XHYw/63YZ28psyfuAun7kCgYBSyogWiwA33pJr2BTCc0RWDDvl/+SfW3h0V3naB/pdOeHyXZaeK7KoemlKuHRHFfRqe7KgDcGkBImXGuriRtxELoHwdNJl8sFnytA2YJYLDsYnZRagLI/x8nVrbZKhrS2bFZDb0iGl/Y+cvax/5MpPvT199cx2VZfO67p4S1KMbg==";
    //公钥
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg+V3vQgx19w6mNO0xlWukrVoUJcnvdxFRD9VOmAHKXgIJBQOKA8UxSvwYu7q7DbZt6myQn7QzHwD8vHdizWnfLIcDbT+r7/LcoI4T3Ro0BvMlekZB7S29x4XLQRANqi9f19iWyJk+b+FFVCoC0nXHra2ittnHFeSgvCiqGAUMzyam7X7b1i0sfp9iL/bJwQDPyLoPo67LBjzPObg4bD2s3tii7DJE3C8VSp4GoOlr0g4x0a98lig9kYpew20ObguRxM1y2edWdxFG/27zs2qjhWGaS9ZDb8f8w+F3kOIu/jsHFcs50zUEzcTnqCUFb1t4CAcO4fOm3Ada0+5d2xzrwIDAQAB";
    //支付宝异步通知路径（付款完毕后会异步调用本项目的方法，必须为公网网址）
    public static String notify_url = "";
    //支付宝同步通知路径（当付款完毕后跳转本项目的页面，可以不是公网地址）
    public static String return_url = "http://localhost:8080/demo/success";
    //签名方式
    public static String sign_type = "RSA2";
    public static String charset = "utf-8";
    //沙箱测试环境，正式环境为：https://openapi.alipay.com/geteway.do
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";


}

