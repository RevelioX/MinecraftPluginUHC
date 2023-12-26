package org.example.tok.uhcdragon;

import org.bukkit.plugin.java.JavaPlugin;
import org.example.tok.uhcdragon.handlers.DamageHandler;
import org.example.tok.uhcdragon.handlers.LifesHandler;
import org.example.tok.uhcdragon.handlers.ShareLifeHandler;

public final class UHCDragon extends JavaPlugin {

    @Override
    public void onEnable() {
        DamageHandler damageHandler = new DamageHandler(this);
        ShareLifeHandler shareLifeHandler = new ShareLifeHandler(this);
        LifesHandler lifesHandler = new LifesHandler(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
