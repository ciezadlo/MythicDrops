/*
 * Copyright (C) 2013 Richard Harrah
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package net.nunnerycode.bukkit.mythicdrops.managers;

import net.nunnerycode.bukkit.mythicdrops.MythicDrops;
import net.nunnerycode.bukkit.mythicdrops.configuration.MythicConfigurationFile;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class LanguageManager {

    private final MythicDrops plugin;

    public LanguageManager(MythicDrops plugin) {
        this.plugin = plugin;
    }

    public MythicDrops getPlugin() {
        return plugin;
    }

    @SuppressWarnings("unused")
    public void sendMessage(CommandSender reciever, String path) {
        String message = getMessage(path);
        if (message == null) {
            return;
        }
        reciever.sendMessage(message);
    }

    @SuppressWarnings("unused")
    public void sendMessage(CommandSender reciever, String path,
                            String[][] arguments) {
        String message = getMessage(path, arguments);
        if (message == null) {
            return;
        }
        reciever.sendMessage(message);
    }

    public List<String> getStringList(String path) {
        List<String> message =
                getPlugin().getConfigurationManager().getConfiguration(MythicConfigurationFile.LANGUAGE)
                        .getStringList(
                                path);
        List<String> strings = new ArrayList<String>();
        for (String s : message) {
            strings.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        return strings;
    }

    public List<String> getStringList(String path, String[][] arguments) {
        List<String> message =
                getPlugin().getConfigurationManager().getConfiguration(MythicConfigurationFile.LANGUAGE)
                        .getStringList(
                                path);
        List<String> strings = new ArrayList<String>();
        for (String s : message) {
            for (String[] argument : arguments) {
                strings.add(ChatColor.translateAlternateColorCodes('&', s.replace(argument[0], argument[1])));
            }
        }
        return strings;
    }

    public String getMessage(String path) {
        String message =
                getPlugin().getConfigurationManager().getConfiguration(MythicConfigurationFile.LANGUAGE)
                        .getString(
                                path);
        if (message == null) {
            return null;
        }
        message = ChatColor.translateAlternateColorCodes('&', message);
        return message;
    }

    public String getMessage(String path, String[][] arguments) {
        String message =
                getPlugin().getConfigurationManager().getConfiguration(MythicConfigurationFile.LANGUAGE)
                        .getString(
                                path);
        if (message == null) {
            return null;
        }
        message = ChatColor.translateAlternateColorCodes('&', message);
        for (String[] argument : arguments) {
            message = message.replaceAll(argument[0], argument[1]);
        }
        return message;
    }
}