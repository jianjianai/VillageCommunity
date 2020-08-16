package cn.jji8.VillageCommunity;

import cn.jji8.VillageCommunity.gui.guilist;
import cn.jji8.VillageCommunity.gui.information;
import cn.jji8.VillageCommunity.gui.list;
import cn.jji8.VillageCommunity.lingdi.lingdi;
import cn.jji8.VillageCommunity.lingdi.lingdilist;
import cn.jji8.VillageCommunity.quandi.quandi;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * 专注于处理命令
 * */
public class Command {
    public static boolean 创建村庄(CommandSender commandSender, String[] strings){
        if(strings.length<2){
            commandSender.sendMessage("/VillageCommunity chuangjian 村庄名字");
            return true;
        }
        lingdi lingdi = lingdilist.chaxun((Player) commandSender);
        if(lingdi==null){
            quandi.chuangjian(commandSender.getName(),strings[1]);
            return true;
        }
        commandSender.sendMessage("你已经有村庄了");
        return true;
    }
    public static boolean 村庄列表(CommandSender commandSender, String[] strings){
        if(commandSender instanceof Player){
            list.dakai((Player) commandSender);
            return true;
        }
        commandSender.sendMessage("此命令只可以玩家执行");
        return true;
    }
    public static boolean 打开(CommandSender commandSender, String[] strings){
        if(commandSender instanceof Player){
            guilist.dakai((Player) commandSender);
            return true;
        }
        commandSender.sendMessage("此命令只可以玩家执行");
        return true;
    }
    public static boolean 添加成员(CommandSender commandSender, String[] strings){
        if(strings.length<2){
            commandSender.sendMessage("/VillageCommunity add 玩家名字");
            return true;
        }
        if(commandSender instanceof Player){
            lingdi lingdi = lingdilist.chaxun((Player) commandSender);
            if(lingdi==null){
                commandSender.sendMessage("你还没有村庄");
                return true;
            }
            boolean guanli = false;
            for(int i = 0;i<lingdi.guanli.size();i++){
                if(lingdi.guanli.get(i).equals(commandSender.getName())){
                    guanli = true;
                }
            }
            if(guanli|lingdi.cunzhang.equals(commandSender.getName())){
                if(lingdilist.chaxunname(strings[1])!=null){
                    commandSender.sendMessage("这个玩家已经加入其他村庄了");
                    return true;
                }
                lingdi.cunmin.add(strings[1]);
                commandSender.sendMessage("添加成功");
                lingdi.baocun();
                return true;
            }else {
                commandSender.sendMessage("只有村长和管理可以添加村民");
            }
            return true;
        }
        commandSender.sendMessage("此命令只可以玩家执行");
        return true;
    }
    public static boolean 添加管理(CommandSender commandSender, String[] strings){
        if(strings.length<2){
            commandSender.sendMessage("/VillageCommunity addadmin 玩家名字");
            return true;
        }
        if(commandSender instanceof Player){
            lingdi lingdi = lingdilist.chaxun((Player) commandSender);
            if(lingdi==null){
                commandSender.sendMessage("你还没有村庄");
                return true;
            }
            if(lingdi.cunzhang.equals(commandSender.getName())){
                if(lingdilist.chaxunname(strings[1])!=null){
                    commandSender.sendMessage("这个玩家已经加入其他村庄了");
                    return true;
                }
                lingdi.guanli.add(strings[1]);
                commandSender.sendMessage("添加成功");
                lingdi.baocun();
                return true;
            }else {
                commandSender.sendMessage("只有村长添加管理");
            }
            return true;
        }
        commandSender.sendMessage("此命令只可以玩家执行");
        return true;
    }
    public static boolean 成员信息(CommandSender commandSender, String[] strings){
        if(commandSender instanceof Player) {
            lingdi lingdi = lingdilist.chaxun((Player) commandSender);
            if (lingdi == null) {
                commandSender.sendMessage("你还没有村庄");
                return true;
            }
            information.dakai((Player) commandSender);
        }
        commandSender.sendMessage("此命令只可以玩家执行");
        return true;
    }
    public static boolean 设置村庄出生点(CommandSender commandSender, String[] strings){
        if(commandSender instanceof Player){
            lingdi lingdi = lingdilist.chaxun((Player) commandSender);
            if(lingdi==null){
                commandSender.sendMessage("你还没有村庄");
                return true;
            }
            boolean guanli = false;
            for(int i = 0;i<lingdi.guanli.size();i++){
                if(lingdi.guanli.get(i).equals(commandSender.getName())){
                    guanli = true;
                }
            }
            if(guanli|lingdi.cunzhang.equals(commandSender.getName())){
                lingdi lingdi1 = lingdilist.chaxun(((Player)commandSender).getLocation());
                if(lingdi1!=lingdi){
                    commandSender.sendMessage("出生点只可以设置在村庄里面");
                    return true;
                }
                lingdi.出生点x = ((Player)commandSender).getLocation().getX();
                lingdi.出生点y = ((Player)commandSender).getLocation().getY();
                lingdi.出生点z = ((Player)commandSender).getLocation().getZ();
                lingdi.出生点WORID = ((Player)commandSender).getLocation().getWorld().getName();
                commandSender.sendMessage("设置成功");
                lingdi.baocun();
                return true;
            }else {
                commandSender.sendMessage("只有村长和管理可以设置出生点");
            }
            return true;
        }
        commandSender.sendMessage("此命令只可以玩家执行");
        return true;
    }
    public static boolean 离开村庄(CommandSender commandSender, String[] strings){
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage("此命令只可以玩家执行");
            return true;
        }
        lingdi lingdi = lingdilist.chaxun((Player) commandSender);
        if(lingdi==null){
            commandSender.sendMessage("你还没有村庄");
            return true;
        }
        boolean guanli = false;
        for(int i = 0;i<lingdi.guanli.size();i++){
            if(lingdi.guanli.get(i).equals(commandSender.getName())){
                guanli = true;
            }
        }
        if(guanli){
            lingdi.guanli.remove(commandSender.getName());
            commandSender.sendMessage("你已放弃管理并且退出村庄");
            lingdi.baocun();
            return true;
        }
        boolean cunmin = false;
        for(int i = 0;i<lingdi.cunmin.size();i++){
            if(lingdi.cunmin.get(i).equals(commandSender.getName())){
                cunmin = true;
            }
        }
        if(cunmin){
            lingdi.cunmin.remove(commandSender.getName());
            commandSender.sendMessage("你已退出村庄");
            lingdi.baocun();
            return true;
        }
        if(lingdi.cunzhang.equals(commandSender.getName())){
            if(lingdilist.shanchu(lingdi)){
                commandSender.sendMessage("你已解散村庄");
            }else {
                commandSender.sendMessage("解散村庄出错");
            }
            return true;
        }
        return true;
    }
    public static boolean 申请(CommandSender commandSender, String[] strings){
        if(strings.length!=2){
            commandSender.sendMessage("/VillageCommunity apply/申请 村庄名字           //申请加入一个村庄");
            return true;
        }
        if(lingdilist.chaxunname(commandSender.getName())!=null){
            commandSender.sendMessage("你已经有一个村庄了");
            return true;
        }
        lingdi lingdi = lingdilist.chaxun(strings[1]);
        if(lingdi==null){
            commandSender.sendMessage("你输入的村庄名字有误");
            return true;
        }
        lingdi.shenqingliebiao.add(commandSender.getName());
        lingdi.baocun();
        commandSender.sendMessage("已申请，等待管理审核");
        //提醒在线村长
        Player cunzhang = org.bukkit.Bukkit.getPlayerExact(lingdi.cunzhang);
        if(cunzhang==null){
            return true;
        }
        cunzhang.sendMessage("玩家"+commandSender.getName()+"申请加入你的村庄，打开管理面板即可处理");
        //提醒在线管理
        for(int i = 0;i<lingdi.guanli.size();i++){
            Player guanli = org.bukkit.Bukkit.getPlayerExact(lingdi.guanli.get(i));
            if(guanli==null){
                return true;
            }
            guanli.sendMessage("玩家"+commandSender.getName()+"申请加入你的村庄，打开管理面板即可处理");
        }
        return true;
    }
}
