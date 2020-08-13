package cn.jji8.VillageCommunity.lingdi;

import cn.jji8.VillageCommunity.main;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
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
    List<String> cunmin = new ArrayList<String>();//村民
    List<String> chengyuan = new ArrayList<String>();;//成员
    //领地的点坐标
    public double x1;
    public double z1;
    public double x2;
    public double z2;
    public ItemStack biaoshi;//领地标识
    lingdi(){
        biaoshi = new ItemStack(Material.BLUE_BANNER);
    }
    double qian = 0;//领地钱

    /**
     * 一个静态的读取方法，用于读取领地并获得领地对象
     * */
    public static lingdi duqu(String name){
        lingdi lingdi = new lingdi();
        File File = new File(main.getShuju(),"lingdi/"+name+".yml");
        YamlConfiguration Y = YamlConfiguration.loadConfiguration(File);
        lingdi.name = name;
        lingdi.cunzhang = Y.getString("cunzhang");
        lingdi.worid = Y.getString("worid");
        lingdi.cunmin = Y.getStringList("cunmin");
        lingdi.chengyuan = Y.getStringList("chengyuan");
        lingdi.x1 = Y.getInt("x1");
        lingdi.z1 = Y.getInt("z1");
        lingdi.x2 = Y.getInt("x2");
        lingdi.z2 = Y.getInt("z2");
        lingdi.qian = Y.getInt("qian");
        lingdi.biaoshi = Y.getItemStack("biaoshi");
        return lingdi;
    }

    /**
     * 用于保存领地数据
     * */
    public void baocun(){
        File File = new File(main.getShuju(),"lingdi/"+name+".yml");
        YamlConfiguration Y = YamlConfiguration.loadConfiguration(File);
        Y.set("cunzhang",cunzhang);
        Y.set("worid",worid);
        Y.set("x1",x1);
        Y.set("z1",z1);
        Y.set("x2",x2);
        Y.set("z2",z2);
        Y.set("cunmin",cunmin);
        Y.set("chengyuan",chengyuan);
        Y.set("biaoshi",biaoshi);
        Y.set("qian",qian);
        try {
            Y.save(File);
        } catch (IOException e) {
            e.printStackTrace();
            main.getMain().getLogger().warning("村庄数据保存失败，失败村庄："+name);
        }
    }
}
