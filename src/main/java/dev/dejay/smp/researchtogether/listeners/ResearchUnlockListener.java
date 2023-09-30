package dev.dejay.smp.researchtogether.listeners;

import dev.dejay.smp.researchtogether.ResearchTogether;
import io.github.thebusybiscuit.slimefun4.api.events.ResearchUnlockEvent;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import net.kyori.adventure.util.Ticks;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.GRAY;
import static net.kyori.adventure.text.format.Style.style;
import static net.kyori.adventure.text.format.TextDecoration.ITALIC;

public class ResearchUnlockListener implements Listener {

    ResearchTogether plugin;

    public ResearchUnlockListener(ResearchTogether plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onResearchUnlock(ResearchUnlockEvent event) {
        Player player = event.getPlayer();
        Research research = event.getResearch();

        plugin.getServer().getOnlinePlayers().forEach(p -> {
            if (player.getUniqueId() == p.getUniqueId()) return;
            plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                research.unlock(p, true, (self) -> {
                    p.sendMessage(
                            text("Your friend,")
                                    .appendSpace()
                                    .append(player.name())
                                    .append(text(","))
                                    .appendSpace()
                                    .append(text("helped you unlock this research."))
                                    .style(style(GRAY, ITALIC))
                    );
                });
            }, Ticks.TICKS_PER_SECOND * 3);
        });

    }

}
