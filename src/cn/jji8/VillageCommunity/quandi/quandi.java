package cn.jji8.VillageCommunity.quandi;

import cn.jji8.VillageCommunity.lingdi.lingdilist;
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
                org.bukkit.Bukkit.getPlayer(name).sendTitle("创建失败","需要选择两个点哦",10,40,10);
            }
        }else {
            org.bukkit.Bukkit.getPlayer(name).sendTitle("创建失败","你需要先选区",10,40,10);
        }
    }
    /**
     * 玩家选择1点时调用
     * */
    public static void xuanze1(String name, Location wz1){
        if(map.containsKey(name)){
           map.get(name).wz1 = wz1;
        }else {
            xuanqu xuanqu = new xuanqu();
            xuanqu.wz1 = wz1;
        }
    }
    /**
     * 玩家选择2点时调用
     * */
    public static void xuanze2(String name,Location wz2){
        if(map.containsKey(name)){
            map.get(name).wz2 = wz2;
        }else {
            xuanqu xuanqu = new xuanqu();
            xuanqu.wz2 = wz2;
        }
    }
}
