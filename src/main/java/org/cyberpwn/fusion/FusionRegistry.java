package org.cyberpwn.fusion;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.cyberpwn.fusion.registry.FusionRegistrar;
import org.cyberpwn.fusionapi.Registry;
import org.cyberpwn.fusionapi.registry.Fuse;
import org.cyberpwn.fusionapi.registry.Registrar;
import org.cyberpwn.fusionapi.resource.ResourceHolder;
import org.cyberpwn.fusionapi.type.FModel;
import org.cyberpwn.fusionapi.type.FResource;
import org.cyberpwn.fusionapi.type.FShader;
import org.cyberpwn.fusionapi.type.FSound;
import org.cyberpwn.fusionapi.type.FTexture;

public class FusionRegistry implements Registry
{
	private ResourceHolder holder;
	private String host;
	private Map<String, Registrar> registrars;
	
	public FusionRegistry(String host, ResourceHolder holder)
	{
		registrars = new HashMap<String, Registrar>();
		startRegistration();
		this.holder = holder;
	}
	
	@Override
	public void writeResources() throws IOException 
	{
		for(String i : holder.getTextureHorde().getIds())
		{
			FResource r = holder.getTextureHorde().getResource(i);
			FusionPlugin.log("WRITE: " + i + "::" + r.getResource());
			holder.getTextureHorde().writeToFile(i, new File(holder.getTextureHorde().getHordeLocation(), i));
		}
		
		for(String i : holder.getModelHorde().getIds())
		{
			FResource r = holder.getModelHorde().getResource(i);
			FusionPlugin.log("WRITE: " + i + "::" + r.getResource());
			holder.getModelHorde().writeToFile(i, new File(holder.getModelHorde().getHordeLocation(), i));
		}
		
		for(String i : holder.getShaderHorde().getIds())
		{
			FResource r = holder.getShaderHorde().getResource(i);
			FusionPlugin.log("WRITE: " + i + "::" + r.getResource());
			holder.getShaderHorde().writeToFile(i, new File(holder.getShaderHorde().getHordeLocation(), i));
		}
		
		for(String i : holder.getSoundHorde().getIds())
		{
			FResource r = holder.getSoundHorde().getResource(i);
			FusionPlugin.log("WRITE: " + i + "::" + r.getResource());
			holder.getSoundHorde().writeToFile(i, new File(holder.getSoundHorde().getHordeLocation(), i));
		}
	}
	
	@Override
	public void startRegistration()
	{
		FusionPlugin.log("Load Fused Plugins");
		
		for(Plugin i : Bukkit.getPluginManager().getPlugins())
		{
			if(i instanceof Fuse)
			{
				FusionPlugin.log("Load Fuse " + i.getName());
				Fuse fuse = (Fuse) i;
				FusionPlugin.log("Found Fusion Source: " + fuse.getSource());
				startRegistration(fuse, fuse.getSource());
				FusionPlugin.log("Registration complete: " + fuse.getSource());
				FusionPlugin.log("Begin Resource horder for " + fuse.getSource());
				Registrar r = getRegistrar(fuse.getSource());
				
				for(String j : r.getTextureRegistrant().getIds())
				{
					FTexture res = r.getTextureRegistrant().get(j);
					FusionPlugin.log("REGISTER " + " (" + res.getRegistryType() + ")" + ": " + fuse.getSource() + ":" + j + "@" + res.getResource());
					holder.getTextureHorde().addResource(j, res);
				}
				
				for(String j : r.getSoundRegistrant().getIds())
				{
					FSound res = r.getSoundRegistrant().get(j);
					FusionPlugin.log("REGISTER " + " (" + res.getRegistryType() + ")" + ": " + fuse.getSource() + ":" + j + "@" + res.getResource());
					holder.getSoundHorde().addResource(j, res);
				}
				
				for(String j : r.getModelRegistrant().getIds())
				{
					FModel res = r.getModelRegistrant().get(j);
					FusionPlugin.log("REGISTER " + " (" + res.getRegistryType() + ")" + ": " + fuse.getSource() + ":" + j + "@" + res.getResource());
					holder.getModelHorde().addResource(j, res);
				}
				
				for(String j : r.getShaderRegistrant().getIds())
				{
					FShader res = r.getShaderRegistrant().get(j);
					FusionPlugin.log("REGISTER " + " (" + res.getRegistryType() + ")" + ": " + fuse.getSource() + ":" + j + "@" + res.getResource());
					holder.getShaderHorde().addResource(j, res);
				}
				
				FusionPlugin.log("Resource Hording complete for " + fuse.getSource());
			}
		}
	}
	
	@Override
	public void startRegistration(Fuse i, String source)
	{
		FusionPlugin.log("Create Registrars: " + source);
		FusionPlugin.log("Begin Registration: " + source);
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

	@Override
	public ResourceHolder getResourceHolder()
	{
		return holder;
	}
}
