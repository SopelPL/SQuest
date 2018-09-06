package pl.sopelplyt.questy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileManager {

	static File cacheFile;
	public static FileConfiguration cache;
	
	public static void loadData(){
		cacheFile = new File(Main.getInst().getDataFolder(), "cache.yml");
		try{
			firstRun();
		}catch (Exception e){
			Main.getInst().getServer().getConsoleSender().sendMessage("§aNie bylo potrzeby wczytania pliku!");
		}
		cache = new YamlConfiguration();
		loadYml();
	}
	
	static void loadYml(){
		try {
            cache.load(cacheFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void saveYml(){
		try {
            cache.save(cacheFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	static void firstRun() throws Exception {
        if(!cacheFile.exists()){                        
            cacheFile.getParentFile().mkdirs();         
            copy(Main.getInst().getResource("cache.yml"), cacheFile);
        }
    }
	
	static void copy(InputStream in, File file) throws IOException {
		OutputStream out = null;
        try {
            out = new FileOutputStream(file);
	        byte[] buf = new byte[1024];
	        int len;
	        while((len=in.read(buf))>0){
	        	out.write(buf,0,len);
	        }
        } catch (Exception e) {
        	Main.getInst().getServer().getConsoleSender().sendMessage("§aNie bylo potrzeby przeniesienia pliku!");
        }finally{
        	out.close();
	        in.close();
        }
    }
}
