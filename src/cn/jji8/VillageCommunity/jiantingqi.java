package cn.jji8.VillageCommunity;

import cn.jji8.VillageCommunity.gui.guilist;
import cn.jji8.VillageCommunity.gui.information;
import cn.jji8.VillageCommunity.gui.list;
import cn.jji8.VillageCommunity.lingdi.lingdi;
import cn.jji8.VillageCommunity.lingdi.lingdilist;
import cn.jji8.VillageCommunity.quandi.quandi;
import com.sun.javafx.scene.traversal.ContainerTabOrder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于监听各种事件，将事件传递给其他类处理
 * */
public class jiantingqi implements Listener, CommandExecutor, TabCompleter {
    @Override
    //命令的监听器
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length==0){
            help(commandSender);
            return true;
        }
        switch (strings[0]){
            case "establish":
            case "创建村庄": {//创建村庄
                if(!commandSender.hasPermission("VillageCommunity.establish")){
                    commandSender.sendMessage("你没有执行此命令的权限");
                    return true;
                }
               return cn.jji8.VillageCommunity.Command.创建村庄(commandSender,strings);
            }
            case "村庄列表":
            case "list":{//村庄列表
                if(!commandSender.hasPermission("VillageCommunity.list")){
                    commandSender.sendMessage("你没有执行此命令的权限");
                    return true;
                }
                return cn.jji8.VillageCommunity.Command.村庄列表(commandSender,strings);
            }
            case "帮助":
            case "help":{//帮助
                if(!commandSender.hasPermission("VillageCommunity.help")){
                    commandSender.sendMessage("你没有执行此命令的权限");
                    return true;
                }
                help(commandSender);
                return true;
            }
            case "控制面板":
            case "open":{//打开
                if(!commandSender.hasPermission("VillageCommunity.open")){
                    commandSender.sendMessage("你没有执行此命令的权限");
                    return true;
                }
                return cn.jji8.VillageCommunity.Command.打开(commandSender,strings);
            }
            case "添加成员":
            case "add":{//添加成员
                if(!commandSender.hasPermission("VillageCommunity.add")){
                    commandSender.sendMessage("你没有执行此命令的权限");
                    return true;
                }
                return cn.jji8.VillageCommunity.Command.添加成员(commandSender,strings);
            }
            case "添加管理":
            case "addadmin":{//添加管理
                if(!commandSender.hasPermission("VillageCommunity.addadmin")){
                    commandSender.sendMessage("你没有执行此命令的权限");
                    return true;
                }
                return cn.jji8.VillageCommunity.Command.添加管理(commandSender,strings);
            }
            case "成员信息":
            case "information":{//成员信息
                if(!commandSender.hasPermission("VillageCommunity.information")){
                    commandSender.sendMessage("你没有执行此命令的权限");
                    return true;
                }
                return cn.jji8.VillageCommunity.Command.成员信息(commandSender,strings);
            }
            case "设置村庄出生点":
            case "birthplace":{//设置村庄出生点
                if(!commandSender.hasPermission("VillageCommunity.birthplace")){
                    commandSender.sendMessage("你没有执行此命令的权限");
                    return true;
                }
                return cn.jji8.VillageCommunity.Command.设置村庄出生点(commandSender,strings);
            }
            case "离开村庄":
            case "signout":{//离开村庄
                if(!commandSender.hasPermission("VillageCommunity.signout")){
                    commandSender.sendMessage("你没有执行此命令的权限");
                    return true;
                }
                return cn.jji8.VillageCommunity.Command.离开村庄(commandSender,strings);
            }
            case "申请":
            case "apply":{//申请
                if(!commandSender.hasPermission("VillageCommunity.apply")){
                    commandSender.sendMessage("你没有执行此命令的权限");
                    return true;
                }
                return cn.jji8.VillageCommunity.Command.申请(commandSender,strings);
            }
            default:return false;
        }
    }
    void help(CommandSender commandSender){
        commandSender.sendMessage("/VillageCommunity establish/创建村庄 村庄名字   //创建村庄");
        commandSender.sendMessage("/VillageCommunity open/控制面板                //打开gui");
        commandSender.sendMessage("/VillageCommunity list/村庄列表                //打开村庄列表");
        commandSender.sendMessage("/VillageCommunity modify/改名                 //修改村庄名字");//待完成
        commandSender.sendMessage("/VillageCommunity add/添加成员                 //添加村民");
        commandSender.sendMessage("/VillageCommunity addadmin/添加管理            //添加管理");
        commandSender.sendMessage("/VillageCommunity information/成员信息         //成员信息");
        commandSender.sendMessage("/VillageCommunity birthplace/设置村庄出生点     //设置村庄出生点");
        commandSender.sendMessage("/VillageCommunity signout/离开村庄             //退出或解散村庄");
        commandSender.sendMessage("/VillageCommunity apply/申请 村庄名字           //申请加入一个村庄");
    }
    List<String> ml(){
        ArrayList<String> ArrayList = new ArrayList();
        ArrayList.add("创建村庄");
        ArrayList.add("村庄列表");
        ArrayList.add("帮助");
        ArrayList.add("控制面板");
        ArrayList.add("添加成员");
        ArrayList.add("添加管理");
        ArrayList.add("成员信息");
        ArrayList.add("设置村庄出生点");
        ArrayList.add("离开村庄");

        ArrayList.add("establish");
        ArrayList.add("list");
        ArrayList.add("help");
        ArrayList.add("open");
        ArrayList.add("add");
        ArrayList.add("addadmin");
        ArrayList.add("information");
        ArrayList.add("birthplace");
        ArrayList.add("signout");

        return ArrayList;

    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1){
            return ml();
        }else {
            return new ArrayList<>();
        }
    }

    @EventHandler
    public void wanjiadianji(PlayerInteractEvent a){//玩家点击时触发
        if(a.getItem()==null){//判断玩家手中是否有物品
            return;
        }else if(!a.getItem().getType().equals(Material.BLAZE_ROD)){//判断玩家手中物品是否是选取物品
            return;
        }else if(a.getClickedBlock()==null){//判断点击方块是否是空
            return;
        }
        a.setCancelled(true);
        if(a.getAction().equals(Action.LEFT_CLICK_BLOCK)){//玩家左键一个方块
            if(lingdilist.chaxun(a.getClickedBlock().getLocation())!=null){
                a.getPlayer().sendMessage(main.getMain().getConfig().getString("coincidence"));
                return;
            }
            quandi.xuanze1(a.getPlayer().getName(),a.getClickedBlock().getLocation());
            a.getPlayer().sendTitle(main.getMain().getConfig().getString("position1"),"",10,40,10);
        }else if(a.getAction().equals(Action.RIGHT_CLICK_BLOCK)){//玩家右键一个方块
            if(lingdilist.chaxun(a.getClickedBlock().getLocation())!=null){
                a.getPlayer().sendMessage(main.getMain().getConfig().getString("coincidence"));
                return;
            }
            quandi.xuanze2(a.getPlayer().getName(),a.getClickedBlock().getLocation());
            a.getPlayer().sendTitle(main.getMain().getConfig().getString("position2"),"",10,40,10);

        }
    }
    ArrayList<String> 领地中的玩家 = new ArrayList<String>();
    @EventHandler
    public void wanjia(PlayerMoveEvent a){//玩家移动时触发
        Location Location = a.getTo();
        lingdi lingdi = lingdilist.chaxun(Location);
        if(lingdi==null){
            if(领地中的玩家.contains(a.getPlayer().getName())){
                领地中的玩家.remove(a.getPlayer().getName());
                a.getPlayer().sendTitle(main.getMain().getConfig().getString("leave"),main.getMain().getConfig().getString("leaveX"),10,40,10);
            }
        }else {
            if(!领地中的玩家.contains(a.getPlayer().getName())){
                领地中的玩家.add(a.getPlayer().getName());
                a.getPlayer().sendTitle(main.getMain().getConfig().getString("getinto").replaceAll("%村庄%",lingdi.name),main.getMain().getConfig().getString("getintoX").replaceAll("%村长%",lingdi.cunzhang),10,40,10);
                lingdi.xianshi(a.getPlayer());
            }
        }
    }
    @EventHandler
    public void wanjia(InventoryClickEvent a){//玩家点击物品栏格子时触发
        list.dianji(a);
        guilist.diaji(a);
        information.dianji(a);
    }

    @EventHandler(priority=EventPriority.MONITOR)
    public void wanjia(PlayerRespawnEvent a){//玩家重生时调用
        lingdi lingdi = lingdilist.chaxun(a.getPlayer());
        if(lingdi == null){
            return;
        }
        if(!a.getRespawnLocation().getWorld().getName().equals(lingdi.出生点WORID)){
            return;
        }
        Location Location = new Location(a.getRespawnLocation().getWorld(),lingdi.出生点x,lingdi.出生点y,lingdi.出生点z);
        BukkitRunnable R = new BukkitRunnable() {
            @Override
            public void run() {
                a.getPlayer().teleport(Location);
            }
        };
        R.runTaskLater(main.main,20);
    }

}
