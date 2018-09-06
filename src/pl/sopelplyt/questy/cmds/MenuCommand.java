package pl.sopelplyt.questy.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.sopelplyt.questy.FileManager;
import pl.sopelplyt.questy.Main;

public class MenuCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("zadania")){
			if(sender instanceof Player){
				((Player) sender).openInventory(Main.inv);
				return true;
			}
		}
		if(cmd.getName().equalsIgnoreCase("quest")){
			if(sender.hasPermission("questy.admin")){
				if(args.length == 1){
					if(args[0].equalsIgnoreCase("reload")){
						Main.getInst().reload();
						sender.sendMessage(Main.getInst().Color("&2Plugin zostal przeladowany!"));
						return true;
					}
				}else if(args.length == 2){
					if(args[0].equalsIgnoreCase("reset")){
						if(FileManager.cache.getString("users." + args[1]) != null){
							FileManager.cache.set("users." + args[1], null);
							sender.sendMessage("§2Questy zostaly zresetowane!");
							return true;
						}
						sender.sendMessage(Main.getInst().Color("&cTen gracz nie wykonal jeszcze zadnego zadania!"));
						return false;
					}
					if(args[0].equalsIgnoreCase("stats")){
						if(FileManager.cache.getString("users." + args[1]) != null){
							String s = FileManager.cache.getString("users." + args[1]);
							sender.sendMessage(Main.getInst().Color(" "));
							sender.sendMessage(Main.getInst().Color("    &6" + args[1] + " &awykonal zadania numer:"));
							sender.sendMessage(Main.getInst().Color(" "));
							sender.sendMessage(Main.getInst().Color("    &l" + s));
							sender.sendMessage(Main.getInst().Color(" "));
							return true;
						}
						sender.sendMessage(Main.getInst().Color("&cTen gracz nie wykonal jeszcze zadnego zadania!"));
						return false;
					}
					sender.sendMessage(Main.getInst().Color("&7Nieprawidlowe uzycie! &6/quest reset <nick>"));
					return false;
				}
				sender.sendMessage(Main.getInst().Color("&4Nieprawidlowa liczba argumentow!"));
				return false;
			}
			sender.sendMessage(Main.getInst().Color("&4Brak uprawnien!"));
			return false;
		}
		return false;
	}

	
}