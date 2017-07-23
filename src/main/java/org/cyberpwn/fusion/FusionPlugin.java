package org.cyberpwn.fusion;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;
import org.cyberpwn.fusion.resource.FusionResourceHolder;
import org.cyberpwn.fusionapi.Fusion;

public class FusionPlugin extends JavaPlugin
{
	private static FusionPlugin i;
	private Fusion api;
	
	@Override
	public void onEnable()
	{
		i = this;
		api = new Fusion(new FusionRegistry("fusion", new FusionResourceHolder(new File(getDataFolder(), "resources"))));
	}
	
	@Override
	public void onDisable()
	{
		
	}
		
	public static void log(String s)
	{
		i.getLogger().info(s);
	}
	
	public Fusion getApi()
	{
		return api;
	}
}
