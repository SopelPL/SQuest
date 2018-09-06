package pl.sopelplyt.questy;

import java.util.List;

import org.bukkit.inventory.ItemStack;

public class Quest {

	private int id;
	private String name;
	private String desc;
	private int lvl;
	private ItemStack item;
	private boolean spawnXP;
	private int XP;
	private List<ItemStack> items;
	private List<ItemStack> prices;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	public List<ItemStack> getItems() {
		return items;
	}
	public void setItems(List<ItemStack> items) {
		this.items = items;
	}
	public ItemStack getItem() {
		return item;
	}
	public void setItem(ItemStack item) {
		this.item = item;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<ItemStack> getPrices() {
		return prices;
	}
	public void setPrices(List<ItemStack> prices) {
		this.prices = prices;
	}
	public boolean isSpawnXP() {
		return spawnXP;
	}
	public void setSpawnXP(boolean spawnXP) {
		this.spawnXP = spawnXP;
	}
	public int getXP() {
		return XP;
	}
	public void setXP(int xP) {
		XP = xP;
	}
}
