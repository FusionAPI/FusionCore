package org.cyberpwn.fusion;

import org.bukkit.plugin.java.JavaPlugin;
import org.cyberpwn.fusionapi.Fusion;

public class FusionPlugin extends JavaPlugin
{
	private Fusion api;
	
	@Override
	public void onEnable()
	{
		api = new Fusion(new FusionRegistry("fusion"));
	}
	
	@Override
	public void onDisable()
	{
		
	}
	
	public Fusion getApi()
	{
		return api;
	}
}
