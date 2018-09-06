package pl.sopelplyt.questy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public class Loader {
	
	public static void loadQuests(){
		ConfigurationSection ms = Main.getInst().getConfig().getConfigurationSection("quests");
		for(String s : ms.getKeys(false)){
			Quest quest = new Quest();
			ConfigurationSection section = ms.getConfigurationSection(s);
			int id = section.getInt("id");
			quest.setId(id);
			String name = section.getString("name");
			if(name == null){
				Bukkit.getPluginManager().disablePlugin(Main.getInst());
			}
			quest.setName(name);
			String desc = section.getString("desc");
			if(desc == null){
				Bukkit.getPluginManager().disablePlugin(Main.getInst());
			}
			quest.setDesc(desc);
			int lvl = section.getInt("lvl");
			if(lvl <= 0){
				Bukkit.getPluginManager().disablePlugin(Main.getInst());
			}
			quest.setLvl(lvl);
			Material itemMaterial = Material.matchMaterial(section.getString("item"));
			if(itemMaterial == null){
				Bukkit.getPluginManager().disablePlugin(Main.getInst());
			}
			quest.setItem(new ItemStack(itemMaterial, 1));
			boolean spawnXP = section.getBoolean("spawnXP");
			if(spawnXP){
				int XP = section.getInt("XP");
				if(XP > 0){
					quest.setSpawnXP(true);
					quest.setXP(XP);
				}else{
					Bukkit.getPluginManager().disablePlugin(Main.getInst());
				}
			}else{
				quest.setSpawnXP(false);
			}
			List<ItemStack> items = new ArrayList<ItemStack>();
			for(String i : section.getStringList("items")){
				String[] prop = i.split(":");
				Material m = Material.matchMaterial(prop[0].toUpperCase());
				if(m == null){
					Bukkit.getPluginManager().disablePlugin(Main.getInst());
				}
				ItemStack is = new ItemStack(m, Integer.parseInt(prop[1]));
				items.add(is);
			}
			quest.setItems(items);
			List<ItemStack> prices = new ArrayList<ItemStack>();
			for(String i : section.getStringList("prices")){
				String[] prop = i.split(":");
				Material m = Material.matchMaterial(prop[0].toUpperCase());
				if(m == null){
					Bukkit.getPluginManager().disablePlugin(Main.getInst());
				}
				ItemStack is = new ItemStack(m, Integer.parseInt(prop[1]));
				prices.add(is);
			}
			quest.setPrices(prices);
			Main.quests.add(quest);
		}
	}
	
	public static boolean questEnd(String nickname, int id){
		if(FileManager.cache.getString("users." + nickname) != null){
			List<String> quest = Arrays.asList(FileManager.cache.getString("users." + nickname).split(","));
			if(quest.contains(Integer.toString(id))) return true;
			return false;
		}
		return false;
	}
}
