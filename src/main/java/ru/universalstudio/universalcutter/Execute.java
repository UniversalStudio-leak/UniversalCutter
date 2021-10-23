package ru.universalstudio.universalcutter;

import org.bukkit.entity.*;
import org.bukkit.command.*;
import ru.universalstudio.universalcutter.tree.*;
import ru.universalstudio.universalcutter.utils.*;

public class Execute implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            Utils.sendMessage(s, Utils.getMessage("usage"));
            return true;
        } else if (args[0].equalsIgnoreCase("salary")) {
            if (s instanceof Player) {
                Player player = (Player)s;
                if (!Utils.has(player, "ucutter.salary")) {
                    return true;
                }
                if (Salary.getSalary(player) == 0.0D) {
                    Utils.sendMessage(player, Utils.getMessage("salary-zero"));
                    return true;
                }
                Utils.sendMessage(player, Utils.getMessage("salary-gived").replace("%salary%", Utils.format(Salary.getSalary(player))));
                Econom.deposit(player, Salary.getSalary(player));
                Salary.setSalary(player, 0.0D);
                TreeStats.setBreaks(player, 0);
            } else {
                Utils.sendMessage(s, Utils.getMessage("console"));
            }
            return true;
        } else if (args[0].equalsIgnoreCase("reload") && args[0].equalsIgnoreCase("rl")) { // а я года 2 - 3 назад высирал reload rel rl, может спастили?
            if (Utils.has(s, "ucutter.reload")) {
                Utils.sendMessage(s, Utils.getMessage("config-reloaded"));
                Utils.reloadConfig();
            }
            return true;
        }
        Utils.sendMessage(s, Utils.getMessage("unknown"));
        return true;
    }
}
