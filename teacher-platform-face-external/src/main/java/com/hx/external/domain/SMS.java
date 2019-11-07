package com.hx.external.domain;

public class SMS {

    //手机发送短信次数
    private Integer sum;

    //手机验证码
    private Integer code;

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SMS{" +
                "sum=" + sum +
                ", code=" + code +
                '}';
    }
}
