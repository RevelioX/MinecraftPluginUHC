package org.example.tok.uhcdragon.handlers;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.hover.content.Item;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.example.tok.uhcdragon.UHCDragon;
import org.example.tok.uhcdragon.effects.PlaySound;

import java.util.List;

public class DamageHandler implements Listener {

    public DamageHandler(UHCDragon plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void damagePlayer(EntityDamageEvent event){
        if (event.getEntity() instanceof Player) {
        Player player = (Player) event.getEntity();
        Bukkit.broadcastMessage(ChatColor.YELLOW + "[UHC] " + ChatColor.DARK_GREEN + player.getName() + ChatColor.RED
                   + " ha recibido daño! ☠ ");


            Sound sonido = Sound.ENTITY_BREEZE_HURT;
            PlaySound ps = new PlaySound(sonido);
            ps.playSoundexcludingPlayer(player);

       }
    }

    @EventHandler
    public void healByPotion(EntityPotionEffectEvent event){
        if(event.getEntity() instanceof Player){
            if(event.getNewEffect().getType() == PotionEffectType.REGENERATION
            || event.getNewEffect().getType() == PotionEffectType.HEAL){
                Player player = (Player) event.getEntity();
                messageHealPlayer(player);
                Sound sonido = Sound.BLOCK_CONDUIT_ACTIVATE;
                PlaySound ps = new PlaySound(sonido);
                ps.playSoundexcludingPlayer(player);
            }
        }
    }

    public void messageHealPlayer(Player player){
        Bukkit.broadcastMessage(ChatColor.YELLOW + "[UHC] " + ChatColor.DARK_GREEN + player.getName() + ChatColor.GREEN
                + " se ha regenerado! ❤");
    }
}
