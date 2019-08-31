package com.shuihuo.blog.result;

public class Result {

        //状态码
        private int code;
        //消息
        private String message;
        //额外的内容
        private int number;
        private Object data;

        public Result(){

        }

        public Result setNumber(int number) {
            this.number = number;
            return this;
        }

        public int getNumber() {
            return number;
        }

        public Result setCode(ResultCode code) {
            this.code = code.getCode();
            return this;
        }
        public int getCode() {
            return code;
        }

        public Result setCode(int code) {
            this.code = code;
            return this;
        }

        public String getMessage() {
            return message;
        }

        public Result setMessage(String message) {
            this.message = message;
            return this;
        }

        public Object getData() {
            return data;
        }

        public Result setData(Object data) {
            this.data = data;
            return this;
        }
}
