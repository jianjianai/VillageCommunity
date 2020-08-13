package cn.jji8.VillageCommunity.lingdi;

import cn.jji8.VillageCommunity.main;
import org.bukkit.Location;

import java.io.File;
import java.util.ArrayList;

/**
 * 主要负责领地的读取和保存
 * */
public class lingdilist {
    static ArrayList<lingdi> biao = new ArrayList<lingdi>();//领地列表
    public static void chuangjian(String name, String cunzhang,Location wz1,Location wz2){
        if(!wz1.getWorld().equals(wz2.getWorld())){
            return;
        }
        chuangjian(name,cunzhang,wz1.getWorld().getName(),wz1.getBlockX(),wz1.getBlockZ(),wz2.getBlockX(),wz2.getBlockZ());
    }
    /**
     * 用于创建领地
     * */
    public static void chuangjian(String name,String cunzhang,String world,double x1,double z1,double x2,double z2){
        lingdi lingdi = new lingdi();
        lingdi.name = name;
        lingdi.cunzhang = cunzhang;
        lingdi.x1 = x1;
        lingdi.z1 = z1;
        lingdi.x2 = x2;
        lingdi.z2 = z2;
        lingdi.worid = world;
        biao.add(lingdi);
    }
    /**
     * 用于保存全部领地
     * */
    public static void baocun(){
        for(int i = 1;i<biao.size();i++){
            lingdi lingdi = biao.get(i);
            lingdi.baocun();
        }
    }
    /**
     * 用于加载全部领地
     * */
    public static void jiazai(){
        File File = new File(main.getShuju(),"lingdi");
        String[] list = File.list();
        for(int i = 0;i< list.length;i++){
             biao.add(lingdi.duqu(list[i]));
        }
    }

    public static ArrayList<lingdi> getBiao() {
        return biao;
    }
}
