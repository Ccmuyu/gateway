package com.zzw.myGate.util;

public class ResultUtil {
    private static final String success_msg = "success";
    private static final String error_msg = "error";
    private static final int success_code = 200;
    private static final int error_code = 500;
    private static Result success_default = null;
    private static Result failed_default = null;

    static {
        success_default = new Result(success_msg, success_code, null);
        failed_default = new Result(error_msg, error_code, null);
    }


    public static <T> Result<T> success() {
        return success_default;
    }

    public static <T> Result<T> success(T data) {
        return success(data, success_msg);
    }

    public static <T> Result<T> success(T t, String msg) {
        return success(t, msg, success_code);
    }

    public static <T> Result<T> success(T t, String msg, int code) {
        return new Result<>(msg, code, t);
    }

    public static <T> Result<T> failed() {
        return failed_default;
    }

    public static <T> Result<T> failed(int code, String msg) {
        return new Result<>(msg, code, null);
    }


    public static class Result<T> {
        private String msg;
        private Integer code;
        private T data;

        public Result() {
        }

        public Result(String result, Integer code, T data) {
            this.msg = result;
            this.code = code;
            this.data = data;
        }
    }
}
