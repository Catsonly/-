package com.hba.constellation.bean;

import java.util.List;



public class LuckBean {

    /**
     * code : 200
     * msg : success
     * newslist : [{"type":"综合指数","content":"90%"},{"type":"爱情指数","content":"90%"},{"type":"工作指数","content":"80%"},{"type":"财运指数","content":"80%"},{"type":"健康指数","content":"80%"},{"type":"幸运颜色","content":"蓝色"},{"type":"幸运数字","content":"1"},{"type":"速配Ｑ友","content":"双子"},{"type":"今日概述","content":"今天的双子宝宝们可以说是最幸福的人了，无论遇到什么事情都会被解决。可以出去游玩的宝宝们当然是抓紧时间好好地享受旅游啦，说不定会在途中遇到对自己有帮助的人噢。要加班的宝宝今天的合作运不错，不如趁着运势，跟合作方谈谈合作吧。"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * type : 综合指数
         * content : 90%
         */

        private String type;
        private String content;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
