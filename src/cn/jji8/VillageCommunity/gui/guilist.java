package cn.jji8.VillageCommunity.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;

/**
 * 用于负责每个玩家的gui
 * */
public class guilist {
    static HashMap<String,gui> biao = new HashMap<String, gui>();//储存每个玩家的gui
    /**
     * 为指定玩家打开gui
     * */
    public static void dakai(Player P){
        if(biao.containsKey(P.getName())){
            biao.get((P.getName())).dakai(P);
        }else {
            biao.put(P.getName(),new gui());
            biao.get((P.getName())).dakai(P);
        }
    }
    public static void diaji(InventoryClickEvent a){
        if(biao.containsKey(a.getWhoClicked().getName())){
            biao.get((a.getWhoClicked().getName())).dianji(a);
        }else {
            biao.put(a.getWhoClicked().getName(),new gui());
            biao.get((a.getWhoClicked().getName())).dianji(a);
        }
    }
}
