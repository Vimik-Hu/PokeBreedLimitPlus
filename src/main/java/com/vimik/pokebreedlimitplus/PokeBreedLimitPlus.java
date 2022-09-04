package com.vimik.pokebreedlimitplus;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.vimik.pokebreedlimitplus.command.Command;
import com.vimik.pokebreedlimitplus.event.PokeListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class PokeBreedLimitPlus extends JavaPlugin {
    public static PokeBreedLimitPlus main;
    public static String prefix;
    @Override
    public void onEnable() {
        main = this;
        loadConfig();
        File f = new File(getDataFolder(),"data");
        if(!f.exists()){
            f.mkdirs();
        }
        getCommand("pokebreedlimitplus").setExecutor(new Command());
        getServer().getPluginManager().registerEvents((Listener) new PokeListener(),this);
        Bukkit.getConsoleSender().sendMessage("§a--------§9§oPokeBreedLimitPlus§7精灵繁殖次数限制插件§a--------");
        Bukkit.getConsoleSender().sendMessage("§b插件作者：Vimik");
        Bukkit.getConsoleSender().sendMessage("§f联系方式：QQ1137748680");
        Bukkit.getConsoleSender().sendMessage("§a--------§9§oPokeBreedLimitPlus§7精灵繁殖次数限制插件§a--------");
        File file = new File(main.getDataFolder(),"config.yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        if(yamlConfiguration.getString("message")==null){
            yamlConfiguration.set("message.nobreed","&a精灵 &f{pokemon} &a今日可繁殖次数已&c达到上限 &a无法再次继续繁殖");
            yamlConfiguration.set("message.noadd","§c精灵 &f{pokemon} &c今日繁殖次数到达限制,暂时无法加入牧场!");
            yamlConfiguration.set("message.add","&e成功繁殖出一只精灵 &9精灵 &f{pokemon}&e 今日繁殖次数 &f+1");
            yamlConfiguration.set("message.open",true);
            yamlConfiguration.set("message.tips","要开启消息提示请将message处改为false");
        }
        if(yamlConfiguration.getString("message.open")==null){
            yamlConfiguration.set("message.open",true);
            yamlConfiguration.set("message.tips","要关闭消息提示请将message处改为false");
        }
        try {
            yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
    }

    public void loadConfig(){
        saveDefaultConfig();
        reloadConfig();
        prefix = Util.parseColor(getConfig().getString("prefix"));
    }
}
