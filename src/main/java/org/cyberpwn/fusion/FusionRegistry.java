package org.cyberpwn.fusion;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.cyberpwn.fusion.registry.FusionRegistrar;
import org.cyberpwn.fusionapi.Registry;
import org.cyberpwn.fusionapi.registry.Fuse;
import org.cyberpwn.fusionapi.registry.Registrar;

public class FusionRegistry implements Registry
{
	private String host;
	private Map<String, Registrar> registrars;
	
	public FusionRegistry(String host)
	{
		registrars = new HashMap<String, Registrar>();
		startRegistration();
	}
	
	@Override
	public void startRegistration()
	{
		for(Plugin i : Bukkit.getPluginManager().getPlugins())
		{
			if(i instanceof Fuse)
			{
				Fuse fuse = (Fuse) i;
				startRegistration(fuse, fuse.getSource());
			}
		}
	}
	
	@Override
	public void startRegistration(Fuse i, String source)
	{
		i.onRegister(registrars.put(source, new FusionRegistrar(source)));
	}
	
	@Override
	public Registrar getRegistrar(String source)
	{
		return registrars.get(source);
	}
	
	@Override
	public String getHost()
	{
		return host;
	}
}
