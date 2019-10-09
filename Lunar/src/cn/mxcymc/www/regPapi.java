/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.mxcymc.www;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

/**
 *
 * @author Administrator
 */
public class regPapi extends PlaceholderExpansion {

    public Lunar plugin;

    public regPapi(Lunar s) {
        plugin = s;
    }

    @Override
    public String onPlaceholderRequest(Player player, String string) {
        if (string.equalsIgnoreCase("today")) {
            return LunarDate.Today();
        }
        if (string.equalsIgnoreCase("year")) {
            return LunarDate.getTodayYear();
        }
        if (string.equalsIgnoreCase("month")) {
            return LunarDate.getTodayLunar();
        }
        if (string.equalsIgnoreCase("solar_terms")) {
            return LunarDate.getTodaySolarTerms();
        }
        if (string.equalsIgnoreCase("festival")) {
            return Lunar.getTodayFestival();
        }
        return null;
    }

    @Override
    public String getIdentifier() {
        return "lunar";
    }

    @Override
    public String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }
}
