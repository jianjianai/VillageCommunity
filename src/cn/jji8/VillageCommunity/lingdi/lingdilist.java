package cn.jji8.VillageCommunity.lingdi;

import cn.jji8.VillageCommunity.main;
import org.apache.logging.log4j.core.appender.AbstractOutputStreamAppender;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 主要负责领地的读取和保存
 * */
public class lingdilist {
    static ArrayList<lingdi> biao = new ArrayList<lingdi>();//领地列表
    public static void chuangjian(String name, String cunzhang,Location wz1,Location wz2){
        if(!wz1.getWorld().equals(wz2.getWorld())){
            return;
        }
        chuangjian(name,cunzhang,wz1.getWorld().getName(),wz1.getBlockX(),wz1.getBlockZ(),wz2.getBlockX(),wz2.getBlockZ());
    }
    /**
     * 用于删除村庄
     * */
    public static boolean shanchu(lingdi a){
       return biao.remove(a)&a.shanchu();
    }
    /**
     * 用于创建领地
     * */
    public static void chuangjian(String name,String cunzhang,String world,double x1,double z1,double x2,double z2){
        if(chaxun(name)!=null){
            org.bukkit.Bukkit.getPlayer(cunzhang).sendMessage(main.getMain().getConfig().getString("Thereisalreadyavillagewiththesamename").replaceAll("%name%",name));
            return;
        }
        if(chaxun(x1,z1,x2,z2)!=null){
            org.bukkit.Bukkit.getPlayer(cunzhang).sendMessage(main.getMain().getConfig().getString("Youcantoverlapwithothervillages"));
            return;
        }
        if(chognhe(world,x1,z1,x2,z2)){
            org.bukkit.Bukkit.getPlayer(cunzhang).sendMessage(main.getMain().getConfig().getString("Youcantoverlapwithothervillages"));
            return;
        }
        lingdi lingdi = new lingdi();
        lingdi.name = name;
        lingdi.cunzhang = cunzhang;
        lingdi.x1 = x1;
        lingdi.z1 = z1;
        lingdi.x2 = x2;
        lingdi.z2 = z2;
        lingdi.worid = world;
        biao.add(lingdi);
        lingdi.baocun();
        org.bukkit.Bukkit.getPlayer(cunzhang).sendMessage(main.getMain().getConfig().getString("Createdsuccessfully"));
    }
    /**
     * 用于判断领地是否和其他领地重合
     * 返回true代表重合
     * */
    public static boolean chognhe(String world1,double xx,double zz,double xxx,double zzz){

        String world = world1;
        double x1 = xx;
        double z1 = zz;
        double x2 = xxx;
        double z2 = zzz;
        if(x1>x2){
            x1++;
        }else {
            x2++;
        }
        if(z1>z2){
            z1++;
        }else {
            z2++;
        }
        if(chognhex(world,x1,z1,x2)| chognhexz(world,x1,z1,z2)| chognhex(world,x2,z2,x1)| chognhexz(world,x2,z2,z1)){
            return true;
        }else {
            return false;
        }
    }
    static boolean chognhex(String world, double x, double z, double x2){
        if(x>x2){
            double a = x;
            x = x2;
            x2 = a;
        }
        for(double i = x;i<=x2;i++){
            if(chaxun(world,i,z)!=null){
                return true;
            }
        }
        return false;
    }
    static boolean  chognhexz(String world, double x, double z, double z2){
        if(z>z2){
            double a = z;
            z = z2;
            z2 = a;
        }
        for(double i = z;i<=z2;i++){
            if(chaxun(world,x,i)!=null){
                return true;
            }
        }
        return false;
    }
    /**
     * 查询任意一个点在选区坐标中的村庄
     * */
    public static lingdi chaxun(double xx,double zz,double xxx,double zzz){
        for(int i=0;i<biao.size();i++){
            if(xx>xxx){
                double a = xx;
                xx = xxx;
                xxx = a;
            }
            if(zz>zzz){
                double a = zz;
                zz = zzz;
                zzz = a;
            }
            lingdi lingdi = biao.get(i);
            double x1 =lingdi.x1;
            double z1 =lingdi.z1;
            double x2 =lingdi.x2;
            double z2 =lingdi.z2;
            if(xx<=x1 & x1<=xxx & zz<=z1 & z1<=zzz){
                return lingdi;
            }
            if(xx<=x2 & x2<=xxx & zz<=z2 & z2<=zzz){
                return lingdi;
            }
        }
        return null;
    }
    /**
     * 通过位置查询村庄
     * */
    public static lingdi chaxun(Location a){
       return chaxun(a.getWorld().getName(),a.getX(),a.getZ());
    }
    /**
     * 通过坐标查询村庄
     * 没有返回null
     * */
    public static lingdi chaxun(String world,double x,double z){
        for(int i=0;i<biao.size();i++){
            lingdi lingdi = biao.get(i);
            double x1 =lingdi.x1;
            double z1 =lingdi.z1;
            double x2 =lingdi.x2;
            double z2 =lingdi.z2;
            if(x1>x2){
                double a = x1;
                x1 = x2;
                x2 = a;
            }
            if(z1>z2){
                double a = z1;
                z1 = z2;
                z2 = a;
            }
            if(x1<=x&x<=x2){
                if(z1<=z&z<=z2){
                    return lingdi;
                }
            }
        }
        return null;
    }
    /**
     * 通过村庄名字查询村庄
     * 没有返回null
     * */
    public static lingdi chaxun(String name){
        for(int i=0;i<biao.size();i++){
            if(biao.get(i).name.equals(name)){
                return biao.get(i);
            }
        }
        return null;
    }
    public static lingdi chaxun(Player name){
        return chaxunname(name.getName());
    }
    /**
     * 通过玩家名字查询
     * */
    public static lingdi chaxunname(String name){
        for(int i=0;i<biao.size();i++){
            if(biao.get(i).cunzhang.equals(name)){
                return biao.get(i);
            }
            for (int ii = 0;ii<biao.get(i).cunmin.size();ii++ ){
                if(biao.get(i).cunmin.get(ii).equals(name)){
                    return biao.get(i);
                }
            }
            for (int ii = 0;ii<biao.get(i).guanli.size();ii++ ){
                if(biao.get(i).guanli.get(ii).equals(name)){
                    return biao.get(i);
                }
            }
        }
        return null;
    }
    /**
     * 用于保存全部领地
     * */
    public static void baocun(){
        for(int i = 1;i<biao.size();i++){
            lingdi lingdi = biao.get(i);
            lingdi.baocun();
        }
    }
    /**
     * 用于加载全部领地
     * */
    public static void jiazai(){
        File File = new File(main.getShuju(),"lingdi");
        String[] list = File.list();
        if(list==null){
            return;
        }
        for(int i = 0;i< list.length;i++){
             biao.add(lingdi.duqu(list[i]));
            System.out.println("已加载村庄："+list[i]);
        }
    }

    public static ArrayList<lingdi> getBiao() {
        return biao;
    }
}
