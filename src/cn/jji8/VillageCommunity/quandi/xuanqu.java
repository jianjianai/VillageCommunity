package cn.jji8.VillageCommunity.quandi;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * 用于储存玩家选区
 * */
public class xuanqu {
    Location wz1,wz2;

    /**
     * 异步显示领地范围
     * */
    public void yibuxianshi(Player player){
        Thread T = new Thread(){
            @Override
            public void run() {
                xianshi(player);
            }
        };
        T.start();
    }
    /**
     * 显示领地范围
     * */
    public void xianshi(Player player){
        if(wz1==null|wz2==null){
            return;
        }
        if(!wz1.getWorld().equals(wz2.getWorld())){
            return;
        }

        World World = wz1.getWorld();

        double x1 = wz1.getBlockX();
        double z1 = wz1.getBlockZ();

        double x2 = wz2.getBlockX();
        double z2 = wz2.getBlockZ();
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
        xianshix(player,World,x1,z1,x2);
        xianshiz(player,World,x1,z1,z2);

        xianshix(player,World,x2,z2,x1);
        xianshiz(player,World,x2,z2,z1);
    }
    static void xianshix(Player player, World world, double x, double z, double x2){
        if(x>x2){
            double a = x;
            x = x2;
            x2 = a;
        }
        for(double i = x;i<=x2;i++){
            xianshiy(player,world,i,z);
        }
    }
    static void xianshiz(Player player, World world, double x, double z, double z2){
        if(z>z2){
            double a = z;
            z = z2;
            z2 = a;
        }
        for(double i = z;i<=z2;i++){
            xianshiy(player,world,x,i);
        }
    }
    static void xianshiy(Player player, World world, double x,double z){
        double ywz = player.getLocation().getBlockY();
        double y1 = ywz-3;
        double y2 = ywz+5;
        for(double i = y1;i<=y2;i++){
            Location a = new Location(world,x,i,z);
            player.spawnParticle(Particle.FLAME,a,0);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        }
    }
    public static void yibuxianshi(Player player,double x1,double z1,double x2,double z2){
        Thread T = new Thread(){
            @Override
            public void run() {
                xianshi(player,x1,z1,x2,z2);
            }
        };
        T.start();
    }
    public static void xianshi(Player player,double x1,double z1,double x2,double z2){
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
        xianshix(player,player.getWorld(),x1,z1,x2);
        xianshiz(player,player.getWorld(),x1,z1,z2);
        xianshix(player,player.getWorld(),x2,z2,x1);
        xianshiz(player,player.getWorld(),x2,z2,z1);
    }
}
