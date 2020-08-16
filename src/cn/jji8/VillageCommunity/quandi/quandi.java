package cn.jji8.VillageCommunity.quandi;

import cn.jji8.VillageCommunity.lingdi.lingdilist;
import cn.jji8.VillageCommunity.main;
import org.bukkit.Location;

import java.util.HashMap;

/**
 * 主要负责玩家圈地
 * */
public class quandi {
    static HashMap<String,xuanqu> map = new HashMap();
    /**
     * 玩家确定圈地时调用
     * */
    public static void chuangjian(String name , String ld){
        if(map.containsKey(name)){
            xuanqu xuanqu = map.get(name);
            if(xuanqu.wz1!=null&xuanqu.wz2!=null){
                lingdilist.chuangjian(ld,name,xuanqu.wz1,xuanqu.wz2);
            }else {
                org.bukkit.Bukkit.getPlayer(name).sendMessage(main.getMain().getConfig().getString("Villagecreationfailed"));
            }
        }else {
            org.bukkit.Bukkit.getPlayer(name).sendMessage(main.getMain().getConfig().getString("Villagecreationfailed"));
        }
    }
    /**
     * 玩家选择1点时调用
     * */
    public static void xuanze1(String name, Location wz1){
        xuanqu xuanqu;
        if(map.containsKey(name)){
            xuanqu = map.get(name);
            xuanqu.wz1 = wz1;
        }else {
            xuanqu = new xuanqu();
            xuanqu.wz1 = wz1;
            map.put(name,xuanqu);
        }
        xuanqu.yibuxianshi(org.bukkit.Bukkit.getPlayer(name));
    }
    /**
     * 玩家选择2点时调用
     * */
    public static void xuanze2(String name,Location wz2){
        xuanqu xuanqu;
        if(map.containsKey(name)){
            xuanqu = map.get(name);
            xuanqu.wz2 = wz2;
        }else {
            xuanqu = new xuanqu();
            xuanqu.wz2 = wz2;
            map.put(name,xuanqu);
        }
        xuanqu.yibuxianshi(org.bukkit.Bukkit.getPlayer(name));
    }
}
