package com.example.timviec365.model;

import java.util.List;

public class abc {

    /**
     * kq : true
     * data : {"RenderLuong":[{"from":0,"to":0,"value":7},{"from":1000000,"to":3000000,"value":2},{"from":3000000,"to":5000000,"value":0},{"from":5000000,"to":7000000,"value":4},{"from":7000000,"to":10000000,"value":21},{"from":10000000,"to":15000000,"value":15},{"from":15000000,"to":20000000,"value":3},{"from":20000000,"to":30000000,"value":1},{"from":30000000,"to":0,"value":1}],"RenderKinhNghiem":[{"from":"Chưa có kinh nghiệm","value":7},{"from":"0 - 1 năm kinh nghiệm","value":18},{"from":"1 - 2 năm kinh nghiệm","value":22},{"from":"2 - 5 năm kinh nghiệm","value":7},{"from":"5 - 10 năm kinh nghiệm","value":0},{"from":"Hơn 10 năm kinh nghiệm","value":0}],"RenderBangCap":[{"from":"Không yêu cầu","value":13},{"from":"Sau đại học","value":1},{"from":"Đại học","value":17},{"from":"Cao đẳng","value":23},{"from":"Trung học","value":0},{"from":"Khác","value":0}]}
     */

    private boolean kq;
    private DataBean data;

    public boolean isKq() {
        return kq;
    }

    public void setKq(boolean kq) {
        this.kq = kq;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<RenderLuongBean> RenderLuong;
        private List<RenderKinhNghiemBean> RenderKinhNghiem;
        private List<RenderBangCapBean> RenderBangCap;

        public List<RenderLuongBean> getRenderLuong() {
            return RenderLuong;
        }

        public void setRenderLuong(List<RenderLuongBean> RenderLuong) {
            this.RenderLuong = RenderLuong;
        }

        public List<RenderKinhNghiemBean> getRenderKinhNghiem() {
            return RenderKinhNghiem;
        }

        public void setRenderKinhNghiem(List<RenderKinhNghiemBean> RenderKinhNghiem) {
            this.RenderKinhNghiem = RenderKinhNghiem;
        }

        public List<RenderBangCapBean> getRenderBangCap() {
            return RenderBangCap;
        }

        public void setRenderBangCap(List<RenderBangCapBean> RenderBangCap) {
            this.RenderBangCap = RenderBangCap;
        }

        public static class RenderLuongBean {
            /**
             * from : 0
             * to : 0
             * value : 7
             */

            private int from;
            private int to;
            private int value;

            public int getFrom() {
                return from;
            }

            public void setFrom(int from) {
                this.from = from;
            }

            public int getTo() {
                return to;
            }

            public void setTo(int to) {
                this.to = to;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }

        public static class RenderKinhNghiemBean {
            /**
             * from : Chưa có kinh nghiệm
             * value : 7
             */

            private String from;
            private int value;

            public String getFrom() {
                return from;
            }

            public void setFrom(String from) {
                this.from = from;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }

        public static class RenderBangCapBean {
            /**
             * from : Không yêu cầu
             * value : 13
             */

            private String from;
            private int value;

            public String getFrom() {
                return from;
            }

            public void setFrom(String from) {
                this.from = from;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }
    }
}
