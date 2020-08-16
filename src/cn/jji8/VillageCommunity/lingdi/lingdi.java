package cn.jji8.VillageCommunity.lingdi;

import cn.jji8.VillageCommunity.main;
import cn.jji8.VillageCommunity.quandi.xuanqu;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 主要负责领地的处理
 * */
public class lingdi {
    public String name;//领地名字
    public String cunzhang;//村长名字
    public String worid;//领地的世界
    public List<String> cunmin = new ArrayList<>();//村民
    public List<String> guanli = new ArrayList<>();//成员
    public List<String> shenqingliebiao = new ArrayList<>();//申请列表
    //领地的点坐标
    public double x1;
    public double z1;
    public double x2;
    public double z2;
    public ItemStack biaoshi;//领地标识
    lingdi(){
        biaoshi = new ItemStack(Material.BLUE_BANNER);
    }
    public double qian = 0;//领地钱
    public double 出生点x;
    public double 出生点y;
    public double 出生点z;
    public String 出生点WORID;
    /**
     * 用于删除文件
     * */
    public boolean shanchu(){
        File File = new File(main.getShuju(),"lingdi/"+name);
        return File.delete();
    }
    /**
     * 用于显示领地范围
     * */
    public void xianshi(Player player){
        xuanqu.yibuxianshi(player,x1,z1,x2,z2);
    }

    /**
     * 一个静态的读取方法，用于读取领地并获得领地对象
     * */
    public static lingdi duqu(String name){
        lingdi lingdi = new lingdi();
        File File = new File(main.getShuju(),"lingdi/"+name);
        YamlConfiguration Y = YamlConfiguration.loadConfiguration(File);
        lingdi.name = name;
        lingdi.cunzhang = Y.getString("cunzhang");
        lingdi.worid = Y.getString("worid");
        lingdi.cunmin = Y.getStringList("cunmin");
        lingdi.guanli = Y.getStringList("chengyuan");
        lingdi.shenqingliebiao = Y.getStringList("shenqingliebiao");//申请列表
        lingdi.x1 = Y.getDouble("x1");
        lingdi.z1 = Y.getDouble("z1");
        lingdi.x2 = Y.getDouble("x2");
        lingdi.z2 = Y.getDouble("z2");
        lingdi.qian = Y.getDouble("qian");
        lingdi.biaoshi = Y.getItemStack("biaoshi");
        lingdi.出生点x = Y.getDouble("x");
        lingdi.出生点y = Y.getDouble("y");
        lingdi.出生点z =Y.getDouble("z");
        lingdi.出生点WORID =Y.getString("1WORID");
        return lingdi;
    }

    /**
     * 用于保存领地数据
     * */
    public void baocun(){
        File File = new File(main.getShuju(),"lingdi/"+name);
        YamlConfiguration Y = YamlConfiguration.loadConfiguration(File);
        Y.set("cunzhang",cunzhang);
        Y.set("worid",worid);
        Y.set("x1",x1);
        Y.set("z1",z1);
        Y.set("x2",x2);
        Y.set("z2",z2);
        Y.set("cunmin",cunmin);
        Y.set("chengyuan",guanli);
        Y.set("biaoshi",biaoshi);
        Y.set("qian",qian);
        Y.set("x",出生点x);
        Y.set("y",出生点y);
        Y.set("z",出生点z);
        Y.set("1WORID",出生点WORID);
        Y.set("shenqingliebiao",shenqingliebiao);
        try {
            Y.save(File);
        } catch (IOException e) {
            e.printStackTrace();
            main.getMain().getLogger().warning("村庄数据保存失败，失败村庄："+name);
        }
    }
}
