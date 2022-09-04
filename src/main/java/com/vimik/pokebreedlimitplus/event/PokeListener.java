package com.vimik.pokebreedlimitplus.event;

import catserver.api.bukkit.ForgeEventV2;
import com.pixelmonmod.pixelmon.api.daycare.event.DayCareEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.vimik.pokebreedlimitplus.PokeBreedLimitPlus;
import com.vimik.pokebreedlimitplus.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PokeListener implements Listener {
    @EventHandler
    public void onForgeEvent(ForgeEventV2 event) {
        if (event.getForgeEvent() instanceof DayCareEvent.PostCollect) {
            DayCareEvent.PostCollect e = (DayCareEvent.PostCollect) event.getForgeEvent();
            if(e.getChildGiven()!=null){
                Player player =Bukkit.getPlayer( e.getPlayer().func_110124_au());
                Util.addTime(player,e.getParentOne());
                Util.addTime(player,e.getParentTwo());
            }

        }
        if (event.getForgeEvent() instanceof DayCareEvent.PrePokemonAdd) {
            DayCareEvent.PrePokemonAdd e = (DayCareEvent.PrePokemonAdd) event.getForgeEvent();
            Player player = Bukkit.getPlayer(e.getPlayer().func_110124_au());
            Pokemon pokemon1 = e.getParentOne();
            Pokemon pokemon2 = e.getParentTwo();
            if(!Util.checkPokemon(player,pokemon1) || !Util.checkPokemon(player,pokemon2)){
                if(PokeBreedLimitPlus.main.getConfig().getBoolean("message.open")){
                    if(!Util.checkPokemon(player,pokemon1)){
                        player.sendMessage(Util.parseColor(PokeBreedLimitPlus.prefix+PokeBreedLimitPlus.main.getConfig().getString("message.nobreed").replace("{pokemon}",pokemon1.getLocalizedName())));
                    }
                    if(!Util.checkPokemon(player,pokemon2)){
                        player.sendMessage(Util.parseColor(PokeBreedLimitPlus.prefix+PokeBreedLimitPlus.main.getConfig().getString("message.nobreed").replace("{pokemon}",pokemon2.getLocalizedName())));
                    }
                }
                e.setCanceled(true);
            }
        }
    }

}
