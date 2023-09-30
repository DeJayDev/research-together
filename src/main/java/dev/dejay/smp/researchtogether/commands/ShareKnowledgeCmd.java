package dev.dejay.smp.researchtogether.commands;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Subcommand;
import dev.jorel.commandapi.annotations.arguments.APlayerArgument;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.GREEN;
import static net.kyori.adventure.text.format.NamedTextColor.RED;

@Command("research")
public class ShareKnowledgeCmd {

    @Default
    public static void research(CommandSender sender) {
        sender.sendMessage("Use: /research with|take");
    }

    @Subcommand("give")
    public static void researchGive(Player sender, @APlayerArgument Player target) {
        if(sender.getUniqueId() == target.getUniqueId()) {
            sender.sendMessage(
                    text("You cannot give knowledge to yourself!", RED)
            );
            return;
        }

        PlayerProfile.get(sender, senderProfile -> {
            PlayerProfile.get(target, targetProfile -> {
                copyResearch(senderProfile, targetProfile);
            });
        });

        sender.sendMessage(
                text("You've shared research with")
                        .appendSpace()
                        .append(target.name())
                        .append(text("!"))
                        .color(GREEN)
        );
    }

    @Subcommand("take")
    public static void researchTake(Player sender, @APlayerArgument Player target) {
        if(sender.getUniqueId() == target.getUniqueId()) {
            sender.sendMessage(
                    text("You cannot take knowledge from yourself!", RED)
            );
            return;
        }

        PlayerProfile.get(sender, senderProfile -> {
            PlayerProfile.get(target, targetProfile -> {
                copyResearch(targetProfile, senderProfile);
            });
        });

        sender.sendMessage(
                text("You've gained research from")
                        .appendSpace()
                        .append(target.name())
                        .append(text("!"))
                        .color(GREEN)
        );
    }

    public static void copyResearch(PlayerProfile source, PlayerProfile destination) {
        for (Research research : source.getResearches()) {
            research.unlock(destination.getPlayer(), true);
        }
    }


}
