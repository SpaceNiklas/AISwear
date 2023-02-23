package com.spaceniklas.aiswear;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogFile {
    private static final String LOGS_DIRECTORY_NAME = "/logs";

    File file;
    YamlConfiguration yamlConfiguration;

    public static void put(String uuid, String username, String message, Plugin plugin) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(new Date());
        String fileName = String.format("%s.txt", formattedDate);

        SimpleDateFormat formatter = new SimpleDateFormat("[HH:mm:ss]");
        Date date = new Date();
        String formattedTime = formatter.format(date);

        String formattedMessage = String.format("%s [%s] [%s] [%s] \n", formattedTime, uuid, username, message);
        try {
            File logsDirectory = new File(plugin.getDataFolder() + LOGS_DIRECTORY_NAME);
            if (!logsDirectory.exists()) {
                logsDirectory.mkdir();
            }
            File file = new File(plugin.getDataFolder() + LOGS_DIRECTORY_NAME, fileName);
            FileWriter writer = new FileWriter(file, true);
            writer.write(formattedMessage);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
