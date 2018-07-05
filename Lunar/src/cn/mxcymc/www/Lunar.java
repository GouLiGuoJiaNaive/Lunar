package cn.mxcymc.www;

import static cn.mxcymc.www.LunarDate.Today;
import static cn.mxcymc.www.LunarDate.getSolarTerms;
import static cn.mxcymc.www.LunarDate.getYear;
import static cn.mxcymc.www.LunarDate.getLunar;
import static cn.mxcymc.www.LunarDate.getLunarPlus;
import static cn.mxcymc.www.LunarDate.getTodayLunar;
import static cn.mxcymc.www.LunarDate.getTodayLunarPlus;
import static cn.mxcymc.www.LunarDate.getTodaySolarTerms;
import static cn.mxcymc.www.LunarDate.getTodayYear;
import java.io.File;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Lunar extends JavaPlugin {
    @Override
    public void onEnable() {		
                say(ChatColor.BLUE+"[农历]"+ChatColor.AQUA+"插件已启动!");
                first();
                say(ChatColor.BLUE+"[农历]"+ChatColor.GREEN+"正在兼容PlaceholderAPI");
                if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                    say(ChatColor.BLUE+"[农历]"+ChatColor.RED+"未找到PlaceholderAPI");
                } else {
                    new regPapi(this).hook();
                    say(ChatColor.BLUE+"[农历]"+ChatColor.GREEN+"已兼容PlaceholderAPI");
                }
    }
    @Override
    public void onDisable() {
		say(ChatColor.RED+"插件关闭!");
	}
    private void first(){
        say(ChatColor.BLUE+"[农历]"+ChatColor.GREEN+"读取config.yml");
        if (!new File(getDataFolder() + File.separator + "config.yml").exists()) {
                        saveDefaultConfig();
                        say(ChatColor.BLUE+"[农历]"+ChatColor.YELLOW + "无法找到config.yml,正在创建");
                } try {
                        reloadConfig();
                    if (!getConfig().getString("version").equals("1.2")){
                        say(ChatColor.BLUE+"[农历]"+ChatColor.RED + "config.yml已经过期，重新生成");
                        new File(getDataFolder() + File.separator + "config.yml").delete();
                        saveDefaultConfig();
                    } else {
                        reloadConfig();
                        say(ChatColor.BLUE+"[农历]"+ChatColor.GREEN + "成功加载config");
                    }
                } catch (Exception e) {
              getServer().getPluginManager().disablePlugin(this);
              say(ChatColor.BLUE+"[农历]"+ChatColor.RED + "无法读取config，加载config.yml失败！");
                }
	}
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
                if(cmd.getName().equalsIgnoreCase("lunar")){
                    if (args.length==0){
                        sender.sendMessage((getConfig().getString("help.header").replaceAll("&", "§")));
                        sender.sendMessage((getConfig().getString("help.date").replaceAll("&", "§").replaceAll("%year%", getTodayYear()).replaceAll("%date%", getTodayLunar()).replaceAll("%festival%", getTodayFestival()).replaceAll("%solar_terms%", getTodaySolarTerms())));
                        sender.sendMessage((getConfig().getString("help.help").replaceAll("&", "§")));
                        sender.sendMessage((getConfig().getString("help.today").replaceAll("&", "§")));
                        sender.sendMessage((getConfig().getString("help.test").replaceAll("&", "§")));
                        sender.sendMessage((getConfig().getString("help.reload").replaceAll("&", "§")));
                        sender.sendMessage((getConfig().getString("help.footer").replaceAll("&", "§")));
                    }
                    if (args.length>0){
                    String s = args[0];
                    if (s.equals("today")) {
                            sender.sendMessage((getConfig().getString("lunar.header").replaceAll("&", "§").replaceAll("%year%", getTodayYear()).replaceAll("%date%", getTodayLunar()).replaceAll("%festival%", getTodayFestival()).replaceAll("%solar_terms%", getTodaySolarTerms())));
                            sender.sendMessage((getConfig().getString("lunar.year").replaceAll("&", "§").replaceAll("%year%", getTodayYear()).replaceAll("%date%", getTodayLunar()).replaceAll("%festival%", getTodayFestival()).replaceAll("%solar_terms%", getTodaySolarTerms())));
                            sender.sendMessage((getConfig().getString("lunar.date").replaceAll("&", "§").replaceAll("%year%", getTodayYear()).replaceAll("%date%", getTodayLunar()).replaceAll("%festival%", getTodayFestival()).replaceAll("%solar_terms%", getTodaySolarTerms())));
                            sender.sendMessage((getConfig().getString("lunar.solar_terms").replaceAll("&", "§").replaceAll("%year%", getTodayYear()).replaceAll("%date%", getTodayLunar()).replaceAll("%festival%", getTodayFestival()).replaceAll("%solar_terms%", getTodaySolarTerms())));
                            sender.sendMessage((getConfig().getString("lunar.festival").replaceAll("&", "§").replaceAll("%year%", getTodayYear()).replaceAll("%date%", getTodayLunar()).replaceAll("%festival%", getTodayFestival()).replaceAll("%solar_terms%", getTodaySolarTerms())));
                            sender.sendMessage((getConfig().getString("lunar.footer").replaceAll("&", "§").replaceAll("%year%", getTodayYear()).replaceAll("%date%", getTodayLunar()).replaceAll("%festival%", getTodayFestival()).replaceAll("%solar_terms%", getTodaySolarTerms())));
                    }
                    if (s.equals("reload")){
                        if(sender.hasPermission("lunar.admin")){
                            onDisable();
                            first();
                            sender.sendMessage((getConfig().getString("prefix").replaceAll("&", "§")+getConfig().getString("reload").replaceAll("&", "§")));
                        }
                        else {
                            sender.sendMessage((getConfig().getString("prefix").replaceAll("&", "§")+getConfig().getString("noperm").replaceAll("&", "§")));
                        }
                    }
                    if (s.equals("custom")) {
                        if (args.length==4) {
                            int y = Integer.parseInt(args[1]);
                            int m = Integer.parseInt(args[2]);
                            int d = Integer.parseInt(args[3]);
                            sender.sendMessage((getConfig().getString("lunar.header").replaceAll("&", "§").replaceAll("%year%", getYear(y,m,d)).replaceAll("%date%", getLunar(y,m,d)).replaceAll("%festival%", getFestival(y,m,d)).replaceAll("%solar_terms%", getSolarTerms(y,m,d))));
                            sender.sendMessage((getConfig().getString("lunar.year").replaceAll("&", "§").replaceAll("%year%", getYear(y,m,d)).replaceAll("%date%", getLunar(y,m,d)).replaceAll("%festival%", getFestival(y,m,d)).replaceAll("%solar_terms%", getSolarTerms(y,m,d))));
                            sender.sendMessage((getConfig().getString("lunar.date").replaceAll("&", "§").replaceAll("%year%", getYear(y,m,d)).replaceAll("%date%", getLunar(y,m,d)).replaceAll("%festival%", getFestival(y,m,d)).replaceAll("%solar_terms%", getSolarTerms(y,m,d))));
                            sender.sendMessage((getConfig().getString("lunar.solar_terms").replaceAll("&", "§").replaceAll("%year%", getYear(y,m,d)).replaceAll("%date%", getLunar(y,m,d)).replaceAll("%festival%", getFestival(y,m,d)).replaceAll("%solar_terms%", getSolarTerms(y,m,d))));
                            sender.sendMessage((getConfig().getString("lunar.festival").replaceAll("&", "§").replaceAll("%year%", getYear(y,m,d)).replaceAll("%date%", getLunar(y,m,d)).replaceAll("%festival%", getFestival(y,m,d)).replaceAll("%solar_terms%", getSolarTerms(y,m,d))));
                            sender.sendMessage((getConfig().getString("lunar.footer").replaceAll("&", "§").replaceAll("%year%", getYear(y,m,d)).replaceAll("%date%", getLunar(y,m,d)).replaceAll("%festival%", getFestival(y,m,d)).replaceAll("%solar_terms%", getSolarTerms(y,m,d))));
                        }
                        else
                            sender.sendMessage((getConfig().getString("prefix").replaceAll("&", "§")+getConfig().getString("usage").replaceAll("&", "§"))); 
                    }  
                    }
                }
                else
                {
                        sender.sendMessage((getConfig().getString("help.header").replaceAll("&", "§")));
                        sender.sendMessage((getConfig().getString("help.date").replaceAll("&", "§").replaceAll("%year%", getTodayYear()).replaceAll("%date%", getTodayLunar()).replaceAll("%festival%", getTodayFestival()).replaceAll("%solar_terms%", getTodaySolarTerms())));
                        sender.sendMessage((getConfig().getString("help.help").replaceAll("&", "§")));
                        sender.sendMessage((getConfig().getString("help.today").replaceAll("&", "§")));
                        sender.sendMessage((getConfig().getString("help.test").replaceAll("&", "§")));
                        sender.sendMessage((getConfig().getString("help.reload").replaceAll("&", "§")));
                        sender.sendMessage((getConfig().getString("help.footer").replaceAll("&", "§")));
                }
                return true;
    }
    public void say(String s){
                ConsoleCommandSender sender = Bukkit.getConsoleSender();
                sender.sendMessage(s);
        }
    public String advanced() {
        if (getConfig().getBoolean("advanced_fastival")==true)
            return "true";
        else
            return "false";
	}

    public static String getFestival(int y, int m, int d) {
                if (getSolarTerms(y,m,d).equalsIgnoreCase("清明"))
                    return "清明节";
                if (getSolarTerms(y,m,d).equalsIgnoreCase("冬至"))
                    return "冬至节";
                if (getLunarPlus(y,m,d).equalsIgnoreCase("大年初一"))
                    return "除夕";
                switch (getLunar(y,m,d).toLowerCase()) {
                    case "大年初一":
                        return "春节";
                    case "正月十五":
                        return "元宵节";
                    case "三月初三":
                        return "上巳节";
                    case "五月初五":
                        return "端午节";
                    case "七月初七":
                        return "七夕节";
                    case "七月十五":
                        return "中元节";
                    case "八月十五":
                        return "中秋节";
                    case "九月初九":
                        return "重阳节";
                    case "十月初一":
                        return "寒衣节";
                    case "十月十五":
                        return "下元节";
                    case "腊月初八":
                        return "腊八节";
                    case "大年廿三":
                        return "小年";
                    case "大年廿四":
                        return "小年";
                    case "大年三十":
                        return "除夕";
                }
        return "";
    }
    public static String getTodayFestival() {
                if (getTodaySolarTerms().equalsIgnoreCase("清明"))
                    return "清明节";
                if (getTodaySolarTerms().equalsIgnoreCase("冬至"))
                    return "冬至节";
                if (getTodayLunarPlus().equalsIgnoreCase("大年初一"))
                    return "除夕";
                switch (getTodayLunar().toLowerCase()) {
                    case "大年初一":
                        return "春节";
                    case "正月十五":
                        return "元宵节";
                    case "三月初三":
                        return "上巳节";
                    case "五月初五":
                        return "端午节";
                    case "七月初七":
                        return "七夕节";
                    case "七月十五":
                        return "中元节";
                    case "八月十五":
                        return "中秋节";
                    case "九月初九":
                        return "重阳节";
                    case "十月初一":
                        return "寒衣节";
                    case "十月十五":
                        return "下元节";
                    case "腊月初八":
                        return "腊八节";
                    case "大年廿三":
                        return "小年";
                    case "大年廿四":
                        return "小年";
                }
        return "";
    }
}