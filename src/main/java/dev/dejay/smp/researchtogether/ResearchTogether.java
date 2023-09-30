package dev.dejay.smp.researchtogether;

import dev.dejay.smp.researchtogether.commands.ShareKnowledgeCmd;
import dev.dejay.smp.researchtogether.listeners.ResearchUnlockListener;
import dev.jorel.commandapi.CommandAPI;
import org.bukkit.plugin.java.JavaPlugin;

public final class ResearchTogether extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ResearchUnlockListener(this), this);

        CommandAPI.registerCommand(ShareKnowledgeCmd.class);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
