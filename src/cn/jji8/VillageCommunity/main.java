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
    static Economy econ = null;
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
        if (!setupEconomy()) {
            System.out.println("经济错误");
        }
        saveDefaultConfig();
        getLogger().info("VillageCommunity加载完毕");
    }

    /**
     * 用于加载经济
     * */
    public static boolean setupEconomy() {
        if (main.getServer().getPluginManager().getPlugin("Vault") == null) {
            System.out.println("没有找到Vault依赖");
            return false;
        }
        RegisteredServiceProvider< Economy > rsp = main.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            System.out.println("请安装ess");
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
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
    public static Economy getEcon() {
        return econ;
    }
}
