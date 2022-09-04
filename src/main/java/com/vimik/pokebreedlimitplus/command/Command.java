package com.vimik.pokebreedlimitplus.command;

import com.vimik.pokebreedlimitplus.PokeBreedLimitPlus;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender Sender, org.bukkit.command.Command command, String s, String[] args) {
        if(Sender instanceof Player){
            Player player = (Player) Sender;
            if(Sender.isOp()){
                if(args.length == 1 && args[0].equalsIgnoreCase("reload")){
                    PokeBreedLimitPlus.main.loadConfig();
                    player.sendMessage("§a插件已重载");
                    return false;
                }else {
                    player.sendMessage("请输入 §a/pblp reload §f进行重载");
                    return false;
                }
            }else {
                player.sendMessage("§c您没有权限嗷");
                return false;
            }
        }else {
            Bukkit.getConsoleSender().sendMessage("§c请以玩家的身份进行执行");
        }
        return false;
    }
}
