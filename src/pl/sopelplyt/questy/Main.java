package pl.sopelplyt.questy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import pl.sopelplyt.questy.cmds.MenuCommand;
import pl.sopelplyt.questy.events.InventoryClick;

public class Main extends JavaPlugin{

	static Main inst;
	public static List<Quest> quests = new ArrayList<Quest>();
	public static Inventory inv = Bukkit.createInventory(null, (6*9), "§lMenu zadan");
	
	@Override
	public void onEnable(){
		inst = this;
		saveDefaultConfig();
		this.getServer().getPluginManager().registerEvents(new InventoryClick(), this);
		Loader.loadQuests();
		FileManager.loadData();
		setInventory();
		this.getCommand("zadania").setExecutor(new MenuCommand());
		this.getCommand("quest").setExecutor(new MenuCommand());
	}
	
	@Override
	public void onDisable(){
		FileManager.saveYml();
	}
	
	void setInventory(){
		int i = 0;
		for(Quest q : Main.quests){
			ItemStack is = q.getItem();
			ItemMeta im = is.getItemMeta();
			im.setDisplayName(ChatColor.translateAlternateColorCodes('&', q.getName()));
			String[] desc = Color(q.getDesc()).split("%n%");
			im.setLore(Arrays.asList(desc));
			is.setItemMeta(im);
			inv.setItem(i, is);
			i++;
		}
	}
	
	public void reload(){
		Bukkit.getPluginManager().getPlugin("SQuest").getServer().reload();
	}
	
	public String Color(String m){
		return ChatColor.translateAlternateColorCodes('&', m);
	}
	
	public static Main getInst(){return inst;}
}
