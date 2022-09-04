package com.vimik.pokebreedlimitplus;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class Util {
    public static String parseColor(String str){
        return str.replace("&","§");
    }

    public static void addTime(Player player,Pokemon pokemon){
        File file = new File(PokeBreedLimitPlus.main.getDataFolder()+"/data/",pokemon.getUUID().toString()+".yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        if(!file.exists()){
            try {
                file.createNewFile();
                yamlConfiguration.set("date",day);
                yamlConfiguration.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(yamlConfiguration.getInt("date") != day){
            yamlConfiguration.set("date",day);
            yamlConfiguration.set("times",1);
            try {
                yamlConfiguration.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(PokeBreedLimitPlus.main.getConfig().getBoolean("message.open")){
                player.sendMessage(Util.parseColor(PokeBreedLimitPlus.prefix+PokeBreedLimitPlus.main.getConfig().getString("message.add").replace("{pokemon}",pokemon.getLocalizedName())));
            }
        }else{
            yamlConfiguration.set("times",yamlConfiguration.getInt("times")+1);
            try {
                yamlConfiguration.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(PokeBreedLimitPlus.main.getConfig().getBoolean("message.open")){
                player.sendMessage(Util.parseColor(PokeBreedLimitPlus.prefix+PokeBreedLimitPlus.main.getConfig().getString("message.add").replace("{pokemon}",pokemon.getLocalizedName())));
            }
        }
    }
    public static boolean checkPokemon(Player player, Pokemon pokemon){
        boolean isok = true;
        int times = PokeBreedLimitPlus.main.getConfig().getInt("pfr.default");
        for(String a : PokeBreedLimitPlus.main.getConfig().getConfigurationSection("pfr.permission-limit").getKeys(false)){
            if(player.hasPermission(PokeBreedLimitPlus.main.getConfig().getString("pfr.permission-limit."+a+".permission"))){
                times = PokeBreedLimitPlus.main.getConfig().getInt("pfr.permission-limit."+a+".number");
                break;
            }
        }
        File file = new File(PokeBreedLimitPlus.main.getDataFolder()+"/data/",pokemon.getUUID().toString()+".yml");
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        if(!file.exists()){
            try {
                file.createNewFile();
                yamlConfiguration.set("date",day);
                yamlConfiguration.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(yamlConfiguration.getInt("date")!=day){
            yamlConfiguration.set("date",day);
            yamlConfiguration.set("times",0);
            try {
                yamlConfiguration.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(yamlConfiguration.getInt("times")>=times){
            isok = false;
        }
        return isok;
    }
}
