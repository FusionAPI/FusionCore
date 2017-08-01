package org.cyberpwn.fusion;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.cyberpwn.fusionapi.pack.PackIcon;
import org.cyberpwn.fusionapi.pack.ResourceObject;
import org.cyberpwn.fusionapi.pack.ResourcePack;

public class FusionPlugin extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		Logger l = getLogger();
		l.info("Testing basic resource pack");
		
		String res = "org.cyberpwn.fusion.r";
		ResourcePack pack = new ResourcePack("Wood Sword Pack");
		
		// Add Pack properties
		pack.setIcon(new PackIcon(res, "fusion-icon.png"));
		pack.setDescription("Better Wooden Sword Pack");
		pack.setFormat(3);
		
		// Make a better name for wooden swords
		pack.addLang("item.swordWood.name", "Better Wooden Sword");
		
		// Write Textures
		pack.write().texture().item().add(new ResourceObject(res, "wood_sword_new.png"));
		pack.write().texture().item().add(new ResourceObject(res, "wood_sword_worn.png"));
		pack.write().texture().item().add(new ResourceObject(res, "wood_sword_splintered.png"));
		pack.write().texture().item().add(new ResourceObject(res, "wood_sword_wrecked.png"));
		
		// Sacrifice the sword model for 4 allocation textures
		pack.sacrifice(Material.WOOD_SWORD);
		
		// Allocate textures to 4 sections
		pack.allocate(Material.WOOD_SWORD, "wood_sword_new", 0);
		pack.allocate(Material.WOOD_SWORD, "wood_sword_worn", 1);
		pack.allocate(Material.WOOD_SWORD, "wood_sword_splintered", 2);
		pack.allocate(Material.WOOD_SWORD, "wood_sword_wrecked", 3);
		
		// Write the pack
		pack.writeTo(new File(getDataFolder(), "Wood Sword Pack"));
	}
}
