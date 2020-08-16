package cn.jji8.VillageCommunity;

import cn.jji8.VillageCommunity.lingdi.lingdilist;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
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
        lingdilist.jiazai();//加载全部村庄
        Bukkit.getPluginCommand("VillageCommunity").setExecutor(jiantingqi);//注册命令
        Bukkit.getPluginCommand("VillageCommunity").setTabCompleter(jiantingqi);//注册命令补全器
        Bukkit.getPluginManager().registerEvents(jiantingqi,this);//注册监听器
        if (!money.setupEconomy()) {
            System.out.println("经济错误");
        }
        saveDefaultConfig();
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
