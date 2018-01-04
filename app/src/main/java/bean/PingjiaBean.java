package bean;

import java.util.List;

/**
 * Created by zhangyueyi on 2018/1/1.
 */

public class PingjiaBean {

    /**
     * msg : 成功
     * ret : {"pnum":1,"totalRecords":9,"records":20,"list":[{"msg":"又是精神病患者的幻觉","phoneNumber":"喵喵💏","dataId":"ff8080815ffc3a7f016020a77a911684","userPic":"http://phonemovie.ks3-cn-beijing.ksyun.com/%2Fupload/memberPic/2017/12/01/1512088865516148711.jpg","time":"2017-12-04 16:32:14","likeNum":0},{"msg":"不好看，但是为了马天宇看了","phoneNumber":"5小鹿斑比","dataId":"ff8080815f387f1e015f3a125fbd2ab9","userPic":"http://tvax2.sinaimg.cn/crop.0.15.640.640.1024/ea453348ly8fkb7swytigj20hs0ingmg.jpg","time":"2017-10-20 21:56:43","likeNum":0},{"msg":"鬼👻","phoneNumber":"气质型男生","dataId":"ff8080815ec76df0015ece53f99a3c73","userPic":"http://q.qlogo.cn/qqapp/1101034181/AFD281C1B0E8945479EAF53876C5231A/40","time":"2017-09-29 23:49:23","likeNum":1},{"msg":"一般，很一般","phoneNumber":"Nancy","dataId":"ff8080815dcb4639015ddbfb47d11466","userPic":"","time":"2017-08-13 22:24:24","likeNum":2},{"msg":"\u2026\u2026\u2026\u2026","phoneNumber":"158****4172","dataId":"ff8080815d63eb9a015d728da4b32d89","userPic":"","time":"2017-07-24 11:04:31","likeNum":2},{"msg":"整体一般，不推荐，除非你很有空。","phoneNumber":"诉说你的悲伤","dataId":"ff8080815cb03cdc015cc4efb86836d4","userPic":"","time":"2017-06-20 18:12:43","likeNum":3},{"msg":"精彩！精彩！精彩！重要的事情说三遍！","phoneNumber":"一人一心一世界","dataId":"ff8080815cb03cdc015cc4efb86d36d6","userPic":"","time":"2017-06-04 19:57:43","likeNum":1},{"msg":"演员的表演很卖力嘛！","phoneNumber":"給n1的誓言","dataId":"ff8080815cb03cdc015cc4efb86a36d5","userPic":"","time":"2017-05-21 22:14:43","likeNum":1},{"msg":"看了一半，明天接着看。","phoneNumber":"旧情有余温","dataId":"ff8080815cb03cdc015cc4efb86536d3","userPic":"","time":"2017-05-19 02:25:43","likeNum":0}],"totalPnum":1}
     * code : 200
     */

    private String msg;
    private RetBean ret;
    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RetBean getRet() {
        return ret;
    }

    public void setRet(RetBean ret) {
        this.ret = ret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class RetBean {
        /**
         * pnum : 1
         * totalRecords : 9
         * records : 20
         * list : [{"msg":"又是精神病患者的幻觉","phoneNumber":"喵喵💏","dataId":"ff8080815ffc3a7f016020a77a911684","userPic":"http://phonemovie.ks3-cn-beijing.ksyun.com/%2Fupload/memberPic/2017/12/01/1512088865516148711.jpg","time":"2017-12-04 16:32:14","likeNum":0},{"msg":"不好看，但是为了马天宇看了","phoneNumber":"5小鹿斑比","dataId":"ff8080815f387f1e015f3a125fbd2ab9","userPic":"http://tvax2.sinaimg.cn/crop.0.15.640.640.1024/ea453348ly8fkb7swytigj20hs0ingmg.jpg","time":"2017-10-20 21:56:43","likeNum":0},{"msg":"鬼👻","phoneNumber":"气质型男生","dataId":"ff8080815ec76df0015ece53f99a3c73","userPic":"http://q.qlogo.cn/qqapp/1101034181/AFD281C1B0E8945479EAF53876C5231A/40","time":"2017-09-29 23:49:23","likeNum":1},{"msg":"一般，很一般","phoneNumber":"Nancy","dataId":"ff8080815dcb4639015ddbfb47d11466","userPic":"","time":"2017-08-13 22:24:24","likeNum":2},{"msg":"\u2026\u2026\u2026\u2026","phoneNumber":"158****4172","dataId":"ff8080815d63eb9a015d728da4b32d89","userPic":"","time":"2017-07-24 11:04:31","likeNum":2},{"msg":"整体一般，不推荐，除非你很有空。","phoneNumber":"诉说你的悲伤","dataId":"ff8080815cb03cdc015cc4efb86836d4","userPic":"","time":"2017-06-20 18:12:43","likeNum":3},{"msg":"精彩！精彩！精彩！重要的事情说三遍！","phoneNumber":"一人一心一世界","dataId":"ff8080815cb03cdc015cc4efb86d36d6","userPic":"","time":"2017-06-04 19:57:43","likeNum":1},{"msg":"演员的表演很卖力嘛！","phoneNumber":"給n1的誓言","dataId":"ff8080815cb03cdc015cc4efb86a36d5","userPic":"","time":"2017-05-21 22:14:43","likeNum":1},{"msg":"看了一半，明天接着看。","phoneNumber":"旧情有余温","dataId":"ff8080815cb03cdc015cc4efb86536d3","userPic":"","time":"2017-05-19 02:25:43","likeNum":0}]
         * totalPnum : 1
         */

        private int pnum;
        private int totalRecords;
        private int records;
        private int totalPnum;
        private List<ListBean> list;

        public int getPnum() {
            return pnum;
        }

        public void setPnum(int pnum) {
            this.pnum = pnum;
        }

        public int getTotalRecords() {
            return totalRecords;
        }

        public void setTotalRecords(int totalRecords) {
            this.totalRecords = totalRecords;
        }

        public int getRecords() {
            return records;
        }

        public void setRecords(int records) {
            this.records = records;
        }

        public int getTotalPnum() {
            return totalPnum;
        }

        public void setTotalPnum(int totalPnum) {
            this.totalPnum = totalPnum;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * msg : 又是精神病患者的幻觉
             * phoneNumber : 喵喵💏
             * dataId : ff8080815ffc3a7f016020a77a911684
             * userPic : http://phonemovie.ks3-cn-beijing.ksyun.com/%2Fupload/memberPic/2017/12/01/1512088865516148711.jpg
             * time : 2017-12-04 16:32:14
             * likeNum : 0
             */

            private String msg;
            private String phoneNumber;
            private String dataId;
            private String userPic;
            private String time;
            private int likeNum;

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public String getDataId() {
                return dataId;
            }

            public void setDataId(String dataId) {
                this.dataId = dataId;
            }

            public String getUserPic() {
                return userPic;
            }

            public void setUserPic(String userPic) {
                this.userPic = userPic;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public int getLikeNum() {
                return likeNum;
            }

            public void setLikeNum(int likeNum) {
                this.likeNum = likeNum;
            }
        }
    }
}
