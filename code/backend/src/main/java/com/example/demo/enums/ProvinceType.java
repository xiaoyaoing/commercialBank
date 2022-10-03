package com.example.demo.enums;

public enum ProvinceType {
    BeiJing("北京"),
    ShangHai("上海"),
    TianJing("天津"),
    ChongQing("重庆"),
    HeBei("河北"),
    ShanXi1("山西"),
    JiLin("吉林"),
    LiaoNing("辽宁"),
    HeiLongJiang("黑龙江"),
    ShanXi2("陕西"),
    GanSu("甘肃"),
    QingHai("青海"),
    ShanDong("山东"),
    FuJian("福建"),
    ZheJiang("浙江"),
    HeNan("河南"),
    HuBei("湖北"),
    HuNan("湖南"),
    JiangXi("江西"),
    JiangSu("江苏"),
    AnHui("安徽"),
    GuangDong("广东"),
    HaiNan("海南"),
    SiChuan("四川"),
    GuiZhou("贵州"),
    YunNan("云南"),
    TanWan("台湾"),
    NeiMongGu("内蒙古"),
    XinJiang("新疆"),
    XiZang("西藏"),
    NingXia("宁夏"),
    GuangXi("广西"),
    Hongkong("香港"),
    Machao("澳门"),
    Wu("无");


    private String value;

    ProvinceType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static ProvinceType value2Object(String value){
        for (ProvinceType e : ProvinceType.values()) {
            if (e.toString().equals(value)){
                return e;
            }
        }
        throw new IllegalArgumentException("Illegal ProvinceType value: " + value + ". ");
    }
}
