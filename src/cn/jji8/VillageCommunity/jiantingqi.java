package cn.jji8.VillageCommunity;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * 用于监听各种事件，将事件传递给其他类处理
 * */
public class jiantingqi implements Listener, CommandExecutor {
    @Override
    //命令的监听器
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        switch (strings[0]){
            case "chuangjian":
            case "poen"://调用打开gui方法
            default://调用打开gui方法
        }
        return true;
    }

    @EventHandler
    public void wanjiadianji(PlayerInteractEvent a){//玩家点击时触发
        if(a.getItem()==null){//判断玩家手中是否有物品
            return;
        }else if(!a.getItem().getType().equals(Material.ACACIA_BOAT)){//判断玩家手中物品是否是选取物品
            return;
        }else if(a.getClickedBlock()==null){//判断点击方块是否是空
            return;
        }
        a.setCancelled(true);
        if(a.getAction().equals(Action.LEFT_CLICK_BLOCK)){//玩家左键一个方块

        }else if(a.getAction().equals(Action.RIGHT_CLICK_BLOCK)){//玩家右键一个方块

        }
    }
}
