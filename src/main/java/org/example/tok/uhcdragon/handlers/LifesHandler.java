package org.example.tok.uhcdragon.handlers;

import com.opencsv.exceptions.CsvException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.example.tok.uhcdragon.CsvGestor;
import org.example.tok.uhcdragon.UHCDragon;
import org.example.tok.uhcdragon.auxiliar.Tupla;

import java.io.IOException;
import java.util.List;

public class LifesHandler implements Listener {
    public LifesHandler(UHCDragon plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void playerDeath(PlayerDeathEvent event) throws IOException, CsvException {
        Player player = (Player) event.getEntity();
        if(getRemainingLifes(player) >= 1){
            reduceLifes(player);
            player.sendMessage(ChatColor.RED + "Te quedan " + getRemainingLifes(player) + " vidas.");
        }
        if(getRemainingLifes(player) == 0){
            Bukkit.getBanList(org.bukkit.BanList.Type.NAME).addBan(player.getName(), "Has perdido todas tus vidas.", null, null);
            player.kickPlayer(ChatColor.RED + "Has perdido todas tus vidas.");
        }
    }


    public int getRemainingLifes(Player player) throws IOException, CsvException {
        CsvGestor gestor = new CsvGestor();
            List<Tupla> tuplas = gestor.leerCSV("./lives.csv");
            for (Tupla tupla : tuplas){
                System.out.println(tupla.getIdJugador() + " --- " + player.getUniqueId().toString());
                if(tupla.getIdJugador().equals( player.getUniqueId().toString())){
                    System.out.println("Entre aca");
                    return tupla.getCantVidas();
                }
            }
            gestor.agregarLinea("./lives.csv",player.getUniqueId().toString(), 2);
            return 3;
    }

    public void reduceLifes(Player player) throws IOException, CsvException {
        System.out.println("Estoy reduciendo la vida!");
        CsvGestor gestor = new CsvGestor();
        List<Tupla> tuplas = gestor.leerCSV("./lives.csv");
        for (Tupla tupla : tuplas){
            if(tupla.getIdJugador().equals( player.getUniqueId().toString())){
                int ActualLive = tupla.getCantVidas() - 1;
                gestor.cambiarVidaJugador("./lives.csv", player.getUniqueId().toString(),ActualLive);
            }
        }
    }
}
