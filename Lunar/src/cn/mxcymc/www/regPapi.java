/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.mxcymc.www;

import static cn.mxcymc.www.Lunar.getTodayFestival;
import static cn.mxcymc.www.LunarDate.Today;
import static cn.mxcymc.www.LunarDate.getLunar;
import static cn.mxcymc.www.LunarDate.getLunarPlus;
import static cn.mxcymc.www.LunarDate.getTodayLunar;
import static cn.mxcymc.www.LunarDate.getTodayLunarPlus;
import static cn.mxcymc.www.LunarDate.getTodaySolarTerms;
import static cn.mxcymc.www.LunarDate.getTodayYear;
import java.util.Calendar;
import org.bukkit.entity.Player;
import me.clip.placeholderapi.external.EZPlaceholderHook;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Administrator
 */
public class regPapi extends EZPlaceholderHook {
    public regPapi (Lunar s) {
            super(s, "lunar");
    }
     
    @Override
    public String onPlaceholderRequest(Player player,String string) {
            if (player == null) {
                return "";
            }
            if (string.equalsIgnoreCase("today")) {
                return Today();
            }
            if (string.equalsIgnoreCase("year")) {
                return getTodayYear();
            }
            if (string.equalsIgnoreCase("month")) {
                return getTodayLunar();
            }
            if (string.equalsIgnoreCase("solar_terms")) {
                return getTodaySolarTerms();
            }
            if (string.equalsIgnoreCase("festival")) 
                return getTodayFestival();
            return null;
            } 
}
