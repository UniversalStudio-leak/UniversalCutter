package ru.universalstudio.universalcutter;

import org.bukkit.entity.*;
import me.clip.placeholderapi.*;
import ru.universalstudio.universalcutter.tree.*;
import ru.universalstudio.universalcutter.utils.*;

public class Placeholder extends PlaceholderHook {

    @Override
    public String onPlaceholderRequest(Player player, String placeholder) {
        if (placeholder.equals("salary")) {
            return Utils.format(Salary.getSalary(player));
        } else {
            return placeholder.equals("breaks") ? String.valueOf(TreeStats.getBreaks(player)) : null;
        }
    }

}
