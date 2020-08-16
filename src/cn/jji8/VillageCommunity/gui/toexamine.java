package cn.jji8.VillageCommunity.gui;

import cn.jji8.VillageCommunity.Command;
import cn.jji8.VillageCommunity.lingdi.lingdi;
import cn.jji8.VillageCommunity.lingdi.lingdilist;
import cn.jji8.VillageCommunity.main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * 主要负责打开申请列表
 * */
public class toexamine {
    static HashMap<String, Inventory> biao = new HashMap<String,Inventory>();
    static HashMap<String, Integer> yeshu = new HashMap<String,Integer>();
    static HashMap<String, lingdi> lingdilista = new HashMap<String,lingdi>();
    /**
     * 给玩家打开申请列表
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
        if(lingdi==null){
            P.sendMessage("你还没有村庄");
            return;
        }
        Inventory xiangzi = org.bukkit.Bukkit.createInventory(null,6*9, main.getMain().getConfig().getString("toexaminelist").replaceAll("%页数%",页数+""));
        biao.put(P.getName(),xiangzi);
        yeshu.put(P.getName(),页数);
        页数--;

        for(int i=45*页数;i<45*页数+45;i++){
            if(i<lingdi.shenqingliebiao.size()){
                int mm = i-(45*页数);
                ItemStack wp = new ItemStack(Material.PLAYER_HEAD);
                ItemMeta wpsj = wp.getItemMeta();
                wpsj.setDisplayName(lingdi.shenqingliebiao.get(i));
                ArrayList A = new ArrayList();
                A.add("§d左键通过，Shift+鼠标左键删除");
                wpsj.setLore(A);
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
            if(guanli|lingdi.cunzhang.equals(a.getWhoClicked().getName())){//村长代码
                int t = yeshu.get(a.getWhoClicked().getName())-1;
                if(a.getRawSlot()+(45*t)>=lingdi.shenqingliebiao.size()){
                    return;
                }
                String shengqingwanjia = lingdi.shenqingliebiao.get(a.getRawSlot()+(45*t));
                if(a.getClick()==ClickType.LEFT){
                    String aaaa[] = {"add",shengqingwanjia};
                    lingdi.shenqingliebiao.remove(shengqingwanjia);
                    Command.添加成员(a.getWhoClicked(),aaaa);
                    lingdi.baocun();
                }else if(a.getClick()==ClickType.SHIFT_LEFT){
                    lingdi.shenqingliebiao.remove(shengqingwanjia);
                    lingdi.baocun();
                    a.getWhoClicked().sendMessage("删除成功");
                }
                dakai((Player) a.getWhoClicked());
                return;
            } else {
                a.getWhoClicked().sendMessage("你不是村庄的村长或管理，不可以进行操作");
                return;
            }

        }
    }
}
