package pl.sopelplyt.questy.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import pl.sopelplyt.questy.FileManager;
import pl.sopelplyt.questy.Loader;
import pl.sopelplyt.questy.Main;
import pl.sopelplyt.questy.Quest;

public class InventoryClick implements Listener{

	@EventHandler
	public void onClick(InventoryClickEvent event){
		if(event.getInventory().getName().equals(Main.inv.getName())){
			Player player = (Player) event.getWhoClicked();
			ItemStack clicked = event.getCurrentItem();
			if(!(clicked == null)){
				for(Quest q : Main.quests){
					if(q.getItem().getType().equals(clicked.getType())){
						if(!(Loader.questEnd(player.getName(), q.getId()))){
							player.closeInventory();
							for(ItemStack is : q.getItems()){
								if(!player.getInventory().contains(is.getType(), is.getAmount())){
									player.sendMessage(Main.getInst().Color("&cNie masz wymaganych itemow!"));
									return;
								}
							}
							for(ItemStack is : q.getItems()){
								player.getInventory().removeItem(is);
							}
							for(ItemStack price : q.getPrices()){
								player.getInventory().addItem(price);
							}
							if(q.isSpawnXP()){
								player.giveExp(q.getXP());
							}
							FileManager.cache.set("users." + player.getName(), (FileManager.cache.get("users." + player.getName()) == null) ? Integer.toString(q.getId()) : FileManager.cache.get("users." + player.getName()) + "," + q.getId());
							player.sendMessage(Main.getInst().Color("&aZaliczyles questa " + q.getName()));
							return;
						}else{
							player.sendMessage(Main.getInst().Color("&6Juz wykonales to zadanie!"));
						}
					}
				}
			}
			event.setCancelled(true);
			return;
		}
	}
}
