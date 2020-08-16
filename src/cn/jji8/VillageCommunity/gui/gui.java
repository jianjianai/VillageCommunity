package cn.jji8.VillageCommunity.gui;

import cn.jji8.VillageCommunity.lingdi.lingdi;
import cn.jji8.VillageCommunity.lingdi.lingdilist;
import cn.jji8.VillageCommunity.main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class gui {
    Inventory xiangzi;
    gui(){
        xiangzi = org.bukkit.Bukkit.createInventory(null,main.getMain().getConfig().getInt("Maininterfacesize")*9,main.getMain().getConfig().getString("Nameofmaininterface"));
    }
    /**
     * 给玩家打开自己的gui
     * */
    void dakai(Player P){
        //显示qui
        ItemStack ItemStack = new ItemStack(Material.getMaterial(main.getMain().getConfig().getString("Fillingitems")));
        ItemMeta ItemMeta = ItemStack.getItemMeta();
        ItemMeta.setDisplayName(main.getMain().getConfig().getString("Fillingitemsname"));
        ItemStack.setItemMeta(ItemMeta);
        for(int i = 0;i<main.getMain().getConfig().getInt("Maininterfacesize")*9;i++){
            xiangzi.setItem(i,ItemStack);
        }
        //填充空位
        xiangzi.setItem(main.getMain().getConfig().getInt("Placementposition"),new ItemStack(Material.AIR));
        //显示换旗帜按钮
        ItemStack = new ItemStack(Material.getMaterial(main.getMain().getConfig().getString("determineposition")));
        ItemMeta = ItemStack.getItemMeta();
        ItemMeta.setDisplayName(main.getMain().getConfig().getString("determinepositionname"));
        ItemStack.setItemMeta(ItemMeta);
        xiangzi.setItem(main.getMain().getConfig().getInt("determinepositionseat"),ItemStack);
        //显示旗帜
        lingdi lingdi = lingdilist.chaxun(P);
        if(lingdi==null){
            ItemStack = new ItemStack(Material.getMaterial(main.getMain().getConfig().getString("NoterritorypositionseatTYPE")));
            ItemMeta = ItemStack.getItemMeta();
            ItemMeta.setDisplayName(main.getMain().getConfig().getString("Noterritorypositionseatname"));
            ItemStack.setItemMeta(ItemMeta);
            xiangzi.setItem(main.getMain().getConfig().getInt("Noterritorypositionseat"),ItemStack);
        }else {
            xiangzi.setItem(main.getMain().getConfig().getInt("Noterritorypositionseat"),lingdi.biaoshi);
        }
        //余额
        ItemStack = new ItemStack(Material.getMaterial(main.getMain().getConfig().getString("Balanceitems")));
        ItemMeta = ItemStack.getItemMeta();
        if(lingdi==null){
            ItemMeta.setDisplayName(main.getMain().getConfig().getString("Balanceitemsname1"));
        }else {
            ItemMeta.setDisplayName(main.getMain().getConfig().getString("Balanceitemsname").replaceAll("%钱%",lingdi.qian+""));
        }
        ItemStack.setItemMeta(ItemMeta);
        xiangzi.setItem(main.getMain().getConfig().getInt("Balanceitemsint"),ItemStack);
        //成员列表
        ItemStack = new ItemStack(Material.getMaterial(main.getMain().getConfig().getString("Memberlist")));
        ItemMeta = ItemStack.getItemMeta();
        ItemMeta.setDisplayName(main.getMain().getConfig().getString("Memberlistname"));
        ItemStack.setItemMeta(ItemMeta);
        xiangzi.setItem(main.getMain().getConfig().getInt("Memberlistint"),ItemStack);

        //申请列表
        ItemStack = new ItemStack(Material.getMaterial(main.getMain().getConfig().getString("apply")));
        ItemMeta = ItemStack.getItemMeta();
        ItemMeta.setDisplayName(main.getMain().getConfig().getString("applyname"));
        ItemStack.setItemMeta(ItemMeta);
        xiangzi.setItem(main.getMain().getConfig().getInt("applyint"),ItemStack);

        P.openInventory(xiangzi);
    }
    /**
     *玩家点击gui做出反应
     * */
    void dianji(InventoryClickEvent a){
        if(xiangzi.equals(a.getClickedInventory())){
            if(main.getMain().getConfig().getInt("Placementposition")!=a.getRawSlot()){
                a.setCancelled(true);
            }
            if(main.getMain().getConfig().getInt("determinepositionseat")==a.getRawSlot()){
                ItemStack ItemStack = xiangzi.getItem(main.getMain().getConfig().getInt("Placementposition"));
                if(ItemStack==null){
                    a.getWhoClicked().sendMessage(main.getMain().getConfig().getString("Noterritory2"));
                    return;
                }
                lingdi lingdi = lingdilist.chaxun((Player) a.getWhoClicked());
                if(lingdi==null){
                    a.getWhoClicked().sendMessage(main.getMain().getConfig().getString("Noterritory"));
                    return;
                }
                boolean guanli = false;
                for(int q = 0;q<lingdi.guanli.size();q++){
                    if(lingdi.guanli.get(q).equals(a.getWhoClicked().getName())){
                        guanli =true;
                    }
                }
                if(lingdi.cunzhang.equals(a.getWhoClicked().getName())|guanli){
                    xiangzi.setItem(main.getMain().getConfig().getInt("Placementposition"),lingdi.biaoshi);
                    lingdi.biaoshi = ItemStack;
                    a.getWhoClicked().sendMessage(main.getMain().getConfig().getString("Replacementsuccessful"));
                    lingdi.baocun();
                    xiangzi.setItem(main.getMain().getConfig().getInt("Noterritorypositionseat"),lingdi.biaoshi);
                    return;
                }else {
                    a.getWhoClicked().sendMessage(main.getMain().getConfig().getString("Noterritory1"));
                    return;
                }
            }
            if(main.getMain().getConfig().getInt("Memberlistint")==a.getRawSlot()){
                lingdi lingdi = lingdilist.chaxun((Player) a.getWhoClicked());
                if(lingdi==null){
                    a.getWhoClicked().sendMessage(main.getMain().getConfig().getString("noMemberlistint"));
                    return;
                }
                information.dakai((Player) a.getWhoClicked());
                return;
            }
            if(main.getMain().getConfig().getInt("applyint")==a.getRawSlot()){
                toexamine.dakai((Player) a.getWhoClicked());
                return;
            }
        }
    }
}
