package cn.jji8.VillageCommunity;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * 主要负责操控玩家金钱
 * */
public class money {
    static Economy econ = null;
    /**
     * 插件启动时被调用,用于加载经济
     * */
    public static boolean setupEconomy() {
        if (main.main.getServer().getPluginManager().getPlugin("Vault") == null) {
            System.out.println("没有找到Vault依赖");
            return false;
        }
        RegisteredServiceProvider< Economy > rsp = main.main.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            System.out.println("请安装ess");
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    /**
     * 扣除玩家一定钱
     * 扣除成功返回true
     * 扣除失败或没钱返回false
     * */
    public static boolean kouqian(Player P, int qian){
        if(econ==null){
            return false;
        }
        if(!econ.has(P,qian)){//检查玩家是否有足够的钱
            P.sendMessage("你没有足够的钱，需要："+qian);
            return false;
        }
        EconomyResponse EconomyResponse = econ.withdrawPlayer(P,qian);//尝试扣钱
        if(EconomyResponse.transactionSuccess()){//判断操作是否成功
            P.sendMessage("你花费了："+qian);
            return true;
        }else {
            P.sendMessage("操作失败");
            return false;
        }
    }
}
