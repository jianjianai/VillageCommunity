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

public class information {
    static HashMap<String, Inventory> biao = new HashMap<String,Inventory>();
    static HashMap<String, Integer> yeshu = new HashMap<String,Integer>();
    static HashMap<String, lingdi> lingdilista = new HashMap<String,lingdi>();
    /**
     * 给玩家打开成员列表
     * */
    public static void dakai(Player P){
        lingdi lingdi = lingdilist.chaxun(P);
        dakai(P,lingdi);
    }
    public static void dakai(Player P,lingdi lingdi){
        lingdilista.put(P.getName(),lingdi);
        dakai(P,1,lingdi);
    }
    public static void dakai(Player P,int 页数){
        lingdi lingdi = lingdilista.get(P.getName());
        if(lingdi==null){
            return;
        }
        dakai(P,页数,lingdi);
    }
    public static void dakai(Player P,int 页数,lingdi lingdi){
        Inventory xiangzi = org.bukkit.Bukkit.createInventory(null,6*9, main.getMain().getConfig().getString("informationlist").replaceAll("%页数%",页数+""));
        biao.put(P.getName(),xiangzi);
        yeshu.put(P.getName(),页数);
        页数--;
        ArrayList<chenyuan> ldbiao = new ArrayList<>();
        ldbiao.add(new chenyuan(lingdi.cunzhang,"村长"));
        for(int i = 0;i<lingdi.guanli.size();i++){
            ldbiao.add(new chenyuan(lingdi.guanli.get(i),"管理"));
        }
        for(int i = 0;i<lingdi.cunmin.size();i++){
            ldbiao.add(new chenyuan(lingdi.cunmin.get(i),"村民"));
        }
        for(int i=45*页数;i<45*页数+45;i++){
            if(i<ldbiao.size()){
                int mm = i-(45*页数);
                ItemStack wp = new ItemStack(Material.PLAYER_HEAD);
                ItemMeta wpsj = wp.getItemMeta();
                wpsj.setDisplayName(ldbiao.get(i).name+"  |  "+ldbiao.get(i).身份);
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
            lingdi lingdi = lingdilista.get(a.getWhoClicked().getName());
            boolean guanli = false;
            for(int i = 0;i<lingdi.guanli.size();i++){
                if(lingdi.guanli.get(i).equals(a.getWhoClicked().getName())){
                    guanli = true;
                }
            }
            if(lingdi.cunzhang.equals(a.getWhoClicked().getName())){
                ArrayList<chenyuan> ldbiao = new ArrayList<>();
                ldbiao.add(new chenyuan(lingdi.cunzhang,"村长"));
                for(int i = 0;i<lingdi.guanli.size();i++){
                    ldbiao.add(new chenyuan(lingdi.guanli.get(i),"管理"));
                }
                for(int i = 0;i<lingdi.cunmin.size();i++){
                    ldbiao.add(new chenyuan(lingdi.cunmin.get(i),"村民"));
                }
                int t = yeshu.get(a.getWhoClicked().getName())-1;
                if(a.getRawSlot()+(45*t)>=ldbiao.size()){
                    return;
                }
                chenyuan C = ldbiao.get(a.getRawSlot()+(45*t));
                boolean AAA = lingdi.cunmin.remove(C.getName());
                boolean BBB = lingdi.guanli.remove(C.getName());
                if(AAA|BBB){
                    a.getWhoClicked().sendMessage(C.getName()+"删除成功");
                    lingdi.baocun();
                }
                information.dakai((Player) a.getWhoClicked(),lingdi);
                return;
            } else  if(guanli){
                ArrayList<chenyuan> ldbiao = new ArrayList<>();
                ldbiao.add(new chenyuan(lingdi.cunzhang,"村长"));
                for(int i = 0;i<lingdi.guanli.size();i++){
                    ldbiao.add(new chenyuan(lingdi.guanli.get(i),"管理"));
                }
                for(int i = 0;i<lingdi.cunmin.size();i++){
                    ldbiao.add(new chenyuan(lingdi.cunmin.get(i),"村民"));
                }
                int t = yeshu.get(a.getWhoClicked().getName())-1;
                if(a.getRawSlot()+(45*t)>=ldbiao.size()){
                    return;
                }
                chenyuan C = ldbiao.get(a.getRawSlot()+(45*t));
                boolean AAA = lingdi.cunmin.remove(C.getName());
                if(AAA){
                    a.getWhoClicked().sendMessage(C.getName()+"删除成功");
                    lingdi.baocun();
                }
                information.dakai((Player) a.getWhoClicked(),lingdi);
                return;
            }else {
                a.getWhoClicked().sendMessage("你不是村庄的村长或管理，不可以进行操作");
                return;
            }

        }
    }
}
