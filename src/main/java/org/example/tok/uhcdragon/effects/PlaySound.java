package org.example.tok.uhcdragon.effects;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

public class PlaySound {
    Sound sound;

    public PlaySound(Sound s){
        sound = s;
    }

    public void playSound(){
        List<Player> players = (List<Player>) Bukkit.getServer().getOnlinePlayers();
        players.stream().forEach( p -> {
                p.playSound(p.getLocation(), sound, 1, 1);
    });
    }

    public void playSoundexcludingPlayer(Player player){
        List<Player> players = (List<Player>) Bukkit.getServer().getOnlinePlayers();
        players.stream().forEach( p -> {
            if(!(p.getName() == player.getName()))
                p.playSound(p.getLocation(), sound, 1, 1);
        });
}}
