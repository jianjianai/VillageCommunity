package cn.jji8.VillageCommunity;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class main extends JavaPlugin {
    static main main;//主
    static File shuju;//数据目录
    @Override
    public void onEnable() {
        getLogger().info("VillageCommunity启动");
        main = this;
        shuju = getDataFolder();
        jiantingqi jiantingqi = new jiantingqi();
        Bukkit.getPluginCommand("VillageCommunity").setExecutor(jiantingqi);//注册命令
        Bukkit.getPluginManager().registerEvents(jiantingqi,this);//注册监听器
        getLogger().info("VillageCommunity加载完毕");
    }

    /*
    * set get方法
    * */
    public static cn.jji8.VillageCommunity.main getMain() {
        return main;
    }
    public static File getShuju() {
        return shuju;
    }
}
