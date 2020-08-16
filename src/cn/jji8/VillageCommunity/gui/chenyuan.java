package cn.jji8.VillageCommunity.gui;
/**
 * 用于封装村长成员
 * */
public class chenyuan {
    String name;
    String 身份;

    chenyuan(String name,String 身份){
        this.name = name;
        this.身份 = 身份;
    }

    public String getName() {
        return name;
    }

    public String get身份() {
        return 身份;
    }
}
