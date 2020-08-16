package cn.jji8.VillageCommunity.gui;

import cn.jji8.VillageCommunity.lingdi.lingdi;
import cn.jji8.VillageCommunity.lingdi.lingdilist;
import cn.jji8.VillageCommunity.main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 负责给玩家打开村庄列表
 * */
public class list {
    static HashMap<String,Inventory> biao = new HashMap<String,Inventory>();
    static HashMap<String, Integer> yeshu = new HashMap<String,Integer>();
    /**
     * 给玩家打开村庄列表
     * */
    public static void dakai(Player P){
        dakai(P,1);
    }
    public static void dakai(Player P,int 页数){
        Inventory xiangzi = org.bukkit.Bukkit.createInventory(null,6*9,main.getMain().getConfig().getString("createInventoryname").replaceAll("%页数%",页数+""));
        biao.put(P.getName(),xiangzi);
        yeshu.put(P.getName(),页数);
        页数--;
        ArrayList<lingdi> ldbiao = lingdilist.getBiao();
        for(int i=45*页数;i<45*页数+45;i++){
            if(i<ldbiao.size()){
                int mm = i-(45*页数);
                ItemStack wp = new ItemStack(ldbiao.get(i).biaoshi);
                ItemMeta wpsj = wp.getItemMeta();
                wpsj.setDisplayName(ldbiao.get(i).name);
                wp.setItemMeta(wpsj);
                xiangzi.setItem(mm,wp);
            }
        }
        ItemStack wp = new ItemStack(Material.getMaterial(main.getMain().getConfig().getString("previouspageTYPE")));
        ItemMeta wpsj = wp.getItemMeta();
        wpsj.setDisplayName(main.getMain().getConfig().getString("previouspage"));
        wp.setItemMeta(wpsj);
        xiangzi.setItem(main.getMain().getConfig().getInt("previouspageint"),wp);

        ItemStack wp1 = new ItemStack(Material.getMaterial(main.getMain().getConfig().getString("nextpageTYPE")));
        ItemMeta wpsj1 = wp.getItemMeta();
        wpsj1.setDisplayName(main.getMain().getConfig().getString("nextpage"));
        wp1.setItemMeta(wpsj1);
        xiangzi.setItem(main.getMain().getConfig().getInt("nextpageint"),wp1);

        P.openInventory(xiangzi);
    }
    public static void dianji(InventoryClickEvent a){//玩家点击时调用
        Inventory Inventory = biao.get(a.getWhoClicked().getName());
        if(Inventory==null){
            return;
        }
        if(!Inventory.equals(a.getClickedInventory())){
            return;
        }
        a.setCancelled(true);
        if(a.getRawSlot()==main.getMain().getConfig().getInt("nextpageint")){
            int s = yeshu.get(a.getWhoClicked().getName());
            s++;
            yeshu.put(a.getWhoClicked().getName(),s);
            dakai((Player) a.getWhoClicked(),yeshu.get(a.getWhoClicked().getName()));
        }
        if(a.getRawSlot()==main.getMain().getConfig().getInt("previouspageint")){
                int s = yeshu.get(a.getWhoClicked().getName());
                if(s>1){
                    s--;
                }
                yeshu.put(a.getWhoClicked().getName(),s);
                dakai((Player) a.getWhoClicked(),yeshu.get(a.getWhoClicked().getName()));
        }
        if(a.getRawSlot()<45){
            ArrayList<lingdi> ldbiao = lingdilist.getBiao();
            int t = yeshu.get(a.getWhoClicked().getName())-1;
            if(a.getRawSlot()+(45*t)>=ldbiao.size()){
                return;
            }
            lingdi lingdi = ldbiao.get(a.getRawSlot()+(45*t));
            information.dakai((Player) a.getWhoClicked(),lingdi);
            return;
        }
    }
}
