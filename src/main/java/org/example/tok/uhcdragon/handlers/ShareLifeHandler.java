package org.example.tok.uhcdragon.handlers;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.example.tok.uhcdragon.UHCDragon;
import org.example.tok.uhcdragon.effects.PlaySound;

public class ShareLifeHandler implements Listener {
    public ShareLifeHandler(UHCDragon plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void playerRigthClick(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Player) {
            Player playerA = event.getPlayer();
            Player playerB = (Player) event.getRightClicked();

            // Verifica que la acción sea un clic derecho
            if (event.getRightClicked() != null && event.getRightClicked().equals(playerB) && event.getHand().name().equals("HAND")) {
                if (playerA.getHealth() >= 2.1 && playerB.getHealth() <= 18) {
                    playerA.setHealth(playerA.getHealth() - 2);
                    playerB.setHealth(playerB.getHealth() + 2);
                    Bukkit.broadcastMessage(ChatColor.YELLOW + "[UHC] " + ChatColor.DARK_GREEN + playerA.getName() + ChatColor.GREEN
                            + " ha cedido un corazón ❤ a " + ChatColor.DARK_GREEN + playerB.getName());
                    Sound sound = Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE;
                    PlaySound ps = new PlaySound(sound);
                    ps.playSound();
                }
            }
        }
    }
}